package org.example.imagewebdemo.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 返回实体类
 *
 * @author yangwang
 */
public class ResultEntity implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * 默认返回值
     */
    private static Object DEFAULT_RETURN_DATA = new Object();
    /**
     * 数据
     */
    private Object data = DEFAULT_RETURN_DATA;
    /**
     * 状态码
     */
    @JsonIgnore
    private ResultStatus status = ResultStatus.OK;
    /**
     * 返回消息
     */
    private String msg = ResultStatus.OK.getMsg();

    public ResultEntity(Object data) {
        this(ResultStatus.OK, data, ResultStatus.OK.getMsg());
    }

    public ResultEntity(ResultStatus status) {
        this(status, new HashMap<String, String>(), status.getMsg());
    }
    public ResultEntity(ResultStatus status, int status1) {
        this(status, new HashMap<String, String>(), status.getMsg(),status1);
    }
    public ResultEntity(ResultStatus status, Object data) {
        this(status, status == ResultStatus.OK ? data : null,
                status == ResultStatus.OK ? ResultStatus.OK.getMsg() : (data == null ? "" : data.toString()));
    }

    public ResultEntity(ResultStatus status, Object data, String msg) {
        this.status = status;
        this.data = data;
        if (data instanceof List || data instanceof Object[]) {
            Map<String, Object> mapData = new HashMap<String, Object>();
            mapData.put("list", data);
            this.data = mapData;
        }
        this.msg = msg;
    }
    public ResultEntity(ResultStatus status, Object data, String msg, int status1) {
        this.status = status;
        this.data = data;
        if (data instanceof List || data instanceof Object[]) {
            Map<String, Object> mapData = new HashMap<String, Object>();
            mapData.put("list", data);
            mapData.put("status", status1);
            this.data = mapData;
        }else{
            Map<String, Object> mapData = new HashMap<String, Object>();
            mapData.put("status", status1);
            this.data = mapData;
        }
        this.msg = msg;
    }
    public Object getData() {
        return data;
    }

    public ResultEntity setData(Object data) {
        this.data = data == null ? DEFAULT_RETURN_DATA : data;
        return this;
    }

    public int getCode() {
        return status.getCode();
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "ResultEntity{" +
                "data=" + data +
                ", status=" + status +
                ", msg='" + msg + '\'' +
                '}';
    }

    public enum ResultStatus {
        OK(1000, "OK"),

        OBJECT_NOT_FOUND(801, "找不到对象"),
        UNEXPECTED_VALUE(802, "值无法对应"),
        CAN_NOT_BE_NULL(803, "值不能为空"),
        UNSUPPORT_TYPE(804, "不支持的类型"),
        EXECUTION_FAILED(805, "执行失败"),
        UNCONFIRMED_STANDARDS(806, "不符合规范"),
        LICENSE_EXCEPTION(807, "License异常"),
        ACCESS_WITHOUT_PERMISSION(808, "没有权限访问"),
        OPERATIONEXCEPTION(809, "操作失误"),
        DATASOURCEEXCEPTION(810, "数据连接失败"),
        SQL_EXECUTION_EXCEPTION(811, "SQL执行失败"),

        FAILURE(1001, "failure"),
        ERROR(1002, "error"),
        ERROR_PARAMETER(407, "参数错误"),

        LIST_FILES_NOT_SUPPORT(701,"连接类型不支持列出文件");



        private int code;
        private String msg;

        private ResultStatus(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }
}
