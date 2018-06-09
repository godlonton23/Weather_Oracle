package com.godlontonconsulting.weatheroracle.activities;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.godlontonconsulting.weatheroracle.R;
import com.godlontonconsulting.weatheroracle.adapters.ForecastPagerAdapter;
import com.godlontonconsulting.weatheroracle.models.Forecast;

import org.parceler.Parcels;
import java.util.ArrayList;
import butterknife.ButterKnife;

public class ForecastDetailActivity extends AppCompatActivity {

    ViewPager mViewPager;
    private ForecastPagerAdapter adapterViewPager;
    ArrayList<Forecast> mForecasts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_forecast_detail);
        ButterKnife.bind(this);

        mForecasts = Parcels.unwrap(getIntent().getParcelableExtra("forecasts"));
        int startingPosition = Integer.parseInt(getIntent().getStringExtra("position"));

        adapterViewPager = new ForecastPagerAdapter(getSupportFragmentManager(), mForecasts);
        mViewPager=findViewById(R.id.viewPager);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}
