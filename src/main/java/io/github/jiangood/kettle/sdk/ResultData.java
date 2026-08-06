package io.github.jiangood.kettle.sdk;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultData<T> extends Result {

   T data;


}
