package com.sy.chat;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class RadarCahrtActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radar_cahrt);

        RadarChart mRadarChart = findViewById(R.id.radarchart);

        mRadarChart.setWebLineWidth(1.5f);//设置基线的宽度 默认是1.5
        mRadarChart.setWebLineWidthInner(1.5f);//设置环线的宽度 默认是1.5
        mRadarChart.setDrawWeb(true);//是否画网格线
        mRadarChart.setSkipWebLineCount(0);//间隔几条画一次线默认为0全部画出来
        mRadarChart.setWebAlpha(200);//设置网格线的透明度 默认为150
        mRadarChart.setWebColor(ContextCompat.getColor(this ,android.R.color.holo_purple));//设置基线的颜色
        mRadarChart.setWebColorInner(ContextCompat.getColor(this , android.R.color.holo_orange_dark));//设置环线的颜色


        Legend l = mRadarChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(5f);
        l.setTextColor(Color.BLUE);
        l.setTextSize(14);
        l.setForm(Legend.LegendForm.CIRCLE);
        l.setFormSize(15);

        XAxis xAxis = mRadarChart.getXAxis();
        xAxis.setAxisMaximum(7);
        xAxis.setAxisMinimum(0);
        xAxis.setGranularity(1);
        xAxis.setTextSize(12);
//        xAxis.setGranularityEnabled(true);
//        xAxis.setDrawAxisLine(false);
//        xAxis.setDrawGridLines(false);
//        xAxis.setAvoidFirstLastClipping(true);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            private String[] mActivities = new String[]{"击杀", "生存", "助攻", "物理", "魔法", "防御", "金钱"};

            @Override
            public String getFormattedValue(float value, AxisBase axis) {

                return mActivities[(int) value % mActivities.length];
            }
        });

        YAxis yAxis = mRadarChart.getYAxis();
        yAxis.setAxisMinimum(0);
        yAxis.setLabelCount(5);
        yAxis.setAxisMaximum(80);

        List<RadarEntry> radarEntries = new ArrayList<>();

        radarEntries.add(new RadarEntry(82));
        radarEntries.add(new RadarEntry(70));
        radarEntries.add(new RadarEntry(45));
        radarEntries.add(new RadarEntry(88));
        radarEntries.add(new RadarEntry(66));
        radarEntries.add(new RadarEntry(31));
        radarEntries.add(new RadarEntry(79));

        List<RadarEntry> radarEntries2 = new ArrayList<>();

        radarEntries2.add(new RadarEntry(60));
        radarEntries2.add(new RadarEntry(70));
        radarEntries2.add(new RadarEntry(80));
        radarEntries2.add(new RadarEntry(100));
        radarEntries2.add(new RadarEntry(90));
        radarEntries2.add(new RadarEntry(50));
        radarEntries2.add(new RadarEntry(65));


        RadarDataSet radarDataSet = new RadarDataSet(radarEntries, "我的能力");
        radarDataSet.setLineWidth(2);
        radarDataSet.setDrawHighlightCircleEnabled(true);
        radarDataSet.setValueTextSize(12);
        radarDataSet.setColor(ContextCompat.getColor(this , android.R.color.holo_purple));
        radarDataSet.setDrawFilled(true);
        radarDataSet.setFillColor(ContextCompat.getColor(this , android.R.color.holo_purple));

        RadarDataSet radarDataSet2 = new RadarDataSet(radarEntries2 , "我的信用");
        radarDataSet2.setColor(ContextCompat.getColor(this , android.R.color.holo_blue_bright));
        radarDataSet2.setLineWidth(2);
        radarDataSet2.setValueTextSize(12);
        radarDataSet2.setDrawFilled(true);
        radarDataSet2.setFillColor(ContextCompat.getColor(this , android.R.color.holo_blue_bright));

        RadarData radarData = new RadarData(radarDataSet,radarDataSet2);

        mRadarChart.setData(radarData);

        mRadarChart.animateX(2000 , Easing.EasingOption.Linear);
    }
}
