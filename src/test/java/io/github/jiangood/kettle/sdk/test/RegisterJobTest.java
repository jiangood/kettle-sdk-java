package io.github.jiangood.kettle.sdk.test;

import io.github.mxvc.jackson.JsonTool;
import io.github.jiangood.kettle.sdk.AbstructTest;
import io.github.jiangood.kettle.sdk.KettleSdk;
import io.github.jiangood.kettle.sdk.LogLevel;
import io.github.jiangood.kettle.sdk.Result;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class RegisterJobTest extends AbstructTest {

    public static void main(String[] args) throws IOException {
        KettleSdk sdk = new KettleSdk(url,repo,  username, password);
        String xml = FileUtils.readFileToString(new File("D:\\job33.kjb"));


        HashMap<String, String> params = new HashMap<>();
        params.put("filename","okkkkk");
        Result result = sdk.registerJob(xml, params);
        System.out.println(JsonTool.toPrettyJsonQuietly(result));

    }
}
