package com.neighbourjobs.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.neighbourjobs.Models.Job;
import com.neighbourjobs.Models.User;
import com.neighbourjobs.R;
import com.neighbourjobs.Utilities.Constants;
import com.neighbourjobs.Utilities.Randoms;

/**
 * Created by dsraj on 5/4/2016.
 */
public class JobDetailActivity extends AppCompatActivity {

    Bundle fromSearch;
    FloatingActionButton floatingbutton;
    private String  mJobTitle, mJobKey, mJobUser, mJobDescription, mJobMain, mJobSub,
            mJobType, mJobPayMode, mJobPayType, abc123string,
            mJobAddress1, mJobAddress2, mJobCity, mJobZipCode;

    private TextView mJobTitleText, mJobDescriptionText, mJobMainText, mJobSubText,
            mJobTypeText, mJobPayModeText, mJobPayTypeText, abc123stringText,
            mJobAddress1Text, mJobAddress2Text, mJobCityText, mJobZipCodeText;
    private Firebase mFirebaseRef;
    private Job selectedJob;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_detail);

        mFirebaseRef = new Firebase(Constants.FIREBASE_URL);

        Intent intent = getIntent();
        fromSearch = intent.getExtras();
        mJobTitle = fromSearch.getString("mJobTitle");
        mJobKey = fromSearch.getString("mJobKey");        

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(mJobTitle);

        loadBackdrop();
        
        Initialize();

        InitializeValues();
        
        MoveToApplypage();
    }
    
    public void  Initialize(){
        mJobTitleText  = (TextView) findViewById(R.id.jobTitle);
        mJobDescriptionText = (TextView) findViewById(R.id.jobDesc);
        mJobMainText = (TextView) findViewById(R.id.jobMain);
        mJobSubText = (TextView) findViewById(R.id.jobSub);
        mJobTypeText = (TextView) findViewById(R.id.jobOnline); 
        mJobPayModeText = (TextView) findViewById(R.id.jobMode); 
        mJobPayTypeText = (TextView) findViewById(R.id.jobType); 
        abc123stringText = (TextView) findViewById(R.id.jobAmount);
        mJobAddress1Text = (TextView) findViewById(R.id.jobA1);
        mJobAddress2Text = (TextView) findViewById(R.id.jobA2);
        mJobCityText = (TextView) findViewById(R.id.jobCity); 
        mJobZipCodeText  = (TextView) findViewById(R.id.jobZip);
    }
    
    public void  InitializeValues(){
        mFirebaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                DataSnapshot jobsnapshot = snapshot.child("jobs").child(mJobKey);
                selectedJob = jobsnapshot.getValue(Job.class);
                mJobTitle = selectedJob.getmJobTitle(); 
                mJobUser = selectedJob.getmJobUser(); 
                mJobDescription = selectedJob.getmJobDescription(); 
                mJobMain = selectedJob.getmJobMain(); 
                mJobSub = selectedJob.getmJobSub();
                mJobType = selectedJob.getmJobType();
                mJobPayMode = selectedJob.getmJobPayMode(); 
                mJobPayType = selectedJob.getmJobPayType(); 
                abc123string = selectedJob.getmJobPayAmount();
                mJobAddress1 = selectedJob.getmJobAddress1();
                mJobAddress2 = selectedJob.getmJobAddress2();
                mJobCity = selectedJob.getmJobCity();
                mJobZipCode = selectedJob.getmJobZipCode();

                mJobTitleText.setText(mJobTitle);
                mJobDescriptionText.setText(mJobDescription);
                mJobMainText.setText(mJobMain);
                mJobSubText.setText(mJobSub);
                mJobTypeText.setText(mJobType);
                mJobPayModeText.setText(mJobPayMode);
                mJobPayTypeText.setText(mJobPayType);
                abc123stringText.setText(abc123string);
                mJobAddress1Text.setText(mJobAddress1);
                mJobAddress2Text.setText(mJobAddress2);
                mJobCityText.setText(mJobCity);
                mJobZipCodeText.setText(mJobZipCode);
                
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }
    private void loadBackdrop() {
        final ImageView imageView = (ImageView) findViewById(R.id.backdrop);
        Glide.with(this).load(Randoms.getRandomCheeseDrawable()).centerCrop().into(imageView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void MoveToApplypage()
    {
        floatingbutton = (FloatingActionButton)findViewById(R.id.applyjob);
        floatingbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(JobDetailActivity.this, JobApplyActivity.class);
                Bundle extras = new Bundle();
                extras.putString("selectedjob", mJobKey);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });
    }
}
