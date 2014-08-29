// Brandon Mackey
// Java1
// Week 4
// Term: 1408

package com.java1wk4.bmackey.connectedapp;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import java.net.URL;
import java.net.URLConnection;


public class MainActivity extends Activity {


    static String TAG = "API App";
    ProgressBar myProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myProgress = (ProgressBar) findViewById(R.id.progressBar);
        myProgress.setVisibility(View.INVISIBLE);



        Button mainBtn = (Button) findViewById(R.id.btnSearch);
        mainBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                TextView movieQuery = (TextView) findViewById(R.id.etMovieSearch);
                String query = movieQuery.getText().toString();

                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                try{
                    String myURL = "http://api.rottentomatoes.com/api/public/v1.0/movies.json?apikey=6cvyrv4n86brwgx7s528kzru&q=";
                    URL queryURL = new URL(myURL + query + "&page_limit=1");
                    new getData().execute(queryURL);
                } catch (Exception e){
                    Log.e(TAG, "Invalid Search");
                }

            }
        });
        // END OF onCREATE METHOD //
    }

    private void loadDetailView(MyJsonData myJsonData){
        ((TextView) findViewById(R.id.tvDetailView)).setText(myJsonData.getRating());
        ((TextView) findViewById(R.id.tvDetailView2)).setText(myJsonData.getRuntime());
    }


    private class getData extends AsyncTask<URL, Integer, JSONObject> {

        final String TAG = "API DEMO ASYNCTASK";

        protected void onPreExecute(){
            myProgress = (ProgressBar) findViewById(R.id.progressBar);
            myProgress.setVisibility(View.VISIBLE);

        }

        @Override
        protected JSONObject doInBackground(URL... urls) {

            String jsonString = "";

            //COLLECT STRING RESPONSES FROM API
            for(URL queryURL : urls){
                try{
                    URLConnection conn = queryURL.openConnection();
                    jsonString = IOUtils.toString(conn.getInputStream());
                    break;
                } catch (Exception e){
                    Log.e(TAG, "Could not establish URLConnection to " + queryURL.toString());
                }
            }

            Log.i(TAG, "Received Data: " + jsonString);


            //CONVERT API STRING RESPONSE TO JSONOBJECT

            JSONObject apiData;

            try{
                apiData = new JSONObject(jsonString);
            } catch (Exception e){
                Log.e(TAG, "Cannot convert API response to JSON");
                apiData = null;
            }

            try{
                apiData = (apiData != null) ? apiData.getJSONArray("movies").getJSONObject(0) : null;
                Log.i(TAG, "API JSON data received: " + apiData.toString());
            } catch (Exception e){
                Log.e(TAG, "Could not parse data record from response: " + apiData.toString());
                apiData = null;
            }

            return apiData;
        }

        protected void onPostExecute(JSONObject apiData) {
            MyJsonData result = new MyJsonData(apiData);
            loadDetailView(result);
            myProgress.setVisibility(View.INVISIBLE);
        }
    }

// END //
}
