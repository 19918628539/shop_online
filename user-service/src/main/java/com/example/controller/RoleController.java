package com.example.controller;



import com.example.R;
import com.example.pojo.Role;
import com.example.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 系统角色 前端控制器
 * </p>
 *
 * @author liang
 * @since 2021-11-16
 */
@Slf4j
@RestController
@RequestMapping("/api/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    //查询多个类别
    @GetMapping("list")
    public List<Role> list()  {
        return roleService.list();
    }

    @GetMapping("{roleId}")//
    public Object getrole(
            @PathVariable Long  roleId
    ){
        return roleService.getById(roleId);
    }


    //json格式添加
    @PostMapping("add")
    public void addJson(@RequestBody Role role){
        try {
            roleService.save(role);
        }catch (Exception e) {
            throw new RuntimeException("添加失败",e);

        }
    }

    @PutMapping("update")
    public void update(@RequestBody Role role){
        try {
            roleService.updateById(role);

        }catch (Exception e) {
            throw new RuntimeException("修改失败",e);

        }
    }

    //删除
    @DeleteMapping("delete/{roleId}")
    public boolean delete(@PathVariable("roleId") Long roleId){
        try {
            roleService.removeById(roleId);
            R.ok().setMessage("删除成功");
            return true;
        }catch (Exception e){
            log.debug("删除失败",e);
            return false;
        }

    }
}

