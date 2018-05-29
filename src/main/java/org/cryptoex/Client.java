package org.cryptoex;

import org.cryptoex.client.RestClient;

import java.util.HashMap;
import java.util.Map;

public class Client {
    public static void main(String[] args) {
        RestClient.Builder builder = new RestClient.Builder("http://localhost:8088");
        //builder.authenticate("ManageInternalKey", "ManageInternalSecret");
        builder.authenticate("UIInternalKey", "UIInternalSecret");
        RestClient client = builder.build();

        /* post
        User user = new User();
        user.canSignin = true;
        user.canWithdraw = true;
        System.out.println(client.post(Map.class,"/ui/users", user).toString());

*/

        Map<String,String> query = new HashMap<>();
        query.put("currency", "ETH");
        System.out.println(client.get(Map.class, "/ui/users/100/deposit/ETH/address", null));
        System.out.println("232");

    }
}
