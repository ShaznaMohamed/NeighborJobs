package com.neighbourjobs.Utilities;

import com.neighbourjobs.R;

import java.util.Random;

/**
 * Created by dsraj on 5/4/2016.
 */
public class Randoms {
    private static final Random RANDOM = new Random();

    public static int getRandomCheeseDrawable() {
        switch (RANDOM.nextInt(5)) {
            default:
            case 0:
                return R.drawable.job_1;
            case 1:
                return R.drawable.job_2;
            case 2:
                return R.drawable.job_3;
            case 3:
                return R.drawable.job_4;
            case 4:
                return R.drawable.job_5;
        }
    }

}
