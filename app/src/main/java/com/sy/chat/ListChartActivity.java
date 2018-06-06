package com.sy.chat;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.List;

public class ListChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_chart);

        RecyclerView mRecyclerView = findViewById(R.id.id_recycleview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL, R.drawable.item_divier));

        List<ChartItem> mChartItems = new ArrayList<>();

        int color1 = ContextCompat.getColor(this, android.R.color.holo_orange_light);
        int color2 = ContextCompat.getColor(this, android.R.color.holo_blue_light);
        int color3 = ContextCompat.getColor(this, android.R.color.holo_orange_light);
        int color4 = ContextCompat.getColor(this, android.R.color.holo_green_light);
        int color5 = ContextCompat.getColor(this, android.R.color.holo_red_light);
        int color6 = ContextCompat.getColor(this, android.R.color.holo_blue_dark);
        int color7 = ContextCompat.getColor(this, android.R.color.holo_purple);

        int colors[] = {color1, color2, color3, color4, color5, color6, color7};
        //1 2 3 4 5 6 7 8 9 10
        for (int i = 1; i <= 30; i++) {
            if (i % 4 == 1) {//饼图
                List<PieEntry> pieEntries = new ArrayList<>();
                String str[] = {"A", "B", "C", "D", "E"};

                for (int index = 0; index < 5; index++) {
                    pieEntries.add(new PieEntry((float) Math.random() * 50 + 5, "Pary" + str[index]));
                }
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
                PieData pieData = new PieData(pieDataSet);
                ChartItem chartItem = new ChartItem();
                chartItem.setViewType(ListChartAdapter.TYPE_PIECHART);
                chartItem.setmChartData(pieData);
                mChartItems.add(chartItem);
            } else if (i % 4 == 2) {//线
                List<Entry> entries = new ArrayList<>();
                for (int index = 1; index < 8; index++) {
                    entries.add(new Entry(index, (float) Math.random() * 100));
                }

                LineDataSet lineDataSet = new LineDataSet(entries, "增长比例");
                /*
                LINEAR,直线
                STEPPED,
                CUBIC_BEZIER,
                HORIZONTAL_BEZIER
                */
                lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);//设置折线图的显示模式，可以自行设置上面的值进行查看不同之处
                lineDataSet.setColor(colors[(int) (Math.random() * 6)]);//设置线的颜色
                lineDataSet.setLineWidth(1.5f);//设置线的宽度
                lineDataSet.setCircleColor(colors[(int) (Math.random() * 6)]);//设置圆圈的颜色
                lineDataSet.setCircleColorHole(colors[(int) (Math.random() * 6)]);//设置圆圈内部洞的颜色
                lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);//设置线数据依赖于左侧y轴
                lineDataSet.setDrawFilled(true);//设置不画数据覆盖的阴影层
                lineDataSet.setDrawValues(true);//不绘制线的数据
                lineDataSet.setValueTextColor(ContextCompat.getColor(this, R.color.text_color));//设置数据的文本颜色，如果不绘制线的数据 这句代码也不用设置了
                lineDataSet.setValueTextSize(14f);//如果不绘制线的数据 这句代码也不用设置了
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
                ChartItem chartItem = new ChartItem();
                chartItem.setViewType(ListChartAdapter.TYPE_LINECHART);
                chartItem.setmChartData(lineData);
                mChartItems.add(chartItem);
            } else if (i % 4 == 3) {//柱状
                List<BarEntry> barEntries = new ArrayList<>();
                for (int index = 1; index < 8; index++) {
                    barEntries.add(new BarEntry(index, (float) Math.random() * 100));
                }
                BarDataSet barDataSet = new BarDataSet(barEntries, "各班级语文平均成绩");
                barDataSet.setColors(colors);
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

                ChartItem chartItem = new ChartItem();
                chartItem.setViewType(ListChartAdapter.TYPE_BARCHART);
                chartItem.setmChartData(barData);
                mChartItems.add(chartItem);
            } else if (i % 4 == 0) {
                //组合图
                CombinedData data = new CombinedData();
                data.setData(generateBarData());
                data.setData(generateLineData());
                ChartItem chartItem = new ChartItem();
                chartItem.setViewType(ListChartAdapter.TYPE_COMBINEDCHART);
                chartItem.setmChartData(data);
                mChartItems.add(chartItem);

                //蜘蛛网图
//                List<RadarEntry> radarEntries = new ArrayList<>();
//
//                for (int index = 0; index < 7; index++) {
//                    radarEntries.add(new RadarEntry((int) (Math.random() * 100)));
//                }
//
//                List<RadarEntry> radarEntries2 = new ArrayList<>();
//                for (int index = 0; index < 7; index++) {
//                    radarEntries2.add(new RadarEntry((int) (Math.random() * 100)));
//                }
//
//                RadarDataSet radarDataSet = new RadarDataSet(radarEntries, "我的能力");
//                radarDataSet.setLineWidth(2);
//                radarDataSet.setDrawHighlightCircleEnabled(true);
//                radarDataSet.setValueTextSize(12);
//                radarDataSet.setColor(ContextCompat.getColor(this, android.R.color.holo_purple));
//                radarDataSet.setDrawFilled(true);
//                radarDataSet.setFillColor(ContextCompat.getColor(this, android.R.color.holo_purple));
//
//                RadarDataSet radarDataSet2 = new RadarDataSet(radarEntries2, "我的信用");
//                radarDataSet2.setColor(ContextCompat.getColor(this, android.R.color.holo_blue_bright));
//                radarDataSet2.setLineWidth(2);
//                radarDataSet2.setValueTextSize(12);
//                radarDataSet2.setDrawFilled(true);
//                radarDataSet2.setFillColor(ContextCompat.getColor(this, android.R.color.holo_blue_bright));
//
//                RadarData radarData = new RadarData(radarDataSet, radarDataSet2);
//                ChartItem chartItem = new ChartItem();
//                chartItem.setViewType(ListChartAdapter.TYPE_RADARCHART);
//                chartItem.setmChartData(radarData);
//                mChartItems.add(chartItem);
            }
        }
        ListChartAdapter listChartAdapter = new ListChartAdapter(this, mChartItems);
        mRecyclerView.setAdapter(listChartAdapter);


