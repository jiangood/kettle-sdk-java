package io.github.jiangood.kettle.sdk;

// pentaho-kettle\core\src\main\java\org\pentaho\di\core\logging\LogLevel.java
public enum LogLevel {

    NOTHING("Nothing"),
    ERROR("Error"),
    MINIMAL("Minimal"),
    BASIC("Basic"),
    DETAILED(
            "Detailed"),
    DEBUG("Debug"),
    ROWLEVEL("Rowlevel");


    LogLevel(String code) {
        this.code = code;
    }

    private String code;

    public String getCode() {
        return code;
    }
}
