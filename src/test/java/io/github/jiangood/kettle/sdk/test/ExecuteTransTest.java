package io.github.jiangood.kettle.sdk.test;

import io.github.mxvc.jackson.JsonTool;
import io.github.jiangood.kettle.sdk.AbstructTest;
import io.github.jiangood.kettle.sdk.KettleSdk;
import io.github.jiangood.kettle.sdk.LogLevel;
import io.github.jiangood.kettle.sdk.Result;

public class ExecuteTransTest extends AbstructTest {

    public static void main(String[] args) {
        KettleSdk sdk = new KettleSdk(url,repo,  username, password);

        Result result = sdk.executeTrans("t1", LogLevel.DEBUG,null);
        System.out.println(JsonTool.toPrettyJsonQuietly(result));
    }
}
