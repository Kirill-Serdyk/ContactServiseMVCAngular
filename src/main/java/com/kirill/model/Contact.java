package com.kirill.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.kirill.entity.MappedContact;
import com.kirill.entity.MappedHobby;
import com.kirill.holders.HolderContact;
import com.kirill.holders.HolderContactHobby;
import com.kirill.holders.HolderContactPlace;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

public class Contact {

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    private Set<Hobby> hobbies;

    private Set<Contact> friends;

    public Contact() {
    }

    public Contact(MappedContact mappedContact) {
        this.firstName = mappedContact.getFirstName();
        this.lastName = mappedContact.getLastName();
        this.birthDate = mappedContact.getBirthDate();
    }

    public Contact(HolderContact contact) {
        this.firstName = contact.getFirstName();
        this.lastName = contact.getLastName();
        this.birthDate = LocalDate.parse(contact.getBirthDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public Contact(HolderContactHobby holder) {
        this.firstName = holder.getFirstName();
        this.lastName = holder.getLastName();
        this.birthDate = LocalDate.parse(holder.getBirthDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public Contact(String firstName, String lastName, String birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public Contact(HolderContactPlace holder) {
        this.firstName = holder.getFirstName();
        this.lastName = holder.getLastName();
        this.birthDate = LocalDate.parse(holder.getBirthDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Set<Hobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(Set<Hobby> hobbies) {
        this.hobbies = hobbies;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact that = (Contact) o;

        return Objects.equal(this.firstName, that.firstName) & Objects.equal(this.lastName, that.lastName) & Objects.equal(this.birthDate, that.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.firstName, this.lastName, this.birthDate);
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