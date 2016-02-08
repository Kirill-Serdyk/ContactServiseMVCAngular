package com.kirill.dao.impl;

import com.kirill.dao.HobbyDao;
import com.kirill.entity.MappedContact;
import com.kirill.entity.MappedHobby;
import com.kirill.model.Contact;
import com.kirill.model.Hobby;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Kirill on 30.01.2016.
 */

@Repository
public class HobbyDaoImpl implements HobbyDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void addNewHobby(Contact contact, MappedHobby hobby) {
        em.persist(hobby);

        List<MappedContact> mpl = em.createQuery("SELECT mappedContact FROM MappedContact mappedContact WHERE mappedContact.firstName ='" + contact.getFirstName() + "' AND mappedContact.lastName ='"+ contact.getLastName() + "'").getResultList();
        mpl.get(0).addHobby(hobby);

        em.persist(mpl.get(0));
    }

    @Override
    public void addExistHobby(Contact contact, Hobby hobby) {
        List<MappedContact> mpl = em.createQuery("SELECT mappedContact FROM MappedContact mappedContact WHERE mappedContact.firstName ='" + contact.getFirstName() + "' AND mappedContact.lastName ='"+ contact.getLastName() + "'").getResultList();
        List<MappedHobby> mph = em.createQuery("SELECT mappedHobby FROM MappedHobby mappedHobby WHERE mappedHobby.title ='" + hobby.getTitle() + "' AND mappedHobby.description ='"+ hobby.getDescription() + "'").getResultList();
        System.out.println(mph.get(0));
        mpl.get(0).addHobby(mph.get(0));
        em.persist(mpl.get(0));
    }

    @Override
    public Set<Contact> getAllContactsWithHobby(Hobby hobby) {
        List<MappedContact> mpl = em.createQuery("SELECT mappedContact FROM MappedContact mappedContact").getResultList();
        List<MappedHobby> mph = em.createQuery("SELECT mappedHobby FROM MappedHobby mappedHobby WHERE mappedHobby.title ='" + hobby.getTitle() + "' AND mappedHobby.description ='"+ hobby.getDescription() + "'").getResultList();

        Set<Contact> contacts = new HashSet<>();

        for (MappedContact mappedContact : mpl){
            if(mappedContact.getHobbies().contains(mph.get(0))){
                contacts.add(new Contact(mappedContact));
            }
        }

        return contacts;
    }

    @Override
    public List<MappedHobby> getAllHobbies() {
        return em.createQuery("SELECT mappedHobby FROM MappedHobby mappedHobby").getResultList();
    }

    @Override
    public Set<MappedHobby> getContactHobbies(Contact contact) {
        List<MappedContact> mpl = em.createQuery("SELECT mappedContact FROM MappedContact mappedContact WHERE mappedContact.firstName ='" + contact.getFirstName() + "' AND mappedContact.lastName ='"+ contact.getLastName() + "'").getResultList();
        return mpl.get(0).getHobbies();
    }
}