package com.project.SchoolBusApp.login.data;

import com.project.SchoolBusApp.login.data.model.LoggedInUser;
import com.project.SchoolBusApp.login.data.model.LoginRequest;
import com.project.SchoolBusApp.login.data.model.LoginResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */

public class LoginDataSource {

    public Result<LoggedInUser> login(String phoneNumber, String password) {

        try {
            // TODO: handle loggedInUser authentication
            LoginRequest loginRequest = new LoginRequest(phoneNumber, password);
            Call<LoginResponse> loginResponseCall = ApiClient.getUserService().userLogin(loginRequest);
            LoginResponse result = new LoginResponse();

            ApiClient.getUserService().userLogin(loginRequest).enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                    if(response.isSuccessful()){
                        result.setUserId(response.body().getUserId());
                        result.setName(response.body().getName());
                        result.setNumber(response.body().getNumber());
                        result.setLocation(response.body().getLocation());
                    }
                    else{
                        result.setUserId(-101);
                        result.setName("Error");
                    }

                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                        result.setUserId(-404);
                        result.setName("Error Must Enable Internet");
                }
            });
//            LoggedInUser fakeUser =
//                    new LoggedInUser(
//                            java.util.UUID.randomUUID().toString(),
//                            "Jane Doe");
            return new Result.Success<>(result);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}