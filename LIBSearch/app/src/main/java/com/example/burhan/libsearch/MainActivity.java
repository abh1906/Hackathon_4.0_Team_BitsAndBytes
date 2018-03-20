package com.example.burhan.libsearch;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public static String userdata;
    EditText et_login;
    String user="";
    String loginurl="";
    String loginres="";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Button login=findViewById(R.id.btn_login);
        et_login=findViewById(R.id.et_login);

        userdata="userdata";
        sharedPreferences=getSharedPreferences(userdata, Context.MODE_PRIVATE);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    public void login(){

        user=et_login.getText().toString();
        loginurl="http://45.55.34.11/login.php?p="+user;

        new login().execute();
    }


    public class login extends AsyncTask<String,String,String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            //super.onPostExecute(s);
            if(loginres.equals("yes"))
            {
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("username",user);
                editor.commit();

                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
            else{
                Toast.makeText(MainActivity.this,"Incorrect Username",Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url= new URL(loginurl);
                HttpURLConnection con=(HttpURLConnection)url.openConnection();
                con.setRequestMethod("GET");
                con.connect();

                BufferedReader bf=new BufferedReader(new InputStreamReader(con.getInputStream()));
                String vl=bf.readLine();
                loginres=vl;

            }
            catch (Exception e){
                System.out.print(e);
            }

            return null;
        }
    }
}
