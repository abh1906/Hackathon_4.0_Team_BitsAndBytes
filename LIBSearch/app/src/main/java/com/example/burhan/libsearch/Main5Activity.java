package com.example.burhan.libsearch;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class Main5Activity extends AppCompatActivity {

    String issueres="";
    SharedPreferences sharedPreferences;
    TextView b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15,b16,b17,b18,b19,b20,b21;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        b1=findViewById(R.id.b1);b2=findViewById(R.id.b2); b3=findViewById(R.id.b3);b4=findViewById(R.id.b4);b5=findViewById(R.id.b5);b6=findViewById(R.id.b6);b7=findViewById(R.id.b7);b8=findViewById(R.id.b8);
        b9=findViewById(R.id.b9);b10=findViewById(R.id.b10); b11=findViewById(R.id.b11);b12=findViewById(R.id.b12);b13=findViewById(R.id.b13);b14=findViewById(R.id.b14);b15=findViewById(R.id.b15);b16=findViewById(R.id.b16);
        b17=findViewById(R.id.b17);b18=findViewById(R.id.b18); b19=findViewById(R.id.b19);b20=findViewById(R.id.b20);b21=findViewById(R.id.b21);

        sharedPreferences=getSharedPreferences(MainActivity.userdata, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        String str= sharedPreferences.getString("username","");

        Bundle extra=getIntent().getExtras();
        if(extra!=null){
            issueres=extra.getString("IssueResult");
        }

        String ar[]=issueres.split(",");
            if(ar.length==8) {
            b1.setText(ar[1]);
            b2.setText(ar[2]);
            b3.setText(ar[3]);
            b4.setText(ar[4]);
            b5.setText(ar[5]);
            b6.setText(ar[6]);
            b7.setText(ar[7]);
        }

         else if(ar.length==15){
            b1.setText(ar[1]);
        b2.setText(ar[2]);
        b3.setText(ar[3]);
        b4.setText(ar[4]);
        b5.setText(ar[5]);
        b6.setText(ar[6]);
        b7.setText(ar[7]);
        b8.setText(ar[8]);
        b9.setText(ar[9]);
        b10.setText(ar[10]);
        b11.setText(ar[11]);
        b12.setText(ar[12]);
        b13.setText(ar[13]);
        b14.setText(ar[14]);
        }



        else if(ar.length==22){
            b1.setText(ar[1]);
        b2.setText(ar[2]);
        b3.setText(ar[3]);
        b4.setText(ar[4]);
        b5.setText(ar[5]);
        b6.setText(ar[6]);
        b7.setText(ar[7]);
        b8.setText(ar[8]);
        b9.setText(ar[9]);
        b10.setText(ar[10]);
        b11.setText(ar[11]);
        b12.setText(ar[12]);
        b13.setText(ar[13]);
        b14.setText(ar[14]);
        b15.setText(ar[15]);
        b16.setText(ar[16]);
            b17.setText(ar[17]);
            b18.setText(ar[18]);
            b19.setText(ar[19]);
            b20.setText(ar[20]);
            b21.setText(ar[21]);
        }

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
}
