package io.github.jiangood.kettle.sdk;

public class KettleSdkException extends RuntimeException {


    int httpCode;

    int code;


    public KettleSdkException(String message, int httpCode, int code) {
        super(message);
        this.httpCode = httpCode;
        this.code = code;
    }


}
