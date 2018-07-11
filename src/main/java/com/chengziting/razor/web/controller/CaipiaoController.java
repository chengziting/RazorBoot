package com.chengziting.razor.web.controller;

import com.chengziting.razor.core.annotations.WithoutAuthorize;
import com.chengziting.razor.utils.http.HttpUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by user on 2018/6/19.
 */
@Controller
@WithoutAuthorize
@CrossOrigin(methods = {RequestMethod.POST,RequestMethod.GET},origins = "*")
@RequestMapping("caipiao")
public class CaipiaoController extends BaseController {

    @RequestMapping("ssq")
    public ModelAndView sjq(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("/caipiao/ssq");
//        String result = HttpUtils.instance().get("http://f.apiplus.net/ssq-10.json");
        String result = HttpUtils.instance().get(String.format("http://%s:%s/caipiao/gettest",request.getServerName(),request.getServerPort()));
        mv.addObject("data",result);
        return mv;
    }

    @RequestMapping("gettest")
    @ResponseBody
    public String getTestData(HttpServletRequest request){
        String str = "{'rows':10,'code':'ssq','info':'免费接口随机延迟3-6分钟，实时接口请访问www.opencai.net查询、购买或续费','data':[{'expect':'2018073','opencode':'02,09,14,15,16,23+10','opentime':'2018-06-26 21:18:20','opentimestamp':1530019100},{'expect':'2018072','opencode':'11,16,19,22,25,30+08','opentime':'2018-06-24 21:18:20','opentimestamp':1529846300},{'expect':'2018071','opencode':'02,05,06,13,16,19+03','opentime':'2018-06-21 21:18:20','opentimestamp':1529587100},{'expect':'2018070','opencode':'04,09,15,16,19,27+10','opentime':'2018-06-19 21:18:20','opentimestamp':1529414300},{'expect':'2018069','opencode':'06,13,17,19,23,31+12','opentime':'2018-06-17 21:18:20','opentimestamp':1529241500},{'expect':'2018068','opencode':'08,10,17,20,27,30+01','opentime':'2018-06-14 21:18:20','opentimestamp':1528982300},{'expect':'2018067','opencode':'01,04,09,12,15,18+05','opentime':'2018-06-12 21:18:20','opentimestamp':1528809500},{'expect':'2018066','opencode':'09,16,17,19,22,26+10','opentime':'2018-06-10 21:18:20','opentimestamp':1528636700},{'expect':'2018065','opencode':'01,04,06,14,28,33+01','opentime':'2018-06-07 21:18:20','opentimestamp':1528377500},{'expect':'2018064','opencode':'02,05,10,11,17,21+05','opentime':'2018-06-05 21:18:20','opentimestamp':1528204700}]}";
        return str;
    }

    @RequestMapping("testcros")
    @ResponseBody
    @CrossOrigin(origins = {"http://f.apiplus.net"})
    public String testCROS(HttpServletResponse response){
        String str = HttpUtils.instance().get("http://f.apiplus.net/ssq-10.json");

        return str;

    }
}
