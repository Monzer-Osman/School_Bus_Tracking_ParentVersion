package com.project.SchoolBusApp.Interface;

import com.project.SchoolBusApp.ui.login.model.LoginResponse;
import com.project.SchoolBusApp.model.FeedBack;
import com.project.SchoolBusApp.model.Kid;
import com.project.SchoolBusApp.model.Message;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface ApiInterface {

    public static String QRY_TOKEN = "token";
    public static String QRY_KID_ID = "kid_id";
    public static String STATUS_OK = "ok";
    public static String STATUS_FAIL = "error";
    public static String STATUS_COMPLETED="completed";

    // method to login parent and get access token
    @POST("school_bus/login.php")
    Call<LoginResponse> loginAndGetToken(@Query("phone") String phone,@Query("password") String password);

    @GET("school_bus/send_message.php")
    Call<Message> sendMessage(@Query("title") String title, @Query("message") String message ,@Query("sender") int sender, @Query("receiver") int receiver, @Query("date") String date);

    @GET("school_bus/send_feedback.php")
    Call<FeedBack> sendFeedback(@Query("user_id") int userId, @Query("rate") float rate, @Query("feed") String comment);

    @GET("school_bus/inbox.php")
    Call<ArrayList<Message>> inbox(@Query("id") int userId);

    @GET("school_bus/outbox.php")
    Call<ArrayList<Message>> outbox(@Query("id") int userId);

    // get kids detail
    @GET("school_bus/kids.php")
    Call<ArrayList<Kid>> getKidsInfo(@Query("id") int userId);
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
