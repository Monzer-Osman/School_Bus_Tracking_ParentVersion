package com.project.SchoolBusApp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.project.SchoolBusApp.Interface.ApiInterface;

import java.io.IOError;
import java.io.IOException;
import java.util.List;

public class MapsFragment extends Fragment {

    FusedLocationProviderClient client;
    SupportMapFragment mapFragment;
    SharedPreferences sharedPreferences;
    private static final String TAG = "map_activity";
    //private static final LatLng HOME_GPS = new LatLng(22.2136881, 79.7462894);
    private static final int DEFAULT_ZOOM_LEVEL = 14;
    private static final Handler handler = new Handler();
    private static final int UPDATE_INTERVAL = 10000; // 10 seconds
    private GoogleMap mGoogleMap;
    private Marker mMarker;
    private ApiInterface apiService;
    private Polyline mLine;

//    Runnable updateMarkerRunnable = new Runnable() {
//        @Override
//        public void run() {
//            loadLocation(getKidId());
//            handler.postDelayed(updateMarkerRunnable, UPDATE_INTERVAL);
//        }
//    };
//
//    private OnMapReadyCallback callback = new OnMapReadyCallback() {
//
//        @Override
//        public void onMapReady(GoogleMap googleMap) {
//
//            mGoogleMap = googleMap;
//            moveCameraHereWithZoom();
//
////            LatLng sydney = new LatLng(30.044420, 31.235712);
////            googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Cairo"));
////            googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//        }
//    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maps, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);


//        if (mapFragment != null) {
//            mapFragment.getMapAsync(callback);
//        }
    }

//    private void moveCameraHereWithZoom() {
//        LatLng home_hps = getHomeGps();
//        if (home_hps != null)
//            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(home_hps, DEFAULT_ZOOM_LEVEL));
//    }

    private void moveCameraHere(LatLng location) {
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(location));
    }

    private void enableMyHomeLocation(boolean enable) {
        if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mGoogleMap.setMyLocationEnabled(enable);
    }

    private void addMarker(LatLng location, String time) {
        if (mGoogleMap != null) {
            MarkerOptions mMarkerOption = new MarkerOptions();
            mMarkerOption.position(location);
            mMarkerOption.title("Bus is here");
            mMarkerOption.snippet(time);
            mMarkerOption.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_face_black_24));
            removeMarker();
            mMarker = mGoogleMap.addMarker(mMarkerOption);
        }
    }

    private void addCustomMarker(MarkerOptions options) {
        if (mGoogleMap != null && options != null) {
            mGoogleMap.addMarker(options);
        }
    }

    private void removeMarker() {
        if (mGoogleMap != null && mMarker != null) {
            mMarker.remove();
        }
    }

    private void addLine(LatLng from, LatLng to) {
        if (mGoogleMap != null) {
            PolylineOptions lineOption = new PolylineOptions().add(from).add(to).color(Color.GRAY);
            // remove line if already drawn
            if (mLine != null) mLine.remove();
            mLine = mGoogleMap.addPolyline(lineOption);
        }
    }
    private void addLine(List<LatLng> lines) {
        if (mGoogleMap != null) {
            PolylineOptions lineOption = new PolylineOptions().addAll(lines).color(Color.BLUE).geodesic(true);
            mGoogleMap.addPolyline(lineOption);
        }
    }


    private String getKidId(){
        sharedPreferences = getContext().getSharedPreferences("IM_IN",0);
        int parentId = sharedPreferences.getInt("id",-1);
        if (parentId == -1) {
            Log.e("Parent ID Error","id is not provided");
        }
        return String.valueOf(parentId);
    }

//    private int getType() {
//        return getIntent().getIntExtra("type", -1);
//    }

//    private LatLng getHomeGps() {
//        String home_gps = sharedPrefHelper.getString(SharedPrefHelper.HOME_GPS);
//        if (!home_gps.equals(SharedPrefHelper.DEFAULT_STRING)) {
//            LatLong l = new LatLong(home_gps);
//            if (l.isValid()) return new LatLng(l.getLat(), l.getLon());
//        }
//        return null;
//    }

}