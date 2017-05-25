package com.neighbourjobs;

/**
 * Created by dsraj on 4/28/2016.
 */
import com.firebase.client.Firebase;

public class NeighbourJobsApplication extends android.app.Application  {

    @Override
    public void onCreate() {
        super.onCreate();
        /* Initialize Firebase */
        Firebase.setAndroidContext(this);
        /* Enable disk persistence  */
        Firebase.getDefaultConfig().setPersistenceEnabled(true);
    }
}
