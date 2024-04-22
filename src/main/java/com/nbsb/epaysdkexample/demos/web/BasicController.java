/*
 * Copyright 2013-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nbsb.epaysdkexample.demos.web;

import com.alibaba.fastjson.JSON;
import com.nbsb.epaysdk.api.EPayFactory;
import com.nbsb.epaysdk.api.EPayInterface;
import com.nbsb.epaysdk.api.Impl.EPayMZF;
import com.nbsb.epaysdk.api.entity.reponse.MapiResponse;
import com.nbsb.epaysdk.api.entity.request.GetQRCmd;
import com.nbsb.epaysdk.epaybase.enumeration.PayType;
import com.nbsb.epaysdk.epaybase.enumeration.PaymentMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
* @author: Wanghaonan @戏人看戏
* @description: 实现回调接口，和获取支付信息接口和查询订单接口，更多的使用案例可以看test文件下的测试用例
* @create: 2024/4/21 18:42
*/
@RestController
public class BasicController  implements EPayInterface {
    private static final Logger logger = LoggerFactory.getLogger(BasicController.class);

    @GetMapping("/pay/notify/")
    @Override
    public String onPayResult(@RequestParam Map<String, String> params) {
        // 打印所有传入的GET参数
        params.forEach((key, value) -> logger.info("GET parameter - {}: {}", key, value));
        // 验证签名！必须验证！如果你不想让你自己被盗刷的话（别人直接请求你就保存支付成功状态了）验证方法在SignUtil类中有，可以直接使用 SignUtil.map2Md5()

        // 获取异步返回的内容
        // 进行修改订单状态并持久化存贮
        return null;
    }
    @GetMapping("/pay/get/")
    //这里以码支付获取参数为例
    public Object executePay() {
        EPayFactory ePayFactory = new EPayFactory();
        //获取码支付信息
        EPayMZF ePay = (EPayMZF) ePayFactory.create(PayType.MZF);
        //这里是模拟的数据，可以根据自己实际进行填写，更多案例可以看项目 epay-sdk-example测试用例中的内容
        GetQRCmd cmd = new GetQRCmd("测试商品名称","20214014211111173712331","0.1",
                PaymentMethod.ALIPAY,
                "https://baidu1.com/","https://baidu1.com/");
        MapiResponse mapi = ePay.mapi(cmd);
        System.out.println(JSON.toJSONString(mapi));
        return mapi;
    }
    @GetMapping("/pay/queryOrder/")
    //这里以码支付获取参数为例
    public Object queryOrder() {
        GetQRCmd cmd = new GetQRCmd("测试商品名称","20214014211111173712331","0.1",
                PaymentMethod.ALIPAY,
                "https://baidu1.com/","https://baidu1.com/");
        EPayMZF ePayMZF = new EPayMZF();
        MapiResponse mapi = ePayMZF.mapi(cmd);
        System.out.println(JSON.toJSONString(mapi));
        return mapi;
    }

}
