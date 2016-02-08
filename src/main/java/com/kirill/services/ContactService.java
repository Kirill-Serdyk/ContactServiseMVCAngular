package com.kirill.services;


import com.kirill.model.Contact;
import com.kirill.model.Hobby;
import com.kirill.holders.HolderContact;
import com.kirill.model.Place;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface ContactService {
    void createContact(String firstName, String lastName, LocalDate birthDate);

    void deleteContact(Contact contact);

    void clearAll();

    List<Contact> getAllContacts();


    void addHobby(Contact contact, Hobby hobby);

    void addExistHobby(Contact contact, Hobby hobby);

    List<Hobby> getAllHobbies();

    List<Hobby> getContactHobbies(Contact contact);

    List<Contact> getAllContactsWidthHobby(Hobby hobby);


    Set<Contact> getFriendList(Contact contact);

    void addFriendship(Contact contact1, Contact contact2);


    void addNewPalace(Contact contact, Place place);

    void addExistPlace(Contact contact, Place place);

    List<Place> getAllPlaces();

    List<Place> getContactPlaces(Contact contact);

    List<Contact> getAllContactsWidthPlace(Place place);


    //Contact getContact(String email);

    //void update(String name, String phone, String email);
}