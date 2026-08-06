package io.github.jiangood.kettle.sdk.test;

import io.github.mxvc.jackson.JsonTool;
import io.github.jiangood.kettle.sdk.AbstructTest;
import io.github.jiangood.kettle.sdk.KettleSdk;
import io.github.jiangood.kettle.sdk.response.SlaveServerJobStatus;

import java.io.IOException;

public class JobStatusTest extends AbstructTest {

    public static void main(String[] args) throws IOException {
        KettleSdk sdk = new KettleSdk(url,repo, username, password);

        SlaveServerJobStatus status = sdk.jobStatus("84f9013d-d13a-439c-b54d-07ecd5e7eca9","job2");
        System.out.println(JsonTool.toPrettyJsonQuietly(status));
    }
}
