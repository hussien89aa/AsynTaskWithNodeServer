package com.example.hussienalrubaye.myapplication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

public class Main2Activity extends AppCompatActivity {
    TextView txtv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
     txtv=(TextView)findViewById(R.id.textView3);

        String myurl = "http://selling.alruabye.net/UsersWS.asmx/Login?UserName=a&Password=حسين"  ;
        new  MyAsyncTaskgetNews().execute(myurl);
    }
    public class MyAsyncTaskgetNews extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {


        }
        @Override
        protected String  doInBackground(String... params) {
            String NewsData="";
            try {
                 InputStream  inputStream;
                HttpClient httpClient =new DefaultHttpClient();
                HttpResponse httpResponse =httpClient.execute(new HttpGet(params[0]));
                inputStream = httpResponse.getEntity().getContent();
                 NewsData=Stream2String(inputStream);
                publishProgress(NewsData );

            } catch (Exception e) {
                // TODO Auto-generated catch block

            }
            return null;
        }
        protected void onProgressUpdate(String... progress) {

            try {

                txtv.setText(progress[0]);


            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
        protected void onPostExecute(String  result2){

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
