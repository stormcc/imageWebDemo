package org.example.imagewebdemo.demos.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ModelServiceQps {

    /**
     * uuid
     */
    private String infoId;

    /**
     * 模型调用时间
     */
    @JsonFormat( pattern="yyyy-MM-dd HH:mm:ss")
    private Date callTime;

    /**
     * 模型服务id
     */
    private String serviceId;

    /**
     * 请求token
     */
    private String callToken;

    /**
     * 请求参数
     */
    private String requestParam;

    /**
     * type:1 校验token ;2 返回参数
     */
    private Integer type;

    /**
     * responseBody :{}模型服务接口的响应
     */
    private String  responseBody;

    /**
     * responseTime:从调用模型服务接口到结束的时间  以毫秒为单位
     */
    private BigDecimal responseTime;

    /**
     * 是否失败：默认为0 未失败 ；  1 失败
     */
    private Integer isFalse;


    @Override
    public String toString() {
        return "ModelServiceQps{" +
                "infoId='" + infoId + '\'' +
                ", callTime=" + callTime +
                ", serviceId='" + serviceId + '\'' +
                ", callToken='" + callToken + '\'' +
                ", requestParam='" + requestParam + '\'' +
                ", type=" + type +
                ", responseBody='" + responseBody + '\'' +
                ", responseTime=" + responseTime +
                ", isFalse=" + isFalse +
                '}';
    }
}