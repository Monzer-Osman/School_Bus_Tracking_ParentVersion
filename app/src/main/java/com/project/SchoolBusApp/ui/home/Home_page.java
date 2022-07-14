package com.project.SchoolBusApp.ui.home;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.project.SchoolBusApp.R;
import com.project.SchoolBusApp.databinding.ActivityHomePageBinding;

public class Home_page extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityHomePageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarHomePage.toolbar);


        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.mapsFragment, R.id.nav_profile, R.id.nav_feedback,R.id.nav_send_message,R.id.inbox, R.id.outbox, R.id.nav_trackbus)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home_page);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_page, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home_page);
        TextView name = findViewById(R.id.name_view99);
        TextView email = findViewById(R.id.email_view99);

        StringBuilder fullName = new StringBuilder();
        SharedPreferences sharedPreferences = getSharedPreferences("IM_IN",0);

        fullName.append(sharedPreferences.getString("firstName","null"));
        fullName.append(" ");
        fullName.append(sharedPreferences.getString("lastName","null"));

        name.setText(fullName);
        email.setText(sharedPreferences.getString("email","null"));

        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}