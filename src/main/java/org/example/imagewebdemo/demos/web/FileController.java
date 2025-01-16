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
import org.example.imagewebdemo.demos.dto.ImageWebDemoData;
import org.example.imagewebdemo.demos.dto.ModelServiceQps;
import org.example.imagewebdemo.demos.util.JacksonUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@Slf4j
@RestController
public class FileController {

    @RequestMapping("/file/imageweb/info/enableDo")
    public ResponseEntity<Boolean> info() {
        boolean enableDo = ImageWebFileDao.enableDo();
        return ResponseEntity.ok().body(enableDo);
    }


    @RequestMapping("/file/imageweb/info/name")
    public ResponseEntity<ImageWebDemoData> name() {
        String name = ImageWebFileDao.getName();
        ImageWebDemoData data = new ImageWebDemoData();
        data.setName(name);
        return ResponseEntity.ok().body(data);
    }


    @RequestMapping("/file/imageweb/info/age")
    public ResponseEntity<Integer> age() {
        Integer age = ImageWebFileDao.getAge();
        return ResponseEntity.ok().body(age);
    }
}
