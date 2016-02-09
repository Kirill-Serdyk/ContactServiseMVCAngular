package com.kirill.entity;


import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.kirill.model.Contact;
import com.kirill.model.Hobby;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "Contacts")
public class MappedContact {

    @Id
    @GeneratedValue
    @Column(name = "CONTACT_ID")
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "BIRTH_DATE")
    private LocalDate birthDate;

    @ManyToMany
    private Set<MappedHobby> hobbies;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<MappedContact> friends;

    @ManyToMany
    private Set<MappedPlace> places;


    public MappedContact() {
    }

    public void addHobby(MappedHobby hobby){
        hobbies.add(hobby);
    }

    public void addFriend(MappedContact contact){
        friends.add(contact);
    }

    public void addPlace(MappedPlace place){
        places.add(place);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<MappedHobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(Set<MappedHobby> hobbies) {
        this.hobbies = hobbies;
    }

    public Set<MappedContact> getFriends() {
        return friends;
    }

    public void setFriends(Set<MappedContact> friends) {
        this.friends = friends;
    }

    public Set<MappedPlace> getPlaces() {
        return places;
    }

    public void setPlaces(Set<MappedPlace> places) {
        this.places = places;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MappedContact that = (MappedContact) o;

        return Objects.equal(this.id, that.id) && Objects.equal(this.firstName, that.firstName) & Objects.equal(this.lastName, that.lastName) & Objects.equal(this.birthDate, that.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id, this.firstName, this.lastName, this.birthDate);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .addValue(this.id)
                .addValue(this.firstName)
                .addValue(this.lastName)
                .addValue(this.birthDate)
                .toString();
    }
}