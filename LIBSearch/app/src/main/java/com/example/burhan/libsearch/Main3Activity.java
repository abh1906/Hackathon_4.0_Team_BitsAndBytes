package com.example.burhan.libsearch;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main3Activity extends AppCompatActivity {

    TextView tv_book,tv_author,tv_isbn,tv_av,shelf,num,rfid,rf;
    String result="";
    String surl="";
    String res="";
    String locurl="";
    String locres="";
    String ar[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        tv_book=findViewById(R.id.bookname);
        tv_author=findViewById(R.id.author);
        tv_isbn=findViewById(R.id.isbn);
        tv_av=findViewById(R.id.availability);

        Bundle extra=getIntent().getExtras();
        if(extra!=null){
            result=extra.getString("Result");
        }
        surl="http://45.55.34.11/avail111.php?p="+result;
        locurl="http://45.55.34.11/locate111.php?p="+result;
        new bookinfo().execute();


        Button locate=findViewById(R.id.btn_locate);
        locate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*AlertDialog.Builder mBuilder=new AlertDialog.Builder(Main3Activity.this);
                View mview=getLayoutInflater().inflate(R.layout.locate,null);
                shelf=(TextView) mview.findViewById(R.id.shelf);
                num=(TextView) mview.findViewById(R.id.num);
                rfid=(TextView) mview.findViewById(R.id.rfid);
                rf=(TextView) mview.findViewById(R.id.rf);

                mBuilder.setView(mview);
                AlertDialog dailog=mBuilder.create();
                dailog.show();*/

                new location().execute();
            }
        });

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

     public class bookinfo extends AsyncTask<String,String,String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            // super.onPostExecute(s);
            //Toast.makeText(Main3Activity.this,""+res,Toast.LENGTH_LONG).show();
            ar=res.split(",");
            tv_book.setText(""+ar[1]);
            tv_author.setText(""+ar[2]);
            tv_isbn.setText(""+ar[3]);
            tv_av.setText(""+ar[4]);
            int a4=Integer.parseInt(ar[4]);
            if(a4==0){
                tv_av.setTextColor(Color.rgb(205, 97, 85 ));
            }
            else{
                tv_av.setTextColor(Color.rgb(19, 141, 117));
            }
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url= new URL(surl);
                HttpURLConnection con=(HttpURLConnection)url.openConnection();
                con.setRequestMethod("GET");
                con.connect();

                BufferedReader bf=new BufferedReader(new InputStreamReader(con.getInputStream()));
                String value=bf.readLine();
                res=value;

            }
            catch (Exception e){
                System.out.print(e);
            }

            return null;
        }

    }

    public class location extends AsyncTask<String,String,String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            // super.onPostExecute(s);
            int a4=Integer.parseInt(ar[4]);
            if(a4>0)
            {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Main3Activity.this);
                View mview = getLayoutInflater().inflate(R.layout.locate, null);
                shelf = (TextView) mview.findViewById(R.id.shelf);
                num = (TextView) mview.findViewById(R.id.num);
                rfid = (TextView) mview.findViewById(R.id.rfid);
                rf = (TextView) mview.findViewById(R.id.rf);

                mBuilder.setView(mview);
                AlertDialog dailog = mBuilder.create();
                dailog.show();
                String ar1[]=locres.split(",");
                num.setText("" + ar1[1]);
                rf.setText("" + ar1[2]);
            }
            else{
                Toast.makeText(Main3Activity.this,"Book not Available",Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url= new URL(locurl);
                HttpURLConnection con=(HttpURLConnection)url.openConnection();
                con.setRequestMethod("GET");
                con.connect();

                BufferedReader bf=new BufferedReader(new InputStreamReader(con.getInputStream()));
                String value=bf.readLine();
                locres=value;

            }
            catch (Exception e){
                System.out.print(e);
            }

            return null;
        }

    }
}
