package com.example.e610.mylocation;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.e610.mylocation.Utils.GPSTracker;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    double latitudeValue;
    double longitudeValue ;

    GPSTracker myGPS;
    Button myLocationBtn;
    Button otherLocationBtn;
    EditText latitude;
    EditText longitude;

    Geocoder geocoder;
    List<Address> addresses;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myGPS=new GPSTracker(MainActivity.this);
        myLocationBtn=(Button) findViewById(R.id.myLocationBtn);
        otherLocationBtn=(Button) findViewById(R.id.otherLocationBtn);
        latitude=(EditText)findViewById(R.id.latitude);
        longitude=(EditText)findViewById(R.id.longitude);


        geocoder=new Geocoder(getApplicationContext(), Locale.getDefault());
        addresses=null;

        myLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // if(myGPS.canGetLocation()){
                if(true){
                    try {
                        try {
                            latitudeValue = Double.valueOf(latitude.getText().toString());
                            longitudeValue = Double.valueOf(longitude.getText().toString());
                        }catch (Exception e){
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getApplicationContext());

                            // Setting Dialog Title
                            alertDialog.setTitle("Error");

                            // Setting Dialog Message
                            alertDialog.setMessage("Please Enter a Valid Number");
                        }
                       // addresses = geocoder.getFromLocation(myGPS.getLatitude(),myGPS.getLongitude(),1);
                        addresses = geocoder.getFromLocation(latitudeValue, longitudeValue,1);
                        Toast.makeText(getApplicationContext(),addresses.get(0).getFeatureName(),Toast.LENGTH_LONG).show();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                else{
                    myGPS.showSettingsAlert();
                }
            }
        });


        /*try {
            latitudeValue = Double.valueOf(latitude.getText().toString());
            longitudeValue = Double.valueOf(longitude.getText().toString());
        }catch (Exception e){
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getApplicationContext());

            // Setting Dialog Title
            alertDialog.setTitle("Error");

            // Setting Dialog Message
            alertDialog.setMessage("Please Enter a Valid Number");
        }
*/


    }
}
