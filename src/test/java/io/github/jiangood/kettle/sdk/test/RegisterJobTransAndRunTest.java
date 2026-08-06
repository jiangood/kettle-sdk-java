package io.github.jiangood.kettle.sdk.test;

import io.github.mxvc.jackson.JsonTool;
import io.github.jiangood.kettle.sdk.AbstructTest;
import io.github.jiangood.kettle.sdk.KettleSdk;
import io.github.jiangood.kettle.sdk.Result;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class RegisterJobTransAndRunTest extends AbstructTest {

    public static void main(String[] args) throws IOException {
        KettleSdk sdk = new KettleSdk(url,repo,  username, password);
        String dir = "D:\\dev\\kettle\\test-repo";

        String job1 = FileUtils.readFileToString(new File(dir, "job1.kjb"));
        String trans1 = FileUtils.readFileToString(new File(dir, "trans1.ktr"));

        Result job_result = sdk.registerJob(job1, null);
        System.out.println(JsonTool.toPrettyJsonQuietly(job_result));

      //  Result trans_result = sdk.registerTrans(trans1, null);
      //  System.out.println(JsonTool.toPrettyJsonQuietly(trans_result));


        Result startResult = sdk.startJob(job_result.getId(), "job1");
        System.out.println(JsonTool.toPrettyJsonQuietly(startResult));



    }
}
