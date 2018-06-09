package com.godlontonconsulting.weatheroracle.activities;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import com.godlontonconsulting.weatheroracle.R;
import com.google.android.gms.maps.model.LatLng;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mGetWeatherButton;
    private EditText mLocationEditTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

             ButterKnife.bind(this);

            mGetWeatherButton = findViewById(R.id.getWeatherButton);
            mGetWeatherButton.setOnClickListener(this);
            mLocationEditTextView= findViewById(R.id.locationEditTextView);
            mLocationEditTextView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    boolean handled = false;
                    if (actionId == EditorInfo.IME_ACTION_SEND) {
                        submitCity();
                        handled = true;
                    }
                    return handled;
                }
            });
        };

    public LatLng getLocationFromAddress(String strAddress) {

        Geocoder geoCoder = new Geocoder(MainActivity.this, Locale.getDefault());
        List<Address> address;
        LatLng coordinates = null;

        try {
            address = geoCoder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }
            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();

            coordinates = new LatLng(location.getLatitude(), location.getLongitude());

            return coordinates;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return coordinates;
    }

    @Override
    public void onClick(View view) {
        submitCity();
    }

    private void submitCity(){
        String enteredLocation = mLocationEditTextView.getText().toString().trim();

        if (enteredLocation.equals("")) {
            mLocationEditTextView.setError("Please enter a valid address");
        } else {
            LatLng newCoordinates = getLocationFromAddress(enteredLocation);
            if(newCoordinates == null) {
                mLocationEditTextView.setError("Couldn't find coordinates for this address, please try a different address");
                return;
            }

            String stringXY = String.valueOf(newCoordinates);

            String coordinates = (stringXY.split("[\\(\\)]")[1]);
            List<String> coordinateList = Arrays.asList(coordinates.split(","));
            String latitude = coordinateList.get(0);
            String longitude = coordinateList.get(1);
            double lat = Double.parseDouble(latitude);
            double lng = Double.parseDouble(longitude);

            Intent intent = new Intent(MainActivity.this, WeekForecastActivity.class);
            intent.putExtra("userLocation", enteredLocation);
            intent.putExtra("lat", lat);
            intent.putExtra("lng", lng);
            startActivity(intent);

        }

    }
}
