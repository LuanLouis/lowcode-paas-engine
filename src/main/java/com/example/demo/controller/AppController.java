package com.example.demo.controller;


import com.example.demo.beans.AppVO;
import com.example.demo.service.LcAppService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/app")
@CrossOrigin("http://localhost:5556")
public class AppController {

    @Resource
    private LcAppService lcAppService;


    @GetMapping("/list")
    public List<AppVO> list(){
        return lcAppService.list().stream().map(item->{
            return new AppVO().setAppCode(item.getAppCode())
                    .setAppName(item.getAppName());
        }).collect(Collectors.toList());
    }
}
