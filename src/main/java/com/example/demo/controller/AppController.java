package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.VO.PageData;
import com.example.demo.beans.AppVO;
import com.example.demo.domain.LcApp;
import com.example.demo.service.LcAppService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/app")
@CrossOrigin("http://localhost:5556")
public class AppController {

    @Resource
    private LcAppService lcAppService;


    @GetMapping("/list")
    public BaseResponse<PageData<AppVO>> list(){
        List<AppVO> list =  lcAppService.list().stream().map(item->{
            return new AppVO().setAppCode(item.getAppCode())
                    .setAppName(item.getAppName());
        }).collect(Collectors.toList());
        return BaseResponse.success(PageData.build(list,list.size()));
    }

    @PostMapping("/createOrUpdate")
    public Boolean create(@RequestBody AppVO page){

        LcApp lcApp = new LcApp();
        lcApp.setAppCode(page.getAppCode());
        lcApp.setAppName(page.getAppName());

        QueryWrapper<LcApp> wrapper = new QueryWrapper<>();
        LcApp query = new LcApp();
        query.setAppCode(page.getAppCode());
        wrapper.setEntity(query);
        LcApp exists = lcAppService.getOne(wrapper,false);
        if(exists!=null){
            exists.setAppName(page.getAppName());
        }else{
            exists = new LcApp();
            exists.setAppCode(StringUtils.defaultIfEmpty(page.getAppCode(),"APP-"+UUID.randomUUID().toString()));
            exists.setAppName(page.getAppName());
        }

        exists.setModifier("admin");
        exists.setLastUpdateTime(new Date());
        exists.setIsDeleted("n");
        exists.setCreator("admin");
        return lcAppService.saveOrUpdate(lcApp);
    }


}
