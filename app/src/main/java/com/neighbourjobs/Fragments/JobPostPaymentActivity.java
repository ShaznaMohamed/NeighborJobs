package com.neighbourjobs.Fragments;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.firebase.client.AuthData;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.MutableData;
import com.firebase.client.Query;
import com.firebase.client.Transaction;
import com.firebase.client.ValueEventListener;
import com.neighbourjobs.Login.LoginActivity;
import com.neighbourjobs.MainActivity;
import com.neighbourjobs.Models.Job;
import com.neighbourjobs.Models.User;
import com.neighbourjobs.R;
import com.neighbourjobs.Utilities.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dsraj on 4/28/2016.
 */
public class JobPostPaymentActivity extends AppCompatActivity {

    private User loggedUser;
    private Button mPostJob;
    private EditText abc123text;
    private Bundle extrasPayment;
    private Firebase mFirebaseRef;
    private AlertDialog.Builder builder;
    private ProgressDialog mAuthProgressDialog;
    private CoordinatorLayout coordinatorLayout;
    private Spinner mPayTypeSpinner, mPayModeSpinner;
    private String  mJobUser, mJobTitle, mJobDescription, mJobMain, mJobSub,
                    mJobType, mJobPayMode, mJobPayType, abc123string,
                    mJobAddress1, mJobAddress2, mJobCity, mJobZipCode,mJobKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_post_payment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mFirebaseRef = new Firebase(Constants.FIREBASE_URL);
        Intent intent = getIntent();
        extrasPayment = intent.getExtras();
        Initialize();
    }

    public void Initialize(){

        mAuthProgressDialog = new ProgressDialog(this);
        mAuthProgressDialog.setTitle("Please wait");
        mAuthProgressDialog.setMessage("Adding");
        mAuthProgressDialog.setCancelable(false);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.postedlayout);

        builder = new AlertDialog.Builder(this, R.style.MyAlertDialogStyle);
        builder.setTitle("Success");
        builder.setMessage("You job was successfully posted");

        mPayModeSpinner = (Spinner) findViewById(R.id.payMode);
        mPayTypeSpinner = (Spinner) findViewById(R.id.payType);

        mPostJob = (Button) findViewById(R.id.Posted);
        mPostJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddPressed();
            }
        });

        abc123text = (EditText) findViewById(R.id.abc123);

        getLogged();
        getAddress();
        InitializeValues();
    }

    public void InitializeValues(){
        mJobTitle = extrasPayment.getString("mJobTitle");
        mJobDescription = extrasPayment.getString("mJobDescription");
        mJobMain = extrasPayment.getString("mJobMain");
        mJobSub = extrasPayment.getString("mJobSub");
        mJobType = extrasPayment.getString("mJobType");
        mJobPayMode = mPayModeSpinner.getSelectedItem().toString();
        mJobPayType = mPayTypeSpinner.getSelectedItem().toString();
        mJobKey = "Temporary";
    }

    public void getLogged(){
        mFirebaseRef.addAuthStateListener(new Firebase.AuthStateListener() {
            @Override
            public void onAuthStateChanged(AuthData authData) {
                if (authData != null) {
                    mJobUser = authData.getUid();
                } else {

                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void getAddress(){
        mFirebaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot snapshot) {
            DataSnapshot usersnapshot = snapshot.child("users").child(mJobUser);
            loggedUser = usersnapshot.getValue(User.class);
            mJobAddress1 = loggedUser.getmAddress1();
            mJobAddress2 = loggedUser.getmAddress2();
            mJobCity = loggedUser.getmCity();
            mJobZipCode = loggedUser.getmZipCode();
        }
        @Override
        public void onCancelled(FirebaseError firebaseError) {
        }
    });
    }

    public void onAddPressed(){
        abc123string = abc123text.getText().toString();
        mAuthProgressDialog.show();
        Firebase postRef = mFirebaseRef.child("jobs").push();
        Job newJob = new Job(mJobKey, mJobUser, mJobTitle, mJobDescription, mJobMain, mJobSub,
                mJobType, mJobPayMode, mJobPayType, abc123string,
                mJobAddress1, mJobAddress2, mJobCity, mJobZipCode);
        postRef.setValue(newJob);

        String jobId = postRef.getKey();

        Firebase KeyRef = mFirebaseRef.child("jobs").child(jobId);
        KeyRef.child("mJobKey").setValue(jobId);

        mAuthProgressDialog.dismiss();

        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, JobPostLocationActivity.class);
                startActivity(intent);
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to discard?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        goMain();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    public void goMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
