// Brandon Mackey
// Java1
// Week 4
// Term: 1408

package com.java1wk4.bmackey.connectedapp;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by brandonmackey on 8/25/14.
 */
public class MyJsonData {
    static String TAG = "API App";

    private String mRating;
    private Integer mRuntime;

    public MyJsonData(String rating, Integer runtime){
        mRating = rating;
        mRuntime = runtime;
    }

    public MyJsonData(JSONObject movieData) {
        try {
            mRating  = movieData.getString("mpaa_rating");
            mRuntime = movieData.getInt("runtime");
        } catch (JSONException e) {
            Log.e(TAG, "Error pulling json");
        }

    }

    public String getRating(){
        return mRating;
    }

    public void setRating(String mRating){
        this.mRating = mRating;
    }

    public Integer getRuntime(){
        return mRuntime;
    }

    public void setRuntime(Integer mRuntime){
        this.mRuntime = mRuntime;
    }



// END //

}
