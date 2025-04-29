package org.example.imagewebdemo.vo;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * @author xingmao
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"code", "message", "data"})
public class JsonResultVo<T> extends BaseVo  {
    private Integer code;
    private String message;

    private T data;

    public JsonResultVo(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public JsonResultVo(T data) {
        this.code = ResultCodeEnum.OK.code;
        this.message = ResultCodeEnum.OK.message;
        this.data = data;
    }


}
