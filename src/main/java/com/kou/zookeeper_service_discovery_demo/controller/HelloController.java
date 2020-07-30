package com.kou.zookeeper_service_discovery_demo.controller;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

@RestController
public class HelloController {

    @RequestMapping("/getAll")
    public Map<String, Object> getAll(HttpServletRequest request) throws UnsupportedEncodingException {
        String str = request.getParameter("name");
        System.out.println(str);

        String encodeStr = URLEncoder.encode(str, "UTF-8");
        System.out.println(encodeStr);
        String decodeStr = URLDecoder.decode(encodeStr, "UTF-8");
        System.out.println(decodeStr);

        encodeStr = Base64.getEncoder().encodeToString(str.getBytes(StandardCharsets.UTF_8));
        System.out.println(encodeStr);

        encodeStr = Base64.getUrlEncoder().encodeToString(str.getBytes(StandardCharsets.UTF_8));
        System.out.println(encodeStr);

        encodeStr = Base64.getMimeEncoder().encodeToString(str.getBytes(StandardCharsets.UTF_8));
        System.out.println(encodeStr);

//        decodeStr = new String(Base64.getMimeDecoder().decode(encodeStr), StandardCharsets.UTF_8);
//        System.out.println(decodeStr);

        String unescapeJavaStr = StringEscapeUtils.escapeJava(str);
        System.out.println(unescapeJavaStr);

//        str = StringEscapeUtils.escapeHtml4(str);
        return ImmutableMap.of("code", 0, "result", str);
    }

}