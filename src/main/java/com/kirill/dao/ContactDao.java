package com.kirill.dao;


import com.kirill.entity.MappedContact;
import com.kirill.model.Contact;

import java.util.List;

public interface ContactDao {

    void addContact(MappedContact contact);

    MappedContact getContact(Contact contact);

    void delleteContact(Contact contact);

    void update(Contact contact);

    List<MappedContact> findAll();

    void addFriendship(Contact contact1, Contact contact2);
}