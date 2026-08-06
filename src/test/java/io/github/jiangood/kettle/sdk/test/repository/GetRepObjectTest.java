package io.github.jiangood.kettle.sdk.test.repository;

import io.github.jiangood.kettle.sdk.AbstructTest;
import io.github.jiangood.kettle.sdk.KettleSdk;

import java.io.IOException;

public class GetRepObjectTest extends AbstructTest {

    public static void main(String[] args) throws IOException {
        KettleSdk sdk = new KettleSdk(url,repo, username, password);

        String repObject = sdk.getRepositoryObjectContent("/运营/J_ODS_一卡通_交易信息.kjb");
        System.out.println(repObject);
    }
}
