package com.project.SchoolBusApp.ui.track_bus;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.SphericalUtil;
import com.project.SchoolBusApp.Interface.ApiInterface;
import com.project.SchoolBusApp.R;
import com.project.SchoolBusApp.model.Loc;
import com.project.SchoolBusApp.model.LocationResponse;
import com.project.SchoolBusApp.network.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsFragment extends Fragment {

    FusedLocationProviderClient client;
    SupportMapFragment mapFragment;
    SharedPreferences sharedPreferences;
    private static final String TAG = "map_activity";
    //private static final LatLng HOME_GPS = new LatLng(22.2136881, 79.7462894);
    private static final int DEFAULT_ZOOM_LEVEL = 15;
    private static final Handler handler = new Handler();
    private static final int UPDATE_INTERVAL = 15000; // 15 seconds
    private GoogleMap mGoogleMap;
    private Marker mMarker;
    private final ApiInterface apiService = ApiClient.getUserService();
    private Polyline mLine;

    Runnable updateMarkerRunnable = new Runnable() {
        @Override
        public void run() {
            loadLocation(getKidId());
            handler.postDelayed(updateMarkerRunnable, UPDATE_INTERVAL);
        }
    };

    private final OnMapReadyCallback callback = new OnMapReadyCallback() {

        @Override
        public void onMapReady(GoogleMap googleMap) {
            mGoogleMap = googleMap;
            addMarkerHome(getHomeGps());
            mGoogleMap.getUiSettings().setZoomControlsEnabled(true);

            moveCameraHereWithZoom();
        }
    };

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

        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }

    private void moveCameraHereWithZoom() {
        LatLng home_hps = getHomeGps();
        if (home_hps != null)
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(home_hps, DEFAULT_ZOOM_LEVEL));
    }

    private void moveCameraHere(LatLng location) {
        if(mGoogleMap != null)
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(location));
    }

    @SuppressLint("MissingPermission")
    private void enableMyHomeLocation(boolean enable) {
        if (ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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
            mMarkerOption.icon(bitmapDescriptor(getContext(),R.drawable.ic_baseline_directions_bus_24));
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

    private LatLng getHomeGps() {
        sharedPreferences = getContext().getSharedPreferences("IM_IN",0);
        String home_gps = sharedPreferences.getString("location","null");
        if (!home_gps.equals("null")) {
            Loc l = new Loc(home_gps);
            return new LatLng(l.getLat(), l.getLon());
        }
        return null;
    }

    private void addMarkerHome(LatLng pos) {
        if (pos == null) return;
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(pos);
        markerOptions.title("Home");
        markerOptions.icon(bitmapDescriptor(getContext(),R.drawable.ic_home));
        mGoogleMap.addMarker(markerOptions);

    }

    // async update current location marker
    private void loadLocation(String userId) {
        apiService.getLocation("0").enqueue(new Callback<LocationResponse>() {
            @Override
            public void onResponse(Call<LocationResponse> call, Response<LocationResponse> response) {
                if (response.isSuccessful()) {
                    LocationResponse locationResponse = response.body();
                    Loc loc = new Loc(locationResponse.getLoc());

                    double distance = SphericalUtil.computeDistanceBetween(new LatLng(loc.getLat(),loc.getLon()), getHomeGps()) /1000;
                    Log.d("distance : ", String.valueOf(distance));

                    if (distance < 0.070) { //distance is less than 100 meter
                        // show Arriving message
                        Toast.makeText(getContext(), "Bus is home.", Toast.LENGTH_LONG).show();
                        NotificationCompat.Builder notificationCompat = new NotificationCompat.Builder(requireContext()).setSmallIcon(R.drawable.ic_baseline_directions_bus_24)
                                .setContentTitle("School Bus Tracking App")
                                .setContentText("Bus is Arrived");

                        NotificationManager manager = (NotificationManager)
                                getContext().getSystemService(getContext().NOTIFICATION_SERVICE);
                        manager.notify(0, notificationCompat.build());

                        // remove handler
                        //handler.removeCallbacks(updateMarkerRunnable);
                    }
                    else if(distance >= 0.500 && distance <= 0.600){
                        Toast.makeText(getContext(), "Bus is near home.", Toast.LENGTH_LONG).show();
                        NotificationCompat.Builder notificationCompat = new NotificationCompat.Builder(requireContext()).setSmallIcon(R.drawable.ic_baseline_directions_bus_24)
                                .setContentTitle("School Bus Tracking App")
                                .setContentText("Bus is near home");

                        NotificationManager manager = (NotificationManager)
                                getContext().getSystemService(Context.NOTIFICATION_SERVICE);
                        manager.notify(1, notificationCompat.build());
                    }
                    // update marker
                    LatLng pos = new LatLng(loc.getLat(), loc.getLon());
                    addMarker(pos, (locationResponse.getTime()));
                    Log.d("pos lat : ", String.valueOf(pos.latitude));
                    Log.d("pos long : ", String.valueOf(pos.longitude));
                }
            }

            @Override
            public void onFailure(Call<LocationResponse> call, Throwable t) {
                Log.e("Error in geting driver location", "can't download current location");
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(updateMarkerRunnable);

    }

    @Override
    public void onResume() {
        super.onResume();
        handler.post(updateMarkerRunnable);
    }

    private BitmapDescriptor bitmapDescriptor(Context context, int vectorResId){
        Drawable vectorDrawable = ContextCompat.getDrawable(context,vectorResId);
        vectorDrawable.setBounds(0,0,
                vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),
                vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

}