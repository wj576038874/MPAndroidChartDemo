package com.sy.chat;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.List;

/**
 * ProjectName：chatdemo
 * PackageName：com.sy.chat
 * Author：wenjie
 * Date：2018-06-06 15:32
 * Description：
 */
public class ListChartAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_PIECHART = 1;
    public static final int TYPE_LINECHART = 2;
    public static final int TYPE_BARCHART = 3;
    public static final int TYPE_RADARCHART = 4;
    public static final int TYPE_COMBINEDCHART = 5;

    private Context mContext;
    private List<ChartItem> mChartItems;


    ListChartAdapter(Context mContext, List<ChartItem> mChartItems) {
        this.mContext = mContext;
        this.mChartItems = mChartItems;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(mContext);//获取mInflater对象
        Legend legend;

        //配置Y轴的线
        LimitLine ll1 = new LimitLine(80f, "优秀");
        ll1.setLineWidth(3f);
        ll1.enableDashedLine(10f, 10f, 0f);
        ll1.setLineColor(ContextCompat.getColor(mContext, R.color.youxiu));
        ll1.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);//设置标签显示的位置
        ll1.setTextColor(ContextCompat.getColor(mContext, R.color.youxiu));
        ll1.setTextSize(10f);

        LimitLine ll2 = new LimitLine(60f, "及格");
        ll2.setLineWidth(3f);
        ll2.setLineColor(ContextCompat.getColor(mContext, android.R.color.holo_orange_light));
        ll2.enableDashedLine(10f, 10f, 0f);//虚线
        ll2.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);//设置标签显示的位置
        ll2.setTextColor(ContextCompat.getColor(mContext, android.R.color.holo_orange_light));
        ll2.setTextSize(10f);

        LimitLine ll3 = new LimitLine(25f, "差");
        ll3.setLineWidth(3f);
        ll3.enableDashedLine(10f, 10f, 0f);
        ll3.setLineColor(ContextCompat.getColor(mContext, android.R.color.holo_red_light));
        ll3.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);//设置标签显示的位置
        ll3.setTextColor(ContextCompat.getColor(mContext, android.R.color.holo_red_light));
        ll3.setTextSize(10f);
        switch (viewType) {//根据viewtyupe来区分，是什么图
            case TYPE_PIECHART:
                //饼图
                PieChartHolder pieChartHolder = new PieChartHolder(mInflater.inflate(R.layout.item_pie, parent, false));
                PieChart mPieChart = pieChartHolder.pieChart;
                Description description = new Description();
                description.setText("饼图");
                description.setTextSize(14);
                description.setTextColor(ContextCompat.getColor(mContext, android.R.color.holo_purple));
                description.setPosition(200, 200);
                mPieChart.getDescription().setEnabled(true);
                mPieChart.setDescription(description);

                mPieChart.setCenterText("饼图中心文本");
                mPieChart.setDrawCenterText(true);
                mPieChart.setEntryLabelColor(ContextCompat.getColor(mContext, android.R.color.white));
                mPieChart.setCenterTextColor(ContextCompat.getColor(mContext, android.R.color.white));
//                mPieChart.setCenterTextRadiusPercent(1);
                mPieChart.setDrawHoleEnabled(true);//是否显示中心圈，默认显示
//                mPieChart.setDrawSlicesUnderHole(false);
                mPieChart.setHoleColor(ContextCompat.getColor(mContext, R.color.colorPrimary));//设置中心圈的颜色
                mPieChart.setHoleRadius(35);//设置中心圈的半径大小
//                mPieChart.setMaxAngle(10);//设置饼图的最大角度
                mPieChart.setTransparentCircleAlpha(80);//设置中心圈中外侧透明圈的透明度
                mPieChart.setTransparentCircleColor(ContextCompat.getColor(mContext, R.color.colorPrimary));//设置中心圈中外侧透明圈的颜色
                mPieChart.setTransparentCircleRadius(40);//设置中心圈透明圈的半径 这个要设置比setHoleRadius的值大不然没法显示

                legend = pieChartHolder.pieChart.getLegend();
                legend.setTextSize(14);
                legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
                legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
                legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
                return pieChartHolder;
            case TYPE_LINECHART:
                //线图
                LineChartHolder lineChartHolder = new LineChartHolder(mInflater.inflate(R.layout.item_line, parent, false));
                //设置点击折线图的圆点时弹出view
                MyMarkerView mv = new MyMarkerView(mContext, R.layout.markerview);
                mv.setChartView(lineChartHolder.lineChart);
                lineChartHolder.lineChart.setMarker(mv);

                legend = lineChartHolder.lineChart.getLegend();
                legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);//设置显示在顶部
                legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);//设置图例的显示位置为居中
                legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);//设置图例显示的布局为横线排列
                legend.setTextSize(14f);//设置图例文本的字体大小，根据界面显示自行调配

                XAxis xAxis = lineChartHolder.lineChart.getXAxis();
                xAxis.setAxisMaximum(7);
                xAxis.setAxisMinimum(1);
                xAxis.setGranularity(1);
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
//                xAxis.setTextSize(12);//设置标签文本大小
//                xAxis.setYOffset(10f);//设置标签距离图的距离
                xAxis.setTextColor(ContextCompat.getColor(mContext, R.color.text_color));//设置标签的文本颜色

                //配置左边的Y轴
                YAxis leftAxis = lineChartHolder.lineChart.getAxisLeft();
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
                leftAxis.setTextColor(ContextCompat.getColor(mContext, R.color.text_color));

                lineChartHolder.lineChart.getAxisRight().setEnabled(false);//设置右侧Y轴不显示
                return lineChartHolder;
            case TYPE_BARCHART:
                //柱状图
                BarChartHolder barChartHolder = new BarChartHolder(mInflater.inflate(R.layout.item_bar, parent, false));
                legend = barChartHolder.barChart.getLegend();
                legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
                legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
                legend.setOrientation(Legend.LegendOrientation.VERTICAL);
                legend.setDrawInside(true);
                legend.setTextSize(14f);

                BarChart mBarChart = barChartHolder.barChart;

                mBarChart.setDrawGridBackground(false);
                xAxis = mBarChart.getXAxis();
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                xAxis.setDrawGridLines(false);
                xAxis.setGranularity(1f);
                xAxis.setLabelCount(7);
                xAxis.setAxisMaximum(8);
                xAxis.setAxisMinimum(0);
                xAxis.setDrawLabels(true);
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
//                xAxis.setTextSize(12);//设置标签文本大小
//                xAxis.setYOffset(10f);//设置标签距离图的距离
                xAxis.setTextColor(ContextCompat.getColor(mContext, R.color.text_color));//设置标签的文本颜色

                leftAxis = mBarChart.getAxisLeft();
                leftAxis.setLabelCount(11);
                leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
                leftAxis.setGranularity(10);
                leftAxis.setAxisMaximum(100);
                leftAxis.addLimitLine(ll1);
                leftAxis.addLimitLine(ll2);
                leftAxis.addLimitLine(ll3);
                leftAxis.setAxisMinimum(0); // this replaces setStartAtZero(true)
                mBarChart.getAxisRight().setEnabled(false);
                return barChartHolder;
            case TYPE_RADARCHART:
                RadarChartHolder radarChartHolder = new RadarChartHolder(mInflater.inflate(R.layout.item_radar, parent, false));
                RadarChart mRadarChart = radarChartHolder.radarChart;

                mRadarChart.setWebLineWidth(1.5f);//设置基线的宽度 默认是1.5
                mRadarChart.setWebLineWidthInner(1.5f);//设置环线的宽度 默认是1.5
                mRadarChart.setDrawWeb(true);//是否画网格线
                mRadarChart.setSkipWebLineCount(0);//间隔几条画一次线默认为0全部画出来
                mRadarChart.setWebAlpha(200);//设置网格线的透明度 默认为150
                mRadarChart.setWebColor(ContextCompat.getColor(mContext, android.R.color.holo_purple));//设置基线的颜色
                mRadarChart.setWebColorInner(ContextCompat.getColor(mContext, android.R.color.holo_orange_dark));//设置环线的颜色

                legend = mRadarChart.getLegend();
                legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
                legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
                legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
                legend.setDrawInside(false);
                legend.setXEntrySpace(7f);
                legend.setYEntrySpace(5f);
                legend.setTextColor(Color.BLUE);
                legend.setTextSize(14);
                legend.setForm(Legend.LegendForm.CIRCLE);
                legend.setFormSize(15);

                xAxis = mRadarChart.getXAxis();
                xAxis.setAxisMaximum(7);
                xAxis.setAxisMinimum(0);
                xAxis.setGranularity(1);
                xAxis.setTextSize(12);
