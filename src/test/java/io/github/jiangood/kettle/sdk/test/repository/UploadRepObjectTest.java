package io.github.jiangood.kettle.sdk.test.repository;

import io.github.jiangood.kettle.sdk.AbstructTest;
import io.github.jiangood.kettle.sdk.KettleSdk;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class UploadRepObjectTest extends AbstructTest {

    public static void main(String[] args) throws IOException {
        KettleSdk sdk = new KettleSdk(url, repo, username, password);

      //  sdk.uploadRepObject( FileUtils.readFileToString(new File("D:\\job33.kjb")));
        sdk.uploadRepositoryObject( FileUtils.readFileToString(new File("D:\\repository\\运营\\J_ODS_一卡通_交易信息.kjb")));
       // sdk.uploadRepObject( FileUtils.readFileToString(new File("D:\\repository\\运营\\ODS_一卡通川投_交易订单.ktr")));


    }
}
