package com.neighbourjobs.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.neighbourjobs.Models.Job;
import com.neighbourjobs.R;
import com.neighbourjobs.Utilities.Constants;

/**
 * Created by dsraj on 5/1/2016.
 */
public class JobSearchResultsActivity extends AppCompatActivity {
    private Firebase mFirebaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_search_results);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mFirebaseRef = new Firebase(Constants.FIREBASE_URL);



    }

//    public void Initialize(){
//        mFirebaseRef.child("jobs").limitToLast(5).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot snapshot) {
//                for (DataSnapshot msgSnapshot: snapshot.getChildren()) {
//                    Job jobs = msgSnapshot.getValue(Job.class);
//                }
//            }
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//            }
//        });
//
//
//
//    }
//
//    RecyclerView recycler = (RecyclerView) findViewById(R.id.recyclerview);
//    recycler.setHasFixedSize(true);
//    recycler.setLayoutManager(new LinearLayoutManager(this));
//
//    mAdapter = new FirebaseRecyclerAdapter<Job, JobViewHolder>(Job.class, android.R.layout.two_line_list_item, JobViewHolder.class, mRef) {
//        @Override
//        public void populateViewHolder(JobViewHolder mJobViewHolder, ChatMessage chatMessage, int position) {
//            chatMessageViewHolder.nameText.setText(chatMessage.getName());
//            chatMessageViewHolder.messageText.setText(chatMessage.getMessage());
//        }
//    };
//    recycler.setAdapter(mAdapter);
//
//
//    public static class JobViewHolder extends RecyclerView.ViewHolder {
//        TextView mJobNameText;
//        TextView mJobDescriptionText;
//
//        public JobViewHolder(View itemView) {
//            super(itemView);
//            mJobNameText = (TextView)itemView.findViewById(android.R.id.text1);
//            mJobDescriptionText = (TextView) itemView.findViewById(android.R.id.text2);
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


}
