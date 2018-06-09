package com.godlontonconsulting.weatheroracle.activities;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.godlontonconsulting.weatheroracle.R;
import com.godlontonconsulting.weatheroracle.models.Forecast;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.ButterKnife;


public class ForecastDetailsFragment extends Fragment {

    private TextView mHourlySummary;
    private  TextView mIcon;
    private  ImageView mWeatherIconPlaceholder;
    private  TextView mLowTemperatureTextView;
    private  TextView mHighTemperatureTextView;
    private  TextView mHumidityPrecipitationTextView;

    private Forecast mForecast;

    public static ForecastDetailsFragment newInstance(Forecast forecast) {
        ForecastDetailsFragment forecastDetailsFragment = new ForecastDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable("forecast", Parcels.wrap(forecast));
        forecastDetailsFragment.setArguments(args);
        return forecastDetailsFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mForecast = Parcels.unwrap(getArguments().getParcelable("forecast"));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daily_forecast_detail, container, false);
        ButterKnife.bind(this, view);

        mHourlySummary=view.findViewById(R.id.hourlySummaryTextView);
        mIcon=view.findViewById(R.id.iconTextView);
        mWeatherIconPlaceholder=view.findViewById(R.id.weatherImageView);
        mHighTemperatureTextView=view.findViewById(R.id.highTemperatureTextView);
        mHumidityPrecipitationTextView=view.findViewById(R.id.humidityPrecipitationTextView);
        mLowTemperatureTextView=view.findViewById(R.id.lowTemperatureTextView);

        mHourlySummary.setText(mForecast.getDailySummary());

        switch(mForecast.getDailyIcon()){
            case "clear-day":  Picasso.with(view.getContext()).load(R.drawable.clear_day).into(mWeatherIconPlaceholder);
                break;
            case "clear-night": Picasso.with(view.getContext()).load(R.drawable.clear_night).into(mWeatherIconPlaceholder);
                break;
            case "rain": Picasso.with(view.getContext()).load(R.drawable.rain).into(mWeatherIconPlaceholder);
                break;
            case "snow": Picasso.with(view.getContext()).load(R.drawable.snow).into(mWeatherIconPlaceholder);
                break;
            case "sleet":Picasso.with(view.getContext()).load(R.drawable.sleet).into(mWeatherIconPlaceholder);
                break;
            case "wind": Picasso.with(view.getContext()).load(R.drawable.wind).into(mWeatherIconPlaceholder);
                break;
            case "fog": Picasso.with(view.getContext()).load(R.drawable.fog).into(mWeatherIconPlaceholder);
                break;
            case "cloudy": Picasso.with(view.getContext()).load(R.drawable.cloudy).into(mWeatherIconPlaceholder);
                break;
            case "partly-cloudy-day": Picasso.with(view.getContext()).load(R.drawable.partly_cloudy_day).into(mWeatherIconPlaceholder);
                break;
            case "partly-cloudy-night": Picasso.with(view.getContext()).load(R.drawable.partly_cloudy_night).into(mWeatherIconPlaceholder);
                break;
            case "": Picasso.with(view.getContext()).load(R.drawable.weather_clock_icon).into(mWeatherIconPlaceholder);
                break;
        }

        mHighTemperatureTextView.setText("High Temperature:  " + Double.toString(mForecast.getDailyMaxTemp()) + "°C");
        mLowTemperatureTextView.setText("Low Temperature:  " + Double.toString(mForecast.getDailyMinTemp()) + "°C");
        mHumidityPrecipitationTextView.setText("Humidity: " + mForecast.getHumidityPercent() + "%  -  Preciptation: " + mForecast.getPrecpitationPercent() + "%");
        return view;
    }


}
