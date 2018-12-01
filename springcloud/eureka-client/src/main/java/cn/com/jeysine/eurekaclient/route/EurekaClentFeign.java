package cn.com.jeysine.eurekaclient.route;

import cn.com.jeysine.eurekaclient.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "EUREKA-FEIGN-CLIENT", configuration = FeignConfig.class)
public interface EurekaClentFeign {
    @GetMapping(value = "/feign/hi")
    String sayHiFromFeignClientEureka();
}
