package cn.com.jeysine.ribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("ribbonService")
public class RibbonServiceImpl {
    @Autowired
    RestTemplate restTemplate;
    @HystrixCommand(fallbackMethod = "hiError")
    public String helloWorld() {
        return restTemplate.getForObject("http://EUREKA-DEMO-CLIENT/helloworld", String.class);
    }

    public String hiError() {
        return "hi, error";
    }
}
