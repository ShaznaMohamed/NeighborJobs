package com.neighbourjobs.Fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.neighbourjobs.MainActivity;
import com.neighbourjobs.R;

/**
 * Created by dsraj on 4/28/2016.
 */
public class JobPostFragment extends Fragment implements View.OnClickListener {

    private Button postJob, postedJobs, appliedJobs;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.job_post,container, false);
        Initialize();
        postJob.setOnClickListener(this);
        postedJobs.setOnClickListener(this);
        appliedJobs.setOnClickListener(this);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void Initialize(){
        postJob = (Button) view.findViewById(R.id.postJob);
        postedJobs = (Button) view.findViewById(R.id.postedView);
        appliedJobs = (Button) view.findViewById(R.id.appliedView);
    }

    public void onClick(View view){
        switch (view.getId()) {
            case R.id.postJob:
            {
                Intent intent = new Intent(getActivity(),JobPostStartActivity.class);
                startActivity(intent);
            }
            break;

            case R.id.postedView:
            {
                Intent intent = new Intent(getActivity(),JobRecentsActivity.class);
                startActivity(intent);
            }
            break;

            case R.id.appliedView:
            {
                Intent intent = new Intent(getActivity(),ViewApplication.class);
                startActivity(intent);
            }
            break;

            default:
                break;
        }
    }
}
