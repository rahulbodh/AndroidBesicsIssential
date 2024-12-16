package com.example.lanugagebasicstest;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.lanugagebasicstest.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private LocationManager locationManager;
    private ActivityMainBinding binding; // Binding for accessing layout views

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate the layout using View Binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set up the button's onClick listener
        binding.button.setOnClickListener(v -> detectEnvironment(this));

        // Perform the initial environment detection
        detectEnvironment(this);
    }

    public void detectEnvironment(Context context) {
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        // Check location permissions
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Request permissions if not granted
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }

        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    float accuracy = location.getAccuracy(); // Smaller values = better accuracy
                    binding.accuracyTextView.setText("Accuracy: " + accuracy);
                    Log.d("Location", "Accuracy: " + accuracy);
                    if (accuracy < 20) { // Example threshold for outdoor environment
                        binding.statusTextView.setText("Outdoor");
                    } else {
                        binding.statusTextView.setText("Indoor");
                    }
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {}

                @Override
                public void onProviderEnabled(String provider) {}

                @Override
                public void onProviderDisabled(String provider) {}
            });
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }
}
