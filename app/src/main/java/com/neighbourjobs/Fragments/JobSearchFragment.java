package com.neighbourjobs.Fragments;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.neighbourjobs.R;

/**
 * Created by dsraj on 4/28/2016.
 */
public class JobSearchFragment extends Fragment implements View.OnClickListener {

    Button recentJobs, chatButton, mapButton;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.job_search,container, false);
        recentJobs = (Button) view.findViewById(R.id.recentsButton);
        chatButton = (Button) view.findViewById(R.id.chatButton);
        mapButton = (Button) view.findViewById(R.id.mapButton);

        recentJobs.setOnClickListener(this);
        chatButton.setOnClickListener(this);
        mapButton.setOnClickListener(this);
        return view;
    }

    public void onClick(View view){
        switch (view.getId()) {
            case R.id.recentsButton:
            {
                Intent intent = new Intent(getActivity(),JobRecentsActivity.class);
                startActivity(intent);
            }
            break;

            case R.id.chatButton:
            {
                Intent intent = new Intent(getActivity(),ChatMainActivity.class);
                startActivity(intent);
            }
            break;

            case R.id.mapButton:
            {
                Intent intent = new Intent(getActivity(),MapsActivity.class);
                startActivity(intent);
            }
            break;

            default:
                break;
        }
    }
}
