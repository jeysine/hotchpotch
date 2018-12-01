package cn.com.jeysine.feign.route;

import cn.com.jeysine.feign.config.FeignConfig;
import cn.com.jeysine.feign.hystrix.HiHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "EUREKA-DEMO-CLIENT", configuration = FeignConfig.class, fallback = HiHystrix.class)
public interface EurekaClentFeign {
    @GetMapping(value = "/helloworld")
    String sayHiFromClientEureka();
}
