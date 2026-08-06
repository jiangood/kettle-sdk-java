/*! ******************************************************************************
 *
 * Pentaho Data Integration
 *
 * Copyright (C) 2002-2017 by Hitachi Vantara : http://www.pentaho.com
 *
 *******************************************************************************
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 ******************************************************************************/

package io.github.jiangood.kettle.sdk.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SlaveServerStatus {
    public static final String XML_TAG = "serverstatus";

    @JacksonXmlProperty(localName = "statusdesc")
    private String statusDescription;

    private String errorDescription;

    @JacksonXmlProperty(localName = "memory_free")
    private long memoryFree;

    @JacksonXmlProperty(localName = "memory_total")
    private long memoryTotal;

    @JacksonXmlProperty(localName = "cpu_cores")
    private int cpuCores;

    @JacksonXmlProperty(localName = "cpu_process_time")
    private long cpuProcessTime;

    private long uptime;

    @JacksonXmlProperty(localName = "thread_count")
    private int threadCount;

    @JacksonXmlProperty(localName = "load_avg")
    private double loadAvg;

    @JacksonXmlProperty(localName = "os_name")
    private String osName;

    @JacksonXmlProperty(localName = "os_version")
    private String osVersion;

    @JacksonXmlProperty(localName = "os_arch")
    private String osArchitecture;

    @JacksonXmlProperty(localName = "jobstatuslist")
    private List<SlaveServerJobStatus> jobStatusList = new ArrayList<>();
}
