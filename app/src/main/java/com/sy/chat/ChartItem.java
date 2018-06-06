package com.sy.chat;

import com.github.mikephil.charting.data.ChartData;

/**
 * ProjectName：chatdemo
 * PackageName：com.sy.chat
 * Author：wenjie
 * Date：2018-06-06 15:37
 * Description：
 */
public class ChartItem {


    private ChartData<?> mChartData;

    private int viewType;

    ChartItem() {
    }

    public ChartItem(ChartData<?> mChartData, int viewType) {
        this.mChartData = mChartData;
        this.viewType = viewType;
    }

    public ChartData<?> getmChartData() {
        return mChartData;
    }

    public void setmChartData(ChartData<?> mChartData) {
        this.mChartData = mChartData;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }
}
