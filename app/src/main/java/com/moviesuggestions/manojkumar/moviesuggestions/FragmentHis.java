package com.moviesuggestions.manojkumar.moviesuggestions;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FragmentHis extends Fragment {
    private RecyclerView recyclerView;
    public static List<Movies> moviesList = new ArrayList<Movies>();
    public static String TAG = "MAIN_ACTIVITY_LOG";
    private String url;
    private ProgressDialog progressDialog;
    // Empty public constructor
    public FragmentHis() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_his, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.main_recycler_view);
        final RecycleAdapter recycleAdapter = new RecycleAdapter(getContext(), moviesList);
        url = "https://api.themoviedb.org/3/list/70010?api_key=a85135fc6142aa13cf24097b37635007&language=en-US";

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setAdapter(recycleAdapter);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Loading, Please Wait ...");
        progressDialog.show();

        // Initialize a new RequestQueue instance
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

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
        return view;
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
}
