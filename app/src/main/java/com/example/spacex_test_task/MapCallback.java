package com.example.spacex_test_task;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import com.example.spacex_test_task.models.RetrofitModel;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

/*
MapCallback, here I used a Google Geocoder to find a station and create marker.
 */

public class MapCallback implements OnMapReadyCallback {

  private Context mContext;
   private GoogleMap googleMap;
   private RetrofitModel LaunchInfo;

   public MapCallback(Context mContext,RetrofitModel launchInfo) {
        this.mContext = mContext;
        this.LaunchInfo = launchInfo;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        CreateMarker(LaunchInfo.getLaunchSite().getSiteNameLong());
    }

    public void CreateMarker(String LocationName){
        try {
            Geocoder geocoder = new Geocoder(mContext);
            List<Address> addresses = geocoder.getFromLocationName(LocationName, 1);
            Address address = addresses.get(0);
            LatLng latLng = new LatLng(address.getLatitude(),address.getLongitude());

            googleMap.addMarker(new MarkerOptions().title(LocationName).position(latLng));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,15f));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
