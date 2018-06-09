package com.godlontonconsulting.weatheroracle.models;


import org.parceler.Parcel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Parcel
public class Forecast {
    public long mTime;
    public String mSummary;
    public String mIcon;
    public double mMinTemp;
    public double mMaxTemp;
    public long mTimeOffset;
    public double mLatitude;
    public double mLongitude;
    public double mHumidity;
    public double mPrecipitation;
    public long mSunrise;
    public long mSunset;
    public String mWeeklyForecast;

    public Forecast() {}

public Forecast(long time, String summary, String icon, double minTemp, double maxTemp, long timeOffset, double latitude, double longitude, double humidity, double precipitation, long sunrise, long sunset, String weeklyForecast) {
    this.mTime = time;
    this.mSummary = summary;
    this.mIcon = icon;
    this.mMinTemp = minTemp;
    this.mMaxTemp = maxTemp;
    this.mTimeOffset = timeOffset;
    this.mLatitude = latitude;
    this.mLongitude = longitude;
    this.mHumidity = humidity;
    this.mPrecipitation = precipitation;
    this.mSunrise = sunrise;
    this.mSunset = sunset;
    this.mWeeklyForecast = weeklyForecast;
    }

    public long getUnixTime() {
        return mTime;
    }

    public String getDailySummary() {
        return mSummary;
    }

    public String getDailyIcon() {
        return mIcon;
    }

    public double getDailyMinTemp() {
        return mMinTemp;
    }

    public double getDailyMaxTemp() {
        return mMaxTemp;
    }

    public long getTimeOffset() { return mTimeOffset; }

    public double getLatitude() { return mLatitude; }

    public double getLongitude() { return mLongitude; }

    public double getHumidity() { return mHumidity; }

    public double getPrecipitation() { return mPrecipitation; }

    public long getSunrise() { return mSunrise; }

    public long getSunset() { return mSunset; }

    public String getWeeklySummary() { return mWeeklyForecast; }


    public String getDayOfWeek() {
        long timeOffset = mTimeOffset * (-1);
        long timeOffsetSeconds = timeOffset * 3600;
        long unixSeconds =  (mTime + timeOffsetSeconds) * 1000L;
        DateFormat day = new SimpleDateFormat("EEEE");
        String dayOfWeek = day.format(new Date(unixSeconds));
        return dayOfWeek;
    }

    public String getSunriseTime() {
        long timeOffset = mTimeOffset * (-1);
        long timeOffsetSeconds = timeOffset * 3600;
        long unixSeconds =  (mSunrise + timeOffsetSeconds) * 1000L;
//        long unixSeconds = mSunrise * 1000L;
        DateFormat sunriseTime = new SimpleDateFormat("h:mm a");
        String timeOfSunrise = sunriseTime.format(new Date(unixSeconds));
        return timeOfSunrise;
    }

    public String getSunsetTime() {
        long timeOffset = mTimeOffset * (-1);
        long timeOffsetSeconds = timeOffset * 3600;
        long unixSeconds = (mSunset + timeOffsetSeconds) * 1000L;
//        long unixSeconds = mSunset * 1000L;
        DateFormat sunsetTime = new SimpleDateFormat("h:mm a");
        String timeOfSunset = sunsetTime.format(new Date(unixSeconds));
        return timeOfSunset;
    }

    public long getHumidityPercent() {
        double humidity = mHumidity;
        double humidityDouble = humidity * 100;
        long humidityPercent = (long) humidityDouble;
        return humidityPercent;
    }

    public long getPrecpitationPercent() {
        double precipitation = mPrecipitation;
        double precipitationDouble = precipitation * 100;
        long precipitationPercent = (long) precipitationDouble;
        return precipitationPercent;
    }
}