//                xAxis.setGranularityEnabled(true);
//                xAxis.setDrawAxisLine(false);
//                xAxis.setDrawGridLines(false);
//                xAxis.setAvoidFirstLastClipping(true);
                xAxis.setValueFormatter(new IAxisValueFormatter() {
                    private String[] mActivities = new String[]{"击杀", "生存", "助攻", "物理", "魔法", "防御", "金钱"};

                    @Override
                    public String getFormattedValue(float value, AxisBase axis) {
                        return mActivities[(int) value % mActivities.length];
                    }
                });
                YAxis yAxis = mRadarChart.getYAxis();
                yAxis.resetAxisMaximum();
                yAxis.resetAxisMinimum();
                yAxis.setAxisMinimum(0);
                yAxis.setLabelCount(5);
                yAxis.setAxisMaximum(80);
                return radarChartHolder;
            case TYPE_COMBINEDCHART:
                CombinedChartHolder combinedChartHolder = new CombinedChartHolder(mInflater.inflate(R.layout.item_combined, parent, false));
                CombinedChart mCombinedChart = combinedChartHolder.combinedChart;
                mCombinedChart.getDescription().setEnabled(false);//设置描述
                mCombinedChart.setPinchZoom(true);//设置按比例放缩柱状图

                //设置绘制顺序，让折线图在柱状图的上层
                mCombinedChart.setDrawOrder(new CombinedChart.DrawOrder[]{
                        CombinedChart.DrawOrder.BAR, CombinedChart.DrawOrder.LINE
                });

                //图例配置
                legend = mCombinedChart.getLegend();
                legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);//设置显示在顶部
                legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);//设置图例的显示位置为居中
                legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);//设置图例显示的布局为横线排列
                legend.setDrawInside(true);
                legend.setYOffset(5f);//设置图例基于Y轴的偏移量 如果不写的话图例的颜色区域会被遮挡一点点
                legend.setDirection(Legend.LegendDirection.LEFT_TO_RIGHT);//这里是这是图例的颜色和文本的显示顺序，LEFT_TO_RIGHT表示颜色在左，文本在右
                legend.setXOffset(0f);//设置图例基于X轴的偏移量 我们需要它居中所以写0即可
