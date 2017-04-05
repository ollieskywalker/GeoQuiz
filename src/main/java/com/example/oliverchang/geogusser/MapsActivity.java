package com.example.oliverchang.geogusser;

import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.TileOverlayOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final String TAG = "onMap()";
    private static final String URL = "https://restcountries.eu/rest/v2/all";
    private GoogleMap mMap;
    public static TextView scoreBoard, timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        scoreBoard = (TextView)findViewById(R.id.textView_score);
        timer = (TextView) findViewById(R.id.textView_countDownTimer);

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.mapstyle));

            if (!success) {
                Log.e(TAG, "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "Can't find style. Error: ", e);
        }

        JSONParser parser = new JSONParser();
        parser.execute(URL);

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                Intent i = new Intent(MapsActivity.this, QuestionActivity.class);
                i.putExtra("EXTRA_NAME", marker.getTitle());
                startActivity(i);

                marker.remove();
                return true;
            }
        });
    }

    public void startTimer(){
        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                timer.setText("Time: "+String.format("%d min, %d sec",
                        TimeUnit.MILLISECONDS.toMinutes( millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }

            public void onFinish() {
                Intent i = new Intent(MapsActivity.this, FinishActivity.class);
                startActivity(i);
            }
        }.start();
    }


    private class JSONParser extends AsyncTask<String, Void, String> {
        private static final String TAG = "TAG";
        String countryJSON = "";

        @Override
        protected String doInBackground(String... urls) {
            try {
                java.net.URL url = new URL(urls[0]);
                URLConnection connection = url.openConnection();

                InputStream inputStream = connection.getInputStream();

                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;

                while ((line = reader.readLine()) != null) {
                    countryJSON += line;
                }
                return countryJSON;

            } catch (Exception e) {
                e.printStackTrace();
                Log.d("onAsyncClass", "NOT WORKING");

            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            ArrayList<Country> countries = new ArrayList<Country>();

            if (s != null) {
                try {
                    JSONArray countriesJSON = new JSONArray(countryJSON);

                    for (int i = 0; i < countriesJSON.length(); i++) {
                        //Log.d(TAG, "onCreate: " + countriesJSON.optJSONObject(0).optString("name"));

                        String name = countriesJSON.optJSONObject(i).optString("name");
                        double lat = countriesJSON.optJSONObject(i).optJSONArray("latlng").optInt(0);
                        double lon = countriesJSON.optJSONObject(i).optJSONArray("latlng").optInt(1);
                        //Log.d("onAsyncClass" , ""+lat+" " + lon);
                        Country x = new Country(lat, lon, name);
                        LatLng c = new LatLng(lat, lon);
                        Marker g = mMap.addMarker(new MarkerOptions().position(c).title(name));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            startTimer();
        }
    }

}
