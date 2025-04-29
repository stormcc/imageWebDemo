package org.example.imagewebdemo.vo;

import lombok.Getter;

/**
 * Create By: Jimmy Song
 * Create At: 2022-08-04 18:00
 */
@Getter
public enum ResultCodeEnum {
    OK(0, "成功"),
    FAILED(1, "处理错误"),
    UNKNOWN(2, "未知错误"),
    INPUT_PARAMETER_ERROR(3, "请求参数错误"),
    INPUT_HEADER_ERROR(4, "请求header错误"),

    INPUT_ERROR(5, "请求错误"),
    ;

    Integer code;
    String message;

    ResultCodeEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
