package com.neighbourjobs.Fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.neighbourjobs.MainActivity;
import com.neighbourjobs.R;

/**
 * Created by dsraj on 4/28/2016.
 */
public class JobPostStartActivity extends AppCompatActivity {

    private String mJobTitle, mJobDescription, mJobMain, mJobSub, mJobType;
    private EditText mJobTitleText, mJobDescriptionText;
    private Spinner mJobMainSpinner, mJobSubSpinner;
    private RadioGroup mRadioJobType;
    private RadioButton mRadioType;
    private Button mNext1;
    private  CoordinatorLayout coordinatorLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_post_start);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Initialize();
    }

    public void Initialize(){
        mJobTitleText = (EditText) findViewById(R.id.jobTitle);
        mJobDescriptionText = (EditText) findViewById(R.id.jobDesc);
        mJobMainSpinner = (Spinner) findViewById(R.id.spinnerMain);
        mJobSubSpinner = (Spinner) findViewById(R.id.spinnerSub);
        mRadioJobType = (RadioGroup) findViewById(R.id.radioTypeGroup);
        mNext1 = (Button) findViewById(R.id.Next1);
        mNext1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Validate();
            }
        });
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.jobPostCoordinator);
    }

    public void InitializeValues(){
        mJobTitle = mJobTitleText.getText().toString();
        mJobDescription = mJobDescriptionText.getText().toString();
        mJobMain = mJobMainSpinner.getSelectedItem().toString();
        mJobSub = mJobSubSpinner.getSelectedItem().toString();
        int selectedId = mRadioJobType.getCheckedRadioButtonId();
        mRadioType = (RadioButton) findViewById(selectedId);
        mJobType = mRadioType.getText().toString();

    }

    public void Validate(){
        if(mRadioJobType.getCheckedRadioButtonId()== -1){
            new AlertDialog.Builder(this)
                    .setMessage("Fill required information")
                    .setCancelable(false)
                    .setPositiveButton("Okay", null)
                    .show();
        }
        else{
            goNext();
        }
    }

    public void goNext(){
        InitializeValues();
        Intent intent = new Intent(this, JobPostLocationActivity.class);
        Bundle extras = new Bundle();
        extras.putString("mJobTitle", mJobTitle);
        extras.putString("mJobDescription", mJobDescription);
        extras.putString("mJobMain", mJobMain);
        extras.putString("mJobSub", mJobSub);
        extras.putString("mJobType",mJobType );
        intent.putExtras(extras);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
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
        this.finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
