package com.kirill.dao;


import com.kirill.entity.MappedContact;

import java.util.List;

public interface ContactDao {

    void save(MappedContact contact);

    List<MappedContact> findAll();

    MappedContact getContact(String email);

    void delleteContact(String email);

    void update(String name, String phone, String email);
}
