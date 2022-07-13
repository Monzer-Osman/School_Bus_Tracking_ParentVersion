package com.project.SchoolBusApp.ui.login.ui_login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.util.Log;
import android.util.Patterns;

import com.project.SchoolBusApp.network.ApiClient;
import com.project.SchoolBusApp.ui.login.LoginRepository;
import com.project.SchoolBusApp.R;
import com.project.SchoolBusApp.ui.login.model.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    private LoginRepository loginRepository;

    LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    public void login(String phoneNumber, String password) {

        LoginResponse result = new LoginResponse();
        try {

            ApiClient.getUserService().loginAndGetToken(phoneNumber,password).enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                    if(response.isSuccessful()){
                        if(response.body() != null) {
                            loginResult.setValue(new LoginResult(new LoggedInUserView("success",response.body().getid(),response.body().getfirst_name(),
                                        response.body().getlast_name(), response.body().getPhone(), response.body().getEmail())));
                        }
                        else{
                            loginResult.setValue(new LoginResult(new LoggedInUserView("failed")));
                        }
                    }
                    else{
                        loginResult.setValue(new LoginResult(new LoggedInUserView("error")));
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    result.setid(-404);
                    result.setfirst_name("Error Must Enable Internet");
                    loginResult.setValue(new LoginResult(R.string.login_failed));
                    Log.d("log in : ","failed");
                    Log.e("TAG","NET_ERROR:" + t.toString());
                }
            });
        }
        catch (Exception e) {
            Log.e("error", e.toString());
        }
    }

    public void loginDataChanged(String phoneNumber, String password) {
        if (!isPhoneNumberValid(phoneNumber)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_phoneNumber, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    // A placeholder phoneNumber validation check
    private boolean isPhoneNumberValid(String phoneNumber) {
        if (phoneNumber.isEmpty()) {
            return false;
        }
        else {
            return Patterns.PHONE.matcher(phoneNumber).matches();
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }

}