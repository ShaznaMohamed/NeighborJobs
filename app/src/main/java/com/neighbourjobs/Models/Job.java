package com.neighbourjobs.Models;

/**
 * Created by dsraj on 4/28/2016.
 */
public class Job {

    private String mJobKey;
    private String mJobTitle;
    private String mJobDescription;
    private String mJobMain;
    private String mJobSub;
    private String mJobType;
    private String mJobPayMode;
    private String mJobPayType;
    private String mJobPayAmount;
    private String mJobAddress1;
    private String mJobAddress2;
    private String mJobCity;
    private String mJobZipCode;
    private String mJobUser;

    public  Job(){}

    public Job(String mJobKeyP, String mJobUserP, String mJobTitleP, String mJobDescriptionP, String mJobMainP, String mJobSubP,
               String mJobTypeP, String mJobPayModeP, String mJobPayTypeP, String mJobPayAmountP,
               String mJobAddress1P, String mJobAddress2P, String mJobCityP, String mJobZipCodeP){
        this.mJobUser = mJobUserP;
        this.mJobKey = mJobKeyP;
        this.mJobTitle = mJobTitleP;
        this.mJobDescription = mJobDescriptionP;
        this.mJobMain = mJobMainP;
        this.mJobSub = mJobSubP;
        this.mJobType = mJobTypeP;
        this.mJobPayMode = mJobPayModeP;
        this.mJobPayType = mJobPayTypeP;
        this.mJobPayAmount = mJobPayAmountP;
        this.mJobAddress1 = mJobAddress1P;
        this.mJobAddress2 = mJobAddress2P;
        this.mJobCity = mJobCityP;
        this.mJobZipCode = mJobZipCodeP;
    }

    public String getmJobTitle() {
        return mJobTitle;
    }

    public void setmJobTitle(String mJobTitle) {
        this.mJobTitle = mJobTitle;
    }

    public String getmJobDescription() {
        return mJobDescription;
    }

    public void setmJobDescription(String mJobDescription) {
        this.mJobDescription = mJobDescription;
    }

    public String getmJobMain() {
        return mJobMain;
    }

    public void setmJobMain(String mJobMain) {
        this.mJobMain = mJobMain;
    }

    public String getmJobSub() {
        return mJobSub;
    }

    public void setmJobSub(String mJobSub) {
        this.mJobSub = mJobSub;
    }

    public String getmJobType() {
        return mJobType;
    }

    public void setmJobType(String mJobType) {
        this.mJobType = mJobType;
    }

    public String getmJobPayMode() {
        return mJobPayMode;
    }

    public void setmJobPayMode(String mJobPayMode) {
        this.mJobPayMode = mJobPayMode;
    }

    public String getmJobPayType() {
        return mJobPayType;
    }

    public void setmJobPayType(String mJobPayType) {
        this.mJobPayType = mJobPayType;
    }

    public String getmJobPayAmount() {
        return mJobPayAmount;
    }

    public void setmJobPayAmount(String mJobPayAmount) {
        this.mJobPayAmount = mJobPayAmount;
    }

    public String getmJobAddress1() {
        return mJobAddress1;
    }

    public void setmJobAddress1(String mJobAddress1) {
        this.mJobAddress1 = mJobAddress1;
    }

    public String getmJobAddress2() {
        return mJobAddress2;
    }

    public void setmJobAddress2(String mJobAddress2) {
        this.mJobAddress2 = mJobAddress2;
    }

    public String getmJobCity() {
        return mJobCity;
    }

    public void setmJobCity(String mJobCity) {
        this.mJobCity = mJobCity;
    }

    public String getmJobZipCode() {
        return mJobZipCode;
    }

    public void setmJobZipCode(String mJobZipCode) {
        this.mJobZipCode = mJobZipCode;
    }

    public String getmJobKey() {
        return mJobKey;
    }

    public void setmJobKey(String mJobKey) {
        this.mJobKey = mJobKey;
    }

    public String getmJobUser() {
        return mJobUser;
    }

    public void setmJobUser(String mJobUser) {
        this.mJobUser = mJobUser;
    }
}
