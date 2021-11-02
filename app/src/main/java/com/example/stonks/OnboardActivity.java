package com.example.stonks;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class OnboardActivity extends AppCompatActivity {


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_onboard);

        // Displays custom app logo in the action bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setIcon(R.drawable.welcome_white_yelllowmdpi);

        //Button on click listener is set to call the startNewActivity method
        Button nextButton = findViewById(R.id.btn_onboard_next);
        nextButton.setOnClickListener(v -> startNewActivity(0));

        //alertDialogBuilder is called to present a legal disclaimer to the user
        alertDialogBuilder();

        //Assigns local variable onboardImages to the linear layout that Images will be placed in
        LinearLayout onboardImages = findViewById(R.id.ll_onboard_images);
        LayoutInflater onboardInflater = LayoutInflater.from(this);

        int[] images = {R.drawable.img_onboard_choosemdpi, R.drawable.img_onboard_learnmdpi, R.drawable.img_onboard_playmdpi};
        String[] imageCaptions = {"Courses","Info","Quiz"};

        for (int i = 0; i < images.length; i++){

            View onBoardView = onboardInflater.inflate(R.layout.onboard_item, onboardImages, false);
            //TODO: this has been temporarily commented out as the captions are all included in the introduction
            //assigns the caption from imageCaption[] with the corresponding value of i
//            TextView imageCaption = onBoardView.findViewById(R.id.tv_onboard_item);
//            imageCaption.setText(imageCaptions[i]);

            //assigns the image from images[] with the corresponding value of i
            ImageView imageView = onBoardView.findViewById(R.id.iv_onboard_item);
            imageView.setImageResource(images[i]);

            //adds the onBoardView view to the linearlayout in activity_onboard.xml
            onboardImages.addView(onBoardView);

        }
    }

    public void startNewActivity(int buttonSelected) {
        if (buttonSelected == 0) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void alertDialogBuilder() {
        AlertDialog.Builder disclaimerDialogBuilder = new AlertDialog.Builder(OnboardActivity.this);
        disclaimerDialogBuilder.setCancelable(false);
        disclaimerDialogBuilder.setTitle("Disclaimer");
        disclaimerDialogBuilder.setMessage("The information on this application is provided for educational information only.\n\n" +
                "Stonks does not provide legal or other professional advice and disclaims any liability arising from the use of the information.\n\n" +
                "If you require legal or other expert advice you should seek assistance from a professional provider.");
        //If the user selects the accept button the alert dialog is dismissed
        disclaimerDialogBuilder.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        })
        //If the user selects the cancel button the app wil exit
        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                System.exit(0);
            }
        });
        disclaimerDialogBuilder.create().show();
    }

}
