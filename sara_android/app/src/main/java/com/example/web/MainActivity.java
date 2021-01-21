package com.example.web;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.web.api.JsonPlaceHolderApi;
import com.example.web.models.LoginResponse;
import com.example.web.models.input;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Call;
import retrofit2.converter.gson.GsonConverterFactory;



public class MainActivity extends AppCompatActivity {
    
    // rest api로 전환하기 전에 webview
    // private WebView mWebView;
    // 처음 초기 setting 해주기
    private final String BASEURL = "https://walkplanetcat.com/";
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    private EditText edit_id;
    private EditText edit_pwd;

    // 시작할 때 같이 시작하는 함수입니다.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /* mobile-view (Now Don't use it this code)
        mWebView = (WebView)findViewById(R.id.webView);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.getSettings().setSupportZoom(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.loadUrl("http://34.64.241.126/category");
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.setWebViewClient(new WebViewClient());
        */

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
    }


    // Category activity로 이어지는 Event입니다.
    public void event_signin(View view) {
        Toast.makeText(this, "success" , Toast.LENGTH_LONG).show();
        edit_id = findViewById(R.id.edt_id1);
        edit_pwd = findViewById(R.id.edt_pwd1);

        Intent intent = new Intent(getApplicationContext(), sub_category.class);
        startActivity(intent);

        /*
        Call<LoginResponse> call = jsonPlaceHolderApi.login(edit_id.getText().toString() , edit_pwd.getText().toString());
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (!response.isSuccessful()) {
                    String code = String.valueOf(response.code());
                    // Intent로 sub_category.class로 이동하게 해주는 것입니다.
                    Intent intent = new Intent(getApplicationContext(), sub_category.class);
                    startActivity(intent);
                    //return;
                }

                LoginResponse postResponse = response.body();

                String content = "";
                content += "Code : " + response.code() + "\n";
                content += "name: " + postResponse.isname() + "\n";
                content += "password: " + postResponse.isPassword() + "\n";
                Toast.makeText(getApplicationContext(), content, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                String message = t.getMessage();
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            }
        });*/

    }

    /*mobile-view (Now Don't use it this code)
    private class WebVieClientClass extends WebViewClient{

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.d("check URL", url);
            view.loadUrl(url);
            return true;
        }
    }*/
}