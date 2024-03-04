package com.example.athletify.ui;

import android.content.Intent;

import android.os.Bundle;
import android.os.Handler;

import android.view.WindowManager;

import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.ViewModelProvider;

import com.airbnb.lottie.LottieAnimationView;

import com.example.athletify.R;
import com.example.athletify.repositories.user.IUserRepository;
import com.example.athletify.repositories.user.UserRepository;
import com.example.athletify.ui.autenticazione.WelcomeActivity;
import com.example.athletify.viewmodels.UserViewModel;
import com.example.athletify.viewmodels.UserViewModelFactory;

public class LaunchScreenActivity extends AppCompatActivity {

    private UserViewModel userViewModel;

    public LaunchScreenActivity() {
        super(R.layout.activity_launch_screen);
    }

    ImageView logo, splashimg;
    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        IUserRepository userRepository = new UserRepository(getApplication());
        userViewModel = new ViewModelProvider(this,
                new UserViewModelFactory(getApplication(), userRepository)).get(UserViewModel.class);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        logo = findViewById(R.id.app_logo);
        splashimg = findViewById(R.id.img);
        lottieAnimationView = findViewById(R.id.lottie);

        splashimg.animate().translationY(-2500).setDuration(1000).setStartDelay(3000);
        logo.animate().translationY(2000).setDuration(1000).setStartDelay(3000);
        lottieAnimationView.animate().translationY(1500).setDuration(1000).setStartDelay(3000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(LaunchScreenActivity.this, WelcomeActivity.class));
                overridePendingTransition(0,0);
            }
        },4500);
    }
}