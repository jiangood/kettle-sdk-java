package io.github.jiangood.kettle.sdk.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class StepStatus {
  public static final String XML_TAG = "stepstatus";

  @JacksonXmlProperty(localName = "stepname")
  private String stepname;

  private int copy;
  private long linesRead;
  private long linesWritten;
  private long linesInput;
  private long linesOutput;
  private long linesUpdated;
  private long linesRejected;
  private long errors;
  private String statusDescription;
  private double seconds;
  private String speed;
  private String priority;
  private boolean stopped;
  private boolean paused;

//  private long accumlatedRuntime;

  //private RowMetaInterface sampleRowMeta;

  @JacksonXmlProperty(localName = "samples")
  private List<Object[]> sampleRows;








}
