package io.github.jiangood.kettle.sdk;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.kevinsawicki.http.HttpRequest;
import io.github.mxvc.jackson.XmlTool;
import io.github.jiangood.kettle.sdk.plugin.RepTreeItem;
import io.github.jiangood.kettle.sdk.response.SlaveServerJobStatus;
import io.github.jiangood.kettle.sdk.response.SlaveServerStatus;
import io.github.jiangood.kettle.sdk.response.WebResult;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static com.github.kevinsawicki.http.HttpRequest.CHARSET_UTF8;

/**
 * reference
 * https://javadoc.pentaho.com/kettle930/kettle-engine-9.3.0.0-424-javadoc/org/pentaho/di/www/package-summary.html
 */
public class KettleSdk {

    private static final String KETTLE_PLUGIN_REPO_OBJ = "/kettle/plugin-repository-object/";
    private String baseUrl;
    private String username;
    private String password;

    private String rep;


    public KettleSdk(String url, String rep, String username, String password) {
        this.baseUrl = url;
        this.rep = rep;
        this.username = username;
        this.password = password;
    }


    /**
     * Get the status of the server
     */
    public SlaveServerStatus status() {
        String url = baseUrl + "/kettle/status?xml=Y";
        String body = HttpRequest.get(url).basic(username, password).body();
        System.out.println(body);

        try {
            SlaveServerStatus serverStatus = XmlTool.xmlToBean(body, SlaveServerStatus.class);
            // 排序
            List<SlaveServerJobStatus> jobStatusList = serverStatus.getJobStatusList();
            if (jobStatusList != null && !jobStatusList.isEmpty()) {
                jobStatusList.sort((o1, o2) -> o2.getLogDate().compareTo(o1.getLogDate()));
            }

            return serverStatus;

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * @param trans
     * @param level
     * @param otherParameters All the other parameters will be sent to the transformation for using as variables. When necessary you can add custom parameters to the request. They will be used to set the transformation variables values..
     * @return https://javadoc.pentaho.com/kettle930/kettle-engine-9.3.0.0-424-javadoc/org/pentaho/di/www/RunTransServlet.html
     */
    public Result executeTrans(String trans, LogLevel level, Map<String, Object> otherParameters) {
        String url = baseUrl + "/kettle/executeTrans/?rep=" + rep + "&trans=" + trans + "&level=" + level.getCode();
        return common_get(url, otherParameters);
    }


    /***
     *
     * @param job
     * @param level
     * @param otherParameters All the other parameters will be sent to the job for using as variables. When necessary you can add custom parameters to the request. They will be used to set the job variables values.
     * @return
     *
     * https://javadoc.pentaho.com/kettle930/kettle-engine-9.3.0.0-424-javadoc/org/pentaho/di/www/ExecuteJobServlet.html
     */
    public Result executeJob(String job, LogLevel level, Map<String, Object> otherParameters) {
        String url = baseUrl + "/kettle/executeJob/?rep=" + rep + "&job=" + job + "&level=" + level.getCode();
        return common_get(url, otherParameters);
    }

    public Result startJob(String id, String jobName) {
        String url = baseUrl + "/kettle/startJob/";

        HashMap<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("name", jobName);
        params.put("xml", "Y");

        return common_get(url, params);
    }

    public SlaveServerJobStatus jobStatus(String id, String jobName) {
        String url = baseUrl + "/kettle/jobStatus/";
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("name", jobName);
        params.put("xml", "Y");

        HttpRequest http = HttpRequest.get(url, params, true).basic(username, password);

        String body = http.body();
        System.out.println(body);

        try {
            SlaveServerJobStatus jobStatus = XmlTool.xmlToBean(body, SlaveServerJobStatus.class);

            String logStr = jobStatus.getLoggingString();
            if (logStr != null) {
                logStr = logStr.substring("<![CDATA[".length(), logStr.length() - "]]>".length());
                try {
                    logStr = HttpUtil.decodeBase64ZippedString(logStr);
                    jobStatus.setLoggingString(logStr);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }


            return jobStatus;

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * add job to carte server, (not the repo)
     *
     * @param jobXml the job file content, such as  xxx.job
     * @param params the job's params
     * @return result
     */
    public Result registerJob(String jobXml, Map<String, String> params) {
        String url = baseUrl + "/kettle/registerJob/?xml=Y";
        if (params == null) {
            params = Collections.emptyMap();
        }

        jobXml = jobXml.replaceFirst("\\<\\?xml.*>", "");


        StringBuilder xml = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");

        xml.append("<job_configuration>");
        {
            xml.append("<job_execution_configuration>");
            {
                xml.append("<exec_local>N</exec_local>");
                xml.append("<pass_export>N</pass_export>");

                xml.append("<parameters>");
                for (Map.Entry<String, String> e : params.entrySet()) {
                    xml.append("<parameter><name>").append(e.getKey())
                            .append("</name><value>").append(e.getValue())
                            .append("</value></parameter>");
                }
                xml.append("</parameters>");


                xml.append("<replay_date/>");
                xml.append("<safe_mode>N</safe_mode>");
                xml.append("<log_level>Basic</log_level>");
                xml.append("<clear_log>Y</clear_log>");
                xml.append("<start_copy_name/>");
                xml.append("<start_copy_nr>0</start_copy_nr>");
                xml.append("<gather_metrics>Y</gather_metrics>");
                xml.append("<expand_remote_job>N</expand_remote_job>");


            }

            xml.append("</job_execution_configuration>");

            xml.append(jobXml);
        }
        xml.append("</job_configuration>");


        return common_post_body(url, null, xml.toString());
    }


    public Result registerTrans(String jobXml, Map<String, String> params) {
        String url = baseUrl + "/kettle/registerTrans/?xml=Y";
        if (params == null) {
            params = Collections.emptyMap();
        }

        jobXml = jobXml.replaceFirst("\\<\\?xml.*>", "");


        StringBuilder xml = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");

        xml.append("<transformation_configuration>");
        {
            xml.append("<transformation_execution_configuration>");
            {
                xml.append("<parameters>");
                for (Map.Entry<String, String> e : params.entrySet()) {
                    xml.append("<parameter><name>").append(e.getKey())
                            .append("</name><value>").append(e.getValue())
                            .append("</value></parameter>");
                }
                xml.append("</parameters>");
            }

            xml.append("</transformation_execution_configuration>");

            xml.append(jobXml);
        }
        xml.append("</transformation_configuration>");


        return common_post_body(url, null, xml.toString());
    }


    public List<RepTreeItem> getRepositoryObjectTree() {
        String url = baseUrl + KETTLE_PLUGIN_REPO_OBJ + "tree";
        HashMap<String, Object> params = new HashMap<>();
        params.put("xml", "Y");
        params.put("rep", rep);

        HttpRequest http = HttpRequest.get(url, params, true).basic(username, password);
        String body = http.body();

        common_check_result(http, body);


        List<RepTreeItem> list = XmlTool.xmlToBeanListQuietly(body, RepTreeItem.class);

        return list;
    }

    private void common_check_result(HttpRequest http, String body) {
        if (http.code() == 500) {
            WebResult webResult = tryParseWebResult(body);
            throw new KettleSdkException(webResult.getMessage(), http.code(), -1);
        }

        if (http.code() != 200) {
            throw new KettleSdkException(http.message(), http.code(), -1);
        }
    }

    public String getRepositoryObjectContent(String id) {
        String url = baseUrl + KETTLE_PLUGIN_REPO_OBJ + "content";
        HashMap<String, Object> params = new HashMap<>();
        params.put("xml", "Y");
        params.put("rep", rep);
        params.put("id", id);

        HttpRequest http = HttpRequest.get(url, params, true).basic(username, password);

        if (http.code() != 200) {
            throw new IllegalStateException(http.code() + ": " + http.message());
        }

        String xml = http.body();

        return xml;
    }

    public Result deleteRepositoryObject(String id) {
        String url = baseUrl + KETTLE_PLUGIN_REPO_OBJ + "delete";
        HashMap<String, Object> params = new HashMap<>();
        params.put("xml", "Y");
        params.put("rep", rep);
        params.put("id", id);

        return common_get(url, params);
    }

    public Result uploadRepositoryObject(String xml) {
        String url = baseUrl + KETTLE_PLUGIN_REPO_OBJ + "upload";
        HashMap<String, Object> params = new HashMap<>();
        params.put("xml", "Y");
        params.put("rep", rep);

        return common_post_body(url, params, xml);
    }

    public ResultData<byte[]> jobImage(String id, String name) {
        ResultData<byte[]> rs = new ResultData<>();
        if (id == null || id.isEmpty()) {
            rs.setSuccess(false);
            rs.setMessage("id can't be null or empty");
            return rs;
        }

        if (name == null || name.isEmpty()) {
            rs.setSuccess(false);
            rs.setMessage("name can't be null or empty");
            return rs;
        }


        String url = baseUrl + "/kettle/jobImage/";
        HashMap<String, Object> params = new HashMap<>();
        params.put("xml", "Y");
        params.put("id", id);
        params.put("name", name);
        HttpRequest http = HttpRequest.get(url, params, true).basic(username, password);

        if (http.code() != 200) {
            rs.setSuccess(false);
            rs.setMessage("get job image error:" + http.message());
            return rs;
        }

        byte[] content = http.bytes();

        // note: kettle carte will response bytes if success, or xml if error
        // try check the response content is bytes or xml
        String tmp = new String(content, 0, 20);
        boolean isXml = tmp.startsWith("<webresult>");

        if (!isXml) {
            rs.setSuccess(true);
            rs.setData(content);
            return rs;
        }

        // is xml , means that error

        try {
            String xml = new String(content, StandardCharsets.UTF_8);
            System.err.println(xml);
            WebResult webResult = XmlTool.xmlToBean(xml, WebResult.class);
            rs.setSuccess(false);
            rs.setMessage(webResult.getMessage());
            return rs;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    private Result common_get(String url, Map<String, Object> otherParameters) {
        HttpRequest http = HttpRequest.get(url, otherParameters, true).basic(username, password);
        return common_parse_result(http);
    }


    private Result common_post_body(String url, Map<String, Object> params, String bodyContent) {
        HttpRequest http = HttpRequest.post(url, params, true).basic(username, password).contentType("text/xml", CHARSET_UTF8).send(bodyContent);
        return common_parse_result(http);
    }

    private static Result common_parse_result(HttpRequest http) {
        String body = http.body();
        System.out.println("code:" + http.code());
        System.out.println("message:" + http.message());
        System.out.println("body:" + body);
        boolean hasBody = body != null && !body.isEmpty();

        if (http.code() == 200 && (!hasBody)) {
            return Result.ok();
        }


        if (hasBody) {
            try {
                WebResult webResult = XmlTool.xmlToBean(body, WebResult.class);
                if ("ERROR".equals(webResult.getResult())) {
                    return Result.err().msg(webResult.getMessage());
                }
                return Result.ok().msg(webResult.getMessage()).id(webResult.getId());
            } catch (JsonProcessingException e) {
                return Result.err().msg(e.getMessage());
            }
        }

        return Result.err().msg("request kettle err：" + http.code() + "," + http.message());
    }


    public WebResult tryParseWebResult(String body) {
        try {
            return XmlTool.xmlToBean(body, WebResult.class);
        } catch (JsonProcessingException e) {

        }
        return null;
    }
}
