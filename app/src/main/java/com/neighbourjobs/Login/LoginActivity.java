package com.neighbourjobs.Login;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ServerValue;
import com.firebase.client.ValueEventListener;
import com.firebase.client.utilities.Utilities;
import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.UserRecoverableAuthException;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.auth.api.signin.GoogleSignInStatusCodes;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.Scope;
import com.neighbourjobs.BaseActivity;
import com.neighbourjobs.MainActivity;
import com.neighbourjobs.Models.User;
import com.neighbourjobs.R;
import com.neighbourjobs.Utilities.Constants;
import com.neighbourjobs.Login.CreateAccountActivity;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends BaseActivity {

    private Button mSignupButton;
    private Button mSigninButton;

    private EditText mEmailText, mPasswordText;
    private String mEmail, mPassword;

    private ProgressDialog mAuthProgressDialog;

    private Firebase mFirebaseRef;
    private Firebase.AuthStateListener mAuthStateListener;

    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);

        mFirebaseRef = new Firebase(Constants.FIREBASE_URL);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Initialize();

        mPasswordText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {

                if (actionId == EditorInfo.IME_ACTION_DONE || keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    onSignInPressed();
                }
                return true;
            }
        });

    }

    public void Initialize(){
        mEmailText = (EditText) findViewById(R.id.email);
        mPasswordText = (EditText) findViewById(R.id.password);

        mSignupButton = (Button) findViewById(R.id.signupButton);
        mSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSignUpPressed();
            }
        });

        mSigninButton = (Button) findViewById(R.id.signinButton);
        mSigninButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSignInPressed();
            }
        });

        mAuthProgressDialog = new ProgressDialog(this);
        mAuthProgressDialog.setTitle("Please wait");
        mAuthProgressDialog.setMessage("Authenticating");
        mAuthProgressDialog.setCancelable(false);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.main_content);
    }

    protected void onResume() {
        super.onResume();

        mAuthStateListener = new Firebase.AuthStateListener() {
            @Override
            public void onAuthStateChanged(AuthData authData) {
                mAuthProgressDialog.dismiss();

                if (authData != null) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };

        mFirebaseRef.addAuthStateListener(mAuthStateListener);


    }

    @Override
    public void onPause() {
        super.onPause();
        mFirebaseRef.removeAuthStateListener(mAuthStateListener);
    }

    public void onSignUpPressed(){
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
        finish();
    }

    public void onSignInPressed(){

        mEmail = mEmailText.getText().toString();
        mPassword = mPasswordText.getText().toString();

        if (mEmail.equals("") || mPassword.equals("")) {
            mEmailText.setError(getString(R.string.error_cannot_be_empty));
            mPasswordText.setError(getString(R.string.error_cannot_be_empty));
            return;
        }

        mAuthProgressDialog.show();

        mFirebaseRef.authWithPassword(mEmail, mPassword, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                System.out.println("User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());
                // Authentication just completed successfully :)
                Map<String, String> map = new HashMap<String, String>();
                map.put("provider", authData.getProvider());
                if(authData.getProviderData().containsKey("displayName")) {
                    map.put("displayName", authData.getProviderData().get("displayName").toString());
                }
                mFirebaseRef.child("logged").child(authData.getUid()).setValue(map);
                mAuthProgressDialog.dismiss();
                goMain();
            }
            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                mAuthProgressDialog.dismiss();
                ((InputMethodManager) getSystemService(LoginActivity.INPUT_METHOD_SERVICE))
                        .toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);
                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, firebaseError.toString(), Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });
    }

    public void goMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        LoginActivity.this.finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}