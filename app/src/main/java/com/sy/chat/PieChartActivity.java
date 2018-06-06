package com.sy.chat;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.ArrayList;
import java.util.List;

public class PieChartActivity extends AppCompatActivity {

    private PieChart mPieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

        mPieChart = findViewById(R.id.piechart);

        Description description = new Description();
        description.setText("饼图");
        description.setTextSize(14);
        description.setTextColor(ContextCompat.getColor(this, android.R.color.holo_purple));
        description.setPosition(200, 200);
        mPieChart.getDescription().setEnabled(true);
        mPieChart.setDescription(description);

        mPieChart.setCenterText("饼图中心文本");
        mPieChart.setDrawCenterText(true);
        mPieChart.setEntryLabelColor(ContextCompat.getColor(this, android.R.color.white));
        mPieChart.setCenterTextColor(ContextCompat.getColor(this, android.R.color.white));
//        mPieChart.setCenterTextRadiusPercent(1);
        mPieChart.setDrawHoleEnabled(true);//是否显示中心圈，默认显示
//        mPieChart.setDrawSlicesUnderHole(false);
        mPieChart.setHoleColor(ContextCompat.getColor(this, R.color.colorPrimary));//设置中心圈的颜色
        mPieChart.setHoleRadius(35);//设置中心圈的半径大小
//        mPieChart.setMaxAngle(10);//设置饼图的最大角度
        mPieChart.setTransparentCircleAlpha(80);//设置中心圈中外侧透明圈的透明度
        mPieChart.setTransparentCircleColor(ContextCompat.getColor(this, R.color.colorPrimary));//设置中心圈中外侧透明圈的颜色
        mPieChart.setTransparentCircleRadius(40);//设置中心圈透明圈的半径 这个要设置比setHoleRadius的值大不然没法显示


        Legend legend = mPieChart.getLegend();
        legend.setTextSize(14);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);

        List<PieEntry> pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(9, "Pary A"));
        pieEntries.add(new PieEntry(20, "Pary B"));
        pieEntries.add(new PieEntry(25, "Pary C"));
        pieEntries.add(new PieEntry(18, "Pary D"));
        pieEntries.add(new PieEntry(39, "Pary E"));

        PieDataSet pieDataSet = new PieDataSet(pieEntries, "饼图图例");
        pieDataSet.setValueTextColor(ContextCompat.getColor(this, R.color.line3));
        pieDataSet.setValueTextSize(14);
        pieDataSet.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return (int) value + "%";
            }
        });
        pieDataSet.setColors(ContextCompat.getColor(this, android.R.color.holo_purple),
                ContextCompat.getColor(this, android.R.color.holo_blue_bright),
                ContextCompat.getColor(this, android.R.color.holo_green_light),
                ContextCompat.getColor(this, android.R.color.holo_orange_dark),
                ContextCompat.getColor(this, android.R.color.holo_red_light));
        pieDataSet.setSliceSpace(3);//设置每组数据之间的间隔
//        pieDataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);//是否将值放在外面显示
//        pieDataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
//        pieDataSet.setAutomaticallyDisableSliceSpacing(false);
        pieDataSet.setSelectionShift(10);//设置选中饼图区域突出的长度
//        pieDataSet.setValueLineColor(ContextCompat.getColor(this, android.R.color.holo_red_light));
//        pieDataSet.setValueLinePart1Length(20);
        pieDataSet.setFormSize(15);
        pieDataSet.setForm(Legend.LegendForm.CIRCLE);

        PieData pieData = new PieData(pieDataSet);
        mPieChart.setData(pieData);

        /*
        Linear,
        EaseInQuad,
        EaseOutQuad,
        EaseInOutQuad,
        EaseInCubic,
        EaseOutCubic,
        EaseInOutCubic,
        EaseInQuart,
        EaseOutQuart,
        EaseInOutQuart,
        EaseInSine,
        EaseOutSine,
        EaseInOutSine,
        EaseInExpo,
        EaseOutExpo,
        EaseInOutExpo,
        EaseInCirc,
        EaseOutCirc,
        EaseInOutCirc,
        EaseInElastic,
        EaseOutElastic,
        EaseInOutElastic,
        EaseInBack,
        EaseOutBack,
        EaseInOutBack,
        EaseInBounce,
        EaseOutBounce,
        EaseInOutBounce,
         */
        mPieChart.animateY(1400, Easing.EasingOption.Linear);
