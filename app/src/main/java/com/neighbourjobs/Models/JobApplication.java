package com.neighbourjobs.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Shazna on 5/4/2016.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class JobApplication {

    String name,age, email, phone,skills, covernote, salary, availability, hrs, starting, relocate, supervisor, supporters, message,influence;
    String expperiod,refereename, refereeemail,jobname;

    public JobApplication()
    {

    }

    public JobApplication(String namee, String age,String emaill, String phone, String skills, String expperiod , String covernote, String salary, String availability, String hrs,String starting, String relocate, String supervisor,String supporter,String Refereename,   String refereeemail ,String influence, String message,String jobname)
    {
        this.name = namee;
        this.age = age;
        this.email = emaill;
        this.phone = phone;
        this.skills = skills;
        this.covernote = covernote;
        this.salary = salary;
        this.availability = availability;
        this.hrs = hrs;
        this.starting = starting;
        this.relocate = relocate;
        this.supervisor = supervisor;
        this.supporters = supporter;
        this.message = message;
        this.expperiod = expperiod;
        this.refereename = Refereename;
        this.refereeemail = refereeemail;
        this.influence = influence;
        this.jobname = jobname;

    }

    public String getName() { return name;}
    public String getAge() { return age;}
    public String getEmail() { return email;}
    public String getPhone() { return phone;}
    public String getSkills() { return skills;}
    public String getCovernote() { return covernote;}
    public String getSalary() { return salary;}
    public String getAvailability() { return availability;}
    public String getHrs() { return hrs;}
    public String getStarting() { return starting;}
    public String getRelocate() { return relocate;}
    public String getSupervisor() { return supervisor;}
    public String getSupporters() { return supporters;}
    public String getMessage() { return message;}
    public String getExpperiod() { return expperiod;}
    public String getRefereename() { return refereename;}
    public String getRefereeemail() { return refereeemail;}
    public String getInfluence() { return influence;}
    public String getJobname() { return jobname;}


}
