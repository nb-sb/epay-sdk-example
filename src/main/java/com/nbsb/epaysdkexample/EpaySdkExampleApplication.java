package com.nbsb.epaysdkexample;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConfig;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.*;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.alipay.easysdk.kernel.Config;

@SpringBootApplication
public class EpaySdkExampleApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(EpaySdkExampleApplication.class, args);
        main3();
    }

    public static void main2() throws Exception {
//        // 1. 设置参数（全局只需设置一次）
//        Factory.setOptions(getOptions());
//        try {
//            // 2. 发起API调用（以创建当面付收款二维码为例）
//            AlipayTradePagePayResponse response = Payment.Page().pay("Apple iPhone11 128G", "N2024128371908237980123", "0.01", "https://nb.sb/error");
////            AlipayTradePrecreateResponse response = Payment.FaceToFace()
////                    .preCreate("Apple iPhone11 128G", "N2024128371908237980123", "0.01");
//            // 3. 处理响应或异常
////            AlipayTradeCreateResponse n2024128371908237980123 = Payment.Common().create("Apple iPhone11 128G", "N2024128371908237980123", "0.01", "123456");
//            AlipayTradeQueryResponse n2024128371908237980123 = Payment.Common().query("N2024128371908237980123");
//            System.out.println(JSON.toJSONString(n2024128371908237980123));
//
//            if (ResponseChecker.success(response)) {
//                System.out.println("调用成功");
//                System.out.println(response.getBody());
////                System.out.println(JSON.toJSONString(response));
//            } else {
////                System.err.println("调用失败，原因：" + response.msg + "，" + response.subMsg);
//            }
//        } catch (Exception e) {
//            System.err.println("调用遭遇异常，原因：" + e.getMessage());
//            throw new RuntimeException(e.getMessage(), e);
//        }
    }
    public static void main3() throws AlipayApiException {
        // 初始化SDK
        AlipayClient alipayClient = new DefaultAlipayClient(getAlipayConfig());
        // 构造请求参数以调用接口
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
        AlipayTradePrecreateModel model = new AlipayTradePrecreateModel();
        // 设置商户订单号
        model.setOutTradeNo("202403200101010011231231223");
        // 设置订单总金额
        model.setTotalAmount("0.1");
        // 设置订单标题
        model.setSubject("Iphone6 16G");
        request.setBizModel(model);
        // 第三方代调用模式下请设置app_auth_token
        AlipayTradePrecreateResponse response = alipayClient.execute(request);
        System.out.println(response.getBody());

        if (response.isSuccess()) {
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
            // sdk版本是"4.38.0.ALL"及以上,可以参考下面的示例获取诊断链接
            // String diagnosisUrl = DiagnosisUtils.getDiagnosisUrl(response);
            // System.out.println(diagnosisUrl);
        }
    }
    private static Config getOptions() {
        Config config = new Config();
        config.protocol = "https";
        config.gatewayHost = "openapi-sandbox.dl.alipaydev.com";
        config.signType = "RSA2";

        config.appId = "9021000133669471";

        // 为避免私钥随源码泄露，推荐从文件中读取私钥字符串而不是写入源码中
        config.merchantPrivateKey = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCAmoV4f72R3rQo83RatFqBdeOXPQ0Fo+LA+swts/QWcF/83vOv54/KHB7jWAmVsD/0kV4z7yxKzoI4nlNgDzNZ99UzQhfbvZ9YRgi5LqcpDbo9a3gEciV19BtxLlwLisBNaxW2adW8kNT/xrTY/fvXo35StlAmH/hc/08k25q8W5CeO+odDnxxaSMyz2nmH0HPiimV8rSoVsWx8PRlyrg94lcUdZoOlT04R/5zzleqrpR+k/ayK01XPd3JmAnkYEE8A8IFJSaiJkdpiVs0CzddnLPn59lcxyiAnOW1WYjYcvjKFkTf/OwS+JrT1HZuPwuWsdYvqdSdzGQEA0wgmsl3AgMBAAECggEAQBE61voEbcwdwpzuZVWeFQqu9CRE/X9UssD+wPH6csdO+oHzsrlQh+aUeLveIoVohyV4YbUQQsA4XY56iMtp0LNHsj6F9kG0RVy2cdLNEgW1t213n/dhtC6FEVCAKHvaK3dinLqC8WrRwg6y/7sgIKBhiUEsEqIwr9u9COaiYp0hL9jSMEofdOV2BGFAv2hoF5c0rE85oEJNmoVMsDmV8uXb1jmi/xp+ORwfh08gBYOr8DaQ3fCPxxxlhFiivjktmpIb0BWEnV9u4avH0kzqh+3Ml5Btj0n+fLkdEtvJ9N/zC8C6tTkV/npaV2QEE4/yWSMLKSakwouzUshVIBZDoQKBgQDLLiUZELyppuEMRFF1xnngPxTq8j/C2+BnnljIfOfgOS6kaUd5ygfQ9A1/PzOz66XTpA0BJIJnb2Skv2hp+ZPMTdpZl+2amJf43+0cf2NWckwoKgRCVcYW8cZn51efVW2iqbfO2wKgMnKgbKSP+xsUHGtjqNkr4RgVnDGzCRfLcQKBgQCiCTkq6aT2l8cRp+fKQ2hRy83SdhjAk7vE34PysKQBQBFGMiqsnY0vZG29kbfmSZ405KNZydX8Y2e+SJNSbQmyNaJuLCxqSmZ/BHklFq2Ig29PTPiSSSl3kDyXIZvpDUQMlpjTW9l47UckWLG2MyBoWQrEcpY+m6ip19xzXZlfZwKBgF+H3M2CPxKNcjbwUFTN8zPqtQ0axbv64fIWU6xAfk3A1RtcnMy4nyAx/76amta3r4ppEew9nErB51JG5PMe880Vtdp2QrNzl9DeiH/jrXNR3ljsGICQD8lt+FmWPm6eHiRjJBdQhlWIIrknG+DOshasnhmHpESrW0Wd4QhK+DMhAoGAdJAgAN/NHNt4iBn6Y4V0QQtUh+cOzVI5TFniSK6LfJ7DDnU+P6ijDQm47BssaMxis/gaDphkiEhWNAtvd2cbB4kPzSOX2+qjqxHjuCuFMGQkqa5cTpe07d7jx3CVAOTbJBcd30X+vsAqYlovlTtYP9h2J3Wq4WUGww+W9aLKl1cCgYALllNQB8CXMwKDHT2SQV0s3jI8rdhKXKqYrL/p6o/o/zKhVQXKmNcvx+rOHQBV62x357LypRwYQPo9Yx0VfsGyjFknGILOspLNRv1jnmckjzGFSdoVQc2vxHG5erG7Fq8eQL+ULzMweEia9yxJawD+fjFxKyixuDcaBNxJNzspLA==";
        config.alipayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnvYeRzVpwTh2ICZSHodm0N6Z6pu6dX87e9Z1JTykm7zAAP3ewnuRI8wjdKc+i34PVtuUkK48iFFgqucqT70b9vty5aO9rtLjafD4i6bXTMrwwWAKKt4O2BENNo3PeEsByHxaAw/zR1Q9gimGJumH5iGg/ybJ761A3K8k7IDACbJUH8/Tx3PrC6oAr8IZxCOeXwH51moKEXceK7aW7N3FDwcJTGpANeQlxb7LlE9XOCZ+wZJk3S/T7WChGqApvMj0Ko94ZKJh/ozFm2GKUKIjxefhU7yH7TtbOOxX6j/0UB236/km6wBMjIFcagAS0GJfZPu1gfxyGCVHVKNso/EUjwIDAQAB";

        //可设置异步通知接收服务地址（可选）
        config.notifyUrl = "http://hbkjcm.natappfree.cc/pay/notify/";

        return config;
    }
    private static AlipayConfig getAlipayConfig() {
        String privateKey = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCAmoV4f72R3rQo83RatFqBdeOXPQ0Fo+LA+swts/QWcF/83vOv54/KHB7jWAmVsD/0kV4z7yxKzoI4nlNgDzNZ99UzQhfbvZ9YRgi5LqcpDbo9a3gEciV19BtxLlwLisBNaxW2adW8kNT/xrTY/fvXo35StlAmH/hc/08k25q8W5CeO+odDnxxaSMyz2nmH0HPiimV8rSoVsWx8PRlyrg94lcUdZoOlT04R/5zzleqrpR+k/ayK01XPd3JmAnkYEE8A8IFJSaiJkdpiVs0CzddnLPn59lcxyiAnOW1WYjYcvjKFkTf/OwS+JrT1HZuPwuWsdYvqdSdzGQEA0wgmsl3AgMBAAECggEAQBE61voEbcwdwpzuZVWeFQqu9CRE/X9UssD+wPH6csdO+oHzsrlQh+aUeLveIoVohyV4YbUQQsA4XY56iMtp0LNHsj6F9kG0RVy2cdLNEgW1t213n/dhtC6FEVCAKHvaK3dinLqC8WrRwg6y/7sgIKBhiUEsEqIwr9u9COaiYp0hL9jSMEofdOV2BGFAv2hoF5c0rE85oEJNmoVMsDmV8uXb1jmi/xp+ORwfh08gBYOr8DaQ3fCPxxxlhFiivjktmpIb0BWEnV9u4avH0kzqh+3Ml5Btj0n+fLkdEtvJ9N/zC8C6tTkV/npaV2QEE4/yWSMLKSakwouzUshVIBZDoQKBgQDLLiUZELyppuEMRFF1xnngPxTq8j/C2+BnnljIfOfgOS6kaUd5ygfQ9A1/PzOz66XTpA0BJIJnb2Skv2hp+ZPMTdpZl+2amJf43+0cf2NWckwoKgRCVcYW8cZn51efVW2iqbfO2wKgMnKgbKSP+xsUHGtjqNkr4RgVnDGzCRfLcQKBgQCiCTkq6aT2l8cRp+fKQ2hRy83SdhjAk7vE34PysKQBQBFGMiqsnY0vZG29kbfmSZ405KNZydX8Y2e+SJNSbQmyNaJuLCxqSmZ/BHklFq2Ig29PTPiSSSl3kDyXIZvpDUQMlpjTW9l47UckWLG2MyBoWQrEcpY+m6ip19xzXZlfZwKBgF+H3M2CPxKNcjbwUFTN8zPqtQ0axbv64fIWU6xAfk3A1RtcnMy4nyAx/76amta3r4ppEew9nErB51JG5PMe880Vtdp2QrNzl9DeiH/jrXNR3ljsGICQD8lt+FmWPm6eHiRjJBdQhlWIIrknG+DOshasnhmHpESrW0Wd4QhK+DMhAoGAdJAgAN/NHNt4iBn6Y4V0QQtUh+cOzVI5TFniSK6LfJ7DDnU+P6ijDQm47BssaMxis/gaDphkiEhWNAtvd2cbB4kPzSOX2+qjqxHjuCuFMGQkqa5cTpe07d7jx3CVAOTbJBcd30X+vsAqYlovlTtYP9h2J3Wq4WUGww+W9aLKl1cCgYALllNQB8CXMwKDHT2SQV0s3jI8rdhKXKqYrL/p6o/o/zKhVQXKmNcvx+rOHQBV62x357LypRwYQPo9Yx0VfsGyjFknGILOspLNRv1jnmckjzGFSdoVQc2vxHG5erG7Fq8eQL+ULzMweEia9yxJawD+fjFxKyixuDcaBNxJNzspLA==";
        String alipayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnvYeRzVpwTh2ICZSHodm0N6Z6pu6dX87e9Z1JTykm7zAAP3ewnuRI8wjdKc+i34PVtuUkK48iFFgqucqT70b9vty5aO9rtLjafD4i6bXTMrwwWAKKt4O2BENNo3PeEsByHxaAw/zR1Q9gimGJumH5iGg/ybJ761A3K8k7IDACbJUH8/Tx3PrC6oAr8IZxCOeXwH51moKEXceK7aW7N3FDwcJTGpANeQlxb7LlE9XOCZ+wZJk3S/T7WChGqApvMj0Ko94ZKJh/ozFm2GKUKIjxefhU7yH7TtbOOxX6j/0UB236/km6wBMjIFcagAS0GJfZPu1gfxyGCVHVKNso/EUjwIDAQAB";
        AlipayConfig alipayConfig = new AlipayConfig();
        alipayConfig.setServerUrl("https://openapi-sandbox.dl.alipaydev.com/gateway.do");
        alipayConfig.setAppId("9021000133669471");
        alipayConfig.setPrivateKey(privateKey);
        alipayConfig.setFormat("json");
        alipayConfig.setAlipayPublicKey(alipayPublicKey);
        alipayConfig.setCharset("UTF-8");
        alipayConfig.setSignType("RSA2");
        return alipayConfig;
    }

}
