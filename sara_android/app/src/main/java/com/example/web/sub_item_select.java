package com.example.web;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.web.api.JsonPlaceHolderApi;
import com.example.web.models.input;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class sub_item_select extends AppCompatActivity {

    // 처음 초기 setting 해주기
    private final String BASEURL = "https://walkplanetcat.com/";

    // listview와 result 값을 넣을 textview 생성자 호출
    private TextView textViewResult;
    private ListView listview;

    // rest api 호출하기 위한 생성자 생성
    private JsonPlaceHolderApi jsonPlaceHolderApi;


    List<String> ids = new ArrayList<>();
    List<String> parameters = new ArrayList<>();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        textViewResult = findViewById(R.id.textview);
        listview = findViewById(R.id.listview);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        getPosts();
    }


    private void getPosts() {
        Call<List<input>> call = jsonPlaceHolderApi.getPost();
        List<String> list = new ArrayList<>();

        call.enqueue(new Callback<List<input>>() {
            @Override
            public void onResponse(Call<List<input>> call, Response<List<input>> response) {
                // 응답이 성공하지 않았을 경우에 에러메시지를 텍스트에 출력해주는 것입니다.
                if (!response.isSuccessful()) {
                    textViewResult.setText("code: " + response.code());
                    return;
                }
                List<input> posts = response.body();

                for (input post : posts) {
                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    ids.add(post.getId());
                    content += "NAME: " + post.getName() + "\n";
                    parameters.add(post.getName());
                    list.add(content);
                }

                sub_item_select.CustomList adapter = new sub_item_select.CustomList(sub_item_select.this);
                listview.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<input>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = (String)parent.getItemAtPosition(position);
                Toast.makeText(getBaseContext(), item.split(" ")[2].trim(), Toast.LENGTH_LONG).show();
            }
        });
    }

    // 커스텀 리스트 레이아웃를 구성하고 이에 들어가 있는 뷰들의 기능을 구성했습니다.
    public class CustomList extends ArrayAdapter<String> {

        private final Activity context;

        public CustomList(Activity context){
            super(context, R.layout.listitem, parameters);
            this.context = context;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent){

            LayoutInflater inflater = context.getLayoutInflater();
            View rowView = inflater.inflate(R.layout.listitem, null, true);

            // 뷰들을 모두 생성자로 불러와서 코드에서 다룰 예정에 잇습니다.
            ImageView imageView = (ImageView)rowView.findViewById(R.id.listimage);
            TextView titleView = (TextView)rowView.findViewById(R.id.listitle);
            TextView idView = (TextView)rowView.findViewById(R.id.listgenre);
            Button input = (Button)rowView.findViewById(R.id.listinput);
            Button output = (Button)rowView.findViewById(R.id.listoutput);

            titleView.setText(" "+ parameters.get(position));
            idView.setText(" FMU ID : "+ids.get(position));

            // INPUT button을 click하면 발생하는 이벤트
            input.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 무엇을 클릭하였는지 길게 메세지 출력
                    Toast.makeText(context, parameters.get(position), Toast.LENGTH_LONG).show();
                    // Intent에 sub_input.class 연결하기
                    Intent intent = new Intent(context, sub_input.class);
                    // sub_input에 보낼 값("fmu='fmuname'")
                    intent.putExtra("fmu", parameters.get(position));
                    // sub_input.class 이동하기
                    startActivity(intent);

                }
            });

            // OUTPUT button을 click하면 발생하는 이벤트
            output.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 무엇을 클릭하였는지 길게 메세지 출력
                    Toast.makeText(context, parameters.get(position), Toast.LENGTH_LONG).show();
                    // Intent 로 sub_output.class 연결하기
                    Intent intent2 = new Intent(getApplicationContext(), sub_output.class);
                    // sub_output으로 보낼 값 ("fmu='fmuname'")
                    intent2.putExtra("fmu", parameters.get(position));
                    // sub_output.class로 이동합니다.
                    startActivity(intent2);
                }
            });
            return rowView;
        }
    }

}
