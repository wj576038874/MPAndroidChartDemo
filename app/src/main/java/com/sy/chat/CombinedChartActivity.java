package com.sy.chat;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class CombinedChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combined_chart);

        CombinedChart mCombinedChart = findViewById(R.id.combinedChart);

        mCombinedChart.getDescription().setEnabled(false);//设置描述
        mCombinedChart.setPinchZoom(true);//设置按比例放缩柱状图

        //设置绘制顺序，让折线图在柱状图的上层
        mCombinedChart.setDrawOrder(new CombinedChart.DrawOrder[]{
                CombinedChart.DrawOrder.BAR, CombinedChart.DrawOrder.LINE
        });

        //图例配置
        Legend l = mCombinedChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);//设置显示在顶部
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);//设置图例的显示位置为居中
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);//设置图例显示的布局为横线排列
        l.setDrawInside(true);
        l.setYOffset(5f);//设置图例基于Y轴的偏移量 如果不写的话图例的颜色区域会被遮挡一点点
        l.setDirection(Legend.LegendDirection.LEFT_TO_RIGHT);//这里是这是图例的颜色和文本的显示顺序，LEFT_TO_RIGHT表示颜色在左，文本在右
        l.setXOffset(0f);//设置图例基于X轴的偏移量 我们需要它居中所以写0即可
//        l.setYEntrySpace(0f);//设置每个图例之间的Y轴的间距  我们是横线的只有X轴间距没有Y轴，所以不需要设置这个
        l.setXEntrySpace(10f);//设置每个图例之间的X轴的间距 设置为10 或者0都可以
        l.setTextSize(10f);//设置图例文本的字体大小，根据界面显示自行调配

        //x坐标轴设置
        XAxis xAxis = mCombinedChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//X轴的显示位置为最下面
        xAxis.setDrawGridLines(false);//是否画X轴的网格线。格局UI显示不需要画，这里设置为false
        xAxis.setAxisMaximum(8);//设置X的最大值8
        xAxis.setAxisMinimum(0);//设置X轴的最小值0
        xAxis.setLabelCount(7);//设置要显示的标签的个数 只有5 6 7 8 9 10 11 月 只有7个所以设置为7 可以根据服务器数据返回的动态设置
        xAxis.setGranularity(1);//设置X轴的评分值
