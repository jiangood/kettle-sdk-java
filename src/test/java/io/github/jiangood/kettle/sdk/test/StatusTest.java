package io.github.jiangood.kettle.sdk.test;

import io.github.mxvc.jackson.JsonTool;
import io.github.jiangood.kettle.sdk.AbstructTest;
import io.github.jiangood.kettle.sdk.KettleSdk;
import io.github.jiangood.kettle.sdk.response.SlaveServerStatus;

public class StatusTest extends AbstructTest {

    public static void main(String[] args) {
        KettleSdk sdk = new KettleSdk(url,repo, username, password);

        SlaveServerStatus status = sdk.status();
        System.out.println(JsonTool.toPrettyJsonQuietly(status));
    }
}
