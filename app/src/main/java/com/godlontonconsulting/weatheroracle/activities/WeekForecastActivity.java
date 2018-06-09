package com.godlontonconsulting.weatheroracle.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.godlontonconsulting.weatheroracle.R;
import com.godlontonconsulting.weatheroracle.adapters.ForecastListAdapter;
import com.godlontonconsulting.weatheroracle.models.Forecast;
import com.godlontonconsulting.weatheroracle.services.ForecastService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WeekForecastActivity extends AppCompatActivity {
    public static final String TAG = WeekForecastActivity.class.getSimpleName();

    private ForecastListAdapter mAdapter;
    private RecyclerView mRecyclerView;

    public ArrayList<Forecast> mForecasts = new ArrayList<>();
    private String userLocation;
    private SweetAlertDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seven_day_forecast);

        pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        double lat = intent.getDoubleExtra("lat", 0.0);
        double lng = intent.getDoubleExtra("lng", 0.0);
        getSupportActionBar().setTitle( intent.getStringExtra("userLocation").toUpperCase()+" - Daily Weather");
        userLocation = intent.getStringExtra("userLocation");
        getDailySummary(lat, lng);
        mRecyclerView= findViewById(R.id.recyclerView);
    }

    private void getDailySummary(Double lat, Double lng) {

        showProgress();
        final ForecastService forecastService = new ForecastService();
        forecastService.getForecast(lat, lng, new Callback() {

        @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

        @Override
            public void onResponse(Call call, Response response) {
                mForecasts = forecastService.processResults(response);
                WeekForecastActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hideProgress();
                        mAdapter = new ForecastListAdapter(getApplicationContext(), mForecasts);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(WeekForecastActivity.this);
                        mRecyclerView.addItemDecoration(new DividerItemDecoration(WeekForecastActivity.this,
                                DividerItemDecoration.HORIZONTAL));
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }

    public void showProgress() {
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#00749e"));
        pDialog.setTitleText("Loading...");
        pDialog.setCancelable(false);
        pDialog.show();
    }

    public void hideProgress() {
        pDialog.hide();
    }
}

