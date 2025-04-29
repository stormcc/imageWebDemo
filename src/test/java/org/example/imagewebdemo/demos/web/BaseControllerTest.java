package org.example.imagewebdemo.demos.web;

import lombok.extern.slf4j.Slf4j;
import org.example.imagewebdemo.ImageWebDemoApplication;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.util.Collection;
import java.util.Map;

/**
 * @author xingmao@
 * @date 2018/7/12
 */

@Slf4j
@ActiveProfiles("dev")
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes= ImageWebDemoApplication.class)
public abstract class BaseControllerTest {
    private static String DEFAULT_CHARACTER = "UTF-8";

    @Autowired
    private MockMvc mockMvc;

    protected String baseTest(String url, long maxTimeCostMs) throws Exception{
        log.info("MvcTest url is:{}", url);
        long start = System.currentTimeMillis();
        MockHttpServletResponse mockHttpServletResponse =  getMockHttpServletResponse(url);
        long cost = System.currentTimeMillis() - start;
        int status = mockHttpServletResponse.getStatus();
        Collection<String> headerNames = mockHttpServletResponse.getHeaderNames();
        for (String headerName : headerNames) {
            log.info("headerName:{}, headerValue is:{}", headerName, mockHttpServletResponse.getHeaders(headerName));
        }
        mockHttpServletResponse.setDefaultCharacterEncoding(DEFAULT_CHARACTER);
        String content = mockHttpServletResponse.getContentAsString();
        log.info("status is:{}, it cost:{} ms, content is:{} ", status, cost, content);
        Assert.isTrue(HttpStatus.OK.value() == status, "返回状态码为:"+status+", 不是:"+HttpStatus.OK.value());
        Assert.isTrue(cost<maxTimeCostMs, "响应耗时 "+cost+" ms, 超过阈值: "+maxTimeCostMs+" ms.");
        return content;
    }

    protected String baseTest(String url, long maxTimeCostMs, Map<String, String> requestHeaderMap) throws Exception{
        log.info("MvcTest url is:{}, requestHeaderMap:{}", url, requestHeaderMap);
        long start = System.currentTimeMillis();
        MockHttpServletResponse mockHttpServletResponse =  getMockHttpServletResponse(url, requestHeaderMap);
        long cost = System.currentTimeMillis() - start;
        int status = mockHttpServletResponse.getStatus();
        Collection<String> headerNames = mockHttpServletResponse.getHeaderNames();
        for (String headerName : headerNames) {
            log.info("headerName:{}, headerValue is:{}", headerName, mockHttpServletResponse.getHeaders(headerName));
        }
        mockHttpServletResponse.setDefaultCharacterEncoding(DEFAULT_CHARACTER);
        String content = mockHttpServletResponse.getContentAsString();
        log.info("status is:{}, it cost:{} ms, content is:{} ", status, cost, content);
        Assert.isTrue(HttpStatus.OK.value() == status, "返回状态码为:"+status+", 不是:"+HttpStatus.OK.value());
        Assert.isTrue(cost<maxTimeCostMs, "响应耗时 "+cost+" ms, 超过阈值: "+maxTimeCostMs+" ms.");
        return content;
    }

    protected String baseTest(String url) throws Exception{
        return baseTest(url, 1000L);
    }

    protected MockHttpServletResponse getMockHttpServletResponse(String url) throws Exception{
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)
                    .characterEncoding(DEFAULT_CHARACTER)
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaType.APPLICATION_JSON)
            ).andReturn();
        return mvcResult.getResponse();
    }

    protected MockHttpServletResponse getMockHttpServletResponse(String url, Map<String, String> requestHeaderMap) throws Exception{
        HttpHeaders httpHeaders = new HttpHeaders();
        requestHeaderMap.forEach((key, value) -> httpHeaders.add(key, value));
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url).headers(httpHeaders)
                .characterEncoding(DEFAULT_CHARACTER)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON)
        ).andReturn();
        return mvcResult.getResponse();
    }



    protected MockHttpServletResponse postMockHttpServletResponse(String url, String content) throws Exception{
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url)
                    .characterEncoding(DEFAULT_CHARACTER)
                    .content( content)
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaType.APPLICATION_JSON_UTF8)
                ).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setDefaultCharacterEncoding(DEFAULT_CHARACTER);
        return mockHttpServletResponse;
    }

    protected MockHttpServletResponse postMockHttpServletResponse1(String url, String fileName) throws Exception{
        MockMultipartFile multipartFile = new MockMultipartFile(fileName, Files.newInputStream(new File(fileName).toPath()));
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.multipart(url).file(multipartFile)
                .accept(MediaType.IMAGE_PNG_VALUE)).andReturn();
        MockHttpServletResponse mockHttpServletResponse =  mvcResult.getResponse();
        mockHttpServletResponse.setDefaultCharacterEncoding(DEFAULT_CHARACTER);
        return mockHttpServletResponse;
    }

    protected void basePost(String url, String content)  throws Exception{
        log.warn("url is:{}, content is:{}", url, content);
        MockHttpServletResponse mockHttpServletResponse = postMockHttpServletResponse(url, content);
        mockHttpServletResponse.setDefaultCharacterEncoding(DEFAULT_CHARACTER);
        log.warn("status is:{}", mockHttpServletResponse.getStatus());
        String result = mockHttpServletResponse.getContentAsString();
        log.warn("result is:{}", result);
    }

    protected void basePostUpload(String url, String fileName)  throws Exception{
        log.warn("url is:{}, fileName is:{}", url, fileName);
        try (FileInputStream inputStream = new FileInputStream(fileName);){
            MockMultipartFile multipartFile = new MockMultipartFile("file", "originalName",MediaType.IMAGE_PNG_VALUE,  inputStream);
            MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders.multipart(url)
                            .file(multipartFile)
                            .accept(MediaType.IMAGE_PNG_VALUE)
                    ).andReturn();
            MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
            mockHttpServletResponse.setDefaultCharacterEncoding(DEFAULT_CHARACTER);
            log.warn("status is:{}", mockHttpServletResponse.getStatus());
            String result = mockHttpServletResponse.getContentAsString();
            log.warn("result is:{}", result);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 暂未使用过，待验证
     * @param urlTemplate
     * @param fileName
     * @param param1
     * @param param2
     * @throws Exception
     */
    protected void basePostUpload1(String urlTemplate, String fileName,  String param1, String param2)  throws Exception{
        String url = String.format(urlTemplate, param1, param2);
        log.warn("url is:{}, fileName is:{}", url, fileName);
        try (FileInputStream inputStream = new FileInputStream(fileName);){
            MockMultipartFile multipartFile = new MockMultipartFile("file", "originalName",
                    MediaType.IMAGE_PNG_VALUE,  inputStream);
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.multipart(url)
                            .file(multipartFile)
                            .accept(MediaType.IMAGE_PNG_VALUE))
                    .andReturn();
            MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
            log.warn("status is:{}", mockHttpServletResponse.getStatus());
            String result = mockHttpServletResponse.getContentAsString();
            log.warn("result is:{}", result);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
