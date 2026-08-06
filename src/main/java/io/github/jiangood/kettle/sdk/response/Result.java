
package io.github.jiangood.kettle.sdk.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;


@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "result")
@Getter
@Setter
public class Result implements Cloneable {


  /** The number of errors during the transformation or job */
  @JacksonXmlProperty(localName = "nr_errors")
  private long nrErrors;

  /** The number of lines input. */
  @JacksonXmlProperty(localName = "lines_input")
  private long nrLinesInput;

  /** The number of lines output. */
  @JacksonXmlProperty(localName = "lines_output")
  private long nrLinesOutput;

  /** The number of lines updated. */
  @JacksonXmlProperty(localName = "lines_updated")
  private long nrLinesUpdated;

  /** The number of lines read. */
  @JacksonXmlProperty(localName = "lines_read")
  private long nrLinesRead;

  /** The number of lines written. */
  private long nrLinesWritten;

  /** The number of lines deleted. */
  private long nrLinesDeleted;

  /** The number of files retrieved. */
  private long nrFilesRetrieved;

  /** The result of the job or transformation, true if successful, false otherwise. */
  private Character result;

  /** The entry number. */
  private long entryNr;

  /** The exit status. */
  private int exitStatus;





  /** Whether the job or transformation was stopped. */
  public boolean stopped;

  /** The number of lines rejected. */
  private long nrLinesRejected;

  /** The log channel id. */
  private String logChannelId;

  /** The log text. */
  private String logText;

  /**
   * safe stop.
   */
  private boolean safeStop;

  /**
   * Elapsed time of the ETL execution in milliseconds
   */
  private long elapsedTimeMillis;

  /**
   * Unique identifier of an ETL execution, should one ever care to declare one such
   */
  private String executionId;




}
