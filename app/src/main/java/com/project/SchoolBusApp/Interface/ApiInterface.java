package com.project.SchoolBusApp.Interface;

import android.media.session.MediaSession;

import com.project.SchoolBusApp.login.data.model.LoginRequest;
import com.project.SchoolBusApp.login.data.model.LoginResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;


public interface ApiInterface {

    public static String QRY_TOKEN = "token";
    public static String QRY_KID_ID = "kid_id";
    public static String STATUS_OK = "ok";
    public static String STATUS_FAIL = "error";
    public static String STATUS_COMPLETED="completed";

    // method to login parent and get access token
    @POST("school_bus/login.php")
    Call<LoginResponse> loginAndGetToken(@Query("phone") LoginRequest login);

    @GET("school_bus/login.php")
    Call<LoginResponse> loginAndGetToken(@Query("phone") String id);

//    // get kids detail with bus detail
//    @GET("get_kids")
//    Call<Kids> getKidsInfo(@Query(QRY_TOKEN) String token);
//
//    // get active ride for particular kid
//    @GET("active_rides")
//    Call<RideStatus> getCurrentStatusMessage(@Query(QRY_TOKEN) String token, @Query(QRY_KID_ID) String kid_id);
//
//    // get active ride for this parent
//    @GET("active_rides")
//    Call<ActiveRides> getActiveRides(@Query(QRY_TOKEN) String token);
//
//    // recent ride history for @param#kid_id
//    @GET("recent_rides")
//    Call<RecentRides> getRecentRides(@Query(QRY_TOKEN) String token, @Query(QRY_KID_ID) String kid_id);
//
//    @POST("feedback")
//    Call<Response> sendFeedback(@Query(QRY_TOKEN) String token, @Body Feedback feedback);
//
//    // get currently arriving buses
//    @GET("arriving")
//    Call<ArrivingBuses> getArrivingBus(@Query(QRY_TOKEN) String token);
//
//    // get current location of bus
//    @GET("location")
//    Call<Location> getLocation(@Query(QRY_TOKEN) String token, @Query(QRY_KID_ID) String kid_id);
//
//    // get all location history associated to this journey
//    @GET("locations")
//    Call<Locations> getLocations(@Query(QRY_TOKEN) String token, @Query(QRY_KID_ID) String kid_id);
//
//    // upload kid image to server
//    @Multipart
//    @POST("upload")
//    Call<Response> uploadImage(@Query(QRY_TOKEN) String token,@Query(QRY_KID_ID) String kid_id, @Part MultipartBody.Part image);
}
