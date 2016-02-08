package com.kirill.dao;

import com.kirill.entity.MappedHobby;
import com.kirill.model.Contact;
import com.kirill.model.Hobby;

import java.util.List;
import java.util.Set;

/**
 * Created by Kirill on 29.01.2016.
 */
public interface HobbyDao {
    void addNewHobby(Contact contact, MappedHobby hobby);

    Set<Contact> getAllContactsWithHobby(Hobby hobby);

    void addExistHobby(Contact contact, Hobby hobby);

    List<MappedHobby> getAllHobbies();

    Set<MappedHobby> getContactHobbies(Contact contact);
}