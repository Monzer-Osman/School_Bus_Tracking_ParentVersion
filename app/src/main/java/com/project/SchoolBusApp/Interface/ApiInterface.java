package com.project.SchoolBusApp.Interface;

import com.project.SchoolBusApp.model.LocationResponse;
import com.project.SchoolBusApp.model.LoginResponse;
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

    @POST("school_bus/login.php")
    Call<LoginResponse> loginAndGetToken(@Query("phone") String phone, @Query("password") String password);

    @GET("school_bus/send_message.php")
    Call<Message> sendMessage(@Query("title") String title, @Query("message") String message ,
                              @Query("sender") int sender, @Query("receiver") int receiver,
                              @Query("date") String date);

    @GET("school_bus/send_feedback.php")
    Call<FeedBack> sendFeedback(@Query("user_id") int userId, @Query("rate") float rate, @Query("feed") String comment);

    @GET("school_bus/inbox.php")
    Call<ArrayList<Message>> inbox(@Query("id") int userId);

    @GET("school_bus/outbox.php")
    Call<ArrayList<Message>> outbox(@Query("id") int userId);

    @GET("school_bus/kids.php")
    Call<ArrayList<Kid>> getKidsInfo(@Query("id") int userId);

    @GET("school_bus/get_location.php")
    Call<LocationResponse> getLocation(@Query("id") String userId);
}
