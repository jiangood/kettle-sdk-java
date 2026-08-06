package io.github.jiangood.kettle.sdk.test.repository;

import io.github.jiangood.kettle.sdk.AbstructTest;
import io.github.jiangood.kettle.sdk.KettleSdk;

import java.io.IOException;

public class DeleteRepObjectTest extends AbstructTest {

    public static void main(String[] args) throws IOException {
        KettleSdk sdk = new KettleSdk(url, repo, username, password);

        sdk.deleteRepositoryObject("/运营/");
    }
}
