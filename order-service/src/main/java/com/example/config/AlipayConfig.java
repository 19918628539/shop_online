package com.example.config;/*
 *ClassName:
 *UserName:86189
 *Time:2021/12/2/18:17
 */

public class AlipayConfig {
    public static String app_id = "9021000128690174";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCWhHeAKB+/Tt/a7ZaySFI/QRLLzE9XqVjn/2yJsu7NGyi4BkCkh50F6uyUpgJkqZzurt6S+yqg7TOT8TXMsXnL1AXj0UfZ48y3UEk9KCa2NVZcCIJD4jT8p3ezth0f0x13WIhIYdSOG8LDSwligwWcPHTEHoi4F3XOeFWK+OWZwa8yh1opYbSiGOPMMVkmmh4sF0IXEEtrS8SXes+C8v7mHigDzpZalBHE9jUkrFU3Cg6+2lwVT1fvkp4tm8SePRZp5oMJL4bsYKodGX4OsUg+BISCQWbUR3DhH0moUqWb0rmnmOOWfihF1MYLIOvZE3Qhv5/wCwnDxzPKf/PHem2bAgMBAAECggEAO0qDKGCl4N5ZIAxnSGNkwrYHiXA58PYsp3becn+hwfno2FTeMfGZ0TLxbrzWRIDr6LnJw2xdJndJ0gU6hB+FMt2aVFukShiKE8v0jbq7RRtNoYNYjRUWelAdvxUF8/tdqB2f0aN372LQHc35LG0gUKMf/AFrKdlT9rlAOTZCe4ZMwT8pomkvMeJmMKjMzSueMAnKhIa0JFosdWL76mKE40FQFlKKr9/YFwbQ7OxR3N3ZrAuDEuVSHHsHDFG1kSdvkrG2Bly7rvxqUvkB4sZBfzeVAF5K7aA/JlQgViWh9PKJfTA7c7iFtGUNCq8wYH8WQ2opG3ErysjDvcogc/xcwQKBgQDbAI/NaKtzxsquBXdSDaGKzSvGK1C4GukG0hE6dJG289vMLsi4CCb7Pl/c2e1zWDpmwLAyQTTxNEDMr4QzJ542ZoRjA7GKsHLZzNHd6VaaxaX99YBMXfl0eYpX5+486vUB6HpGGLr6xZzY5esbPDhdGSLeU982AZJlD7lOVH3ajQKBgQCv8hElIaBDSRVUScp5buHGQ8n7LxaD5MAvTDNi8f7CdHVqHYFMAyZTTeBwsFDxK146Vs4RulEW/I7J4h7ZEg8e5rtxuzYXaFpnhhy9JLfZSSgkaxXgCTGJyTSSIrP5/8Fph33ijI7OLQ14X448+0O9SS7q9w/AcSoczideJk4yxwKBgEUWuUcA5U3iAeDCYrElEbhbH5sl4RIjky4OqVa6nZmM9uNEQxgjokCSneVrbsh7pfjyguNnxM7vQB4SpO8KaZQZ5VvjN2hcX+8r+W07hpHPFp22jGILJ35CNY2j4nmfWenfMGvas2oXW3LlnbT624ErQ9/Fr942wrtVKY13hDilAoGAc+L+w/Q3+NmEQPYxdHZCIzK3MMi9DbZ3ly+bkQpPgIjwZSp15TkV3zymHdMkHRUxhT9zj12m13sWGYbkRpmMeBAFWk2yvQwwr4s4FqS+q1FyLTqTN/MWO+jYxYAyBvIb1IQ3uCZYURh/jM9zglsFLQGs83ozV8+xqzNl3q9gRjUCgYEAp0ZxYCtEgJ/XMU7Buwk5klCTiEsdlEW1T6pzR0FtbPSnFyZIRPz8K07G6eSUjR3ogkpwsGSasRvm2zM67e69BQuzFNXtK64k07mRVoMHTs3b94BOfmOfpYFMV6RbjtkK9yU99J7U6OC5z1IeKPNO9ymuIMSnZxVHBlgUBlzG0bQ=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key ="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhfLJc9vasb3Yf0Bd8KezQAEXx88T6YnkNWB0F3R4F5RWlSoWK+6uzbgJejXrMzeAU9ksWyQdhA8rBbZ5jNn9Db5T2IXud51Y+L53obFeqHwFMxA2mryvu9EvaxsdgE7f0rfIqcDrxXEbTvuMQbkGxQ8WjtD1lC1r7ld0Fknst5S4SXfrrJlzH8a4WjQ9P9oP/1DqtST6YNGH15biCD3ekOTQGJqSGhx8ek450K94bZyrxPSd5BhqwZP6xvh75INovuuBvnAa76qHfSqiehH7AqQ0R4KF2OTAxizNtsiWF/NEr7HyPaHXboXy6Lk+bGr2xTkmS2pYNHfd7JCCTjJBNwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:8080/api/mall-order/return";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关https://openapi.alipay.com/gateway.do
    public static String gatewayUrl = "https://openapi-sandbox.dl.alipaydev.com/gateway.do";
}