//        List<PieEntry> pieEntries = new ArrayList<>();
//        pieEntries.add(new PieEntry(9, "Pary A"));
//        pieEntries.add(new PieEntry(20, "Pary B"));
//        pieEntries.add(new PieEntry(25, "Pary C"));
//        pieEntries.add(new PieEntry(18, "Pary D"));
//        pieEntries.add(new PieEntry(39, "Pary E"));
//        PieDataSet pieDataSet = new PieDataSet(pieEntries, "饼图图例");
//        pieDataSet.setValueTextColor(ContextCompat.getColor(this, R.color.line3));
//        pieDataSet.setValueTextSize(14);
//        pieDataSet.setValueFormatter(new IValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
//                return (int) value + "%";
//            }
//        });
//        pieDataSet.setColors(ContextCompat.getColor(this, android.R.color.holo_purple),
//                ContextCompat.getColor(this, android.R.color.holo_blue_bright),
//                ContextCompat.getColor(this, android.R.color.holo_green_light),
//                ContextCompat.getColor(this, android.R.color.holo_orange_dark),
//                ContextCompat.getColor(this, android.R.color.holo_red_light));
//        pieDataSet.setSliceSpace(3);//设置每组数据之间的间隔
//        PieData pieData = new PieData(pieDataSet);
//        ChartItem chartItem1 = new ChartItem();
//        chartItem1.setViewType(ListChartAdapter.TYPE_PIECHART);
//        chartItem1.setmChartData(pieData);
//
//
//        List<Entry> entries = new ArrayList<>();
//        entries.add(new Entry(1, 13));
//        entries.add(new Entry(2, 35));
//        entries.add(new Entry(3, 68));
//        entries.add(new Entry(4, 58));
//        entries.add(new Entry(5, 73));
//        entries.add(new Entry(6, 42));
//        entries.add(new Entry(7, 21));
//
//        LineDataSet lineDataSet = new LineDataSet(entries, "增长比例");
//        /*
//            LINEAR,直线
//            STEPPED,
//            CUBIC_BEZIER,
//            HORIZONTAL_BEZIER
//         */
//        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);//设置折线图的显示模式，可以自行设置上面的值进行查看不同之处
//        lineDataSet.setColor(ContextCompat.getColor(this, android.R.color.holo_blue_light));//设置线的颜色
//        lineDataSet.setLineWidth(1.5f);//设置线的宽度
//        lineDataSet.setCircleColor(ContextCompat.getColor(this, android.R.color.holo_blue_light));//设置圆圈的颜色
//        lineDataSet.setCircleColorHole(ContextCompat.getColor(this, android.R.color.holo_blue_light));//设置圆圈内部洞的颜色
//        lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);//设置线数据依赖于左侧y轴
//        lineDataSet.setDrawFilled(true);//设置不画数据覆盖的阴影层
//        lineDataSet.setDrawValues(true);//不绘制线的数据
//        lineDataSet.setValueTextColor(ContextCompat.getColor(this, R.color.text_color));//设置数据的文本颜色，如果不绘制线的数据 这句代码也不用设置了
//        lineDataSet.setValueTextSize(14f);//如果不绘制线的数据 这句代码也不用设置了
//        lineDataSet.enableDashedHighlightLine(10f, 5f, 0f);//没看出来效果
//        lineDataSet.setFormLineWidth(10f);//只有lineDataSet.setForm(Legend.LegendForm.LINE);时才有作用 这里我们设置的是圆所以这句代码直接注释
//        lineDataSet.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));//设置虚线，只有lineDataSet.setForm(Legend.LegendForm.LINE);时才有作用
//        lineDataSet.setCircleRadius(4f);//设置每个折线点的大小
//        lineDataSet.setFormSize(15.f);//设置当前这条线的图例的大小
//        lineDataSet.setForm(Legend.LegendForm.LINE);//设置图例显示为线
//        lineDataSet.setValueFormatter(new IValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
//                int val = (int) value;
//                return val + "分";
//            }
//        });
//        LineData lineData = new LineData(lineDataSet);
//        ChartItem chartItem2 = new ChartItem();
//        chartItem2.setViewType(ListChartAdapter.TYPE_LINECHART);
//        chartItem2.setmChartData(lineData);
//
//        List<BarEntry> barEntries = new ArrayList<>();
//        barEntries.add(new BarEntry(1, 13));
//        barEntries.add(new BarEntry(2, 35));
//        barEntries.add(new BarEntry(3, 68));
//        barEntries.add(new BarEntry(4, 90));
//        barEntries.add(new BarEntry(5, 73));
//        barEntries.add(new BarEntry(6, 42));
//        barEntries.add(new BarEntry(7, 21));
//
//        BarDataSet barDataSet = new BarDataSet(barEntries, "各班级语文平均成绩");
//        barDataSet.setColor(ContextCompat.getColor(this, android.R.color.holo_orange_dark));
//        barDataSet.setDrawValues(true);
//        barDataSet.setValueTextSize(14);
//        barDataSet.setValueTextColor(ContextCompat.getColor(this, R.color.text_color));
//        barDataSet.setForm(Legend.LegendForm.SQUARE);
//        barDataSet.setValueFormatter(new IValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
//                int val = (int) value;
//                return val + "分";
//            }
//        });
//        barDataSet.setFormSize(15f);
//        BarData barData = new BarData(barDataSet);
//
//        ChartItem chartItem3 = new ChartItem();
//        chartItem3.setViewType(ListChartAdapter.TYPE_BARCHART);
//        chartItem3.setmChartData(barData);
//
//        mChartItems.add(chartItem1);
//        mChartItems.add(chartItem2);
//        mChartItems.add(chartItem3);


    }

    /**
     * 生成线图数据
     */
    private LineData generateLineData() {
        //数据可以从服务器获取之后动态的来设置，这里用死数据模拟
        ArrayList<Entry> values = new ArrayList<>();
        //从1开始 第0个不显示  1对应的是5月份 2对应的是6月份 一次类推
        //第一条线
        for (int index = 1; index < 8; index++) {
            values.add(new Entry(index, (float) Math.random() * 5));
        }

        //第二条线
        ArrayList<Entry> values1 = new ArrayList<>();
        for (int index = 1; index < 8; index++) {
            values1.add(new Entry(index, (float) Math.random() * 5));
        }

        //第三条线
        ArrayList<Entry> values2 = new ArrayList<>();
        for (int index = 1; index < 8; index++) {
            values2.add(new Entry(index, (float) Math.random() * 5));
        }

        //根据数据获取线的数据LineDataSet 进行折现的一些属性配置
        LineDataSet lineDataSet = new LineDataSet(values, "业绩环比");
        lineDataSet.setColor(ContextCompat.getColor(this, R.color.line1));//设置线的颜色
        lineDataSet.setLineWidth(1.5f);//设置线的宽度
        lineDataSet.setCircleColor(ContextCompat.getColor(this, R.color.line1));//设置圆圈的颜色
        lineDataSet.setCircleColorHole(ContextCompat.getColor(this, R.color.line1));//设置圆圈内部洞的颜色
        lineDataSet.setAxisDependency(YAxis.AxisDependency.RIGHT);//设置线数据依赖于右侧y轴
        lineDataSet.setDrawFilled(false);//设置不画数据覆盖的阴影层
        lineDataSet.setDrawValues(true);//不绘制线的数据

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
        lineDataSet1.setDrawValues(true);//不绘制线的数据
        lineDataSet1.setCircleRadius(4f);//设置每个折线点的大小
        lineDataSet1.setFormSize(15.f);//设置当前这条线的图例的大小
        lineDataSet1.setForm(Legend.LegendForm.CIRCLE);//设置图例显示为圆

        LineDataSet lineDataSet2 = new LineDataSet(values2, "折标率");
        lineDataSet2.setColor(ContextCompat.getColor(this, R.color.line3));//设置线的颜色
        lineDataSet2.setLineWidth(1.5f);//设置线的宽度
        lineDataSet2.setCircleColor(ContextCompat.getColor(this, R.color.line3));//设置圆圈的颜色
        lineDataSet2.setCircleColorHole(ContextCompat.getColor(this, R.color.line3));//设置圆圈内部洞的颜色
        lineDataSet2.setAxisDependency(YAxis.AxisDependency.RIGHT);//设置线数据依赖于右侧y轴
        lineDataSet2.setDrawValues(true);//不绘制线的数据
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
        for (int index = 1; index < 8; index++) {
            yVals1.add(new BarEntry(index, (int) (Math.random() * 100)));
        }
        BarDataSet barDataSet = new BarDataSet(yVals1, "规模业绩");
        barDataSet.setColor(ContextCompat.getColor(this, R.color.bar1));//设置柱状图的颜色
        barDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);//设置柱状图的数据依赖于左边的Y轴
        barDataSet.setDrawValues(true);//设置不显示数据
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
        for (int index = 1; index < 8; index++) {
            yVals2.add(new BarEntry(index, (int) (Math.random() * 100)));
        }
        BarDataSet barDataSet2 = new BarDataSet(yVals2, "年化业绩");
        barDataSet2.setColor(ContextCompat.getColor(this, R.color.bar2));//设置柱状图的颜色
        barDataSet2.setAxisDependency(YAxis.AxisDependency.LEFT);//设置柱状图的数据依赖于左边的Y轴
        barDataSet2.setDrawValues(true);//设置不显示数据
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
