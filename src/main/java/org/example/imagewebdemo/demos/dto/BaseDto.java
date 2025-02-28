package org.example.imagewebdemo.demos.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.example.imagewebdemo.demos.util.LogExceptionStackUtil;

import java.io.Serializable;

@Slf4j
public class BaseDto implements Serializable {
    public String toJson(){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            log.error("return null, JsonProcessingException is:{}", LogExceptionStackUtil.logExceptionStack(e));
            return null;
        }
    }
}
