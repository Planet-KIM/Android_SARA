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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// Change EditText Value

public class sub_input extends AppCompatActivity {

    private final String BASEURL = "https://walkplanetcat.com/";
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    private TextView sub_input;
    private ListView listview;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_input);

        sub_input = findViewById(R.id.sub_input_text);
        listview = findViewById(R.id.listview_sub);

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
        Call<List<PostItem>> call = jsonPlaceHolderApi.getPost2(item);
        List<String> list = new ArrayList<>();

        call.enqueue(new Callback<List<PostItem>>() {
            @Override
            public void onResponse(Call<List<PostItem>> call, Response<List<PostItem>> response) {
                if (!response.isSuccessful()) {

                    sub_input.setText("code" + response.code());
                    return;
                }

                List<PostItem> posts = response.body();

                for (PostItem post : posts) {
                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    content += "PARAMETER: " + post.getParameter() + "\n";
                    content += "UNIT: " + post.getUnit() + "\n";
                    content += "VALUE: " + post.getValue() + "\n";
                    list.add(content);
                    //sub_input.append(content);
                    //sub_input.append(item);
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(),
                        android.R.layout.simple_list_item_1, list);

                listview.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<PostItem>> call, Throwable t) {

                sub_input.setText("Error"+t.getMessage());
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = (String)parent.getItemAtPosition(position);
                Toast.makeText(getBaseContext(), item, Toast.LENGTH_LONG).show();
                //intent.putExtra("fmu", item.split(" ")[2].trim());
                //getPosts2(item);
                //startActivity(intent);
            }
        });
    }
}
