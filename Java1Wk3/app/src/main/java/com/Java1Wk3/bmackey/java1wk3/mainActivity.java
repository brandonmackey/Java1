// Brandon Mackey
// Java1
// Week 3
// Term: 1407

package com.Java1Wk3.bmackey.java1wk3;


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





import static android.widget.AdapterView.OnItemSelectedListener;


public class mainActivity extends Activity {


    public Spinner mySpinner;
    ArrayAdapter<String> spinAdapter, listAdapter;
    public ListView myListView;
    static Context context;

    Context mContext;
    String[] mListItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        context = this;

        //Initializing Constants
        mContext = this;
        mListItems = getResources().getStringArray(R.array.teamsArray);

        final TextView textView1 = (TextView) findViewById(R.id.textView1);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            // Create Adapter //
            spinAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_dropdown_item, mListItems);
            spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            // Create Spinner //
            mySpinner = (Spinner) findViewById(R.id.spinner1);
            mySpinner.setAdapter(spinAdapter);
            mySpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    String selected = mListItems[position];

                    String myStr = myData.readJSON(selected);

                    textView1.setText(myStr);

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // DO NOTHING
                }
            });
        }else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            // Create ListView
            listAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, mListItems);

            // Create ListView //
            myListView = (ListView) findViewById(R.id.listView);
            myListView.setAdapter(listAdapter);

            myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    String selected = mListItems[position];

                    String myStr = myData.readJSON(selected);

                    textView1.setText(myStr);
                }
            });
        }

    // End of onCreate Method //
    }

// END
}
