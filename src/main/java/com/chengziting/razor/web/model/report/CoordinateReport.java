package com.chengziting.razor.web.model.report;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2018/5/30.
 */
public abstract class CoordinateReport implements IReport{
    static final int REPORT_TYPE_COORDINATE_LINE = 0;
    static final int REPORT_TYPE_COORDINATE_BAR  = 1;
    //<editor-folder desc="inner class">
    static class XAxis{
        private String type;
        private String []data;
        public XAxis(String []categories){
            this.data = categories;
            this.type = "category";
        }

        public String getType() {
            return type;
        }

        public String[] getData() {
            return data;
        }
    }
    static class YAxis{
        private String type;
        public YAxis(){
            this.type = "value";
        }
        public String getType(){
            return this.type;
        }
    }
    static class Series{
        private String type;
        private int []data;
        public Series(int []data,int type){
            this.data = data;
            switch (type){
                case CoordinateReport.REPORT_TYPE_COORDINATE_LINE:
                    this.type = "line";
                    break;
                case CoordinateReport.REPORT_TYPE_COORDINATE_BAR:
                    this.type = "bar";
                    break;
                default:
                    this.type = "bar";
                    break;
            }
        }

        public String getType() {
            return type;
        }

        public int[] getData() {
            return data;
        }
    }
    //</editor-folder>

    protected XAxis xAxis;
    protected YAxis yAxis;
    protected List<Series> series = new ArrayList<Series>();

    public XAxis getxAxis() {
        return xAxis;
    }

    public void setxAxis(XAxis xAxis) {
        this.xAxis = xAxis;
    }

    public YAxis getyAxis() {
        return yAxis;
    }

    public void setyAxis(YAxis yAxis) {
        this.yAxis = yAxis;
    }

    public List<Series> getSeries() {
        return series;
    }

    public void setSeries(List<Series> series) {
        this.series = series;
    }

    public void addSeries(Series series){
        this.series.add(series);
    }

}