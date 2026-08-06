package io.github.jiangood.kettle.sdk.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SlaveSequence {

  public static final String XML_TAG = "sequence";

  @JacksonXmlProperty(localName = "name")
  private String name;

  @JacksonXmlProperty(localName = "start")
  private long startValue;


  /**
   * The schema to use
   */
  @JacksonXmlProperty(localName = "schema")
  private String schemaName;

  /**
   * The table to use
   */
  @JacksonXmlProperty(localName = "table")
  private String tableName;

  /**
   * The sequence name field in the table
   */
  @JacksonXmlProperty(localName = "sequence_field")
  private String sequenceNameField;

  /**
   * The current value of the sequence
   */
  @JacksonXmlProperty(localName = "value_field")
  private String valueField;





}
