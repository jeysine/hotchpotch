package cn.com.jeysine.eurekaclient2.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EurekaDemoClientApplication {

    @Value("${server.port}")
    String port;

    @RequestMapping("/helloworld")
    public String test() {
        return "Hello SpringCloud~PORT:" + port;

    }
}
