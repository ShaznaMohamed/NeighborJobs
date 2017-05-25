package com.neighbourjobs.Login;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.neighbourjobs.BaseActivity;
import com.neighbourjobs.Models.User;
import com.neighbourjobs.R;
import com.neighbourjobs.Utilities.Constants;

import java.util.Map;
/**
 * Created by dsraj on 4/28/2016.
 */
public class CreateAccountActivity extends BaseActivity {
    private ProgressDialog mAuthProgressDialog;
    private Firebase mFirebaseRef;
    private Button mSignupButton;
    private EditText mEmailText, mPasswordText, mFirstText, mLastText, mAddress1Text, mAddress2Text, mCityText, mZipCodeText, mProvinceText, mCountryText;
    private String mEmail, mPassword, mFirst, mLast, mAddress1, mAddress2, mCity, mProvince, mCountry, mZipCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        mFirebaseRef = new Firebase(Constants.FIREBASE_URL);
        mSignupButton = (Button) findViewById(R.id.signupButton);
        Initialize();

        mSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnCreateAccount(v);
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
            new AlertDialog.Builder(this)
                    .setMessage("Are you sure you want to exit?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            CreateAccountActivity.this.finish();
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
    }

    public void Initialize(){
        mEmailText = (EditText) findViewById(R.id.email);
        mPasswordText = (EditText) findViewById(R.id.password);
        mFirstText = (EditText) findViewById(R.id.first);
        mLastText = (EditText) findViewById(R.id.last);
        mAddress1Text = (EditText) findViewById(R.id.addressLine1);
        mAddress2Text = (EditText) findViewById(R.id.addressLine2);
        mCityText = (EditText) findViewById(R.id.addressCity);
        mZipCodeText = (EditText) findViewById(R.id.addressZip);
        mProvinceText = (EditText) findViewById(R.id.addressProvince);
        mCountryText = (EditText) findViewById(R.id.addressCountry);

        /* Setup the progress dialog that is displayed later when authenticating with Firebase */
        mAuthProgressDialog = new ProgressDialog(this);
        mAuthProgressDialog.setTitle(getResources().getString(R.string.progress_dialog_loading));
        mAuthProgressDialog.setMessage(getResources().getString(R.string.progress_dialog_message));
        mAuthProgressDialog.setCancelable(false);
    }

    public void OnCreateAccount(View view){
        mEmail = mEmailText.getText().toString();
        mPassword = mPasswordText.getText().toString();
        mFirst = mFirstText.getText().toString();
        mLast = mLastText.getText().toString();
        mAddress1 = mAddress1Text.getText().toString();
        mAddress2 = mAddress2Text.getText().toString();
        mCity = mCityText.getText().toString();
        mZipCode = mZipCodeText.getText().toString();
        mProvince = mProvinceText.getText().toString();
        mCountry = mCountryText.getText().toString();

        boolean validEmail = isEmailValid(mEmail);
        boolean validAll1 = isValid1(mPassword);
        boolean validAll2 = isValid2(mFirst);
        boolean validAll3 = isValid3(mLast);
        boolean validAll4 = isValid4(mAddress1);
        boolean validAll5 = isValid5(mAddress2);
        boolean validAll6 = isValid6(mCity);
        boolean validAll7 = isValid7(mProvince);
        boolean validAll8 = isValid8(mCountry);
        boolean validAll9 = isValid9(mZipCode);


        if (!validEmail || !validAll1 || !validAll2 || !validAll3 || !validAll4 || !validAll5 || !validAll6 || !validAll7 || !validAll8 || !validAll9) return;

        mAuthProgressDialog.show();

        mFirebaseRef.createUser(mEmail, mPassword, new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {
                //System.out.println("Successfully created user account with uid: " + result.get("uid"));
                Firebase userRef = mFirebaseRef.child("users").child(result.get("uid").toString());
                User newUser = new User(result.get("uid").toString(),mEmail,mFirst, mLast, mAddress1, mAddress2, mCity, mZipCode, mProvince, mCountry);
                userRef.setValue(newUser);
                goToLogin();
            }
            @Override
            public void onError(FirebaseError firebaseError) {
                mAuthProgressDialog.dismiss();
            }
        });
    }

    private  void goToLogin(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean isEmailValid(String email) {
        boolean isGoodEmail =
                (email != null && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches());
        if (!isGoodEmail) {
            mEmailText.setError(getString(R.string.error_invalid_email_not_valid));
            return false;
        }
        return isGoodEmail;
    }

    private boolean isValid1( String mPasswordP) {

        if (mPasswordP.equals("")) {
            mPasswordText.setError(getResources().getString(R.string.error_cannot_be_empty));
            return false;
        }
        return true;
    }
    private boolean isValid2(String mFirstP) {
        if (mFirstP.equals("")) {
            mFirstText.setError(getResources().getString(R.string.error_cannot_be_empty));
            return false;
        }
        return true;
    }
    private boolean isValid3(String mLastP) {

        if (mLastP.equals("")) {
            mLastText.setError(getResources().getString(R.string.error_cannot_be_empty));
            return false;
        }
        return true;
    }
    private boolean isValid4(String mAddress1P) {

        if (mAddress1P.equals("")) {
            mAddress1Text.setError(getResources().getString(R.string.error_cannot_be_empty));
            return false;
        }

        return true;
    }
    private boolean isValid5(String mAddress2P) {


        if (mAddress2P.equals("")) {
            mAddress2Text.setError(getResources().getString(R.string.error_cannot_be_empty));
            return false;
        }

        return true;
    }
    private boolean isValid6(String mCityP) {


        if (mCityP.equals("")) {
            mCityText.setError(getResources().getString(R.string.error_cannot_be_empty));
            return false;
        }

        return true;
    }
    private boolean isValid7(String mProvinceP) {

        if (mProvinceP.equals("")) {
            mProvinceText.setError(getResources().getString(R.string.error_cannot_be_empty));
            return false;
        }

        return true;
    }
    private boolean isValid8(String mCountryP) {
        if (mCountryP.equals("")) {
            mCountryText.setError(getResources().getString(R.string.error_cannot_be_empty));
            return false;
        }
        return true;
    }
    private boolean isValid9(String mZipCodeP) {
        if (mZipCodeP.equals("")) {
            mZipCodeText.setError(getResources().getString(R.string.error_cannot_be_empty));
            return false;
        }
        return true;
    }

}
