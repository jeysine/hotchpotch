package cn.com.jeysine.ribbon.controller;

import cn.com.jeysine.ribbon.service.RibbonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RibbonController {

    @Autowired
    RibbonServiceImpl ribbonService;

    @GetMapping("/helloworld")
    public String hi() {
        return ribbonService.helloWorld();
    }
}
