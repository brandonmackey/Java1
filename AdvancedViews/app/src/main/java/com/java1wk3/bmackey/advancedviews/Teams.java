// Brandon Mackey
// Java1
// Week 3
// Term: 1408

package com.java1wk3.bmackey.advancedviews;

/**
 * Created by brandonmackey on 8/21/14.
 */
public class Teams {

    // Custom object data points
    private String mTeamCity;
    private String mName;
    private String mStadium;
    private String mState;

    public Teams(String _teamCity, String _name, String _stadium, String _state){

        this.mTeamCity = _teamCity;
        this.mName = _name;
        this.mStadium = _stadium;
        this.mState = _state;
    }


    // Getter and Setter //
    public String getTeamCity(){
        return mTeamCity;
    }

    public String getName(){
        return mName;
    }


    public String getStadium(){
        return mStadium;
    }

    public String getState(){
        return mState;
    }

}
