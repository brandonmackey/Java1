// Brandon Mackey
// Java1
// Week 3
// Term: 1407

package com.Java1Wk3.bmackey.java1wk3;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by brandonmackey on 7/24/14.
 */
public class myData {


    public static JSONObject createJson(){
        // create data JSONobject
        JSONObject dataObject = new JSONObject();

        // create enum JSONobject
        JSONObject queryObject = new JSONObject();
        try {
            // create teams objects in enum
            for(myEnum team : myEnum.values()){

                //create team object
                JSONObject teamInfo = new JSONObject();

                //add info to object
                teamInfo.put("name", team.setName());

                teamInfo.put("stadium", team.setStadium());

                teamInfo.put("state", team.setState());

                queryObject.put(team.name().toString(), teamInfo);


            }

            dataObject.put("query", queryObject);

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return dataObject;
    }

    public static String readJSON(String selected){

        String result, name, stadium, state;

        JSONObject object = createJson();

        try {
            name    = object.getJSONObject("query").getJSONObject(selected).getString("name");
            stadium = object.getJSONObject("query").getJSONObject(selected).getString("stadium");
            state   = object.getJSONObject("query").getJSONObject(selected).getString("state");

            // Populates into textView
            result = "Name:  " + name + "\r\n"
                    + "Stadium:  " + stadium + "\r\n"
                    +  "State: " + state + "\r\n";

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            result = e.toString();
        }

        return result;

    }

}
