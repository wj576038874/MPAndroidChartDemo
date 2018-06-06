package com.sy.chat;

import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.List;

public class SignLineChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_line_chart);

        LineChart mLineChart = findViewById(R.id.sign_line_chart);

        //设置描述
        mLineChart.getDescription().setEnabled(true);
        Description description = new Description();
        description.setText("单折线图");
        description.setTextAlign(Paint.Align.CENTER);
        description.setTextSize(16);
        description.setPosition(200, 150);
        description.setTextColor(ContextCompat.getColor(this, android.R.color.holo_blue_bright));
        mLineChart.setDescription(description);

        //设置是否后台绘制
        mLineChart.setDrawGridBackground(false);
        //设置支持触控手势
        mLineChart.setTouchEnabled(true);
        //设置缩放
        mLineChart.setDragEnabled(true);
        //设置推动
        mLineChart.setScaleEnabled(true);
        //如果禁用,扩展可以在x轴和y轴分别完成
        mLineChart.setPinchZoom(true);

        //设置点击折线图的圆点时弹出view
        MyMarkerView mv = new MyMarkerView(this, R.layout.markerview);
        mv.setChartView(mLineChart);
        mLineChart.setMarker(mv);

        //设置图例
        Legend legend = mLineChart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);//设置显示在顶部
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);//设置图例的显示位置为居中
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);//设置图例显示的布局为横线排列
        legend.setTextSize(10f);//设置图例文本的字体大小，根据界面显示自行调配

        //配置X轴线
//        LimitLine llXAxis = new LimitLine(5, "Index 10");
//        llXAxis.setLineWidth(4f);
//        llXAxis.enableDashedLine(10f, 10f, 0f);
//        llXAxis.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
//        llXAxis.setTextSize(10f);


        //配置X轴
        XAxis xAxis = mLineChart.getXAxis();
//        xAxis.enableGridDashedLine(10f, 10f, 0f);
        xAxis.setAxisMaximum(7);
        xAxis.setAxisMinimum(1);
        xAxis.setGranularity(1);
//        xAxis.addLimitLine(llXAxis);//添加X轴线
        xAxis.setLabelCount(7);//设置标签个数
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); //设置x标签显示的在底部
        xAxis.setDrawGridLines(false);//不画X轴网格线
        xAxis.setDrawAxisLine(true);
        //自定义X轴标签显示
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                int index = (int) value;
                String label;
                switch (index) {
                    case 1:
                        label = "语文";
                        break;
                    case 2:
                        label = "数学";
                        break;
                    case 3:
                        label = "英语";
                        break;
                    case 4:
                        label = "物理";
                        break;
                    case 5:
                        label = "化学";
                        break;
                    case 6:
                        label = "政治";
                        break;
                    case 7:
                        label = "体育";
                        break;
                    default:
                        label = "";
                        break;
                }
                return label;
            }
        });
//        xAxis.setTextSize(12);//设置标签文本大小
//        xAxis.setYOffset(10f);//设置标签距离图的距离
        xAxis.setTextColor(ContextCompat.getColor(this, R.color.text_color));//设置标签的文本颜色

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


        //配置左边的Y轴
        YAxis leftAxis = mLineChart.getAxisLeft();
        leftAxis.removeAllLimitLines();
        leftAxis.removeAllLimitLines();
        leftAxis.addLimitLine(ll1);//将配置好的Y轴的线添加到图表中
        leftAxis.addLimitLine(ll2);//将配置好的Y轴的线添加到图表中
        leftAxis.addLimitLine(ll3);//将配置好的Y轴的线添加到图表中
        leftAxis.setAxisMaximum(100);//设置最大值
        leftAxis.setAxisMinimum(0f);//设置最小值
        leftAxis.setGranularity(10);//设置以10各单位平分
        leftAxis.setLabelCount(11);//设置标签的个数
        leftAxis.setDrawAxisLine(false);//设置不画左边线的第一条线
        //自定义标签显示
        leftAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                int val = (int) value;
                return val + "分";
            }
        });
        //设置标签文本颜色
        leftAxis.setTextColor(ContextCompat.getColor(this, R.color.text_color));

        mLineChart.getAxisRight().setEnabled(false);//设置右侧Y轴不显示

        //设置数据
        List<Entry> values = new ArrayList<>();
        values.add(new Entry(1, 13));
        values.add(new Entry(2, 35));
        values.add(new Entry(3, 68));
        values.add(new Entry(4, 58));
        values.add(new Entry(5, 73));
        values.add(new Entry(6, 42));
        values.add(new Entry(7, 21));

        LineDataSet lineDataSet = new LineDataSet(values, "增长比例");
        /*
            LINEAR,直线
            STEPPED,
            CUBIC_BEZIER,
            HORIZONTAL_BEZIER
         */
        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);//设置折线图的显示模式，可以自行设置上面的值进行查看不同之处
        lineDataSet.setColor(ContextCompat.getColor(this, android.R.color.holo_blue_light));//设置线的颜色
        lineDataSet.setLineWidth(1.5f);//设置线的宽度
        lineDataSet.setCircleColor(ContextCompat.getColor(this, android.R.color.holo_blue_light));//设置圆圈的颜色
        lineDataSet.setCircleColorHole(ContextCompat.getColor(this, android.R.color.holo_blue_light));//设置圆圈内部洞的颜色
        lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);//设置线数据依赖于左侧y轴
        lineDataSet.setDrawFilled(true);//设置不画数据覆盖的阴影层
        lineDataSet.setDrawValues(true);//不绘制线的数据
        lineDataSet.setValueTextColor(ContextCompat.getColor(this, R.color.text_color));//设置数据的文本颜色，如果不绘制线的数据 这句代码也不用设置了
        lineDataSet.setValueTextSize(15f);//如果不绘制线的数据 这句代码也不用设置了
        lineDataSet.enableDashedHighlightLine(10f, 5f, 0f);//没看出来效果
        lineDataSet.setFormLineWidth(10f);//只有lineDataSet.setForm(Legend.LegendForm.LINE);时才有作用 这里我们设置的是圆所以这句代码直接注释
        lineDataSet.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));//设置虚线，只有lineDataSet.setForm(Legend.LegendForm.LINE);时才有作用
        lineDataSet.setCircleRadius(4f);//设置每个折线点的大小
        lineDataSet.setFormSize(15.f);//设置当前这条线的图例的大小
        lineDataSet.setForm(Legend.LegendForm.LINE);//设置图例显示为线
        lineDataSet.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                int val = (int) value;
                return val + "分";
            }
        });



        LineData lineData = new LineData(lineDataSet);

        mLineChart.setData(lineData);

        //默认动画
        mLineChart.animateXY(3000, 3000);

    }
}
