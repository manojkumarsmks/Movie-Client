package com.moviesuggestions.manojkumar.moviesuggestions;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    /*private void httpRequest() {
        String str = "https://api.themoviedb.org/3/search/movie?api_key=a85135fc6142aa13cf24097b37635007&query=Jack Reacher";
        Task task = new Task(str);
        task.execute();
    }

    private void readStream(InputStream inputStream) throws IOException {
        BufferedReader bufferedInputStream = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder total = new StringBuilder();

        String line;
        while ((line = bufferedInputStream.readLine())!= null) {
            Log.d("LOG_DEBUG", "Line "+line);
            total.append(line).append('\n');
        }

        Log.d("LOG_DEBUG", total.toString());
    }

    class Task extends AsyncTask<String, Void, Void> {

        HttpURLConnection httpURLConnection;
        URL url;
        String str;

        public Task(String str) {
            this.str = str;
        }
        @Override
        protected Void doInBackground(String... strings) {
            try {
                url = new URL(str);
                httpURLConnection = (HttpURLConnection) url.openConnection();

                InputStream inputStream = httpURLConnection.getInputStream();
                if (inputStream != null) {
                    Log.d("LOG_DEBUG", "InputStream is not null");
                    readStream(inputStream);
                }
            } catch (IOException e) {
                Log.d("LOG_DEBUG", "IOException " + e.toString());
            } finally {
                httpURLConnection.disconnect();
            }



            return null;
        }
    }*/
}


