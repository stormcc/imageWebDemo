/*
 * Copyright 2013-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.example.imagewebdemo.demos.web;

import lombok.extern.slf4j.Slf4j;
import org.example.imagewebdemo.dao.file.ImageWebFileDao;
import org.example.imagewebdemo.demos.config.ImageWebDemoConfig;
import org.example.imagewebdemo.demos.dto.JsonResultDto;
import org.example.imagewebdemo.demos.dto.ModelServiceQps;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.example.imagewebdemo.demos.util.JacksonUtil;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@Slf4j
@RestController
public class BasicController {

    @Resource
    private ImageWebDemoConfig imageWebDemoConfig;

    // http://127.0.0.1:8080/hello?name=lisi
    @RequestMapping("/hello")
    @ResponseBody
    public JsonResultDto<String> hello(@RequestParam(name = "name", defaultValue = "unknown user") String name) {
        return new JsonResultDto<>("Hello " + name);
    }

    // http://127.0.0.1:8080/user
    @RequestMapping("/user")
    @ResponseBody
    public User user() {
        User user = new User();
        user.setName("theonefx");
        user.setAge(666);
        log.info("user:{}", JacksonUtil.valueAsString(user));
        return user;
    }

    // http://127.0.0.1:8080/save_user?name=newName&age=11
    @RequestMapping("/save_user")
    @ResponseBody
    public String saveUser(User u) {
        return "user will save: name=" + u.getName() + ", age=" + u.getAge();
    }

    // http://127.0.0.1:8080/html

    @ModelAttribute
    public void parseUser(@RequestParam(name = "name", defaultValue = "unknown user") String name
            , @RequestParam(name = "age", defaultValue = "12") Integer age, User user) {
        user.setName("zhangsan");
        user.setAge(18);
        log.info("name:{}, age:{}", name, age);
    }

    @RequestMapping(value = "/tokenVerify", method = RequestMethod.POST)
    public ResponseEntity<String> tokenVerify(@RequestBody ModelServiceQps modelServiceQps) {
        log.info(" 调用模型服务接口校验token的请求参数为:{}", JacksonUtil.valueAsString(modelServiceQps));
        try {
            if("".equals(modelServiceQps.getCallToken())||modelServiceQps.getCallToken()==null){
                log.error("getCallToken:{} ", modelServiceQps.getCallToken());
                return ResponseEntity.ok().body("getCallToken");
            }
           if ( "".equals(modelServiceQps.getResponseBody())||modelServiceQps.getResponseBody()==null){
               log.error("getResponseBody:{} ", modelServiceQps.getResponseBody());
               return ResponseEntity.ok().body("getResponseBody");
           }
           if ( modelServiceQps.getType()==null||modelServiceQps.getResponseTime()==null){
               log.error("getType:{} ", modelServiceQps.getType());
               return ResponseEntity.ok().body("getType");
           }
            if ("".equals(modelServiceQps.getServiceId())||modelServiceQps.getServiceId()==null){
                log.error("getServiceId:{} ", modelServiceQps.getServiceId());
                return ResponseEntity.ok().body("getServiceId");
            }
            if ("".equals(modelServiceQps.getRequestParam())||modelServiceQps.getRequestParam()==null){
                log.error("getRequestParam:{} ", modelServiceQps.getRequestParam());
                return ResponseEntity.ok().body("getRequestParam");
            }
            if (  modelServiceQps.getCallTime()==null){
                log.error("getCallTime:{} ", modelServiceQps.getCallTime());
                return ResponseEntity.ok().body("getCallTime");
            }
            log.info("传入的参数:{}",modelServiceQps);
            return ResponseEntity.ok().body("ok");
        } catch (Exception e) {
            log.error("failed：", e);
        }
        return ResponseEntity.ok().body("yes");
    }
}
