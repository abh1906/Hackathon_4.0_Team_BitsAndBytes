package com.example.burhan.libsearch;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main4Activity extends AppCompatActivity {

    TextView tv_firstname,tv_lastname,tv_email,tv_mobileno,tv_cardno,tv_bookissued;
    String prores="",issueres="",issueurl="";
    ImageView iv_dp;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        sharedPreferences=getSharedPreferences(MainActivity.userdata, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        String str= sharedPreferences.getString("username","");
        issueurl="http://45.55.34.11/issues.php?p="+str;

        Button btn_issue=findViewById(R.id.btn_issue);
        btn_issue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                issue();
            }
        });

        tv_cardno=findViewById(R.id.cardno);
        tv_firstname=findViewById(R.id.firstname);
        tv_lastname=findViewById(R.id.lastname);
        tv_email=findViewById(R.id.email);
        tv_mobileno=findViewById(R.id.mobileno);
        tv_bookissued=findViewById(R.id.bookissued);
        iv_dp=(ImageView)findViewById(R.id.image);

        Bundle extra=getIntent().getExtras();
        if(extra!=null){
            prores=extra.getString("ProfileResult");
        }
        String ar[]=prores.split(",");
        tv_cardno.setText(""+ar[0]);
        tv_firstname.setText(""+ar[1]);
        tv_lastname.setText(""+ar[2]);
        tv_email.setText(""+ar[3]);
        tv_mobileno.setText(""+ar[4]);
        tv_bookissued.setText(""+ar[6]);

        TextDrawable drawable = TextDrawable.builder()
                .buildRound(""+ar[5], Color.rgb(  36, 113, 163));
        iv_dp.setImageDrawable(drawable);

       if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

   @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void issue(){
        new issued().execute();
    }

    public class issued extends AsyncTask<String,String,String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            //super.onPostExecute(s);
            if(issueres.equals("0")){
                Toast.makeText(Main4Activity.this,"No Issued Books",Toast.LENGTH_SHORT).show();
            }
            else {
                Intent intent = new Intent(Main4Activity.this, Main5Activity.class);
                intent.putExtra("IssueResult", issueres);
                startActivity(intent);
            }
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url= new URL(issueurl);
                HttpURLConnection con=(HttpURLConnection)url.openConnection();
                con.setRequestMethod("GET");
                con.connect();

                BufferedReader bf=new BufferedReader(new InputStreamReader(con.getInputStream()));
                String vl=bf.readLine();
                issueres=vl;

            }
            catch (Exception e){
                System.out.print(e);
            }

            return null;
        }
    }

}
