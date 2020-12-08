package com.example.web;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.web.api.JsonPlaceHolderApi;
import com.example.web.models.PostItem;
import com.example.web.models.output;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class sub_output extends AppCompatActivity {


    private final String BASEURL = "https://walkplanetcat.com/";
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    private TextView sub_output;
    private ListView listView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_output);

        sub_output = findViewById(R.id.sub_output_text);
        listView = findViewById(R.id.listview_sub2);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Intent intent = getIntent();

        getPosts(intent.getStringExtra("fmu"));

    }

    /////두번 째 엑티비티
    private void getPosts(String item) {
        Call<List<output>> call = jsonPlaceHolderApi.getPost1(item);
        List<String> list = new ArrayList<>();

        call.enqueue(new Callback<List<output>>() {
            @Override
            public void onResponse(Call<List<output>> call, Response<List<output>> response) {
                if (!response.isSuccessful()) {

                    sub_output.setText("code" + response.code());
                    return;
                }

                List<output> posts = response.body();

                for (output post : posts) {
                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    content += "PARAMETER: " + post.getParameter() + "\n";
                    content += "UNIT: " + post.getUnit() + "\n";
                    list.add(content);
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(),
                        android.R.layout.simple_list_item_1, list);

                listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<output>> call, Throwable t) {

                sub_output.setText("Error"+t.getMessage());
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = (String)parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), item, Toast.LENGTH_LONG).show();
            }
        });
    }
}
