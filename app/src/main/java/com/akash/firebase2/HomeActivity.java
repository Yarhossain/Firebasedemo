package com.akash.firebase2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.akash.firebase2.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding homeBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
}
