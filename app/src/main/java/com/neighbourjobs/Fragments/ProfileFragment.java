package com.neighbourjobs.Fragments;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.neighbourjobs.Models.Profile;
import com.neighbourjobs.Models.User;
import com.neighbourjobs.R;
import com.neighbourjobs.Utilities.Constants;

import java.util.ArrayList;

/**
 * Created by dsraj on 4/28/2016.
 */
public class ProfileFragment extends Fragment {

    ImageButton bn;
    TextView priname, pristatus, priemail, priphone, priaddress;
    ListView list;
    TextView prilist1,prilist2,prilist3,prilist4,prilist5,prilist6,prilist7,prilist8,prilist9,prilist10;
    private String  mJobUser;
    private Firebase mFirebaseRef;
    String mUID;
    private CoordinatorLayout coordinatorLayout;
    User loggedUser;
    ArrayList<String> mMessages = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_page,container, false);
        Firebase.setAndroidContext(this.getActivity());
        coordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.main_content);

        mFirebaseRef = new Firebase(Constants.FIREBASE_URL);

        viewskills();

        priname = (TextView) view.findViewById(R.id.prname);
        pristatus = (TextView) view.findViewById(R.id.prstatus);
        priemail = (TextView) view.findViewById(R.id.premail);
        priphone = (TextView) view.findViewById(R.id.prphone);
        priaddress = (TextView) view.findViewById(R.id.praddress);
        prilist1 = (TextView)view.findViewById(R.id.prList1);

        return view;

    }

    public void getLogged(){
        mFirebaseRef.addAuthStateListener(new Firebase.AuthStateListener() {
            @Override
            public void onAuthStateChanged(AuthData authData) {
                if (authData != null) {
                    mUID = authData.getUid();
                } else {
                    // user is not logged in
                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        getLogged();

        mFirebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                DataSnapshot usersnapshot = snapshot.child("users").child(mUID);
                loggedUser = usersnapshot.getValue(User.class);
                String s1 = loggedUser.getmLast();
                String s2 = loggedUser.getmFirst();
                priname.setText(s2 + " " + s1);
                priemail.setText(loggedUser.getmAddress1());
                priphone.setText(loggedUser.getmAddress2());

                priaddress.setText(loggedUser.getmCity() + ", " + loggedUser.getmCountry());
                pristatus.setText(loggedUser.getmEmail());

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }

    public void viewskills()
    {
       mFirebaseRef.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
               DataSnapshot usersnapshot = dataSnapshot.child("profile");
               Profile pk = usersnapshot.getValue(Profile.class);

               String skill = pk.getStatus();
               String arr[] = skill.split(", ");

               prilist1.setText(pk.getStatus());
           }

           @Override
           public void onCancelled(FirebaseError firebaseError) {
           }
       });
    }
}
