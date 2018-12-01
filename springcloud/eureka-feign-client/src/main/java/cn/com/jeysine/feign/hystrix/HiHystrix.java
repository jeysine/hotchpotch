package cn.com.jeysine.feign.hystrix;

import cn.com.jeysine.feign.route.EurekaClentFeign;
import org.springframework.stereotype.Component;

@Component
public class HiHystrix implements EurekaClentFeign{
    @Override
    public String sayHiFromClientEureka() {
        return "hi, error";
    }
}
