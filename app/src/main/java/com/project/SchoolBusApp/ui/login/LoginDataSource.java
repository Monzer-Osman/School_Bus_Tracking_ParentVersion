package com.project.SchoolBusApp.ui.login;

import android.util.Log;

import com.project.SchoolBusApp.ui.login.model.LoginResponse;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */

public class LoginDataSource {

    LoginResponse result = new LoginResponse();
    public Result<LoginResponse> login(String phoneNumber, String password) {

        try {
            // TODO: handle loggedInUser authentication
//            LoginRequest loginRequest = new LoginRequest(phoneNumber, password);
//
//            ApiClient.getUserService().loginAndGetToken("1").enqueue(new Callback<LoginResponse>() {
//                LoginResponse result2  = new LoginResponse();
//                @Override
//                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
//
//                    if(response.isSuccessful()){
//                        result.setid(response.body().getid());
//                        result2.setid(response.body().getid());
//                        result.setfirst_name(response.body().getfirst_name());
//                        result2.setfirst_name(response.body().getfirst_name());
//                        result.setlast_name(response.body().getlast_name());
//                        result2.setlast_name(response.body().getlast_name());
//                        result.setPhone(response.body().getPhone());
//                        result2.setPhone(response.body().getPhone());
//                        result.setEmail(response.body().getEmail());
//                        result2.setEmail(response.body().getEmail());
//                        result.notify();
//                    }
//                    else{
//                        result.setid(-101);
//                        result.setfirst_name("Error");
//                    }
//                    Log.d("log in : ", "hello " + result.getfirst_name());
//                }
//
//                @Override
//                public void onFailure(Call<LoginResponse> call, Throwable t) {
//                    result.setid(-404);
//                    result.setfirst_name("Error Must Enable Internet");
//                    Log.d("log in : ","failed");
//                    Log.e("TAG","NET_ERROR:" + t.toString());
//                }
//            });
        }
        catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }

        Log.d("log in ","hello 2 : " + result.getfirst_name());
        return new Result.Success<LoginResponse>(result);
    }

    public void logout() {
        // TODO: revoke authentication
    }
}