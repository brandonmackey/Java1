/*
Brandon Mackey
Java 1
Week 2
Term: 1407
*/

package com.Java1Wk1.bmackey.java1wk1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;
import java.util.HashSet;


public class Main extends Activity {

    //Global Variables//
    TextView numberOfNumbs, avgLength;
    HashSet<String> myContacts;
    String phoneNum;
    AlertDialog.Builder alertBox;

    final Context context = this;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Add Phone Number Text
        final EditText addNumb = (EditText) findViewById(R.id.editText2);

        // Add HashSet so the same numbers can't be added twice
        myContacts = new HashSet<String>();

        numberOfNumbs = (TextView) findViewById(R.id.textView);
        avgLength = (TextView) findViewById(R.id.textView2);

        numberOfNumbs.setVisibility(View.INVISIBLE);
        avgLength.setVisibility(View.INVISIBLE);

        // Add Phone Number Button
        final Button addNumBtn = (Button) findViewById(R.id.button);
        addNumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneNum = addNumb.getText().toString();


                Button selectedButton = (Button) v;


                if (selectedButton == addNumBtn) {
                    if (addNumb.length() == 0) {

                        Toast alert = Toast.makeText(getApplicationContext(), "Please Enter A Phone Number", Toast.LENGTH_SHORT);
                        alert.show();
                    }
                    if (addNumb.getText().length() != 0) {

                        // add to Hashset//
                        myContacts.add(phoneNum);

                        // Show TextView / ListView after button is pressed //
                        numberOfNumbs.setVisibility(View.VISIBLE);
                        avgLength.setVisibility(View.VISIBLE);


                        // alert box //

                        alertBox = new AlertDialog.Builder(context);
                        // set title
                        alertBox.setTitle("Save Number?");
                        // set dialog message
                        alertBox
                                .setMessage(addNumb.getText().toString())
                                .setCancelable(false)
                                .setPositiveButton("Save",new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {

                                        // sets the number of contacts or numbers saved //
                                        numberOfNumbs.setText(String.format(getString(R.string.numberOfNumbs), myContacts.size()));

                                        // sets the average length of numbers entered //
                                        avgLength.setText(String.format(getString(R.string.avgLength), getAvgLength()));

                                        // Clears the EditText //
                                        addNumb.setText("");

                                                                                                                // WEEK 2 UPDATE //
                                        // Alerts user the number was added //               // ADDED THE NUMBER FROM THE EDITTEXT TO THE TOAST //
                                        Toast alert = Toast.makeText(getApplicationContext(), "Number Added " + phoneNum, Toast.LENGTH_SHORT);
                                        alert.show();
                                    }
                                })
                                .setNegativeButton("Removed",new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id)
                                    {
                                        // Clears the EditText //
                                        addNumb.setText("");
                                        dialog.cancel();

                                        Toast alert = Toast.makeText(getApplicationContext(), "Number NOT Saved", Toast.LENGTH_SHORT);
                                        alert.show();
                                    }
                                });

                        // create alert dialog
                        AlertDialog alertDialog = alertBox.create();

                        // Show Icon
                        alertDialog.setIcon(R.drawable.ic_launcher);

                        // show it
                        alertDialog.show();


//                        alertBox = new AlertDialog.Builder(Main.this).create();
//                        alertBox.setTitle("Saved!");
//                        alertBox.setMessage(addNumb.getText().toString());
//                        alertBox.setButton(DialogInterface.BUTTON_POSITIVE, "OK", (DialogInterface.OnClickListener) null);
//                        alertBox.show();

//                        // sets the number of contacts or numbers saved //
//                        numberOfNumbs.setText(String.format(getString(R.string.numberOfNumbs), myContacts.size()));
//
//                        // sets the average length of numbers entered //
//                        avgLength.setText(String.format(getString(R.string.avgLength), getAvgLength()));
//
//                        // Clears the EditText //
//                        addNumb.setText("");
//                                                                                            // WEEK 2 UPDATE //
//                        // Alerts user the number was added //               // ADDED THE NUMBER FROM THE EDITTEXT TO THE TOAST //
//                        Toast alert = Toast.makeText(getApplicationContext(), "Number Added " + phoneNum, Toast.LENGTH_SHORT);
//                        alert.show();
                    }
                }
            }
        });

    }
    // WEEK 2 UPDATE / FIX  //
    // FIXED METHOD // TAKEN OUT OF OnCLICK EVENT //
    // Method for Dividing the Average //
    private float getAvgLength() {
        float avgLength = 0f;

        for (String text : myContacts) {
            avgLength += text.length();
        }
        avgLength /= myContacts.size();

        DecimalFormat myAvg = new DecimalFormat("#.00");

        return Float.parseFloat(myAvg.format(avgLength));
    }





}