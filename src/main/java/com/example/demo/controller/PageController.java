package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.VO.PageData;
import com.example.demo.beans.AppVO;
import com.example.demo.beans.PageVO;
import com.example.demo.domain.LcApp;
import com.example.demo.domain.LcPage;
import com.example.demo.service.LcAppService;
import com.example.demo.service.LcPageService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/page")
@CrossOrigin("http://localhost:8000")
public class PageController {

    @Resource
    private LcAppService lcAppService;

    @Resource
    private LcPageService lcPageService;


    /**
     * 页面列表
     * @param appCode appCode
     * @return
     */
    @GetMapping("/list")
    @ResponseBody
    public  BaseResponse<List<PageVO>> list(@RequestParam("appCode")String appCode){
        QueryWrapper<LcPage> wrapper = new QueryWrapper<>();
        LcPage query = new LcPage();
        query.setAppCode(appCode);
        wrapper.setEntity(query);
        wrapper.orderByDesc("last_update_time");
        List<PageVO> pageVOList = lcPageService.list(wrapper).stream().map(item->{
            PageVO page = new PageVO();
            return page.setPageCode(item.getPageCode())
                    .setAppCode(item.getAppCode())
                    .setPageName(item.getPageName())
                    .setAssets(item.getPageAssets())
                    .setSchema(item.getPageSchema());
        }).collect(Collectors.toList());
        return BaseResponse.success(pageVOList);
    }

    /**
     * 保存页面
     * @param pageVO 页面信息
     * @return
     */
    @PostMapping("/save")
    @ResponseBody
    public Boolean savePage(@RequestBody PageVO pageVO){
        QueryWrapper<LcPage> wrapper = new QueryWrapper<>();
        LcPage query = new LcPage();
        query.setPageCode(pageVO.getPageCode());
        wrapper.setEntity(query);
        LcPage exists = lcPageService.getOne(wrapper,false);
        if(exists== null){
            exists = new LcPage();
            exists.setAppCode(pageVO.getAppCode());
            exists.setPageCode(UUID.randomUUID().toString());
            exists.setPageName(pageVO.getPageName());
            exists.setCreator("admin");
            exists.setCreateTime(new Date());
            exists.setLastUpdateTime(new Date());
            exists.setModifier("admin");
            exists.setIsDeleted("n");
            exists.setAppRemark(pageVO.getDescription());
        }else{
            exists.setCreator("admin");
            exists.setCreateTime(new Date());
            exists.setLastUpdateTime(new Date());
            exists.setModifier("admin");
        }

        exists.setPageSchema(pageVO.getSchema());
        exists.setPageAssets(pageVO.getAssets());
        Boolean result = lcPageService.saveOrUpdate(exists);
        return result;
    }

    /**
     * 保存页面
     * @param pageCode pageCode
     * @return
     */
    @GetMapping("/detail")
    @ResponseBody
    public PageVO detail(@RequestParam("pageCode")String pageCode){
        QueryWrapper<LcPage> wrapper = new QueryWrapper<>();
        LcPage query = new LcPage();
        query.setPageCode(pageCode);
        wrapper.setEntity(query);
        LcPage exists = lcPageService.getOne(wrapper,false);
        PageVO page = new PageVO();
        page.setPageCode(exists.getPageCode())
                .setAppCode(exists.getAppCode())
                .setAssets(exists.getPageAssets())
                .setSchema(exists.getPageSchema());
        return page;
    }


}