//        xAxis.setCenterAxisLabels(true);
        //设置X轴根据索引进行标签的转换讲索引替换现实为我们想要的05月06月07月.....前后会多加一个空字符串用来间隔
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                int month = (int) value;
                String str = null;
                switch (month) {
                    case 0:
                        str = "";
                        break;
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
                    case 8:
                        str = "";
                        break;
                }
                return str;
            }
        });

        //左边y轴设置
        YAxis leftAxis = mCombinedChart.getAxisLeft();
        //设置左边Y轴的显示位置最外侧
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        //设置不画Y轴的网格线
        leftAxis.setDrawGridLines(true);
        //设置左边Y轴最小值
        leftAxis.setAxisMinimum(0);
        //设置左边Y轴最大值
        leftAxis.setAxisMaximum(110);
        //设置左边Y轴的标签数
        leftAxis.setLabelCount(12);
        //设置左边Y轴的平分数值  这里有一个公式  最大值-最小值=评分数*标签个数
        leftAxis.setGranularity(10);
        leftAxis.setDrawAxisLine(false);//是否画左边第一条Y轴线
        leftAxis.setDrawLabels(true);//设置显示左边标签
        leftAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                String str;
                switch ((int) value) {
                    case 110:
                        str = "金额(万元)";
                        axis.setGridColor(ContextCompat.getColor(CombinedChartActivity.this , android.R.color.transparent));
                        break;
                    default:
                        str = value + "";
                        axis.setGridColor(ContextCompat.getColor(CombinedChartActivity.this , R.color.line));
                        break;
                }
                return str;
            }
        });


        //右边Y轴设置
        YAxis rightAxis = mCombinedChart.getAxisRight();
        //设置显示最外侧
        rightAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        //设置不画Y轴网格线
        rightAxis.setDrawGridLines(true);
        //根据数据显示右边Y轴最小值为-1.5
        rightAxis.setAxisMinimum(-1.5f);
        //根据数据显示右边Y轴最大值为3.5
        rightAxis.setAxisMaximum(4.0f);
        //设置表签数为11
        rightAxis.setLabelCount(11);
        //设置评分数0.5以05位一个单位进行划分 这里有一个公式  最大值-最小值=评分数*标签个数
        rightAxis.setGranularity(0.5f);
        rightAxis.setDrawAxisLine(false);//是否画右边第一条Y轴线
        rightAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                String str;
                switch ((int) (value*10)) {
                    case 40:
                        str = "折标 指数";
                        break;
                    default:
                        str = value + "";
                        break;
                }
                return str;
            }
        });



        //设置组合图数据
        CombinedData data = new CombinedData();
        data.setData(generateBarData());
        data.setData(generateLineData());

        mCombinedChart.setData(data);//设置组合图数据源

        //使得两侧柱子完全显示
        xAxis.setAxisMinimum(0);
        xAxis.setAxisMaximum(8);
        xAxis.setLabelCount(7, false);

        //距离顶部的偏移量
        mCombinedChart.setExtraTopOffset(30);
        //距离底部的偏移量
        mCombinedChart.setExtraBottomOffset(10);
        //设置动画显示时长
        mCombinedChart.animateXY(3000,3000);
        mCombinedChart.invalidate();
    }

    /**
     * 生成线图数据
     */
    private LineData generateLineData() {
        //数据可以从服务器获取之后动态的来设置，这里用死数据模拟
        ArrayList<Entry> values = new ArrayList<>();
        //从1开始 第0个不显示  1对应的是5月份 2对应的是6月份 一次类推
        //第一条线
        values.add(new Entry(1, -0.7f));//05月份  -0.7折标指数
        values.add(new Entry(2, 0.3f));//06月份  0.3折标指数
        values.add(new Entry(3, 2.1f));//07月份 2.1折标指数
        values.add(new Entry(4, 3.0f));//08月份  3.0折标指数
        values.add(new Entry(5, 2.3f));//09月份  2.3折标指数
        values.add(new Entry(6, 0.7f));//10月份  0.7折标指数
        values.add(new Entry(7, -0.7f));//11月份  -0.7折标指数

        //第二条线
        ArrayList<Entry> values1 = new ArrayList<>();
        values1.add(new Entry(1, -0.4f));//05月份  -0.4折标指数
        values1.add(new Entry(2, 0.7f));//06月份  0.7折标指数
        values1.add(new Entry(3, 1.9f));//07月份  1.9折标指数
        values1.add(new Entry(4, 2.8f));//08月份  2.8折标指数
        values1.add(new Entry(5, 2.7f));//09月份  2.7折标指数
        values1.add(new Entry(6, 1.4f));//10月份  1.4折标指数
        values1.add(new Entry(7, 0.3f));//11月份  0.3折标指数

        //第三条线
        ArrayList<Entry> values2 = new ArrayList<>();
        values2.add(new Entry(1, 0.3f));//05月份  0.3折标指数
        values2.add(new Entry(2, 1.3f));//06月份  1.3折标指数
        values2.add(new Entry(3, 2.5f));//07月份  2.5折标指数
        values2.add(new Entry(4, 3.2f));//08月份  3.2折标指数
        values2.add(new Entry(5, 2.9f));//09月份  2.9折标指数
        values2.add(new Entry(6, 1.8f));//10月份  1.8折标指数
        values2.add(new Entry(7, 0.7f));//11月份  0.7折标指数

        //根据数据获取线的数据LineDataSet 进行折现的一些属性配置
        LineDataSet lineDataSet = new LineDataSet(values, "业绩环比");
        lineDataSet.setColor(ContextCompat.getColor(this, R.color.line1));//设置线的颜色
        lineDataSet.setLineWidth(1.5f);//设置线的宽度
        lineDataSet.setCircleColor(ContextCompat.getColor(this, R.color.line1));//设置圆圈的颜色
        lineDataSet.setCircleColorHole(ContextCompat.getColor(this, R.color.line1));//设置圆圈内部洞的颜色
        lineDataSet.setAxisDependency(YAxis.AxisDependency.RIGHT);//设置线数据依赖于右侧y轴
        lineDataSet.setDrawFilled(false);//设置不画数据覆盖的阴影层
        lineDataSet.setDrawValues(false);//不绘制线的数据

//        lineDataSet.setValueTextColor(ContextCompat.getColor(this, R.color.colorAccent));//设置数据的文本颜色，如果不绘制线的数据 这句代码也不用设置了
//        lineDataSet.setValueTextSize(15f);//如果不绘制线的数据 这句代码也不用设置了
//        lineDataSet.enableDashedHighlightLine(10f, 5f, 0f);没看出来效果
//        lineDataSet.setFormLineWidth(10f);只有lineDataSet.setForm(Legend.LegendForm.LINE);时才有作用 这里我们设置的是圆所以这句代码直接注释
//        lineDataSet.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));//设置虚线，只有lineDataSet.setForm(Legend.LegendForm.LINE);时才有作用
        lineDataSet.setCircleRadius(4f);//设置每个折线点的大小
        lineDataSet.setFormSize(15.f);//设置当前这条线的图例的大小
        lineDataSet.setForm(Legend.LegendForm.CIRCLE);//设置图例显示为圆


        LineDataSet lineDataSet1 = new LineDataSet(values1, "业绩同比");
        lineDataSet1.setColor(ContextCompat.getColor(this, R.color.line2));//设置线的颜色
        lineDataSet1.setLineWidth(1.5f);//设置线的宽度
        lineDataSet1.setCircleColor(ContextCompat.getColor(this, R.color.line2));//设置圆圈的颜色
        lineDataSet1.setCircleColorHole(ContextCompat.getColor(this, R.color.line2));//设置圆圈内部洞的颜色
        lineDataSet1.setAxisDependency(YAxis.AxisDependency.RIGHT);//设置线数据依赖于右侧y轴
        lineDataSet1.setDrawValues(false);//不绘制线的数据
        lineDataSet1.setCircleRadius(4f);//设置每个折线点的大小
        lineDataSet1.setFormSize(15.f);//设置当前这条线的图例的大小
        lineDataSet1.setForm(Legend.LegendForm.CIRCLE);//设置图例显示为圆

        LineDataSet lineDataSet2 = new LineDataSet(values2, "折标率");
        lineDataSet2.setColor(ContextCompat.getColor(this, R.color.line3));//设置线的颜色
        lineDataSet2.setLineWidth(1.5f);//设置线的宽度
        lineDataSet2.setCircleColor(ContextCompat.getColor(this, R.color.line3));//设置圆圈的颜色
        lineDataSet2.setCircleColorHole(ContextCompat.getColor(this, R.color.line3));//设置圆圈内部洞的颜色
        lineDataSet2.setAxisDependency(YAxis.AxisDependency.RIGHT);//设置线数据依赖于右侧y轴
        lineDataSet2.setDrawValues(false);//不绘制线的数据
        lineDataSet2.setCircleRadius(4f);//设置每个折线点的大小
        lineDataSet2.setFormSize(15.f);//设置当前这条线的图例的大小
        lineDataSet2.setForm(Legend.LegendForm.CIRCLE);//设置图例显示为圆

        //组合数据
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        //将三条线的数据都添加数据集
        dataSets.add(lineDataSet);
        dataSets.add(lineDataSet1);
        dataSets.add(lineDataSet2);
        //创建一个数据集的数据对象返回
        return new LineData(dataSets);
    }

    /**
     * 生成柱图数据
     */
    private BarData generateBarData() {
        //模拟数据
        //初始化一个集合来装第一个柱状图的数据
        ArrayList<BarEntry> yVals1 = new ArrayList<>();
        //从1开始 1对应的是5月 2对应的是6月以此类推
        yVals1.add(new BarEntry(1, 10));//5月份 10万元
        yVals1.add(new BarEntry(2, 30));//6月份 30万元
        yVals1.add(new BarEntry(3, 45));//7月份 45万元
        yVals1.add(new BarEntry(4, 72));//8月份 72万元
        yVals1.add(new BarEntry(5, 74));//9月份 74万元
        yVals1.add(new BarEntry(6, 32));//10月份 32万元
        yVals1.add(new BarEntry(7, 12));//11月份 12万元

        BarDataSet barDataSet = new BarDataSet(yVals1, "规模业绩");
        barDataSet.setColor(ContextCompat.getColor(this, R.color.bar1));//设置柱状图的颜色
        barDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);//设置柱状图的数据依赖于左边的Y轴
        barDataSet.setDrawValues(false);//设置不显示数据
        barDataSet.setValueTextColor(Color.rgb(159, 143, 186));//设置数据的文本颜色
        barDataSet.setValueTextSize(15f);//设置数据显示的文字大小
        barDataSet.setFormSize(15.f);//设置图例大小
        barDataSet.setForm(Legend.LegendForm.CIRCLE);