//                legend.setYEntrySpace(0f);//设置每个图例之间的Y轴的间距  我们是横线的只有X轴间距没有Y轴，所以不需要设置这个
                legend.setXEntrySpace(10f);//设置每个图例之间的X轴的间距 设置为10 或者0都可以
                legend.setTextSize(10f);//设置图例文本的字体大小，根据界面显示自行调配

                //x坐标轴设置
                xAxis = mCombinedChart.getXAxis();
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
                leftAxis = mCombinedChart.getAxisLeft();
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
                                axis.setGridColor(ContextCompat.getColor(mContext, android.R.color.transparent));
                                break;
                            default:
                                str = value + "";
                                axis.setGridColor(ContextCompat.getColor(mContext, R.color.line));
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
                rightAxis.setAxisMinimum(0);
                //根据数据显示右边Y轴最大值为3.5
                rightAxis.setAxisMaximum(5.5f);
                //设置表签数为11
                rightAxis.setLabelCount(11);
                //设置评分数0.5以0.5位一个单位进行划分 这里有一个公式  最大值-最小值=评分数*标签个数
                rightAxis.setGranularity(0.5f);
                rightAxis.setDrawAxisLine(false);//是否画右边第一条Y轴线
                rightAxis.setValueFormatter(new IAxisValueFormatter() {
                    @Override
                    public String getFormattedValue(float value, AxisBase axis) {
                        String str;
                        switch ((int) (value * 10)) {
                            case 55:
                                str = "折标 指数";
                                break;
                            default:
                                str = value + "";
                                break;
                        }
                        return str;
                    }
                });
                return combinedChartHolder;
            default:
                //这里的代码是不会执行的,为了去除警告
                return new RecyclerView.ViewHolder(new View(mContext)) {
                    @Override
                    public String toString() {
                        return super.toString();
                    }
                };
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case TYPE_PIECHART:
                PieChartHolder pieChartHolder = (PieChartHolder) holder;
                pieChartHolder.pieChart.setData((PieData) mChartItems.get(position).getmChartData());
                pieChartHolder.pieChart.animateY(2000, Easing.EasingOption.Linear);
                break;
            case TYPE_LINECHART:
                LineChartHolder lineChartHolder = (LineChartHolder) holder;
                lineChartHolder.lineChart.setData((LineData) mChartItems.get(position).getmChartData());
                lineChartHolder.lineChart.animateXY(2000, 2000);
                break;
            case TYPE_BARCHART:
                BarChartHolder barChartHolder = (BarChartHolder) holder;
                barChartHolder.barChart.setData((BarData) mChartItems.get(position).getmChartData());
                barChartHolder.barChart.animateXY(2000, 2000);
                break;
            case TYPE_RADARCHART:
                RadarChartHolder radarChartHolder = (RadarChartHolder) holder;
                radarChartHolder.radarChart.setData((RadarData) mChartItems.get(position).getmChartData());
                radarChartHolder.radarChart.animateX(2000, Easing.EasingOption.Linear);
                break;
            case TYPE_COMBINEDCHART:
                CombinedChartHolder combinedChartHolder = (CombinedChartHolder) holder;
                combinedChartHolder.combinedChart.setData((CombinedData) mChartItems.get(position).getmChartData());
                //使得两侧柱子完全显示
                combinedChartHolder.combinedChart.getXAxis().setAxisMinimum(0);
                combinedChartHolder.combinedChart.getXAxis().setAxisMaximum(8);
                combinedChartHolder.combinedChart.getXAxis().setLabelCount(7, false);

                //距离顶部的偏移量
                combinedChartHolder.combinedChart.setExtraTopOffset(30);
                //距离底部的偏移量
                combinedChartHolder.combinedChart.setExtraBottomOffset(10);
                combinedChartHolder.combinedChart.animateXY(3000, 3000);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mChartItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        switch (mChartItems.get(position).getViewType()) {
            case TYPE_PIECHART:
                return TYPE_PIECHART;
            case TYPE_LINECHART:
                return TYPE_LINECHART;
            case TYPE_BARCHART:
                return TYPE_BARCHART;
            case TYPE_RADARCHART:
                return TYPE_RADARCHART;
            case TYPE_COMBINEDCHART:
                return TYPE_COMBINEDCHART;
        }
        return 0;
    }

    class PieChartHolder extends RecyclerView.ViewHolder {

        PieChart pieChart;

        PieChartHolder(View itemView) {
            super(itemView);
            pieChart = itemView.findViewById(R.id.item_piechart);
        }
    }


    class LineChartHolder extends RecyclerView.ViewHolder {

        LineChart lineChart;

        LineChartHolder(View itemView) {
            super(itemView);
            lineChart = itemView.findViewById(R.id.item_linechart);
        }
    }

    class BarChartHolder extends RecyclerView.ViewHolder {

        BarChart barChart;

        BarChartHolder(View itemView) {
            super(itemView);
            barChart = itemView.findViewById(R.id.item_barchart);
        }
    }

    class RadarChartHolder extends RecyclerView.ViewHolder {

        RadarChart radarChart;

        RadarChartHolder(View itemView) {
            super(itemView);
            radarChart = itemView.findViewById(R.id.item_radarchart);
        }
    }

    class CombinedChartHolder extends RecyclerView.ViewHolder {

        CombinedChart combinedChart;

        public CombinedChartHolder(View itemView) {
            super(itemView);
            combinedChart = itemView.findViewById(R.id.item_combined);
        }
    }


}
