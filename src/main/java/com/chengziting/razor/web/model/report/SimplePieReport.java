package com.chengziting.razor.web.model.report;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2018/5/30.
 */
public class SimplePieReport implements IReport {
    //<editor-folder desc="inner classes">
    static class Title {
        public static final int TITLE_ALIGN_LEFT = 0;
        public static final int TITLE_ALIGN_CENTER = 1;
        public static final int TITLE_ALIGN_RIGHT = 2;
        private String text;
        private String subtext;
        /**
         * alignment of text:left,center,right,default center
         */
        private String x = "center";

        public Title(String text, String subText, int alignment) {
            this.text = text;
            this.subtext = subText;
            switch (alignment) {
                case 0:
                    x = "left";
                    break;
                case 1:
                    x = "center";
                    break;
                case 2:
                    x = "right";
                    break;
                default:
                    x = "center";
                    break;
            }
        }

        public String getText() {
            return text;
        }

        public String getSubText() {
            return subtext;
        }

        public String getX() {
            return x;
        }
    }

    static class ToolTip {
        private String trigger = "item";
        private String formatter = "{a} <br/>{b} : {c} ({d}%)";

        public String getTrigger() {
            return trigger;
        }

        public void setTrigger(String trigger) {
            this.trigger = trigger;
        }
    }

    static class Legend {
        private String orient;
        private String left;
        private String[] data;

        public Legend(String[] data, boolean isLeft, boolean isOrient) {
            this.data = data;
            this.left = isLeft ? "left" : "right";
            this.orient = isOrient ? "vertical" : "horizontal";
        }

        public String getOrient() {
            return orient;
        }

        public String getLeft() {
            return left;
        }

        public String[] getData() {
            return data;
        }
    }

    static class Series{
        static class LegendData{
            private int value;
            private String name;
            public LegendData(int value,String name){
                this.value = value;
                this.name = name;
            }
        }
        private String name;
        private String type = "pie";
        private String radius = "55%";
        private String[]center = {"50%", "60%"};
        private String itemStyle="itemStyle: {emphasis: {shadowBlur: 10,shadowOffsetX: 0,shadowColor: 'rgba(0, 0, 0, 0.5)'}}";
        private List<LegendData> data;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<LegendData> getData() {
            return data;
        }

        public void setData(List<LegendData> data) {
            this.data = data;
        }

        public void addData(LegendData data){
            this.data.add(data);
        }
    }

    //</editor-folder>

    private Title title;
    private ToolTip tooltip;
    private Legend legend;
    private List<Series> series = new ArrayList<Series>();

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public ToolTip getTooltip() {
        return tooltip;
    }

    public void setTooltip(ToolTip tooltip) {
        this.tooltip = tooltip;
    }

    public Legend getLegend() {
        return legend;
    }

    public void setLegend(Legend legend) {
        this.legend = legend;
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

    @Override
    public String toString() {
        return new Gson().toJson(this,SimplePieReport.class);
    }

    @Override
    public String getTestString() {
        SimplePieReport pieReport = new SimplePieReport();

        SimplePieReport.Title title = new SimplePieReport.Title("某站点用户访问来源","PieDemo",2);
        SimplePieReport.ToolTip toolTip = new SimplePieReport.ToolTip();
        SimplePieReport.Legend legend = new SimplePieReport.Legend(new String[]{"baidu","jingdong","alibaba","tencent","google"},true,true);
        SimplePieReport.Series series1 = new SimplePieReport.Series();
        series1.setName("市值");

        List<SimplePieReport.Series.LegendData> legendDatas = new ArrayList<SimplePieReport.Series.LegendData>();
        legendDatas.add(new SimplePieReport.Series.LegendData(100,"baidu"));
        legendDatas.add(new SimplePieReport.Series.LegendData(300,"jingdong"));
        legendDatas.add(new SimplePieReport.Series.LegendData(400,"alibaba"));
        legendDatas.add(new SimplePieReport.Series.LegendData(600,"tencent"));
        legendDatas.add(new SimplePieReport.Series.LegendData(1000,"google"));
        series1.setData(legendDatas);

        pieReport.setTitle(title);
        pieReport.setTooltip(toolTip);
        pieReport.setLegend(legend);
        pieReport.addSeries(series1);

        return new Gson().toJson(pieReport,SimplePieReport.class);
    }
}
