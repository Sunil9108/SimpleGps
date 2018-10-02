package com.example.gyaneshbohara.simplegps;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.gyaneshbohara.simplegps.location.GPSTracker;

public class MainActivity extends AppCompatActivity {

    Button btnShowLocation;

    TextView tvLocationInfroamtion;


    private static final int REQUEST_CODE_PERMISSION = 2;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;

    // GPSTracker class
    GPSTracker gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tvLocationInfroamtion=(TextView)findViewById(R.id.tv_location);




        try {
            if (ActivityCompat.checkSelfPermission(this, mPermission)
                    != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this, new String[]{mPermission},
                        REQUEST_CODE_PERMISSION);

            }



            btnShowLocation=(Button)findViewById(R.id.btn_location);

            btnShowLocation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {



                    try {

                        getLocation();

                    }catch (Exception ex){

                        ex.printStackTrace();

                    }

                }
            });

            gps = new GPSTracker(MainActivity.this);


        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public void getLocation(){

        gps = new GPSTracker(MainActivity.this);

        // check if GPS enabled
        if(gps.canGetLocation()){

            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();

            tvLocationInfroamtion.setText(""+latitude+":"+longitude);


        }else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


    }
}
