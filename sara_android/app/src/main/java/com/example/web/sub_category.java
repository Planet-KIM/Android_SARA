package com.example.web;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.web.api.JsonPlaceHolderApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class sub_category extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void event_connect(View view) {
        Toast.makeText(getApplicationContext(), "Item Selected", Toast.LENGTH_LONG).show();
        Intent intent2 = new Intent(getApplicationContext(), sub_item_select.class);
        startActivity(intent2);
    }
}
