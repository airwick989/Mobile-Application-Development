package com.example.a03_oct_async;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("Thread: ", Thread.currentThread().getName().toString());
        new LongActivity().execute();

    }
}

class LongActivity extends AsyncTask<Void, Void, Void>{
    @Override
    protected Void doInBackground(Void... voids) {
        InputStream in =null;
        int responseCode = -1;
        try{

            URL url = new URL("https://picsum.photos/200/300");//"http://192.xx.xx.xx/mypath/img1.jpg
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setDoInput(true);
            con.connect();
            responseCode = con.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK)
            {
                //download
                in = con.getInputStream();
                in.close();
                Log.d("Thread 2: ", "Image downloaded");
            }

        }
        catch(Exception ex){
            Log.d("Exception",ex.toString());
        }
        return null;
    }
}

