package io.github.jiangood.kettle.sdk;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Result {

    boolean success;

    String message;

    // jobId, ...
    String id;


    public static Result ok(){
        Result rs = new Result();
        rs.setSuccess(true);
        return rs;
    }

    public static Result err(){
        Result rs = new Result();
        rs.setSuccess(false);
        return rs;
    }

    public Result msg(String msg){
        this.message = msg;
        return this;
    }

    public Result id(String id){
        this.id = id;
        return this;
    }
}
