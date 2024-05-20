package com.example.demo;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.beans.PageVO;
import com.example.demo.domain.LcApp;
import com.example.demo.domain.LcPage;
import com.example.demo.service.LcAppService;
import com.example.demo.service.LcPageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

@Controller
public class HomeController {


    @Resource
    private LcAppService lcAppService;

    @Resource
    private LcPageService lcPageService;

    @RequestMapping("/home")
    public String test(){
        LcApp lcApp = new LcApp();
        lcApp.setAppCode(UUID.randomUUID().toString());
        lcApp.setAppName("测试应用名称");
        lcApp.setCreator("admin");
        lcApp.setCreateTime(new Date());
        lcApp.setLastUpdateTime(new Date());
        lcApp.setModifier("admin");
        lcApp.setIsDeleted("n");
        lcApp.setAppRemark("测试效果");
        lcAppService.save(lcApp);
        return "index";
    }


    /**
     * 保存页面
     * @param pageVO 页面信息
     * @return
     */
    @PostMapping("")
    public Boolean savePage(PageVO pageVO){
        Wrapper<LcPage> wrapper = new QueryWrapper<>();
        LcPage exists = lcPageService.getOne(wrapper,false);
        if(exists== null){
            exists = new LcPage();
            exists.setAppCode(pageVO.getAppCode());
            exists.setPageName("");
            exists.setCreator("admin");
            exists.setCreateTime(new Date());
            exists.setLastUpdateTime(new Date());
            exists.setModifier("admin");
            exists.setIsDeleted("n");
            exists.setAppRemark("测试效果");
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



}
