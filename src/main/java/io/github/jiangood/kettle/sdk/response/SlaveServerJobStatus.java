package io.github.jiangood.kettle.sdk.response;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "jobstatus")
public class SlaveServerJobStatus {

  @JacksonXmlProperty(localName = "jobname")
  private String jobName;
  private String id;


  @JacksonXmlProperty(localName = "status_desc")
  private String statusDescription;


  @JacksonXmlProperty(localName = "error_desc")
  private String errorDescription;

  @JacksonXmlProperty(localName = "logging_string")
  private String loggingString;


  @JacksonXmlProperty(localName = "first_log_line_nr")
  private int firstLoggingLineNr;


  @JacksonXmlProperty(localName = "last_log_line_nr")
  private int lastLoggingLineNr;

  /**
   *  远程服务响应的格式： 2024/08/06 10:59:07.031
   *
   */
  @JacksonXmlProperty(localName = "log_date")
  private String logDate;


  private Result result;
}
