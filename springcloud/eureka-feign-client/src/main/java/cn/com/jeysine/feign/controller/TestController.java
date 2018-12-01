package cn.com.jeysine.feign.controller;

import cn.com.jeysine.feign.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private TestService testService;

    @Value("${server.port}")
    String port;

    @GetMapping("/hi")
    public String test() {
        return  testService.test();
    }

    @GetMapping("/feign/hi")
    public String hiFeign() {
        return "Hello Feign~PORT:" + port;
    }
}
