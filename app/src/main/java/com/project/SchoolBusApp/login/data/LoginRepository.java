package com.project.SchoolBusApp.login.data;

import android.util.Log;

import com.project.SchoolBusApp.login.data.model.LoggedInUser;
import com.project.SchoolBusApp.login.data.model.LoginResponse;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class LoginRepository {

    private static volatile LoginRepository instance;

    private LoginDataSource dataSource;

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

    public Result<LoginResponse> login(String phoneNumber, String password) {
        // handle login
        Result<LoginResponse> result = dataSource.login(phoneNumber, password);
        Log.d("tag", "hello " + ((Result.Success<LoginResponse>) result).getData().getfirst_name());

        if (result instanceof Result.Success) {
            setLoggedInUser(((Result.Success<LoginResponse>) result).getData());
        }

        return result;
    }
}