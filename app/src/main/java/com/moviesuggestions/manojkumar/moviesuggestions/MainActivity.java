package com.moviesuggestions.manojkumar.moviesuggestions;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Movies> moviesList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moviesList = new ArrayList<>();
        moviesList.add(new Movies("Album 1", R.drawable.album1));
        moviesList.add(new Movies("Album 2", R.drawable.album2));
        moviesList.add(new Movies("Album 3", R.drawable.album3));
        moviesList.add(new Movies("Album 4", R.drawable.album4));
        moviesList.add(new Movies("Album 5", R.drawable.album5));
        moviesList.add(new Movies("Album 6", R.drawable.album6));
        moviesList.add(new Movies("Album 7", R.drawable.album7));


        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.main_recycler_view);
        RecycleAdapter recycleAdapter = new RecycleAdapter(getApplicationContext(), moviesList);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(recycleAdapter);
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


