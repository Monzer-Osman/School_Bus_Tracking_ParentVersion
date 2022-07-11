package com.project.SchoolBusApp.login.data;

import android.util.Log;

import com.project.SchoolBusApp.login.data.model.LoginRequest;
import com.project.SchoolBusApp.login.data.model.LoginResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class LoginRepository {

    private static volatile LoginRepository instance;

    private LoginDataSource dataSource;
    private LoginResponse result = new LoginResponse();
    // If user credentials will be cached in local storage, it is recommended it be encrypted
    // @see https://developer.android.com/training/articles/keystore
    private LoginResponse user = null;

    // private constructor : singleton access
    private LoginRepository(LoginDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static LoginRepository getInstance(LoginDataSource dataSource) {
        if (instance == null) {
            instance = new LoginRepository(dataSource);
        }
        return instance;
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public void logout() {
        user = null;
        dataSource.logout();
    }

    private void setLoggedInUser(LoginResponse user) {
        this.user = user;
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }

    public LoginResponse login(String phoneNumber, String password) {

        // handle login
        //Result<LoginResponse> result = dataSource.login(phoneNumber, password);
        try {
            // TODO: handle loggedInUser authentication
            LoginRequest loginRequest = new LoginRequest(phoneNumber, password);

            ApiClient.getUserService().loginAndGetToken("1").enqueue(new Callback<LoginResponse>() {
                LoginResponse result2  = new LoginResponse();
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                    if(response.isSuccessful()){
                        result.setid(response.body().getid());
                        result.setfirst_name(response.body().getfirst_name());
                        result.setlast_name(response.body().getlast_name());
                        result.setPhone(response.body().getPhone());
                        result.setEmail(response.body().getEmail());
                    }
                    else{
                        result.setid(-101);
                        result.setfirst_name("Error");
                    }
                    Log.d("log in : ", "hello " + result.getfirst_name());
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    result.setid(-404);
                    result.setfirst_name("Error Must Enable Internet");
                    Log.d("log in : ","failed");
                    Log.e("TAG","NET_ERROR:" + t.toString());
                }
            });


        }
        catch (Exception e) {
            return result;
        }

        if (result != null) {
            setLoggedInUser(result);
        }

        Log.d("log in ","hello 2 : " + result.getfirst_name());
        return result;
    }
}