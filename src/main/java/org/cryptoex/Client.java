package org.cryptoex;

import org.cryptoex.client.RestClient;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.cryptoex.enums.UserType;
import org.cryptoex.store.model.*;

public class Client {
    public static void main(String[] args) {
        RestClient.Builder builder = new RestClient.Builder("http://localhost:8088");
        //builder.authenticate("ManageInternalKey", "ManageInternalSecret");
        builder.authenticate("UIInternalKey", "UIInternalSecret");
        RestClient client = builder.build();

        RestClient.Builder builder_manager = new RestClient.Builder("http://localhost:8088");
        builder_manager.authenticate("ManageInternalKey", "ManageInternalSecret");
        RestClient manage = builder_manager.build();
/*
        Map<String,String> query = new HashMap<>();
        query.put("currency", "ETH");
        System.out.println(client.get(Map.class, "/ui/users/100003/deposit/ETH/address", null));
        System.out.println("232");
        */
/*

        User user = new User();
        user.canSignin = true;
        user.canWithdraw = true;
        user.type = UserType.TRADER;
        System.out.println(client.post(Map.class,"/ui/users", user).toString());
*/
/*
        WithdrawAddress address = new WithdrawAddress();
        address.address = "0xA7d4fC42b48B4e89b1428E5F4357e4f8a0246eb4";
        address.currency = "ETH";
        address.userId = 10001;
        address.description = "test";
        System.out.println(client.post(Map.class,"/ui/users/10001/withdraw/addresses", address).toString());
*/
/*
        WithdrawRule rule = new WithdrawRule();
        rule.feeRate = BigDecimal.valueOf(0);
        rule.maximumFee = BigDecimal.valueOf(0.1);
        rule.minimumAmount = BigDecimal.valueOf(0.01);
        rule.minimumFee = BigDecimal.valueOf(0.003);
        rule.withdrawDisabled = false;
        System.out.println(manage.post(Map.class,"/manage/ETH/withdraw/rules", rule).toString());
*/

        UIWithdrawRequestBean address = new UIWithdrawRequestBean();
        address.amount = BigDecimal.valueOf(0.02);
        address.currency = "ETH";
        address.withdrawAddressId = 100000;
        System.out.println(client.post(Map.class,"/ui/users/10001/withdraw/requests", address).toString());



        WithdrawCheckBean bean = new WithdrawCheckBean();
        bean.errorCode = "";
        bean.errorMessage = "";
        bean.needReview = false;
        bean.success = true;
        System.out.println(manage.post(Map.class,"/manage/withdraws/100004/check", bean).toString());

        Map<String,String> query = new HashMap<>();
        query.put("currency", "ETH");
        //System.out.println(client.get(Map.class, "/ui/users/10001/deposit/ETH/address", null));
        System.out.println(client.get(Map.class, "/ui/users/10001/withdraw/requests", null));
        System.out.println("232");

    }
}
