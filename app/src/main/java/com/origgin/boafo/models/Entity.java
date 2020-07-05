package com.origgin.boafo.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by root on 6/25/17.
 */
//
//{
//        "name": "Francis Amu",
//        "occupation": "Automechanics",
//        "specialization": "",
//        "company": "Star Motors",
//        "location": "",
//        "experience": "10years",
//        "phone": "0245814389"
//        },

public class Entity extends RealmObject {

    @PrimaryKey
    private String id;
    public String name;
    private String occupation;
    private String specialization;
    private String company;
    private String location;
    private String experience;
    private String phone;





    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
