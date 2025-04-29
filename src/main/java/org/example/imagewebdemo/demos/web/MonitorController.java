package org.example.imagewebdemo.demos.web;

import lombok.extern.slf4j.Slf4j;
import org.example.imagewebdemo.vo.JsonResultVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Create By: Jimmy Song
 * Create At: 2022-04-13 10:30
 */
@Slf4j
@RestController
public class MonitorController {


    @GetMapping(value = "/monitor/one")
    public JsonResultVo<Integer> one(){
        log.info("one");
        return new JsonResultVo<>(1);
    }

}
