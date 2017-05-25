package com.neighbourjobs.Models;

/**
 * Created by Shazna on 3/20/2016.
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Profile {

    String name;
    String status;
    String email;
    String phone;
    String address;


    public Profile()    {

    }

    public Profile(String Name, String Status, String Email, String Phone, String Address) {
        this.name = Name;
        this.status = Status;
        this.email = Email;
        this.phone = Phone;
        this.address = Address;

    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Profilemodel{name='" + name + "\', status='" + status + "\', email='" + email + "\' , phone='" + phone + "\', address='" + address + "\'}";
    }
}
