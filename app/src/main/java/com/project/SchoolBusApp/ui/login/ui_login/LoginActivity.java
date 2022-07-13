package com.project.SchoolBusApp.ui.login.ui_login;

import android.annotation.SuppressLint;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.project.SchoolBusApp.databinding.ActivityLoginBinding;
import com.project.SchoolBusApp.ui.home.Home_page;
import com.project.SchoolBusApp.R;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private ActivityLoginBinding binding;
    SharedPreferences sharedPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences sharedPreferences = getSharedPreferences("IM_IN", 0);

        if(sharedPreferences != null){
            String welcome = getString(R.string.welcome) + sharedPreferences.getString("firstName","Null");
            Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
            startActivity(new Intent(LoginActivity.this, Home_page.class));
        }

        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        final EditText phoneNumberEditText = binding.username;
        final EditText passwordEditText = binding.password;
        final Button loginButton = binding.login;
        final ProgressBar loadingProgressBar = binding.loading;

        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getphoneNumberEditText() != null) {
                    phoneNumberEditText.setError(getString(loginFormState.getphoneNumberEditText()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });

        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }
                loadingProgressBar.setVisibility(View.GONE);
                if (loginResult.getError() != null) {
                    showLoginFailed(loginResult.getError());
                }
                if (loginResult.getSuccess() != null) {
                    updateUiWithUser(loginResult.getSuccess());
                }
                //setResult(Activity.RESULT_OK);

                //Complete and destroy login activity once successful
//                finish();
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(String.valueOf(phoneNumberEditText.getText()),
                        passwordEditText.getText().toString());
            }
        };

        phoneNumberEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(phoneNumberEditText.getText().toString(),
                            passwordEditText.getText().toString());
                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                loginViewModel.login(phoneNumberEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        });
    }

    @SuppressLint("ApplySharedPref")
    private void updateUiWithUser(LoggedInUserView model) {

        if(model.getStatus().equals("success")) {
            String welcome = getString(R.string.welcome) + model.getDisplayName();
            sharedPreferences = getSharedPreferences("IM_IN",0);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("id",model.getId());
            editor.putString("firstName",model.getFirstName());
            editor.putString("lastName",model.getLastName());
            editor.putString("phone",model.getPhoneNumber());
            editor.putString("email", model.getEmail());
            editor.commit();
            startActivity(new Intent(LoginActivity.this, Home_page.class));
            Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
        }
        else if(model.getStatus().equals("failed")){
            String welcome = "Wrong PhoneNumber / Password";
            Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
        }
        else{
            String welcome = "Oops Error happened try again... :(";
            Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
        }
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}