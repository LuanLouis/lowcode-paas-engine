package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.VO.PageData;
import com.example.demo.beans.AppVO;
import com.example.demo.beans.PageVO;
import com.example.demo.domain.LcApp;
import com.example.demo.domain.LcPage;
import com.example.demo.service.LcAppService;
import com.example.demo.service.LcPageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/page")
@CrossOrigin({"http://localhost:8000","http://localhost:5556"})
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


        String defaultSchema = "{\"version\":\"1.0.0\",\"componentsMap\":[{\"package\":\"@alilc/antd-lowcode-materials\",\"version\":\"1.2.1\",\"exportName\":\"Typography\",\"main\":\"\",\"destructuring\":true,\"subName\":\"Title\",\"componentName\":\"Typography.Title\"},{\"package\":\"@alilc/antd-lowcode-materials\",\"version\":\"1.2.1\",\"exportName\":\"Col\",\"main\":\"\",\"destructuring\":true,\"componentName\":\"Col\"},{\"package\":\"@alilc/antd-lowcode-materials\",\"version\":\"1.2.1\",\"exportName\":\"Row\",\"main\":\"\",\"destructuring\":true,\"componentName\":\"Row\"},{\"package\":\"@alilc/antd-lowcode-materials\",\"version\":\"1.2.1\",\"exportName\":\"Input\",\"main\":\"\",\"destructuring\":true,\"componentName\":\"Input\"},{\"package\":\"@alilc/antd-lowcode-materials\",\"version\":\"1.2.1\",\"exportName\":\"Form\",\"main\":\"\",\"destructuring\":true,\"subName\":\"Item\",\"componentName\":\"Form.Item\"},{\"package\":\"@alilc/antd-lowcode-materials\",\"version\":\"1.2.1\",\"exportName\":\"InputNumber\",\"main\":\"\",\"destructuring\":true,\"componentName\":\"InputNumber\"},{\"package\":\"@alilc/antd-lowcode-materials\",\"version\":\"1.2.1\",\"exportName\":\"Input\",\"main\":\"\",\"destructuring\":true,\"subName\":\"Password\",\"componentName\":\"Input.Password\"},{\"package\":\"@alilc/antd-lowcode-materials\",\"version\":\"1.2.1\",\"exportName\":\"Input\",\"main\":\"\",\"destructuring\":true,\"subName\":\"TextArea\",\"componentName\":\"Input.TextArea\"},{\"package\":\"@alilc/antd-lowcode-materials\",\"version\":\"1.2.1\",\"exportName\":\"Select\",\"main\":\"\",\"destructuring\":true,\"componentName\":\"Select\"},{\"package\":\"@alilc/antd-lowcode-materials\",\"version\":\"1.2.1\",\"exportName\":\"Slider\",\"main\":\"\",\"destructuring\":true,\"componentName\":\"Slider\"},{\"package\":\"@alilc/antd-lowcode-materials\",\"version\":\"1.2.1\",\"exportName\":\"Checkbox\",\"main\":\"\",\"destructuring\":true,\"subName\":\"Group\",\"componentName\":\"Checkbox.Group\"},{\"package\":\"@alilc/antd-lowcode-materials\",\"version\":\"1.2.1\",\"exportName\":\"Switch\",\"main\":\"\",\"destructuring\":true,\"componentName\":\"Switch\"},{\"package\":\"@alilc/antd-lowcode-materials\",\"version\":\"1.2.1\",\"exportName\":\"TimePicker\",\"main\":\"\",\"destructuring\":true,\"componentName\":\"TimePicker\"},{\"package\":\"@alilc/antd-lowcode-materials\",\"version\":\"1.2.1\",\"exportName\":\"Rate\",\"main\":\"\",\"destructuring\":true,\"componentName\":\"Rate\"},{\"package\":\"@alilc/antd-lowcode-materials\",\"version\":\"1.2.1\",\"exportName\":\"Button\",\"main\":\"\",\"destructuring\":true,\"componentName\":\"Button\"},{\"package\":\"@alilc/antd-lowcode-materials\",\"version\":\"1.2.1\",\"exportName\":\"Form\",\"main\":\"\",\"destructuring\":true,\"componentName\":\"Form\"},{\"devMode\":\"lowCode\",\"componentName\":\"Page\"}],\"componentsTree\":[{\"componentName\":\"Page\",\"id\":\"node_dockcviv8fo1\",\"props\":{\"ref\":\"outerView\",\"style\":{\"height\":\"100%\"}},\"fileName\":\"/\",\"dataSource\":{\"list\":[{\"type\":\"fetch\",\"isInit\":true,\"options\":{\"params\":{},\"method\":\"GET\",\"isCors\":true,\"timeout\":5000,\"headers\":{},\"uri\":\"mock/info.json\"},\"id\":\"info\",\"shouldFetch\":{\"type\":\"JSFunction\",\"value\":\"function() { \\n  console.log('should fetch.....');\\n  return true; \\n}\"}},{\"type\":\"fetch\",\"isInit\":true,\"options\":{\"params\":{\"pageCode\":{\"type\":\"JSExpression\",\"value\":\"this.state.pageCode\"}},\"method\":\"GET\",\"isCors\":true,\"timeout\":5000,\"headers\":{},\"uri\":\"http://localhost:8080/api/page/detail\"},\"id\":\"formSchema\",\"willFetch\":{\"type\":\"JSFunction\",\"value\":\"function(options) {\\n  options.withCredentials = false;\\n\\n  return options;\\n}\"}},{\"type\":\"fetch\",\"isInit\":false,\"options\":{\"params\":{},\"method\":\"POST\",\"isCors\":true,\"timeout\":5000,\"headers\":{},\"uri\":\"/flow/api/form/save\"},\"id\":\"saveFormSchema\"},{\"type\":\"fetch\",\"isInit\":true,\"options\":{\"params\":{},\"method\":\"GET\",\"isCors\":{\"type\":\"JSExpression\",\"value\":\"true\"},\"timeout\":5000,\"headers\":{\"Access-Control-Allow-Credentials\":\"true\"},\"uri\":\"http://localhost:8080/api/app/list\"},\"id\":\"appList\",\"shouldFetch\":{\"type\":\"JSFunction\",\"value\":\"function() { return true; }\"},\"willFetch\":{\"type\":\"JSFunction\",\"value\":\"function(options) { \\n  options.withCredentials=false;\\n  \\n  return options; }\"}}]},\"state\":{\"text\":{\"type\":\"JSExpression\",\"value\":\"\\\"outer\\\"\"},\"isShowDialog\":{\"type\":\"JSExpression\",\"value\":\"false\"},\"curentAppCode\":{\"type\":\"JSExpression\",\"value\":\"\\\"\\\"\"},\"pageCode\":{\"type\":\"JSExpression\",\"value\":\"new URLSearchParams(location.search.slice(1)).get('pageCode')\"}},\"css\":\"body {\\n  font-size: 12px;\\n}\\n\\n.button {\\n  width: 100px;\\n  color: #ff00ff\\n}\",\"lifeCycles\":{\"componentDidMount\":{\"type\":\"JSFunction\",\"value\":\"function componentDidMount() {\\n  console.log('did mount');\\n}\",\"source\":\"function componentDidMount() {\\n  console.log('did mount');\\n}\"},\"componentWillUnmount\":{\"type\":\"JSFunction\",\"value\":\"function componentWillUnmount() {\\n  console.log('will unmount');\\n}\",\"source\":\"function componentWillUnmount() {\\n  console.log('will unmount');\\n}\"}},\"methods\":{\"componentWillMount\":{\"type\":\"JSFunction\",\"value\":\"function componentWillMount() {\\n  console.log(\\\"will mount\\\");\\n}\",\"source\":\"function componentWillMount() {\\n  console.log(\\\"will mount\\\");\\n}\"},\"testFunc\":{\"type\":\"JSFunction\",\"value\":\"function testFunc() {\\n  console.log('test func');\\n}\",\"source\":\"function testFunc() {\\n  console.log('test func');\\n}\"},\"onClick\":{\"type\":\"JSFunction\",\"value\":\"function onClick() {\\n  this.setState({\\n    isShowDialog: true\\n  });\\n}\",\"source\":\"function onClick() {\\n  this.setState({\\n    isShowDialog: true\\n  });\\n}\"},\"closeDialog\":{\"type\":\"JSFunction\",\"value\":\"function closeDialog() {\\n  this.setState({\\n    isShowDialog: false\\n  });\\n}\",\"source\":\"function closeDialog() {\\n  this.setState({\\n    isShowDialog: false\\n  });\\n}\"},\"onClick_new\":{\"type\":\"JSFunction\",\"value\":\"function onClick_new(event) {\\n  // 点击按钮时的回调\\n  console.log('onClick', event);\\n}\",\"source\":\"function onClick_new(event) {\\n  // 点击按钮时的回调\\n  console.log('onClick', event);\\n}\"},\"onClick_new_new\":{\"type\":\"JSFunction\",\"value\":\"function onClick_new_new(event) {\\n  console.log('event', event);\\n  this.setState({\\n    currentAppCode: ''\\n  });\\n  // 点击按钮时的回调\\n  console.log('onClick', event);\\n}\",\"source\":\"function onClick_new_new(event) {\\n  console.log('event', event);\\n  this.setState({\\n    currentAppCode: ''\\n  });\\n  // 点击按钮时的回调\\n  console.log('onClick', event);\\n}\"}},\"originCode\":\"class LowcodeComponent extends Component {\\n  state = {\\n    \\\"text\\\": \\\"outer\\\",\\n    \\\"isShowDialog\\\": false,\\n    \\\"curentAppCode\\\":\\\"\\\",\\n    \\\"pageCode\\\": new URLSearchParams(location.search.slice(1)).get('pageCode')\\n  }\\n\\n  componentDidMount() {\\n    console.log('did mount');\\n  }\\n  componentWillUnmount() {\\n    console.log('will unmount');\\n  }\\n  \\n  componentWillMount(){\\n    console.log(\\\"will mount\\\");\\n  }\\n\\n  testFunc() {\\n    console.log('test func');\\n  }\\n  onClick() {\\n    this.setState({\\n      isShowDialog: true\\n    })\\n  }\\n  closeDialog() {\\n    this.setState({\\n      isShowDialog: false\\n    })\\n  }\\nonClick_new(event){\\n// 点击按钮时的回调\\nconsole.log('onClick', event);\\n}\\n\\nonClick_new_new(event){\\n  console.log('event',event);\\n  this.setState(\\n    {\\n      currentAppCode:''\\n    }\\n  );\\n// 点击按钮时的回调\\nconsole.log('onClick', event);}\\n}\",\"hidden\":false,\"title\":\"\",\"isLocked\":false,\"condition\":true,\"conditionGroup\":\"\",\"children\":[{\"componentName\":\"Row\",\"id\":\"node_oclwt1imqyr\",\"props\":{\"align\":\"top\",\"justify\":\"start\",\"wrap\":false},\"hidden\":false,\"title\":\"\",\"isLocked\":false,\"condition\":true,\"conditionGroup\":\"\",\"children\":[{\"componentName\":\"Col\",\"id\":\"node_oclwt1imqys\",\"props\":{\"span\":24,\"order\":0},\"hidden\":false,\"title\":\"\",\"isLocked\":false,\"condition\":true,\"conditionGroup\":\"\",\"children\":[{\"componentName\":\"Typography.Title\",\"id\":\"node_oclwt1ip7z1\",\"props\":{\"level\":1,\"children\":\"默认表单搭建demo\",\"code\":false,\"copyable\":false,\"delete\":false,\"disabled\":false,\"editable\":false,\"ellipsis\":false,\"mark\":false,\"keyboard\":false,\"underline\":false,\"strong\":false},\"hidden\":false,\"title\":\"\",\"isLocked\":false,\"condition\":true,\"conditionGroup\":\"\"}]}]},{\"componentName\":\"Form\",\"id\":\"node_oclwt1imqy1\",\"props\":{\"labelCol\":{\"span\":6},\"wrapperCol\":{\"span\":14},\"onValuesChange\":{\"type\":\"JSExpression\",\"value\":\"function() {\\n      const self = this;\\n      try {\\n        return (function onValuesChange(changedValues, allValues) {\\n  console.log('onValuesChange', changedValues, allValues);\\n}).apply(self, arguments);\\n      } catch(e) {\\n        console.warn('call function which parsed by lowcode failed: ', e);\\n        return e.message;\\n      }\\n    }\"},\"onFinish\":{\"type\":\"JSExpression\",\"value\":\"function() {\\n      const self = this;\\n      try {\\n        return (function onFinish(values) {\\n  console.log('onFinish', values);\\n}).apply(self, arguments);\\n      } catch(e) {\\n        console.warn('call function which parsed by lowcode failed: ', e);\\n        return e.message;\\n      }\\n    }\"},\"onFinishFailed\":{\"type\":\"JSExpression\",\"value\":\"function() {\\n      const self = this;\\n      try {\\n        return (function onFinishFailed({ values, errorFields, outOfDate }) {\\n  console.log('onFinishFailed', values, errorFields, outOfDate);\\n}).apply(self, arguments);\\n      } catch(e) {\\n        console.warn('call function which parsed by lowcode failed: ', e);\\n        return e.message;\\n      }\\n    }\"},\"name\":\"basic\",\"ref\":\"form_btkv\",\"colon\":true,\"hideRequiredMark\":false,\"preserve\":true,\"scrollToFirstError\":true,\"validateMessages\":{\"required\":\"'${name}' 不能为空\"}},\"hidden\":false,\"title\":\"\",\"isLocked\":false,\"condition\":true,\"conditionGroup\":\"\",\"children\":[{\"componentName\":\"Form.Item\",\"id\":\"node_oclwt1imqy2\",\"props\":{\"label\":\"表单项\",\"labelAlign\":\"right\",\"colon\":true,\"required\":true,\"noStyle\":false,\"valuePropName\":\"value\",\"name\":\"a\",\"requiredobj\":{\"required\":true,\"message\":\"必填\"}},\"hidden\":false,\"title\":\"\",\"isLocked\":false,\"condition\":true,\"conditionGroup\":\"\",\"children\":[{\"componentName\":\"Input\",\"id\":\"node_oclwt1imqy3\",\"props\":{\"placeholder\":\"请输入\",\"bordered\":true,\"disabled\":false,\"size\":\"middle\"},\"hidden\":false,\"title\":\"\",\"isLocked\":false,\"condition\":true,\"conditionGroup\":\"\"}]},{\"componentName\":\"Form.Item\",\"id\":\"node_oclwt1imqy4\",\"props\":{\"label\":\"表单项\",\"labelAlign\":\"right\",\"colon\":true,\"required\":false,\"noStyle\":false,\"valuePropName\":\"value\",\"name\":\"b\"},\"hidden\":false,\"title\":\"\",\"isLocked\":false,\"condition\":true,\"conditionGroup\":\"\",\"children\":[{\"componentName\":\"InputNumber\",\"id\":\"node_oclwt1imqy5\",\"props\":{\"placeholder\":\"请输入\",\"autoFocus\":false,\"disabled\":false,\"controls\":true,\"bordered\":true,\"size\":\"middle\"},\"hidden\":false,\"title\":\"\",\"isLocked\":false,\"condition\":true,\"conditionGroup\":\"\"}]},{\"componentName\":\"Form.Item\",\"id\":\"node_oclwt1imqy6\",\"props\":{\"label\":\"表单项\",\"labelAlign\":\"right\",\"colon\":true,\"required\":false,\"noStyle\":false,\"valuePropName\":\"value\",\"name\":\"c\"},\"hidden\":false,\"title\":\"\",\"isLocked\":false,\"condition\":true,\"conditionGroup\":\"\",\"children\":[{\"componentName\":\"Input.Password\",\"id\":\"node_oclwt1imqy7\",\"props\":{\"bordered\":true,\"disabled\":false,\"visibilityToggle\":true,\"placeholder\":\"请输入\",\"size\":\"middle\"},\"hidden\":false,\"title\":\"\",\"isLocked\":false,\"condition\":true,\"conditionGroup\":\"\"}]},{\"componentName\":\"Form.Item\",\"id\":\"node_oclwt1imqy8\",\"props\":{\"label\":\"表单项\",\"labelAlign\":\"right\",\"colon\":true,\"required\":false,\"noStyle\":false,\"valuePropName\":\"value\",\"name\":\"d\"},\"hidden\":false,\"title\":\"\",\"isLocked\":false,\"condition\":true,\"conditionGroup\":\"\",\"children\":[{\"componentName\":\"Input.TextArea\",\"id\":\"node_oclwt1imqy9\",\"props\":{\"autoSize\":{\"minRows\":3,\"maxRows\":3},\"placeholder\":\"请输入\",\"bordered\":true,\"disabled\":false,\"showCount\":false,\"size\":\"middle\"},\"hidden\":false,\"title\":\"\",\"isLocked\":false,\"condition\":true,\"conditionGroup\":\"\"}]},{\"componentName\":\"Form.Item\",\"id\":\"node_oclwt1imqya\",\"props\":{\"label\":\"表单项\",\"name\":\"e\",\"labelAlign\":\"right\",\"colon\":true,\"required\":false,\"noStyle\":false,\"valuePropName\":\"value\"},\"hidden\":false,\"title\":\"\",\"isLocked\":false,\"condition\":true,\"conditionGroup\":\"\",\"children\":[{\"componentName\":\"Select\",\"id\":\"node_oclwt1imqyb\",\"props\":{\"style\":{\"width\":200},\"options\":[{\"label\":\"A\",\"value\":\"A\"},{\"label\":\"B\",\"value\":\"B\"},{\"label\":\"C\",\"value\":\"C\"}],\"allowClear\":false,\"autoFocus\":false,\"defaultActiveFirstOption\":true,\"disabled\":false,\"labelInValue\":false,\"showSearch\":false,\"size\":\"middle\",\"loading\":false,\"bordered\":true,\"filterOption\":true,\"optionFilterProp\":\"value\",\"tokenSeparators\":[]},\"hidden\":false,\"title\":\"\",\"isLocked\":false,\"condition\":true,\"conditionGroup\":\"\"}]},{\"componentName\":\"Form.Item\",\"id\":\"node_oclwt1imqyc\",\"props\":{\"label\":\"表单项\",\"labelAlign\":\"right\",\"colon\":true,\"required\":false,\"noStyle\":false,\"valuePropName\":\"value\",\"name\":\"f\"},\"hidden\":false,\"title\":\"\",\"isLocked\":false,\"condition\":true,\"conditionGroup\":\"\",\"children\":[{\"componentName\":\"Slider\",\"id\":\"node_oclwt1imqyd\",\"props\":{\"defaultValue\":30,\"range\":false,\"disabled\":false,\"dots\":false,\"reverse\":false,\"vertical\":false},\"hidden\":false,\"title\":\"\",\"isLocked\":false,\"condition\":true,\"conditionGroup\":\"\"}]},{\"componentName\":\"Form.Item\",\"id\":\"node_oclwt1imqye\",\"props\":{\"label\":\"表单项\",\"name\":\"g\",\"initialValue\":\"A\",\"colon\":true,\"required\":false,\"noStyle\":false,\"valuePropName\":\"value\"},\"hidden\":false,\"title\":\"\",\"isLocked\":false,\"condition\":true,\"conditionGroup\":\"\",\"children\":[{\"componentName\":\"Checkbox.Group\",\"id\":\"node_oclwt1imqyf\",\"props\":{\"options\":[{\"label\":\"A\",\"value\":\"A\"},{\"label\":\"B\",\"value\":\"B\"},{\"label\":\"C\",\"value\":\"C\"}]},\"hidden\":false,\"title\":\"\",\"isLocked\":false,\"condition\":true,\"conditionGroup\":\"\"}]},{\"componentName\":\"Form.Item\",\"id\":\"node_oclwt1imqyg\",\"props\":{\"label\":\"表单项\",\"labelAlign\":\"right\",\"colon\":true,\"required\":false,\"noStyle\":false,\"valuePropName\":\"checked\",\"name\":\"i\"},\"hidden\":false,\"title\":\"\",\"isLocked\":false,\"condition\":true,\"conditionGroup\":\"\",\"children\":[{\"componentName\":\"Switch\",\"id\":\"node_oclwt1imqyh\",\"props\":{\"defaultChecked\":false,\"autoFocus\":false,\"disabled\":false,\"loading\":false,\"size\":\"default\"},\"hidden\":false,\"title\":\"\",\"isLocked\":false,\"condition\":true,\"conditionGroup\":\"\"}]},{\"componentName\":\"Form.Item\",\"id\":\"node_oclwt1imqyi\",\"props\":{\"label\":\"表单项\",\"labelAlign\":\"right\",\"colon\":true,\"required\":false,\"noStyle\":false,\"valuePropName\":\"value\",\"name\":\"j\"},\"hidden\":false,\"title\":\"\",\"isLocked\":false,\"condition\":true,\"conditionGroup\":\"\",\"children\":[{\"componentName\":\"TimePicker\",\"id\":\"node_oclwt1imqyj\",\"props\":{\"allowClear\":true,\"autoFocus\":false,\"bordered\":true,\"disabled\":false,\"hideDisabledOptions\":false,\"inputReadOnly\":false,\"use12Hours\":false},\"hidden\":false,\"title\":\"\",\"isLocked\":false,\"condition\":true,\"conditionGroup\":\"\"}]},{\"componentName\":\"Form.Item\",\"id\":\"node_oclwt1imqyk\",\"props\":{\"label\":\"表单项\",\"labelAlign\":\"right\",\"colon\":true,\"required\":false,\"noStyle\":false,\"valuePropName\":\"value\",\"name\":\"k\"},\"hidden\":false,\"title\":\"\",\"isLocked\":false,\"condition\":true,\"conditionGroup\":\"\",\"children\":[{\"componentName\":\"Rate\",\"id\":\"node_oclwt1imqyl\",\"props\":{\"defaultValue\":3,\"allowClear\":true,\"allowHalf\":false,\"autoFocus\":false,\"count\":5,\"disabled\":false,\"tooltips\":[]},\"hidden\":false,\"title\":\"\",\"isLocked\":false,\"condition\":true,\"conditionGroup\":\"\"}]},{\"componentName\":\"Form.Item\",\"id\":\"node_oclwt1imqym\",\"props\":{\"wrapperCol\":{\"offset\":6}},\"hidden\":false,\"title\":\"\",\"isLocked\":false,\"condition\":true,\"conditionGroup\":\"\",\"children\":[{\"componentName\":\"Button\",\"id\":\"node_oclwt1imqyn\",\"props\":{\"type\":\"primary\",\"children\":\"提交\",\"htmlType\":\"submit\"},\"hidden\":false,\"title\":\"\",\"isLocked\":false,\"condition\":true,\"conditionGroup\":\"\"},{\"componentName\":\"Button\",\"id\":\"node_oclwt1imqyo\",\"props\":{\"style\":{\"marginLeft\":20},\"children\":\"取消\"},\"hidden\":false,\"title\":\"\",\"isLocked\":false,\"condition\":true,\"conditionGroup\":\"\"}]}]}]}],\"i18n\":{}}";
        exists.setPageSchema(StringUtils.defaultIfBlank(pageVO.getSchema(),defaultSchema));
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
