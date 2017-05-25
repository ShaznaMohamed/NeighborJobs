package com.neighbourjobs.Fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.neighbourjobs.MainActivity;
import com.neighbourjobs.R;
import com.neighbourjobs.Utilities.Constants;

/**
 * Created by dsraj on 4/28/2016.
 */
public class JobPostLocationActivity extends AppCompatActivity {

    private Button mNext2, mNewAddress, mRemoveNewAddress;
    CardView location;
    Bundle extrasPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_post_location);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        extrasPayment = intent.getExtras();
        Initialize();
    }

    public void Initialize(){
        mNext2 = (Button) findViewById(R.id.next2);
        mNewAddress = (Button) findViewById(R.id.jobAddress);
        mRemoveNewAddress = (Button) findViewById(R.id.removeJobAddress);
        location = (CardView) findViewById(R.id.newLocation);

        mNext2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPayment();
            }
        });

        mNewAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNewLocation();
            }
        });

        mRemoveNewAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeNewLocation();
            }
        });
    }

    public void startPayment(){
        Intent i = new Intent(this, JobPostPaymentActivity.class);
        i.putExtras(extrasPayment);
        startActivity(i);
        this.finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent i = new Intent(this, JobPostStartActivity.class);
                startActivity(i);
                finish();
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

    public void showNewLocation(){
        location.setVisibility(View.VISIBLE);
    }

    public void removeNewLocation(){
        location.setVisibility(View.GONE);
    }


}
