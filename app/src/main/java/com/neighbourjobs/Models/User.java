package com.neighbourjobs.Models;

import java.util.HashMap;
import java.util.StringTokenizer;

public class User {

    private String mID, mEmail, mFirst, mLast, mAddress1, mAddress2, mCity, mZipCode, mProvince, mCountry;
    private HashMap<String, Object> timestampJoined;
    private boolean hasLoggedInWithPassword;

    public User() { }

    public User(String mIDP, String mEmailP, String mFirstP, String mLastP, String mAddress1P, String mAddress2P, String mCityP, String mZipCodeP, String mProvinceP, String mCountryP) {
        this.mID = mIDP;
        this.mEmail = mEmailP;
        this.mFirst = mFirstP;
        this.mLast = mLastP;
        this.mAddress1 = mAddress1P;
        this.mAddress2 = mAddress2P;
        this.mCity = mCityP;
        this.mZipCode = mZipCodeP;
        this.mProvince = mProvinceP;
        this.mCountry = mCountryP;
        this.hasLoggedInWithPassword = false;
    }

    public String getmID() { return mID; }

    public String getmEmail() {
        return mEmail;
    }

    public String getmFirst() {
        return mFirst;
    }

    public String getmLast() {
        return mLast;
    }

    public String getmAddress1() {
        return mAddress1;
    }

    public String getmAddress2() {
        return mAddress2;
    }

    public String getmCity() {
        return mCity;
    }

    public String getmZipCode() {
        return mZipCode;
    }

    public String getmProvince() {
        return mProvince;
    }

    public String getmCountry() {
        return mCountry;
    }

    public boolean isHasLoggedInWithPassword() {
        return hasLoggedInWithPassword;
    }

}

