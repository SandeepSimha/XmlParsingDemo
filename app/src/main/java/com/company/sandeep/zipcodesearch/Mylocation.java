package com.company.sandeep.zipcodesearch;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by chsan_000 on 6/9/2016.
 */
public class Mylocation extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private double lat, lang;
    private String placename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Maps");
        actionBar.setDisplayHomeAsUpEnabled(true);

        lat = getIntent().getDoubleExtra("langitude", 0);
        lang = getIntent().getDoubleExtra("lattitude", 0);
        placename = getIntent().getStringExtra("placename");

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng sydney = new LatLng(lang, lat);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in " + placename));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        mMap.getUiSettings().setZoomControlsEnabled(true);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_maps, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.SATELLITE) {
            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        } else if (item.getItemId() == R.id.Hybrid) {
            mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        } else if (item.getItemId() == R.id.NORMAL) {
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        }

        return super.onOptionsItemSelected(item);
    }
}
