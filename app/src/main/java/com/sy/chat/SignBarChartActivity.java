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
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.List;

public class SignBarChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_bar_chart);

        BarChart mBarChart = findViewById(R.id.sign_bar_chart);

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
        l.setYOffset(10f);
        l.setXOffset(10f);
        l.setYEntrySpace(0f);
        l.setTextSize(14f);

        mBarChart.setDrawGridBackground(false);
        XAxis xAxis = mBarChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(7);
        xAxis.setAxisMaximum(8);
        xAxis.setAxisMinimum(0);
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
        //这里是个小bug如果设置了字体大小大于10 X轴标签会被遮挡一点点
//        xAxis.setTextSize(10);//设置标签文本大小
//        xAxis.setYOffset(10f);//设置标签距离图的距离
        xAxis.setTextColor(ContextCompat.getColor(this, R.color.text_color));//设置标签的文本颜色


        //配置Y轴的线
        LimitLine ll1 = new LimitLine(80f, "优秀");
        ll1.setLineWidth(3f);
        ll1.enableDashedLine(10f, 10f, 0f);
        ll1.setLineColor(ContextCompat.getColor(this, R.color.youxiu));
        ll1.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);//设置标签显示的位置
        ll1.setTextColor(ContextCompat.getColor(this, R.color.youxiu));
        ll1.setTextSize(10f);

        LimitLine ll2 = new LimitLine(60f, "及格");
        ll2.setLineWidth(3f);
        ll2.setLineColor(ContextCompat.getColor(this, android.R.color.holo_orange_light));
        ll2.enableDashedLine(10f, 10f, 0f);//虚线
        ll2.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);//设置标签显示的位置
        ll2.setTextColor(ContextCompat.getColor(this, android.R.color.holo_orange_light));
        ll2.setTextSize(10f);


        LimitLine ll3 = new LimitLine(25f, "差");
        ll3.setLineWidth(3f);
        ll3.enableDashedLine(10f, 10f, 0f);
        ll3.setLineColor(ContextCompat.getColor(this, android.R.color.holo_red_light));
        ll3.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);//设置标签显示的位置
        ll3.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_light));
        ll3.setTextSize(10f);

        YAxis leftAxis = mBarChart.getAxisLeft();
        leftAxis.setLabelCount(11);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setGranularity(10);
        leftAxis.setAxisMaximum(100);
        leftAxis.addLimitLine(ll1);
        leftAxis.addLimitLine(ll2);
        leftAxis.addLimitLine(ll3);
        leftAxis.setAxisMinimum(0); // this replaces setStartAtZero(true)

        mBarChart.getAxisRight().setEnabled(false);

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

        int color1 = ContextCompat.getColor(this, android.R.color.holo_orange_light);
        int color2 = ContextCompat.getColor(this, android.R.color.holo_blue_light);
        int color3 = ContextCompat.getColor(this, android.R.color.holo_orange_light);
        int color4 = ContextCompat.getColor(this, android.R.color.holo_green_light);
        int color5 = ContextCompat.getColor(this, android.R.color.holo_red_light);
        int color6 = ContextCompat.getColor(this, android.R.color.holo_blue_dark);
        int color7 = ContextCompat.getColor(this, android.R.color.holo_purple);

        barDataSet.setColors(color1, color2, color3, color4, color5, color6, color7);


        BarData barData = new BarData(barDataSet);

        mBarChart.setData(barData);

        mBarChart.animateXY(3000, 3000);
    }
}
