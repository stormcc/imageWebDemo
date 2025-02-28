package org.example.imagewebdemo.demos.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


/**
 * @author xingmao
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"code", "msg", "data"})
public class JsonResultDto<T> extends BaseDto  {
    private Integer code;
    private String msg;

    private T data;

    public JsonResultDto(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public JsonResultDto(T data) {
        this.code = ResultStatus.OK.getCode();
        this.msg = ResultStatus.OK.getMsg();
        this.data = data;
    }

    public boolean isSuccess() {
        return ResultStatus.OK.getCode().equals(code);
    }

    @Getter
    public enum ResultStatus {
        OK(1000, "OK"),
        FAILURE(1001, "failure"),
        ERROR(1002, "error"),
        WARNING(1003, "warning"),
        CREATING(1004,"creating"),
        PROCESSING(1005,"processing"),
        ERROR_PARAMETER(407, "参数错误"),
        ERROR_MEMORY(1006, "资源不足");

        private Integer code;
        private String msg;

        ResultStatus(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }
    }
}
