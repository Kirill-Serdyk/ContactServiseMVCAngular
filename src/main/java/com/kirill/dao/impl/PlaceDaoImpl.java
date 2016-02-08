package com.kirill.dao.impl;

import com.kirill.dao.ContactDao;
import com.kirill.dao.HobbyDao;
import com.kirill.dao.PlaceDao;
import com.kirill.entity.MappedContact;
import com.kirill.entity.MappedHobby;
import com.kirill.entity.MappedPlace;
import com.kirill.model.Contact;
import com.kirill.model.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Kirill on 08.02.2016.
 */

@Repository
public class PlaceDaoImpl implements PlaceDao {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private ContactDao contactDao;


    private MappedPlace getPlace(Place place){
        List<MappedPlace> mpl = em.createQuery("SELECT mappedPlace FROM MappedPlace mappedPlace WHERE mappedPlace.title ='" + place.getTitle() + "' AND mappedPlace.description ='"+ place.getDescription() + "'").getResultList();
        return mpl.get(0);
    }

    @Override
    public void addNewPlace(MappedPlace place, Contact contact) {
        em.persist(place);
        MappedContact mappedContact = contactDao.getContact(contact);

        mappedContact.addPlace(place);
        em.persist(mappedContact);
    }

    @Override
    public Set<Contact> getAllContactsForPlace(Place place) {
        List<MappedContact> mappedContacts = contactDao.findAll();
        MappedPlace mappedPlace = getPlace(place);

        Set<Contact> contacts = new HashSet<>();

        for (MappedContact mappedContact : mappedContacts){
            if(mappedContact.getPlaces().contains(mappedPlace)){
                contacts.add(new Contact(mappedContact));
            }
        }
        return contacts;
    }

    @Override
    public void addExistPlace(Contact contact, Place place) {
        MappedContact mappedContact = contactDao.getContact(contact);
        MappedPlace mappedPlace = getPlace(place);

        mappedContact.addPlace(mappedPlace);
        em.persist(mappedContact);
    }

    @Override
    public List<MappedPlace> getAllPlaces() {
        return em.createQuery("SELECT mappedPlace FROM MappedPlace mappedPlace").getResultList();
    }

    @Override
    public Set<MappedPlace> getContactPlaces(Contact contact) {
        MappedContact mappedContact = contactDao.getContact(contact);
        return mappedContact.getPlaces();
    }
}