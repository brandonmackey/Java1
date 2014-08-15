/*
Brandon Mackey
Java 1
Week 2
Term: 1408
*/

package com.Java1Wk1.bmackey.b_mackey_fundamentals;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;


public class MainActivity extends Activity implements AdapterView.OnItemClickListener {

    //Global Variables//
    TextView numberOfWords, avgLength;
    HashSet<String> myWordsSet;
    String wordsStr;
    AlertDialog.Builder alertBox;
    ListView myListView;
    ArrayAdapter<String> listAdapter;
    ArrayList<String> listArray;
    EditText addText;

    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Add Text
        addText = (EditText) findViewById(R.id.editText);

        // Add ListView //
        myListView = (ListView) findViewById(R.id.listView);
        myListView.setOnItemClickListener(this);


        // Add HashSet so the same numbers can't be added twice
        myWordsSet = new HashSet<String>();

        numberOfWords = (TextView) findViewById(R.id.textView);
        avgLength = (TextView) findViewById(R.id.textView2);

        numberOfWords.setVisibility(View.INVISIBLE);
        avgLength.setVisibility(View.INVISIBLE);

        // Add Text Button
        // WEEK 2 CHANGE //
        // CHANGED BUTTON TO TEXTVIEW //////////////////////////////////////////////////////////  WEEK 2 //
        final TextView addTextBtn = (TextView) findViewById(R.id.button);
        addTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wordsStr = addText.getText().toString();

                // WEEK 2 CHANGE //
                // CHANGED BUTTON TO TEXTVIEW ////////////////////////////////////////////////  WEEK 2 //
                TextView selectedButton = (TextView) v;

                if (selectedButton == addTextBtn) {
                    if (addText.length() == 0) {

                        Toast alert = Toast.makeText(getApplicationContext(), getString(R.string.toast1), Toast.LENGTH_SHORT);
                        alert.show();


                    }
                    // Checks hashSet for Duplicate Entries //
                    if (myWordsSet.contains(addText.getText().toString())) {
                        addText.setText("");

                        // HIDE KEYBOARD //
                        InputMethodManager hideKeyboard = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                        hideKeyboard.hideSoftInputFromWindow(addText.getWindowToken(), 0);

                        // Toast Alert //
                        Toast alert = Toast.makeText(getApplicationContext(),wordsStr + " " + getString(R.string.toast2), Toast.LENGTH_SHORT);
                        alert.show();
                    }
                    else if (addText.getText().length() != 0) {

                        // add to Hashset//
                        myWordsSet.add(wordsStr);

                        addToListView(myWordsSet);

                        // sets the number of contacts or numbers saved //
                        numberOfWords.setText(String.format(getString(R.string.numberOfWords), myWordsSet.size()));

                        // sets the average length of numbers entered //
                        avgLength.setText(String.format(getString(R.string.avgLength), getAvgLength()));

                        // Show TextView / ListView after button is pressed //
                        numberOfWords.setVisibility(View.VISIBLE);
                        avgLength.setVisibility(View.VISIBLE);

                        // Clears the EditText //
                        addText.setText("");


                        // HIDE KEYBOARD //
//                        InputMethodManager hideKeyboard = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
//                        hideKeyboard.hideSoftInputFromWindow(addText.getWindowToken(), 0);

                        // Toast Alert //
                        Toast alert = Toast.makeText(getApplicationContext(),wordsStr + " " + getString(R.string.toast3), Toast.LENGTH_SHORT);
                        alert.show();

                    }
                }
            }
        });
    }

    // Method for Dividing the Average //
    private float getAvgLength() {
        float avgLength = 0f;

        for (String text : myWordsSet) {
            avgLength += text.length();
        }
        avgLength /= myWordsSet.size();

        DecimalFormat myAvg = new DecimalFormat("#.00f");

        return Float.parseFloat(myAvg.format(avgLength));
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {


        // WEEK 2 CHANGE //
        // CHANGED ALERT DIALOG TO OK & REMOVE ALERT BOX ////////////////////////////////////////////////  WEEK 2 //

        // alert box //
        alertBox = new AlertDialog.Builder(context);
        // set title
        alertBox.setTitle(R.string.alertTitle);
        // set dialog message
        alertBox.setMessage(listArray.get(position));
        // set onClick and Dismiss button OK //
        alertBox
                .setCancelable(false)
                .setPositiveButton(R.string.Ok,new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {


                    }
                })
                .setNegativeButton(R.string.Remove,new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id)
                    {
                        // Remove from ListView //
                        listArray.remove(position);

                        listAdapter.notifyDataSetChanged();

                        dialog.cancel();

                    }
                });

        // Create and show AlertBox //
        AlertDialog alertDialog = alertBox.create();

        // WEEK 2 CHANGE //
        // ADDED ALERT ICON //////////////////////////////////////////////////////////////////  WEEK 2 //
        // Show Icon
        alertDialog.setIcon(R.drawable.ic_action_error);

        alertDialog.show();
    }

    // Take HashSet to string and add to ListView //
    public void addToListView(HashSet<String> addList){
        listArray = new ArrayList<String>(addList);

        listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listArray);

        myListView.setAdapter(listAdapter);
    }
}