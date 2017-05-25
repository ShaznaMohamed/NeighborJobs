package com.neighbourjobs.Fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.neighbourjobs.MainActivity;
import com.neighbourjobs.Models.User;
import com.neighbourjobs.R;
import com.neighbourjobs.Models.Profile;
import com.neighbourjobs.Utilities.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Shazna on 3/16/2016.
 */
public class ProfileImageUploadActivity extends AppCompatActivity {

    private static final int RESULT_LOAD_IMAGE  = 1;

    ImageView imagetoupload;
    EditText firstname, lastname, password, address1, address2, city, zipcode, province,country, email;
    private Firebase mFirebaseRef;
    String skill="", mUID;
    Button edisave,uploadbtnid ;
    User loggedUser;
    CheckBox c1,c2,c3,c4,c5,c6,c7,c8,c9,c10;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null )//checks whether image selected
        {
            Uri selectedImage = data.getData(); //address of the selected image taken
            imagetoupload.setImageURI(selectedImage);//set selected image to display
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_edit);

        firstname = (EditText)findViewById(R.id.edfirstname);
        lastname = (EditText)findViewById(R.id.edlastname);
        email = (EditText)findViewById(R.id.edemail);
        address1 = (EditText)findViewById(R.id.edaddress1);
        address2 = (EditText)findViewById(R.id.edaddress2);
        city = (EditText)findViewById(R.id.edcity);
        zipcode = (EditText)findViewById(R.id.edzip);
        province = (EditText)findViewById(R.id.edprovince);
        country = (EditText)findViewById(R.id.edcountry);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mFirebaseRef = new Firebase(Constants.FIREBASE_URL);
        uploadbtnid = (Button)findViewById(R.id.uploadbutn);
        imagetoupload = (ImageView)findViewById(R.id.edimage);


        getLogged();

        uploadbtnid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE); //open gallery
            }
        });

        itemClicked();
        edisave = (Button)findViewById(R.id.edsave);
        edisave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addProfileDetails();
                // insertskill();
                updateuser();
                Snackbar.make(view, "Profile change successful", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
    }

    public void addProfileDetails(){

        //Firebase ref = new Firebase(Constants.FIREBASE_URL);
        String name = firstname.getText().toString();
        //String status = edistatus.getText().toString();
        String status = itemClicked();
        String email = lastname.getText().toString();
        //int phone = Integer.parseInt(ediphone.getText().toString());
        String phone = zipcode.getText().toString();
        String address = address1.getText().toString();

        Profile pm = new Profile(name, status, email, phone, address);
        //User pm = new User()
        mFirebaseRef.child("profile").setValue(pm);
    }

    public String itemClicked()
    {
        String skills[]={"Gardening","Computing","Cleaning","Petting","Baby Stitting", "Driving","Cooking","Designing","Teaching","Nursing"};
        int checkid[] = {R.id.s1,R.id.s2 ,R.id.s3,R.id.s4,R.id.s5,R.id.s6,R.id.s7,R.id.s8,R.id.s9,R.id.s10};

        for ( int i=0; i<10; i++)
        {
            CheckBox checkBox = (CheckBox)findViewById(checkid[i]);
            if(checkBox.isChecked())
            {
                skill = skills[i]+", "+skill;
            }
        }

        return skill;
    }

    public void updateuser()
    {
        //Firebase ref = mFirebaseRef.child("users").child(mUID);
        Firebase ref = mFirebaseRef.child("users").child("d608fb45-5355-4822-87b4-ebf400f7a203");
        Map<String, Object> updates = new HashMap<String, Object>();
        updates.put("mFirst",firstname.getText().toString());
        updates.put("mLast",lastname.getText().toString());
        updates.put("mCity", city.getText().toString());
        updates.put("mCountry", country.getText().toString());
        updates.put("mAddress1", address1.getText().toString());
        updates.put("mAddress2", address2.getText().toString());
        updates.put("mEmail", email.getText().toString());
        updates.put("mProvince", province.getText().toString());
        updates.put("mZipCode", zipcode.getText().toString());
        updates.put("mPassword",firstname.getText().toString());

        ref.updateChildren(updates);

    }

    public void insertskill()
    {
        String skill = itemClicked();
    }

    @Override
    public void onStart() {
        super.onStart();

        mFirebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                // DataSnapshot usersnapshot = snapshot.child("users").child(mUID);
                DataSnapshot usersnapshot = snapshot.child("users").child(mUID);
                loggedUser = usersnapshot.getValue(User.class);
                String s1 = loggedUser.getmLast();
                String s2 = loggedUser.getmFirst();
                firstname.setText(s2);
                lastname.setText(s1);
                address1.setText(loggedUser.getmAddress1());
                address2.setText(loggedUser.getmAddress2());
                zipcode.setText(loggedUser.getmZipCode());
                city.setText(loggedUser.getmCity());
                country.setText(loggedUser.getmCountry());
                email.setText(loggedUser.getmEmail());
                province.setText(loggedUser.getmProvince());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.e("error", "error because " + firebaseError.getMessage());
            }
        });
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
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

    public void getLogged(){


        mFirebaseRef.addAuthStateListener(new Firebase.AuthStateListener() {
            @Override
            public void onAuthStateChanged(AuthData authData) {
                if (authData != null) {
                    mUID = authData.getUid();

                } else {

                }
            }
        });


    }

}
