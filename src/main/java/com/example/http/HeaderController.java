package com.example.http;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class HeaderController {
    @PostMapping("/single-header")
    @ResponseBody
    public String singleHeader(
            // HTTP 요청에 포함된 Header하나 가져오고 싶을 때
            @RequestHeader("Content-Type")
            String contentType
    ) {
        return contentType;
    }

    @PostMapping("/option-header")
    @ResponseBody
    public String optionHeader(
            @RequestHeader(
                    value = "x-likelion",
                    // required: 포함을 반드시 해야하는지
                    required = false
            )
            String likelionHeader,
            @RequestHeader(
                    value = "x-likelion-default",
                    required = false,
                    // defaultValue: 포함안되었을때 기본값
                    defaultValue = "hello"
            )
            String likelionDefaultHeader
    ) {
        log.info(likelionHeader);
        log.info(likelionDefaultHeader);
        return likelionDefaultHeader;
    }

    @PostMapping("/all-header")
    @ResponseBody
    public String allHeaders(
            // HttpHeaders, Map<String, String>를 사용하면
            // 전체 헤더를 다 확인할 수 있다.
//            @RequestHeader
//            Map<String, String> headers,
            @RequestHeader
            HttpHeaders headers
    ) {
        for (Map.Entry<String, List<String>> entry:
                headers.entrySet()) {
            log.info(String.format(
                    "%s: %s", entry.getKey(), entry.getValue()
            ));
        }
        return "done";
    }
}
