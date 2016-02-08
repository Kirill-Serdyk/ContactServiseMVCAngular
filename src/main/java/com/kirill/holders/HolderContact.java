package com.kirill.holders;


import com.google.common.base.MoreObjects;

/**
 * Created by Kirill on 31.01.2016.
 */
public class HolderContact {

    private String firstName;

    private String lastName;

    private String birthDate;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .addValue(this.firstName)
                .addValue(this.lastName)
                .addValue(this.birthDate)
                .toString();
    }
}