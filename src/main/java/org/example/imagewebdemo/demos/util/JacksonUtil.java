package org.example.imagewebdemo.demos.util;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.extern.slf4j.Slf4j;

import java.util.TimeZone;

@Slf4j
public final class JacksonUtil {

    private JacksonUtil(){}

    public static ObjectMapper serializeObjectMapper(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        // 允许单引号字段名
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        // 自动给字段名加上引号
        mapper.configure(JsonGenerator.Feature.QUOTE_FIELD_NAMES, true);
        // 时间默认以时间戳格式写
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 设置时间转换所使用的默认时区
        mapper.setTimeZone(TimeZone.getDefault());
        // null不生成到json字符串中
        mapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        mapper.configure(SerializationFeature.FAIL_ON_SELF_REFERENCES, true);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        // 全局日期反序列化配置
        SimpleModule module = new SimpleModule();
        module.addDeserializer(java.util.Date.class, new DateDeserializers.DateDeserializer());
        module.addDeserializer(java.sql.Date.class, new DateDeserializers.SqlDateDeserializer());
        module.addDeserializer(java.sql.Timestamp.class, new DateDeserializers.TimestampDeserializer());
        mapper.registerModule(module);

        return mapper;
    }

    public static ObjectMapper deserializeObjectMapper(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
        return mapper;
    }

    public static String valueAsString(Object o) {
        ObjectMapper mapper = serializeObjectMapper();
        try {
            return mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            log.error("return null, JsonProcessingException is:{}", LogExceptionStackUtil.logExceptionStack(e));
        }
        return null;
    }

    public static String valueAsStringThrow(Object o) throws JsonProcessingException {
        ObjectMapper mapper = deserializeObjectMapper();
        return mapper.writeValueAsString(o);
    }
}
