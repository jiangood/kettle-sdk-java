package io.github.jiangood.kettle.sdk.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SlaveServerTransStatus {
  public static final String XML_TAG = "transstatus";

  private String id;

  @JacksonXmlProperty(localName = "transname")
  private String transName;

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

  @JacksonXmlProperty(localName = "log_date")
  private String logDate;

  @JacksonXmlProperty(localName = "stepstatuslist")
  private List<StepStatus> stepStatusList;

  @JacksonXmlProperty(localName = "paused")
  private boolean paused;


}
