package com.example.controller;


import cn.hutool.core.util.StrUtil;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.R;
import com.example.client.MallAddressClient;
import com.example.client.MallCartClient;
import com.example.client.MallProductClient;
import com.example.config.AlipayConfig;
import com.example.dto.OrderDto;
import com.example.entity.MallOrder;
import com.example.entity.MallOrderDetail;
import com.example.service.MallOrderDetailService;
import com.example.service.MallOrderService;
import com.example.session.AuthUser;
import com.example.vo.MallAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * <p>
 * 商城订单 前端控制器
 * </p>
 *
 * @author liang
 * @since 2021-12-02
 */
@RestController
@RequestMapping("/api/mall-order")
public class MallOrderController {
    @Autowired
    private MallAddressClient addressClient;
    @Autowired
    private MallOrderService orderService;
    @Autowired
    private MallOrderDetailService orderDetailService;
    @Autowired
    private MallProductClient productClient;
    @Autowired
    private MallCartClient cartClient;

    @PostMapping
    public R createOrder(@RequestBody OrderDto dto, HttpSession session) {

        AuthUser user = (AuthUser) session.getAttribute("user");
        if (user == null) {
            throw new RuntimeException("请先登录");
        }

        //2:校验 产品,库存,价格,总金额
        //不校验了


        //3:读取收货人的信息
        MallAddress address = addressClient.getMallAddressByid(dto.getAddressId());

        //4:添加一条订单记录,订单状态设置为0
        MallOrder order = new MallOrder();
        order.setOrderStatus(0);
        order.setUserId(user.getUserId());

        order.setOrderAddressName(address.getAddressName());
        order.setOrderAddressInfo(address.getAddressInfo());
        order.setOrderAddressTel(address.getAddressTel());

        order.setOrderTime(new Date());
        order.setOrderMoney(dto.getOrderMoney());
        order.setOrderMemo(dto.getOrderMemo());
        order.setOrderPayTypeId(1);//支付宝 1
        orderService.save(order);

        //5:添加订单详情
        for (MallOrderDetail od : dto.getList()) {
            od.setOrderId(order.getOrderId());
            orderDetailService.save(od);
        }
        //6:调用支付宝的API

        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = order.getOrderId().toString();
        //付款金额，必填
        String total_amount = order.getOrderMoney().toString();
        //订单名称，必填
        String subject = "[天堂商城]";
        //商品描述，可空
        String body = "";

        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"body\":\"" + body + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
        //alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
        //		+ "\"total_amount\":\""+ total_amount +"\","
        //		+ "\"subject\":\""+ subject +"\","
        //		+ "\"body\":\""+ body +"\","
        //		+ "\"timeout_express\":\"10m\","
        //		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节

        //请求
        try {
            return R.ok().setData(alipayClient.pageExecute(alipayRequest).getBody());
        } catch (AlipayApiException e) {
            throw new RuntimeException("调用阿里支付失败", e);
        }

    }

    @RequestMapping("return")
    public ModelAndView doReturn(HttpServletRequest request) {

        //获取支付宝GET过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
//      valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = false; //调用SDK验证签名
        try {
            signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);
        } catch (AlipayApiException e) {
            return new ModelAndView("redirect:http://localhost:8080/#/pay-fail.html?message=调用支付宝失败");
        }

        //——请在这里编写您的程序（以下代码仅作参考）——
        if (signVerified) {
            //商户订单号
            String out_trade_no = request.getParameter("out_trade_no");

            //支付宝交易号
            String trade_no = request.getParameter("trade_no");

            //付款金额
            String total_amount = request.getParameter("total_amount");

            Long orderId = Long.valueOf(out_trade_no);

            //12:修改支付完成后的订单
            MallOrder order = orderService.getById(orderId);//获取原订单
            order.setOrderStatus(1);
            order.setOrderPayTime(new Date());
            order.setOrderPayId(trade_no);
            orderService.updateById(order);

            //13: 修改商品的销售数量和库存 删除购物车中的商品
            QueryWrapper<MallOrderDetail> q = new QueryWrapper<>();//查询订单下的具体商品和数量
            q.eq("order_id", orderId);
            List<MallOrderDetail> list = orderDetailService.list(q);
            for (MallOrderDetail od : list) {
                //修改商品的销售数量+和库存-
                // update mal_product set product_num_sale=product_num_sale+{num},product_num=product_num-{num} where product_id={productId}
                productClient.updateNum(od.getProductId(), od.getOrderDetailNum());
                //删除购物车中的商品
                cartClient.QueryRemove(order.getUserId(),od.getProductId());
            }


        } else {
            return new ModelAndView("redirect:http://localhost:8080/#/pay-fail.html?message=校验失败");
        }
        return new ModelAndView("redirect:http://localhost:8080/#/pay-success.html");
    }

    @GetMapping("page")
    public Page<MallOrder> page(Page<MallOrder> p, String key,
                                @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date beginTime,
                                @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date endTime, Integer orderStatus, HttpSession session) {
        AuthUser user = (AuthUser) session.getAttribute("user");
        if (user == null) {
            throw new RuntimeException("请先登录");
        }
        return page(p, key, beginTime, endTime, user.getUserId(), orderStatus);
    }


    @GetMapping("admin-page")
    public Page<MallOrder> page(Page<MallOrder> p, String key,
                                @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date beginTime,
                                @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date endTime, Long userId, Integer orderStatus) {
        QueryWrapper<MallOrder> q = new QueryWrapper<>();//条件构造器

        if (StrUtil.isNotBlank(key)) {
            q.and((q1) -> {
                q1.like("order_id", key)
                        .or()
                        .like("order_address_name", key)
                        .or()
                        .like("order_address_tel", key)
                        .or()
                        .like("order_address_info", key);
            });
        }

        if (beginTime != null) {
            q.ge("order_time", beginTime);
        }

        if (endTime != null) {
            q.lt("order_time", endTime);
        }

        if (userId != null) {
            q.eq("user_id", userId);
        }
        if (orderStatus != null && 99 != orderStatus) {
            q.eq("order_status", orderStatus);
        }

        q.orderByDesc("order_time");
        Page<MallOrder> ret = orderService.page(p, q);
        for (MallOrder order : ret.getRecords()) {
            order.setList(orderService.listOrderDetail(order.getOrderId()));
        }
        return ret;
    }

    @PutMapping("updatestatus/{id}")
    public void updatestatus(@PathVariable("id") Long orderId) {
        MallOrder o = new MallOrder();
        o.setOrderId(orderId);
        o.setOrderStatus(2);
        orderService.updateById(o);
    }

    @PutMapping("updatestatu/{id}")
    public void updatestatu(@PathVariable("id") Long orderId) {
        MallOrder o = new MallOrder();
        o.setOrderId(orderId);
        o.setOrderStatus(3);
        orderService.updateById(o);
    }
}