package com.example.burhan.libsearch;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    String surl="";
    String result="";
    String prores="";
    String purl="";

    ListView listView;
    ArrayList<String> items;
    String blisturl="http://45.55.34.11/book.php/p=";
    String blistres="";

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mDrawerLayout=(DrawerLayout) findViewById(R.id.draw_icon);
        mToggle=new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView=(ListView) findViewById(R.id.list);
        new booklist().execute();

        TextView tv_username=findViewById(R.id.username);
        sharedPreferences=getSharedPreferences(MainActivity.userdata, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        String str= sharedPreferences.getString("username","");
        tv_username.setText(str);
        purl="http://45.55.34.11/profile.php?p="+str;

        Button profile=findViewById(R.id.prf);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profile();
            }
        });

        /*Button search=findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search();
            }
        });*/
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.newmenu,menu);
        final SearchView searchView=(SearchView) findViewById(R.id.search);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                ArrayList<String> templist=new ArrayList<>();
                for(String temp:items){
                    if(temp.toLowerCase().contains(s.toLowerCase())){
                        templist.add(temp);
                    }
                }
                ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(Main2Activity.this,android.R.layout.simple_list_item_1,templist);
                listView.setAdapter(arrayAdapter);
                searchView.setOnQueryTextListener(this);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    public void profile(){
        new profile().execute();
    }

   /* public void search(){
        new bookinfo().execute();
    }

   public class bookinfo extends AsyncTask<String,String,String>{
       @Override
       protected void onPreExecute() {
           super.onPreExecute();
       }

       @Override
       protected void onPostExecute(String s) {
          // super.onPostExecute(s);
           Main2Activity main2Activity=new Main2Activity();
           String txt=main2Activity.text;
           Toast.makeText(Main2Activity.this,""+result,Toast.LENGTH_LONG).show();
           Intent i=new Intent(Main2Activity.this,Main3Activity.class);
           i.putExtra("Result",result);
           startActivity(i);
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
               result=value;

              }
           catch (Exception e){
               System.out.print(e);
           }

           return null;
       }

   }*/

   public class profile extends AsyncTask<String,String,String>{
       @Override
       protected void onPreExecute() {
           super.onPreExecute();
       }

       @Override
       protected void onPostExecute(String s) {
           //super.onPostExecute(s);
           Intent intent=new Intent(Main2Activity.this,Main4Activity.class);
           intent.putExtra("ProfileResult",prores);
           startActivity(intent);
       }

       @Override
       protected String doInBackground(String... strings) {
           try {
               URL url= new URL(purl);
               HttpURLConnection con=(HttpURLConnection)url.openConnection();
               con.setRequestMethod("GET");
               con.connect();

               BufferedReader bf=new BufferedReader(new InputStreamReader(con.getInputStream()));
               String vl=bf.readLine();
               prores=vl;

           }
           catch (Exception e){
               System.out.print(e);
           }

           return null;
       }
   }

    public class booklist extends AsyncTask<String,String,String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            //super.onPostExecute(s);
            String ar[]=blistres.split(",");
            items = new ArrayList<>();
            for(int i=1;i<ar.length;i++){
               items.add(""+ar[i]) ;
            }

            ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(Main2Activity.this,android.R.layout.simple_list_item_1,items);
            listView.setAdapter(arrayAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String text=listView.getItemAtPosition(i).toString();
                    //Toast.makeText(Main2Activity.this,""+text,Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(Main2Activity.this,Main3Activity.class);
                    intent.putExtra("Result",text);
                    startActivity(intent);
                }
            });
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url= new URL(blisturl);
                HttpURLConnection con=(HttpURLConnection)url.openConnection();
                con.setRequestMethod("GET");
                con.connect();

                BufferedReader bf=new BufferedReader(new InputStreamReader(con.getInputStream()));
                String vl=bf.readLine();
                blistres=vl;
            }
            catch (Exception e){
                System.out.print(e);
            }

            return null;
        }
    }

}
