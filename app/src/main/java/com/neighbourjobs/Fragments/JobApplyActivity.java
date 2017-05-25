package com.neighbourjobs.Fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.neighbourjobs.MainActivity;
import com.neighbourjobs.R;

/**
 * Created by Shazna on 5/4/2016.
 */
public class JobApplyActivity extends JobPostLocationActivity {


    Button bt, uploadresumee;
    TextView expperiodtext,appnamee, appagee, appemaile, appphonee, appskillse, appcovere;
    RadioGroup radioSexGroup;
    RadioGroup radiouploadgroup;
    String exp ="No experience";
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apply_job);

        expperiodtext = (TextView)findViewById(R.id.appexperiencee);
        appnamee = (TextView)findViewById(R.id.appnamee);
        appagee = (TextView)findViewById(R.id.appagee);
        appemaile = (TextView)findViewById(R.id.appemaile);
        appphonee = (TextView)findViewById(R.id.appphonee);
        appskillse = (TextView)findViewById(R.id.appskillse);
        appcovere = (TextView)findViewById(R.id.appcovere);

        bt = (Button)findViewById(R.id.jobappnext);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        extras = intent.getExtras();

        ViewExperianceRadio();

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             gonext();
            }
        });
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

    public void gonext()
    {
        Intent intent = new Intent(this, JobApplySecondActivity.class);
        Bundle extras = new Bundle();

        String arr[] = {appnamee.getText().toString(),appagee.getText().toString(), appemaile.getText().toString(), appphonee.getText().toString(), appskillse.getText().toString(),ViewExperianceRadio(), appcovere.getText().toString()} ;
        intent.putExtra("array",arr);
        startActivity(intent);
    }

    public String ViewExperianceRadio()
    {

            radioSexGroup = (RadioGroup)findViewById(R.id.expGroup);
            radioSexGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {

                    int selectedID = radioSexGroup.getCheckedRadioButtonId();
                    RadioButton radioSexbutton = (RadioButton) findViewById(selectedID);
                    String cheked = radioSexbutton.getText().toString();

                    if (cheked.equals("Yes")) {
                        expperiodtext.setVisibility(View.VISIBLE);
                        exp = expperiodtext.getText().toString();

                    }
                    else
                        exp = "I don't have experience";

                }
            });
        return exp;

    }

    public void ViewUploadResumeRadio()
    {
               radiouploadgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedID = radiouploadgroup.getCheckedRadioButtonId();
                RadioButton radioSexbutton = (RadioButton)findViewById(selectedID);
                String cheked = radioSexbutton.getText().toString();

                if(cheked.equals("Yes"))
                {
                    uploadresumee.setVisibility(View.VISIBLE);
                }

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
                Intent i = new Intent(this, JobRecentsActivity.class);
                startActivity(i);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
