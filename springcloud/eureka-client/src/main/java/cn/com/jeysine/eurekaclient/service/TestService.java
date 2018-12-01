package cn.com.jeysine.eurekaclient.service;

import cn.com.jeysine.eurekaclient.route.EurekaClentFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @Autowired
    private EurekaClentFeign eurekaClentFeign;

    public String test(){
        return eurekaClentFeign.sayHiFromFeignClientEureka();
    }
}
