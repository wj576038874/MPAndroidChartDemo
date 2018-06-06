package com.sy.chat;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;

public class MultiBarChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);

        BarChart mBarChart = findViewById(R.id.BarChart);

        mBarChart.setDrawBarShadow(false);
        mBarChart.setDrawValueAboveBar(true);

        mBarChart.getDescription().setEnabled(false);

        mBarChart.setMaxVisibleValueCount(60);
        mBarChart.setPinchZoom(false);

        Legend l = mBarChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(true);
        l.setYOffset(0f);
        l.setXOffset(10f);
        l.setYEntrySpace(0f);
        l.setTextSize(8f);

        mBarChart.setDrawGridBackground(false);
        XAxis xAxis = mBarChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f); // only intervals of 1 day
        xAxis.setLabelCount(7);
        xAxis.setAxisMinimum(0);
        xAxis.setAxisMaximum(9);
        xAxis.setDrawLabels(true);
        xAxis.setCenterAxisLabels(true);
        IAxisValueFormatter xAxisFormatter = new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                int month = (int) value;
                String str;
                switch (month) {
                    case 1:
                        str = "05月";
                        break;
                    case 2:
                        str = "06月";
                        break;
                    case 3:
                        str = "07月";
                        break;
                    case 4:
                        str = "08月";
                        break;
                    case 5:
                        str = "09月";
                        break;
                    case 6:
                        str = "10月";
                        break;
                    case 7:
                        str = "11月";
                        break;
                    default:
                        str = "";
                        break;
                }
                return str;
            }
        };
        xAxis.setValueFormatter(xAxisFormatter);


        //配置Y轴的线
        LimitLine ll1 = new LimitLine(80f, "优秀");
        ll1.setLineWidth(3f);
        ll1.enableDashedLine(10f, 10f, 0f);
        ll1.setLineColor(ContextCompat.getColor(this, R.color.youxiu));
        ll1.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);//设置标签显示的位置
        ll1.setTextColor(ContextCompat.getColor(this , R.color.youxiu));
        ll1.setTextSize(10f);

        LimitLine ll2 = new LimitLine(60f, "及格");
        ll2.setLineWidth(3f);
        ll2.setLineColor(ContextCompat.getColor(this, android.R.color.holo_orange_light));
        ll2.enableDashedLine(10f, 10f, 0f);//虚线
        ll2.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);//设置标签显示的位置
        ll2.setTextColor(ContextCompat.getColor(this ,  android.R.color.holo_orange_light));
        ll2.setTextSize(10f);


        LimitLine ll3 = new LimitLine(25f, "差");
        ll3.setLineWidth(3f);
        ll3.enableDashedLine(10f, 10f, 0f);
        ll3.setLineColor(ContextCompat.getColor(this, android.R.color.holo_red_light));
        ll3.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);//设置标签显示的位置
        ll3.setTextColor(ContextCompat.getColor(this ,  android.R.color.holo_red_light));
        ll3.setTextSize(10f);

        YAxis leftAxis = mBarChart.getAxisLeft();
        leftAxis.addLimitLine(ll1);
        leftAxis.addLimitLine(ll2);
        leftAxis.addLimitLine(ll3);
        leftAxis.setLabelCount(11);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setGranularity(10);
        leftAxis.setAxisMaximum(100);
        leftAxis.setAxisMinimum(0);
        leftAxis.setDrawAxisLine(false);

        mBarChart.getAxisRight().setEnabled(false);


        float groupSpace = 0.16f;
        float barSpace = 0.08f; // x4 DataSet
        float barWidth = 0.2f; // x4 DataSet
        // (0.2 + 0.08) * 3 + 0.16 = 1.00 -> interval per "group"

        ArrayList<BarEntry> yVals1 = new ArrayList<>();
        yVals1.add(new BarEntry(1, 10));
        yVals1.add(new BarEntry(2, 30));
        yVals1.add(new BarEntry(3, 45));
        yVals1.add(new BarEntry(4, 72));
        yVals1.add(new BarEntry(5, 74));
        yVals1.add(new BarEntry(6, 32));
        yVals1.add(new BarEntry(7, 12));

        ArrayList<BarEntry> yVals2 = new ArrayList<>();
        yVals2.add(new BarEntry(1, 12));
        yVals2.add(new BarEntry(2, 20));
        yVals2.add(new BarEntry(3, 33));
        yVals2.add(new BarEntry(4, 79));
        yVals2.add(new BarEntry(5, 62));
        yVals2.add(new BarEntry(6, 23));
        yVals2.add(new BarEntry(7, 18));

        ArrayList<BarEntry> yVals3 = new ArrayList<>();
        yVals3.add(new BarEntry(1, 8));
        yVals3.add(new BarEntry(2, 18));
        yVals3.add(new BarEntry(3, 40));
        yVals3.add(new BarEntry(4, 85));
        yVals3.add(new BarEntry(5, 56));
        yVals3.add(new BarEntry(6, 29));
        yVals3.add(new BarEntry(7, 15));

        BarDataSet set1, set2,set3;

        if (mBarChart.getData() != null &&
                mBarChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) mBarChart.getData().getDataSetByIndex(0);
            set2 = (BarDataSet) mBarChart.getData().getDataSetByIndex(1);
            set1.setValues(yVals1);
            set2.setValues(yVals2);
            mBarChart.getData().notifyDataChanged();
            mBarChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "2017年增长");
            set2 = new BarDataSet(yVals2, "2018年增长");
            set3 = new BarDataSet(yVals3 , "2015年增长");

            set1.setColor(ContextCompat.getColor(this , android.R.color.holo_green_light));
            set2.setColor(ContextCompat.getColor(this , android.R.color.holo_blue_bright));
            set3.setColor(ContextCompat.getColor(this , android.R.color.holo_purple));

            BarData data = new BarData(set1, set2,set3);
            data.setValueTextSize(10f);
            mBarChart.setData(data);

            mBarChart.getBarData().setBarWidth(barWidth);
            mBarChart.getXAxis().setAxisMinimum(1);
            mBarChart.getXAxis().setAxisMaximum(8);
            mBarChart.groupBars(1, groupSpace, barSpace);
            mBarChart.invalidate();
        }

        mBarChart.animateXY(3000,3000);
    }
}
