package com.sy.chat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_piechart).setOnClickListener(this);
        findViewById(R.id.btn_signBarChart).setOnClickListener(this);
        findViewById(R.id.btn_signBarChart2).setOnClickListener(this);
        findViewById(R.id.btn_signLineChart).setOnClickListener(this);
        findViewById(R.id.btn_multiBarChart).setOnClickListener(this);
        findViewById(R.id.btn_multiLineChart).setOnClickListener(this);
        findViewById(R.id.btn_leida).setOnClickListener(this);
        findViewById(R.id.btn_combined).setOnClickListener(this);
        findViewById(R.id.btn_listchart).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_piechart:
                startActivity(new Intent(this, PieChartActivity.class));
                break;
            case R.id.btn_signBarChart:
                startActivity(new Intent(this, HorizonalBarChartActivity.class));
                break;
            case R.id.btn_signBarChart2:
                startActivity(new Intent(this, SignBarChartActivity.class));
                break;
            case R.id.btn_multiBarChart:
                startActivity(new Intent(this, MultiBarChartActivity.class));
                break;
            case R.id.btn_signLineChart:
                startActivity(new Intent(this, SignLineChartActivity.class));
                break;
            case R.id.btn_multiLineChart:
                startActivity(new Intent(this, MultiLineChartActivity.class));
                break;
            case R.id.btn_leida:
                startActivity(new Intent(this, RadarCahrtActivity.class));
                break;
            case R.id.btn_combined:
                startActivity(new Intent(this, CombinedChartActivity.class));
                break;
            case R.id.btn_listchart:
                startActivity(new Intent(this, ListChartActivity.class));
                break;
        }
    }
}
