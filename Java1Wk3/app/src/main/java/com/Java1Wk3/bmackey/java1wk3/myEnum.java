// Brandon Mackey
// Java1
// Week 3
// Term: 1407

package com.Java1Wk3.bmackey.java1wk3;

/**
 * Created by brandonmackey on 7/24/14.
 */
public enum myEnum {

    // Created Custom Objects
    Indianapolis("Colts", "Lucas Oil Stadium", "Indiana"),
    NewEngland("Patriots", "Gillette Stadium", "Massachusetts"),
    Denver("Broncos", "Sports Authority Field at Mile High", "Colorado"),
    Dallas("Cowboys", "AT&T Stadium", "Texas"),
    NewYork("Giants", "MetLife Stadium", "New Jersey"),
    Seattle("Seahawks", "CenturyLink Field", "Washington"),
    Oakland("Raiders", "Oakland-Alameda County Coliseum", "California"),
    Chicago("Bears", "Soldier Field", "Illinois");

    // Custom object data points
    private final String name;
    private final String stadium;
    private final String state;

    private myEnum(String name, String stadium, String state){

        this.name = name;
        this.stadium = stadium;
        this.state = state;

    }

    public String setName(){
        return name;
    }

    public String setStadium(){
        return stadium;
    }

    public String setState(){
        return state;
    }
}

