package com.example.stonks;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.stonks.databinding.ActivityMainBinding;

import java.util.ArrayList;

/**
 * MainActivity.java
 * Version 1.0
 *'Stonks' is an educational app designed to teach financial investment principles
 *
 * TODO: Use recycler view if there are too many courses
 */
public class
MainActivity extends AppCompatActivity {

    //View member variables
    TextView titleTextView;
    TextView subtitleTextView;

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Displays custom app logo in the action bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.actionbar_image, null);
        getSupportActionBar().setCustomView(view);

        //Checks if this is the first time that the user has launched the app
        Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isFirstRun", true);
        //If the user is launching the app for the first time the onboard activity is launched
        if (isFirstRun) {
            //OnboardActivity is presented to the user
            Intent intent = new Intent(this, OnboardActivity.class);
            startActivity(intent);
        }
        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                .putBoolean("isFirstRun", false).commit();

        //sets an onclick listener for the 'how to play' button which launches onButtonPressed() Method
        Button onBoardButton = findViewById(R.id.btn_main_onboard);
        onBoardButton.setOnClickListener(v -> onButtonPressed(0));

        setListView();

    }

    public void setListView(){
        titleTextView = findViewById(R.id.tv_main_title);
        subtitleTextView = findViewById(R.id.tv_main_description);

        titleTextView.setText("Courses");
        subtitleTextView.setText("Choose a course to learn below");
        //title[] holds the string list of all course titles
        String[] title = {"Introduction to Investment", "Equities","Investor Profiles", "KiwiSaver", "Term Deposits", "Managed Funds"};
        //description[] holds the string list of all course descriptions
        String[] description = {"Learn the basic concepts of investing", "What are equities, and how do they work?","What type of investor are you?", "Learn how to save for your first home!", "What are term deposits and how can you use them?", "Invest and have someone else manage the investment. Sound good?"};
        //courseNum[] holds the integer list of all course numbers to be passed to info activity
        int[] courseNum = {0,1,2,3,4,5};

        ArrayList<Course> courseArrayList = new ArrayList<>();

        for(int i = 0; i< title.length; i++){

            Course course = new Course(title[i], description[i], courseNum[i]);
            courseArrayList.add(course);
        }

        ListAdapter listAdapter = new ListAdapter(MainActivity.this, courseArrayList);
        binding.lvMainListview.setAdapter(listAdapter);
        binding.lvMainListview.setClickable(true);
    }

    public void onButtonPressed(int buttonPressed) {
        if (buttonPressed == 0) {
            Intent intent = new Intent(this, OnboardActivity.class);
            startActivity(intent);
        }
    }

}