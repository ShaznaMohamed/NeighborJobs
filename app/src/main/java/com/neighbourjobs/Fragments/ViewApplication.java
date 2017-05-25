package com.neighbourjobs.Fragments;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.neighbourjobs.MainActivity;
import com.neighbourjobs.Models.JobApplication;
import com.neighbourjobs.R;
import com.neighbourjobs.Utilities.Constants;

/**
 * Created by Shazna on 5/5/2016.
 */
public class ViewApplication extends AppCompatActivity {

    TextView name,age,email,phone,skills,exp,cover,sal,available,hrs,start,relocate,supervision,supporter,refereename,refereeemail,influence,message,jobbbname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_application);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Accept();

        name = (TextView)findViewById(R.id.appvnamee);
        age = (TextView)findViewById(R.id.appagee);
        email = (TextView)findViewById(R.id.appvemaile);
        phone = (TextView)findViewById(R.id.appvphonee);
        skills = (TextView)findViewById(R.id.appvskillse);
        exp = (TextView)findViewById(R.id.appvexp);
        cover = (TextView)findViewById(R.id.appvcovere);
        sal = (TextView)findViewById(R.id.appvsal);
        available = (TextView)findViewById(R.id.appvavailable);
        hrs = (TextView)findViewById(R.id.appvnoofhrs);
        start = (TextView)findViewById(R.id.appvstart);
        relocate = (TextView)findViewById(R.id.appvrelocate);
        supervision = (TextView)findViewById(R.id.appvsupervision);
        supporter = (TextView)findViewById(R.id.appvsupport);
        refereename = (TextView)findViewById(R.id.refname);
        refereeemail = (TextView)findViewById(R.id.redemail);
        influence = (TextView)findViewById(R.id.appsinfluencee);
        message = (TextView)findViewById(R.id.appsmessagee);
        jobbbname = (TextView)findViewById(R.id.jobbname);

    }

    @Override
    protected void onStart() {
        super.onStart();

        Firebase profileRef = new Firebase(Constants.FIREBASE_URL).child("jobapplication");
        profileRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                JobApplication pmn = dataSnapshot.getValue(JobApplication.class);
                String namee = "Name : "+pmn.getName();
                // String agee = age.getText().toString()+" : "+pmn.getAge();
                name.setText(namee);
                // age.setText(agee);
                email.setText("Email : "+pmn.getEmail());
                phone.setText("Phone : "+pmn.getPhone());
                skills.setText("Skills : "+pmn.getSkills());
                exp.setText("Experience : "+pmn.getExpperiod());
                cover.setText("Cover Note : "+pmn.getCovernote());
                sal.setText("Expected Salary : "+pmn.getSalary());
                available.setText("Available for : "+pmn.getAvailability());
                hrs.setText("Available working hours : "+pmn.getHrs());
                start.setText("Can start on : "+pmn.getStarting());
                relocate.setText("Relocation specification : "+pmn.getRelocate());
                supervision.setText("Supervisor specification : "+pmn.getSupervisor());
                supporter.setText("Supporters state : "+pmn.getSupporters());
                refereename.setText("Referee name : "+pmn.getRefereename());
                refereeemail.setText("Referee email : "+pmn.getRefereeemail());
                influence.setText("Influenced by : "+pmn.getInfluence());
                message.setText("Message : "+pmn.getMessage());
                jobbbname.setText(pmn.getJobname());

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.e("error", "eeror because " + firebaseError.getMessage());
            }
        });
    }

    public void Accept()
    {
        Button bt = (Button)findViewById(R.id.applybutt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Application is accepted successfully", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

        Button bt1 = (Button)findViewById(R.id.nexttochat);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Application is Rejected", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

        Button bt3 = (Button) findViewById(R.id.chatchat);
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewApplication.this, ChatMainActivity.class);
                startActivity(intent);
                finish();
            }
        });
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
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
