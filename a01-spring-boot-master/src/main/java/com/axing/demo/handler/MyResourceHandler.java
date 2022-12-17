package com.axing.demo.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName MyResourceHandler.java
 * @Description TODO
 * @createTime 2021年12月27日 16:06:00
 * <p>
 * 视频分段播放
 */
@Component
public class MyResourceHandler extends ResourceHttpRequestHandler {

    public final static String ATTR_FILE = "NON-STATIC-FILE";

    @Override
    protected Resource getResource(HttpServletRequest request) {
        String filePath = (String) request.getAttribute(ATTR_FILE);
        return new FileSystemResource(filePath);
    }
}