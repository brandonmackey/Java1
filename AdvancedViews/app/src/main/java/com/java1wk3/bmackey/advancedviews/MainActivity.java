// Brandon Mackey
// Java1
// Week 3
// Term: 1408

package com.java1wk3.bmackey.advancedviews;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends Activity {

    public Spinner mySpinner;
    ArrayAdapter<String> spinAdapter, listAdapter;
    public ListView myListView;
    static Context context;

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        //Initializing Constants
        mContext = this;

        final Teams[] myTeamsArray = new Teams[8];

        myTeamsArray[0] = new Teams("Indianapolis", "Colts", "Lucas Oil Stadium", "Indiana");
        myTeamsArray[1] = new Teams("New England", "Patriots", "Gillette Stadium", "Massachusetts");
        myTeamsArray[2] = new Teams("Denver", "Broncos", "Sports Authority Field at Mile High", "Colorado");
        myTeamsArray[3] = new Teams("Dallas", "Cowboys", "AT&T Stadium", "Texas");
        myTeamsArray[4] = new Teams("New York", "Giants", "MetLife Stadium", "New Jersey");
        myTeamsArray[5] = new Teams("Seattle", "Seahawks", "CenturyLink Field", "Washington");
        myTeamsArray[6] = new Teams("Oakland", "Raiders", "Oakland-Alameda County Coliseum", "California");
        myTeamsArray[7] = new Teams("Chicago", "Bears", "Soldier Field", "Illinois");

        final ArrayList<String> teamsArrayList = new ArrayList<String>();

        // Pulls first object in my array object to display in spinner/listview //
        teamsArrayList.add(myTeamsArray[0].getTeamCity());
        teamsArrayList.add(myTeamsArray[1].getTeamCity());
        teamsArrayList.add(myTeamsArray[2].getTeamCity());
        teamsArrayList.add(myTeamsArray[3].getTeamCity());
        teamsArrayList.add(myTeamsArray[4].getTeamCity());
        teamsArrayList.add(myTeamsArray[5].getTeamCity());
        teamsArrayList.add(myTeamsArray[6].getTeamCity());
        teamsArrayList.add(myTeamsArray[7].getTeamCity());

        // Check for Orientation //
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            // Create Adapter //
            spinAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, teamsArrayList);
            spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            // Create Spinner //
            mySpinner = (Spinner) findViewById(R.id.spinner1);
            mySpinner.setAdapter(spinAdapter);
            mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                   DetailView(mySpinner.getSelectedItem().toString(), myTeamsArray[mySpinner.getSelectedItemPosition()]);

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // DO NOTHING
                }
            });
        }else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            // Create ListView
            listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, teamsArrayList);

            // Create ListView //
            myListView = (ListView) findViewById(R.id.listView);
            myListView.setAdapter(listAdapter);

            myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    DetailView(myListView.getItemAtPosition(position).toString(), myTeamsArray[position]);

                }
            });
        }

    }

    public void DetailView(String s, Teams teams) {

        TextView teamName = (TextView) findViewById(R.id.textViewName);
        TextView teamStadium = (TextView) findViewById(R.id.textViewStadium);
        TextView teamState = (TextView) findViewById(R.id.textViewState);

        teamName.setText(teams.getName());
        teamStadium.setText(teams.getStadium());
        teamState.setText(teams.getState());


    }


}
