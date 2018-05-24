package com.moviesuggestions.manojkumar.moviesuggestions;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import static com.bumptech.glide.request.RequestOptions.centerCropTransform;


public class MainActivity extends AppCompatActivity {

    public static List<Movies> moviesList = new ArrayList<Movies>();
    public static List<Movies> profile1MoviesList = new ArrayList<>();
    public static List<Movies> profile2MoviesList = new ArrayList<>();
    public static String TAG = "MAIN_ACTIVITY_LOG";
    private String url;
    private ProgressDialog progressDialog;
    private LinearLayout linearLayout;
    private RecyclerView recyclerView;
    BottomSheetBehavior bottomSheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        url = "https://api.themoviedb.org/3/list/70010?api_key=a85135fc6142aa13cf24097b37635007&language=en-US";
        linearLayout = (LinearLayout) findViewById(R.id.main_activity_linear_layout);
        recyclerView = (RecyclerView) findViewById(R.id.main_recycler_view);

        final RecycleAdapter recycleAdapter = new RecycleAdapter(getApplicationContext(), moviesList);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(recycleAdapter);

        bottomSheetBehavior = BottomSheetBehavior.from(linearLayout);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading, Please Wait ...");
        progressDialog.show();

        // Initialize a new RequestQueue instance
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());
                try {
                    readJSONData(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                progressDialog.hide();
                recycleAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, error.getMessage());
            }
        });

        requestQueue.add(jsonObjectRequest);
        Log.d(TAG, "Movies are "+moviesList.size());
        for(int i=0; i<moviesList.size(); i++) {
            Log.d(TAG,  "Movies are "+moviesList.get(i).getMovie());
        }


    }

    // JSON reader
    private void readJSONData(JSONObject response) throws JSONException {

        Log.d(TAG, response.toString());
        JSONArray jsonArray = response.getJSONArray("items");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String movieTitle = jsonObject.getString("title");
            String movieTumbnail = "https://image.tmdb.org/t/p/w200/" + jsonObject.getString("poster_path");
            Movies movies = new Movies(movieTitle, movieTumbnail);
            moviesList.add(movies);
        }

    }

    private void httpRequest() {
        String str = "https://api.themoviedb.org/3/search/movie?api_key=a85135fc6142aa13cf24097b37635007&query=Jack Reacher";
        Task task = new Task(str);
        task.execute();
    }

    private String readStream(InputStream inputStream) throws IOException {
        BufferedReader bufferedInputStream = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder total = new StringBuilder();

        String line;
        while ((line = bufferedInputStream.readLine()) != null) {
            Log.d("LOG_DEBUG", "Line " + line);
            total.append(line).append('\n');
        }

        Log.d("LOG_DEBUG", total.toString());

        return total.toString();
    }

    class Task extends AsyncTask<String, Void, String> {

        HttpURLConnection httpURLConnection;
        URL url;
        String str;
        String returnString = null;

        public Task(String str) {
            this.str = str;
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                url = new URL(str);
                httpURLConnection = (HttpURLConnection) url.openConnection();

                InputStream inputStream = httpURLConnection.getInputStream();
                if (inputStream != null) {
                    Log.d("LOG_DEBUG", "InputStream is not null");
                    returnString = readStream(inputStream);
                }
            } catch (IOException e) {
                Log.d("LOG_DEBUG", "IOException " + e.toString());
            } finally {
                httpURLConnection.disconnect();
            }

            return returnString;
        }

        @Override
        protected void onPostExecute(String s) {
            onFinishing(s);
        }


        private String onFinishing(String s) {
            return s;
        }
    }
}


