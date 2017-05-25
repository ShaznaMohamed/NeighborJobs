package com.neighbourjobs.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.neighbourjobs.Models.JobApplication;
import com.neighbourjobs.R;
import com.neighbourjobs.Utilities.Constants;

/**
 * Created by Shazna on 5/4/2016.
 */
public class JobApplySecondActivity extends AppCompatActivity implements DatePicker.OnDateChangedListener {


    private Firebase mFirebaseRef;
    RadioGroup addreference,appsrelocatee, appssupervisione, appssupportere;
    TextView refnamee, reftitle, appssalarye, appsnoofhrse, appsinfluense, appsmessage;
    String daate, srefemail, srefname;
    String relocate = "I don't want to relocate";
    String supervision="I don't want a suprervisor";
    String supporters = "I don't have any supporters";
    Button ba;
    DatePicker dp;
    Spinner spin;
    String arr[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apply_second);

        mFirebaseRef = new Firebase(Constants.FIREBASE_URL);

        addreference = (RadioGroup)findViewById(R.id.referencegrp);
        refnamee = (TextView)findViewById(R.id.refname);
        reftitle = (TextView)findViewById(R.id.redemail);
        spin = (Spinner)findViewById(R.id.appsavailablee);
        appsrelocatee = (RadioGroup)findViewById(R.id.appsrelocatee);
        appssupervisione = (RadioGroup)findViewById(R.id.appssupervisione);
        appssupportere = (RadioGroup)findViewById(R.id.appssupporterse);
        appssalarye = (TextView)findViewById(R.id.appssalarye);
        appsnoofhrse = (TextView)findViewById(R.id.appsnoofhrs);
        appsinfluense = (TextView)findViewById(R.id.appsinfluencee);
        appsmessage = (TextView)findViewById(R.id.appsmessagee);

        Intent intent = getIntent();
        Bundle extrass = intent.getExtras();

        arr = extrass.getStringArray("array");

        ViewReferee();
        RelocateRadio();
        SupervisoinRadio();
        SupporterRadio();
        readdata();

        ba = (Button)findViewById(R.id.applybutt);
        ba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onDateChanged(dp,1,1,1);
                insertApplication();
                Snackbar.make(view, "Your Application is submitted successfully", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });



    }

    public void ViewReferee() {

        addreference.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedID = addreference.getCheckedRadioButtonId();
                RadioButton radioSexbutton = (RadioButton) findViewById(selectedID);
                String cheked = radioSexbutton.getText().toString();

                if (cheked.equals("Yes")) {
                    refnamee.setVisibility(View.VISIBLE);
                    reftitle.setVisibility(View.VISIBLE);
                    srefname  = refnamee.getText().toString();
                    srefemail = reftitle.getText().toString();
                }
                else
                {
                    srefemail = "No referees";
                    srefname = "No referees";
                }

            }
        });
    }

    public void RelocateRadio() {


        appsrelocatee.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedID = appsrelocatee.getCheckedRadioButtonId();
                RadioButton radioSexbutton = (RadioButton) findViewById(selectedID);
                String cheked = radioSexbutton.getText().toString();

                if (cheked.equals("Yes")) {
                    relocate="Relocate me";
                }

            }
        });
    }

    public void SupervisoinRadio(){


        appssupervisione.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedID = appssupervisione.getCheckedRadioButtonId();
                RadioButton radioSexbutton = (RadioButton) findViewById(selectedID);
                String cheked = radioSexbutton.getText().toString();

                if (cheked.equals("Yes")) {
                    supervision="I need a supervisor";
                }

            }
        });
    }

    public void SupporterRadio() {


        appssupportere.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedID = appssupportere.getCheckedRadioButtonId();
                RadioButton radioSexbutton = (RadioButton) findViewById(selectedID);
                String cheked = radioSexbutton.getText().toString();

                if (cheked.equals("Yes")) {
                    supporters="I have supporters";
                }

            }
        });
    }

    public void readdata()
    {
        dp = (DatePicker)findViewById(R.id.datePicker);
        dp.setCalendarViewShown(false);

    }


    @Override
    public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {

        int day = dp.getDayOfMonth();
        int month = dp.getMonth()+1;
        int year = dp.getYear();

        daate = day + "/" + month + "/" + year + "";
    }


    public void insertApplication()
    {
        String name =  arr[0];
        String age = arr[1];
        String email = arr[2];
        String phone = arr[3];
        String skill = arr[4];
        String experience = arr[5];
        String covernote = arr[6];
        String jobname = arr[7];
        String sal = appssalarye.getText().toString();
        String available = spin.getSelectedItem().toString();
        String noofhrs = appsnoofhrse.getText().toString();
        String datee = daate;
        String relocatee = relocate;
        String supervise = supervision;
        String support = supporters;
        String refereename =  srefname;
        String refereeemail = srefemail;
        String influence = appsinfluense.getText().toString();
        String message = appsmessage.getText().toString();

        JobApplication jobApp = new JobApplication(name,age,email,phone,skill,experience,covernote,sal,available,noofhrs,datee,relocatee,supervise,support,refereename,refereeemail,influence,message,jobname);
        mFirebaseRef.child("jobapplication").setValue(jobApp);

    }


}
