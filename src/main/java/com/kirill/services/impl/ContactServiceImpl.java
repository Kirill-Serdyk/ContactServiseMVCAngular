package com.kirill.services.impl;

import com.kirill.dao.ContactDao;
import com.kirill.dao.HobbyDao;
import com.kirill.dao.PlaceDao;
import com.kirill.entity.MappedContact;
import com.kirill.entity.MappedHobby;
import com.kirill.entity.MappedPlace;
import com.kirill.model.Contact;
import com.kirill.model.Hobby;
import com.kirill.model.Place;
import com.kirill.services.ContactService;
import com.kirill.holders.HolderContact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactDao contactDao;

    @Autowired
    private HobbyDao hobbyDao;

    @Autowired
    private PlaceDao placeDao;

    @Override
    @Transactional
    public void createContact(String firstName, String lastName, LocalDate birthDate) {
        MappedContact mappedContact = new MappedContact();
        mappedContact.setFirstName(firstName);
        mappedContact.setLastName(lastName);
        mappedContact.setBirthDate(birthDate);

        contactDao.addContact(mappedContact);
    }

    @Override
    @Transactional
    public void deleteContact(Contact contact) {
        contactDao.delleteContact(contact);
    }

    @Override
    public void clearAll() {

    }

    @Override
    @Transactional(readOnly = true)
    public List<Contact> getAllContacts() {
        List<MappedContact> mappedContacts = contactDao.findAll();
        List<Contact> contacts = new ArrayList<Contact>();
        for (MappedContact mappedContact : mappedContacts) {
            contacts.add(new Contact(mappedContact));
        }
        return contacts;
    }

    @Override
    @Transactional
    public void addHobby(Contact contact, Hobby hobby) {
        MappedHobby mappedHobby = new MappedHobby(hobby);
        hobbyDao.addNewHobby(contact, mappedHobby);
    }

    @Override
    @Transactional
    public void addExistHobby(Contact contact, Hobby hobby) {
        hobbyDao.addExistHobby(contact, hobby);
    }

    @Override
    @Transactional
    public List<Hobby> getAllHobbies() {
        List<Hobby> hobbies = new ArrayList<>();

        for (MappedHobby mappedHobby : hobbyDao.getAllHobbies() ){
            hobbies.add(new Hobby(mappedHobby));
        }

        return hobbies;
    }

    @Override
    @Transactional
    public List<Hobby> getContactHobbies(Contact contact) {
        List<Hobby> hobbies = new ArrayList<>();

        for (MappedHobby mappedHobby : hobbyDao.getContactHobbies(contact) ){
            hobbies.add(new Hobby(mappedHobby));
        }

        return hobbies;
    }

    @Override
    @Transactional
    public List<Contact> getAllContactsWidthHobby(Hobby hobby) {
        List<Contact> contacts = new ArrayList<>();

        for (Contact contact : hobbyDao.getAllContactsWithHobby(hobby)){
            contacts.add(contact);
        }

        return contacts;
    }



    @Override
    @Transactional
    public Set<Contact> getFriendList(Contact contact) {
        MappedContact mappedContact = contactDao.getContact(contact);
        Set<Contact> contactSet = new HashSet<>();

        for(MappedContact mpk : mappedContact.getFriends()){
            contactSet.add(new Contact(mpk));
        }

        return contactSet;
    }

    @Override
    @Transactional
    public void addFriendship(Contact contact1, Contact contact2) {
        contactDao.addFriendship(contact1, contact2);
    }




    @Override
    @Transactional
    public void addNewPalace(Contact contact, Place place) {
        MappedPlace mappedPlace = new MappedPlace(place);
        placeDao.addNewPlace(mappedPlace, contact);
    }

    @Override
    @Transactional
    public void addExistPlace(Contact contact, Place place) {
        placeDao.addExistPlace(contact, place);
    }

    @Override
    @Transactional
    public List<Place> getAllPlaces() {
        List<Place> places = new ArrayList<>();

        for (MappedPlace mappedPlace : placeDao.getAllPlaces() ){
            places.add(new Place(mappedPlace));
        }
        return places;
    }

    @Override
    @Transactional
    public List<Place> getContactPlaces(Contact contact) {
        List<Place> places = new ArrayList<>();

        for (MappedPlace mappedPlace : placeDao.getContactPlaces(contact) ){
            places.add(new Place(mappedPlace));
        }
        return places;
    }

    @Override
    @Transactional
    public List<Contact> getAllContactsWidthPlace(Place place) {
        List<Contact> contacts = new ArrayList<>();

        for (Contact contact : placeDao.getAllContactsForPlace(place)){
            contacts.add(contact);
        }
        return contacts;
    }


    //    @Override
//    @Transactional
//    public Contact getContact(String email){
//        Contact contact = new Contact();
//        MappedContact mappedContact = contactDao.getContact(email);
//
//        contact.setName(mappedContact.getName());
//        contact.setEmail(mappedContact.getEmail());
//        contact.setPhone(mappedContact.getPhone());
//
//        return contact;
//    }

//    @Override
//    @Transactional
//    public void update(String name, String phone, String email) {
//        contactDao.update( name, phone, email);
//    }

    public void setContactDao(ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    public void setHobbyDao(HobbyDao hobbyDao) {
        this.hobbyDao = hobbyDao;
    }
}