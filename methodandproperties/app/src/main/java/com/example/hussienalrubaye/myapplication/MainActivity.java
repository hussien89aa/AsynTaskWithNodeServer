package com.example.hussienalrubaye.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;

import android.widget.EditText;
import android.widget.TextView;


import java.io.BufferedInputStream;
import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {
  TextView txtv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      txtv=(TextView)findViewById(R.id.textView3);


    }


    public void buclick(View view) {
        String myurl = "http://10.0.2.2:3000/comments";
      new  MyAsyncTaskgetNews().execute(myurl);


    }

    public void BuAdd(View view) {
        EditText ETName=(EditText)findViewById(R.id.ETName);
       EditText ETComment=(EditText)findViewById(R.id.ETComment);
        String myurl = "http://10.0.2.2:3000/add?PersonName="+ ETName.getText().toString()+"&PersonComment=" +ETComment.getText().toString();
        new  MyAsyncTaskgetNews().execute(myurl);
    }
    String NewsData="";
    public class MyAsyncTaskgetNews extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            NewsData="";

        }
        @Override
        protected String  doInBackground(String... params) {

            publishProgress("open connection" );
            try
            {
              URL url = new URL(params[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                  InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                publishProgress("start  read buffer" );
                 NewsData=Stream2String(in);
                in.close();



            } catch (Exception e) {
                publishProgress("cannot connect to server" );
            }

            return null;

        }
        protected void onProgressUpdate(String... progress) {

            txtv.setText(progress[0]);

        }
        protected void onPostExecute(String  result2){

            txtv.setText(NewsData);
        }




    }

    public String Stream2String(InputStream inputStream) {
        BufferedReader bureader=new BufferedReader( new InputStreamReader(inputStream));
        String line ;
        String Text="";
        try{
            while((line=bureader.readLine())!=null) {
                Text+=line;
            }
            inputStream.close();
        }catch (Exception ex){}
        return Text;
    }

}
