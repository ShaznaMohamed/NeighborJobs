package com.neighbourjobs.Intro;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.neighbourjobs.Login.LoginActivity;
import com.neighbourjobs.MainActivity;
import com.neighbourjobs.R;

/**
 * Created by dsraj on 4/28/2016.
 */
public class MyIntro extends AppIntro2 {

    // Please DO NOT override onCreate. Use init
    @Override
    public void init(Bundle savedInstanceState) {

        // Add your slide's fragments here
        // AppIntro will automatically generate the dots indicator and buttons.

        //addSlide(first_fragment);
        //addSlide(second_fragment);
        //addSlide(third_fragment);
        //addSlide(fourth_fragment);

        //addSlide(SampleSlide.newInstance(R.layout.intro_1));
        //addSlide(SampleSlide.newInstance(R.layout.intro_2));
        //addSlide(SampleSlide.newInstance(R.layout.intro_3));
        //addSlide(SampleSlide.newInstance(R.layout.intro_4));



        // Instead of fragments, you can also use our default slide
        // Just set a title, description, background and image. AppIntro will do the rest
        addSlide(AppIntroFragment.newInstance("Welcome to Neighbour Jobs. Redefine the way you interact with your neighbours!", "It's Free!", R.drawable.intro_1,  Color.parseColor("#7ECBDF")));
        addSlide(AppIntroFragment.newInstance("Posts the jobs you want to get done", "Accept or Reject applicants", R.drawable.intro_2, Color.parseColor("#98DAEA")));
        addSlide(AppIntroFragment.newInstance("Chat with the community", "Interact and get to know better", R.drawable.intro_3, Color.parseColor("#5996CD")));
        addSlide(AppIntroFragment.newInstance("Apply for jobs when you have free time", "Free time = More money", R.drawable.intro_4, Color.parseColor("#4C8DB3")));

        // OPTIONAL METHODS

        // SHOW or HIDE the statusbar
        showStatusBar(false);

        // Edit the color of the nav bar on Lollipop+ devices
        //setNavBarColor(R.color.colorPrimary);

        // Turn vibration on and set intensity
        // NOTE: you will need to ask VIBRATE permission in Manifest if you haven't already
        //setVibrate(true);
        //setVibrateIntensity(30);

        // Animations -- use only one of the below. Using both could cause errors.
        //setFadeAnimation(); // OR
        //setZoomAnimation(); // OR
        //setFlowAnimation(); // OR
        //setSlideOverAnimation(); // OR
        //setDepthAnimation(); // OR
        //setCustomTransformer(yourCustomTransformer);

        // Permissions -- takes a permission and slide number
        //askForPermissions(new String[]{Manifest.permission.CAMERA}, 3);
    }

    @Override
    public void onNextPressed() {
        // Do something when users tap on Next button.
    }

    @Override
    public void onDonePressed() {
        // Do something when users tap on Done button.
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onSlideChanged() {
        // Do something when slide is changed
    }
}