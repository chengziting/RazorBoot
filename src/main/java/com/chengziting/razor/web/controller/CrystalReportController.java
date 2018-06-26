package com.chengziting.razor.web.controller;

import com.chengziting.razor.core.annotations.WithoutAuthorize;
import com.chengziting.razor.web.model.report.SimpleBarReport;
import com.chengziting.razor.web.model.report.SimplePieReport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by user on 2018/5/30.
 */
@RequestMapping("report")
@Controller
@WithoutAuthorize
public class CrystalReportController extends BaseController{

    @RequestMapping("barreport")
    public String barReport(){
        return "/report/barreport";
    }

    @RequestMapping("piereport")
    public String pieReport(){
        return "/report/piereport";
    }

    @RequestMapping(value = "getBarReportData")
    @ResponseBody
    public String getBarReportData(){
        SimpleBarReport barReport = new SimpleBarReport();
        return barReport.getTestString();
    }

    @RequestMapping(value = "getPieReportData")
    @ResponseBody
    public String getPieReportData(){
        SimplePieReport pieReport = new SimplePieReport();
        return pieReport.getTestString();
    }
}
