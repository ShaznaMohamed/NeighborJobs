package com.neighbourjobs.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseRecyclerAdapter;
import com.neighbourjobs.MainActivity;
import com.neighbourjobs.Models.Job;
import com.neighbourjobs.R;
import com.neighbourjobs.Utilities.Constants;
import java.util.ArrayList;

/**
 * Created by dsraj on 5/4/2016.
 */
public class JobRecentsActivity extends AppCompatActivity{

    Firebase mFirebaseRef;
    FirebaseRecyclerAdapter mAdapter;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_recents);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mFirebaseRef = new Firebase(Constants.FIREBASE_URL).child("jobs");

        RecyclerView recycler = (RecyclerView) findViewById(R.id.recyclerView);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new FirebaseRecyclerAdapter<Job, JobViewHolder>(Job.class, R.layout.row_layout, JobViewHolder.class, mFirebaseRef) {
            @Override
            public void populateViewHolder(final JobViewHolder mJobViewHolder, final Job job, int position) {
                mJobViewHolder.recentCard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(JobRecentsActivity.this,JobDetailActivity.class);
                        Bundle extras = new Bundle();
                        extras.putString("mJobKey", job.getmJobKey());
                        extras.putString("mJobTitle", job.getmJobTitle());
                        intent.putExtras(extras);
                        startActivity(intent);
                        finish();
                    }
                });
                mJobViewHolder.titleText.setText(job.getmJobTitle());
                mJobViewHolder.descriptionText.setText(job.getmJobDescription());
            }
        };
        recycler.setAdapter(mAdapter);
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

    public static class JobViewHolder extends RecyclerView.ViewHolder{

        TextView descriptionText, titleText;
        CardView recentCard;

        public JobViewHolder(View itemView) {
            super(itemView);
            recentCard = (CardView) itemView.findViewById(R.id.recentCard);
            titleText = (TextView)itemView.findViewById(R.id.textTitle);
            descriptionText = (TextView) itemView.findViewById(R.id.textDesc);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
