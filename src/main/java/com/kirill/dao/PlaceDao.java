package com.kirill.dao;

import com.kirill.entity.MappedPlace;
import com.kirill.model.Contact;
import com.kirill.model.Place;

import java.util.List;
import java.util.Set;

/**
 * Created by Kirill on 08.02.2016.
 */
public interface PlaceDao {
    void addNewPlace(MappedPlace place, Contact contact);

    Set<Contact> getAllContactsForPlace(Place place);

    void addExistPlace(Contact contact, Place place);

    List<MappedPlace> getAllPlaces();

    Set<MappedPlace> getContactPlaces(Contact contact);

}