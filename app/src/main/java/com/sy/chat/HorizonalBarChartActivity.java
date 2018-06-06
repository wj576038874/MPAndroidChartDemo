package com.sy.chat;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.List;

public class HorizonalBarChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizonal_bar_chart);

        HorizontalBarChart mBarChart = findViewById(R.id.horizonal_barChart);

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
//        l.setYOffset(0f);
//        l.setXOffset(10f);
//        l.setYEntrySpace(0f);
        l.setTextSize(14f);

        mBarChart.setDrawGridBackground(false);
        mBarChart.setDrawBarShadow(false);
        mBarChart.setDrawValueAboveBar(true);

        XAxis xAxis = mBarChart.getXAxis();
        xAxis.setAxisMaximum(8);
        xAxis.setAxisMinimum(0);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);
        //自定义X轴标签显示
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                int index = (int) value;
                String label;
                switch (index) {
                    case 1:
                        label = "一班";
                        break;
                    case 2:
                        label = "二班";
                        break;
                    case 3:
                        label = "三班";
                        break;
                    case 4:
                        label = "四班";
                        break;
                    case 5:
                        label = "五班";
                        break;
                    case 6:
                        label = "六班";
                        break;
                    case 7:
                        label = "七班";
                        break;
                    default:
                        label = "";
                        break;
                }
                return label;
            }
        });

        YAxis yl = mBarChart.getAxisLeft();
        yl.setDrawAxisLine(true);
        yl.setDrawGridLines(true);
        yl.setAxisMaximum(10);
        yl.setGranularity(10);
        yl.setAxisMinimum(0);

        YAxis yr = mBarChart.getAxisRight();
        yr.setDrawAxisLine(true);
        yr.setDrawGridLines(false);
        yl.setAxisMaximum(100);
        yl.setGranularity(10);
        yl.setAxisMinimum(0);


        List<BarEntry> values = new ArrayList<>();
        values.add(new BarEntry(1, 13));
        values.add(new BarEntry(2, 35));
        values.add(new BarEntry(3, 68));
        values.add(new BarEntry(4, 90));
        values.add(new BarEntry(5, 73));
        values.add(new BarEntry(6, 42));
        values.add(new BarEntry(7, 21));

        BarDataSet barDataSet = new BarDataSet(values, "各班级语文平均成绩");
        barDataSet.setColor(ContextCompat.getColor(this, android.R.color.holo_orange_dark));
        barDataSet.setDrawValues(true);
        barDataSet.setValueTextSize(14);
        barDataSet.setValueTextColor(ContextCompat.getColor(this, R.color.text_color));
        barDataSet.setForm(Legend.LegendForm.SQUARE);
        barDataSet.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                int val = (int) value;
                return val + "分";
            }
        });
        barDataSet.setFormSize(15f);
        BarData barData = new BarData(barDataSet);
        mBarChart.setData(barData);

        mBarChart.animateXY(3000,3000);

    }
}
