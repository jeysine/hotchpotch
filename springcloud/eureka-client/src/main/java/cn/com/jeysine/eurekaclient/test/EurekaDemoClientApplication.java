package cn.com.jeysine.eurekaclient.test;

import cn.com.jeysine.eurekaclient.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EurekaDemoClientApplication {

    @Value("${server.port}")
    String port;

    @Autowired
    private TestService testService;

    @RequestMapping("/helloworld")
    public String test() {
        return "Hello SpringCloud~PORT:" + port;

    }

    @GetMapping("/feign/hi")
    public String test2() {
        return  testService.test();
    }
}
