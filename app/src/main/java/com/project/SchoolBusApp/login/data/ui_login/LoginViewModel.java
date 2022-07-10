package com.project.SchoolBusApp.login.data.ui_login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.util.Log;
import android.util.Patterns;

import com.project.SchoolBusApp.login.data.LoginRepository;
import com.project.SchoolBusApp.login.data.Result;
import com.project.SchoolBusApp.login.data.model.LoggedInUser;
import com.project.SchoolBusApp.R;
import com.project.SchoolBusApp.login.data.model.LoginResponse;

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
        // can be launched in a separate asynchronous job
        Result<LoginResponse> result = loginRepository.login(phoneNumber, password);

        if (result instanceof Result.Success) {
            LoginResponse data = ((Result.Success<LoginResponse>) result).getData();
            loginResult.setValue(new LoginResult(new LoggedInUserView(data.getfirst_name())));
        }
        else {
            loginResult.setValue(new LoginResult(R.string.login_failed));
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