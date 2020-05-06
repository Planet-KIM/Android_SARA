package com.example.web;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity {

    private static String IP_ADDRESS = "192.168.10.10";
    private static String TAG = "sara_php_test";

    private EditText mEditTextEmail;
    private EditText mEditTextName;
    private EditText mEditTextPassword;
    private TextView mTextViewResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewResult = (TextView)findViewById(R.id.textView_main_result);
        mTextViewResult.setMovementMethod(new ScrollingMovementMethod());
    }

    public void submit(View view) {
        //이메일, 이름, 비밀번호 들어가는 위젯(Edittext)를 객체화해서 코드에서 다룰 수 있게 해줍니다.
        mEditTextEmail = (EditText)findViewById(R.id.editText_main_email);
        mEditTextName = (EditText)findViewById(R.id.editText_main_name);
        mEditTextPassword = (EditText)findViewById(R.id.editText_main_password);

        //위젯의 다루기위해서는 객체화를 해줘야합니다.
        //그리고 그객체에서 text를 가져옵니다.
        String email = mEditTextEmail.getText().toString();
        String name = mEditTextName.getText().toString();
        String password = mEditTextPassword.getText().toString();

        //이 구문이 시작되면서 php를 들어가는 구문이 시작됩니다.
        InsertData task = new InsertData(); //객체 생성
        task.execute("http://" + IP_ADDRESS + "/author/register", email, name, password); // 실 실행

        //값을 넣고 이름 부분과, 나라 텍스트 부분을 Null로 만들어줘서 다시 작성할 수 있게 해줍니다.
        mEditTextEmail.setText("");
        mEditTextName.setText("");
        mEditTextPassword.setText("");
    }


    class InsertData extends AsyncTask<String, Void, String>{

        //값이 연결되는 부분을 표현하기 위해서 받은 위젯
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //폼을 제출하면 진행흐름을 보여주는 위젯
            progressDialog = ProgressDialog.show(MainActivity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            mTextViewResult.setText(result);
            Log.d(TAG, "POST response  - " + result);
        }


        @Override
        protected String doInBackground(String... params) {

            String serverURL = (String)params[0];
            String email = (String)params[1];
            String name = (String)params[2];
            String password = (String)params[3];

            String postParameters = "email=" +email + "name=" + name + "&password=" + password;

            try {
                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);

                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.connect();

                //여기서 outputStream을 사용한 이유를 파악해야합니다.
                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();


                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "POST response code - " + responseStatusCode);

                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line = null;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }

                bufferedReader.close();

                return sb.toString();

            } catch (Exception e) {
                Log.d(TAG, "InsertData: Error ", e);
                return new String("Error: " + e.getMessage());
            }
        }
    }


}