//        BarData barData = new BarData(barDataSet);
//        barData.setBarWidth(0.9f);
        //设置格式化数据显示根据数据的值自定义显示
//        barData.setValueFormatter(new IValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, Entry entry, int i, ViewPortHandler viewPortHandler) {
//                String str;
//                switch ((int) value) {
//                    case 10:
//                        str = "asdasd";
//                        break;
//                    default:
//                        str = "1111";
//                        break;
//                }
//                return str;
//            }
//        });

        ArrayList<BarEntry> yVals2 = new ArrayList<>();
        yVals2.add(new BarEntry(1, 12));//5月份 12万元
        yVals2.add(new BarEntry(2, 20));//6月份 20万元
        yVals2.add(new BarEntry(3, 33));//7月份 33万元
        yVals2.add(new BarEntry(4, 79));//8月份 79万元
        yVals2.add(new BarEntry(5, 62));//9月份 62万元
        yVals2.add(new BarEntry(6, 32));//10月份 32万元
        yVals2.add(new BarEntry(7, 12));//11月份 12万元
        BarDataSet barDataSet2 = new BarDataSet(yVals2, "年化业绩");
        barDataSet2.setColor(ContextCompat.getColor(this, R.color.bar2));//设置柱状图的颜色
        barDataSet2.setAxisDependency(YAxis.AxisDependency.LEFT);//设置柱状图的数据依赖于左边的Y轴
        barDataSet2.setDrawValues(false);//设置不显示数据
        barDataSet2.setValueTextColor(Color.rgb(159, 143, 186));//设置数据显示的颜色
        barDataSet2.setFormSize(15.f);//设置图例大小
        barDataSet2.setForm(Legend.LegendForm.CIRCLE);

        //设置格式化数据显示根据数据的值自定义显示
//        BarData barData2 = new BarData(barDataSet2);
//        barData2.setValueFormatter(new IValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, Entry entry, int i, ViewPortHandler viewPortHandler) {
//                return "";
//            }
//        });

        //  一个公式 (barWidth+barSpace)*2+groupSpace=1即可  替换对应的值则是  (0.3 + 0.06) * 2 + 0.28 = 1.00
        float groupSpace = 0.28f;//每个柱状图组之间的间距
        float barSpace = 0.06f; // 每组柱状图之间的间距
        float barWidth = 0.3f; // 每个柱状图的宽度
        BarData bd = new BarData(barDataSet, barDataSet2);
        bd.setBarWidth(barWidth);
        //这里是设置柱状图分组从第几个x轴显示 可以是小数  这里是从0.5开始  这样柱状图分组就可以居中显示了
        bd.groupBars(0.5f, groupSpace, barSpace); // start at x = 0
        return bd;
    }
}