//        mPieChart.animateXY(3000 , 3000 );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Linear:
                mPieChart.animateY(1400, Easing.EasingOption.Linear);
                break;
            case R.id.EaseInQuad:
                mPieChart.animateY(1400, Easing.EasingOption.EaseInQuad);
                break;
            case R.id.EaseOutQuad:
                mPieChart.animateY(1400, Easing.EasingOption.EaseOutQuad);
                break;
            case R.id.EaseInOutQuad:
                mPieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
                break;
            case R.id.EaseInCubic:
                mPieChart.animateY(1400, Easing.EasingOption.EaseInCubic);
                break;
            case R.id.EaseOutCubic:
                mPieChart.animateY(1400, Easing.EasingOption.EaseOutCubic);
                break;
            case R.id.EaseInOutCubic:
                mPieChart.animateY(1400, Easing.EasingOption.EaseInOutCubic);
                break;
            case R.id.EaseInQuart:
                mPieChart.animateY(1400, Easing.EasingOption.EaseInQuart);
                break;
            case R.id.EaseOutQuart:
                mPieChart.animateY(1400, Easing.EasingOption.EaseOutQuart);
                break;
            case R.id.EaseInOutQuart:
                mPieChart.animateY(1400, Easing.EasingOption.EaseInOutQuart);
                break;
            case R.id.EaseInSine:
                mPieChart.animateY(1400, Easing.EasingOption.EaseInSine);
                break;
            case R.id.EaseOutSine:
                mPieChart.animateY(1400, Easing.EasingOption.EaseOutSine);
                break;
            case R.id.EaseInOutSine:
                mPieChart.animateY(1400, Easing.EasingOption.EaseInOutSine);
                break;
            case R.id.EaseInExpo:
                mPieChart.animateY(1400, Easing.EasingOption.EaseInExpo);
                break;
            case R.id.EaseOutExpo:
                mPieChart.animateY(1400, Easing.EasingOption.EaseOutExpo);
                break;
            case R.id.EaseInOutExpo:
                mPieChart.animateY(1400, Easing.EasingOption.EaseInOutExpo);
                break;
            case R.id.EaseInCirc:
                mPieChart.animateY(1400, Easing.EasingOption.EaseInCirc);
                break;
            case R.id.EaseOutCirc:
                mPieChart.animateY(1400, Easing.EasingOption.EaseOutCirc);
                break;
            case R.id.EaseInOutCirc:
                mPieChart.animateY(1400, Easing.EasingOption.EaseInOutCirc);
                break;
            case R.id.EaseInElastic:
                mPieChart.animateY(1400, Easing.EasingOption.EaseInElastic);
                break;
            case R.id.EaseOutElastic:
                mPieChart.animateY(1400, Easing.EasingOption.EaseOutElastic);
                break;
            case R.id.EaseInOutElastic:
                mPieChart.animateY(1400, Easing.EasingOption.EaseInOutElastic);
                break;
            case R.id.EaseInBack:
                mPieChart.animateY(1400, Easing.EasingOption.EaseInBack);
                break;
            case R.id.EaseOutBack:
                mPieChart.animateY(1400, Easing.EasingOption.EaseOutBack);
                break;
            case R.id.EaseInOutBack:
                mPieChart.animateY(1400, Easing.EasingOption.EaseInOutBack);
                break;
            case R.id.EaseInBounce:
                mPieChart.animateY(1400, Easing.EasingOption.EaseInBounce);
                break;
            case R.id.EaseOutBounce:
                mPieChart.animateY(1400, Easing.EasingOption.EaseOutBounce);
                break;
            case R.id.EaseInOutBounce:
                mPieChart.animateY(1400, Easing.EasingOption.EaseInOutBounce);
                break;
        }
        mPieChart.invalidate();
        return true;
    }


}
