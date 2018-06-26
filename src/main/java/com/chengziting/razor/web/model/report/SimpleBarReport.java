package com.chengziting.razor.web.model.report;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2018/5/30.
 */
public class SimpleBarReport extends CoordinateReport implements IReport{
    @Override
    public String getTestString() {
        String []categories = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        SimpleBarReport.XAxis xAxis = new SimpleBarReport.XAxis(categories);
        SimpleBarReport.YAxis yAxis = new SimpleBarReport.YAxis();
        SimpleBarReport.Series series1 = new SimpleBarReport.Series(new int[]{120, 200, 150, 80, 70, 110, 130},CoordinateReport.REPORT_TYPE_COORDINATE_BAR);
        //SimpleBarReport.Series series2 = new SimpleBarReport.Series(new int[]{100, 200, 50, 80, 170, 100, 130});

        SimpleBarReport barReport = new SimpleBarReport();
        barReport.setxAxis(xAxis);
        barReport.setyAxis(yAxis);
        barReport.addSeries(series1);
        //barReport.addSeries(series2);

        return new Gson().toJson(barReport,SimpleBarReport.class);
    }

    @Override
    public String toString() {
        return new Gson().toJson(this,SimpleBarReport.class);
    }
}
