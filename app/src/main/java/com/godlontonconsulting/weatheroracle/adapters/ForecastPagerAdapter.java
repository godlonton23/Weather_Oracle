package com.godlontonconsulting.weatheroracle.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.godlontonconsulting.weatheroracle.models.Forecast;
import com.godlontonconsulting.weatheroracle.activities.ForecastDetailsFragment;

import java.util.ArrayList;

public class ForecastPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Forecast> mForecasts;

    public ForecastPagerAdapter(FragmentManager fm, ArrayList<Forecast> forecasts) {
        super(fm);
        mForecasts = forecasts;
    }

    @Override
    public Fragment getItem(int position) {
        return ForecastDetailsFragment.newInstance(mForecasts.get(position));
    }

    @Override
    public int getCount() {
        return mForecasts.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mForecasts.get(position).getDayOfWeek();
    }
}
