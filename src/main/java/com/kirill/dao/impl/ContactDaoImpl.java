package com.kirill.dao.impl;


import com.kirill.dao.ContactDao;
import com.kirill.entity.MappedContact;
import com.kirill.entity.MappedHobby;
import com.kirill.model.Contact;
import com.kirill.model.Hobby;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class ContactDaoImpl implements ContactDao {

    @PersistenceContext
    private EntityManager em;


    @Override
    public void addContact(MappedContact contact) {
        em.persist(contact);
    }

    @Override
    public List<MappedContact> findAll() {
        return em.createQuery("SELECT mappedContact FROM MappedContact mappedContact").getResultList();
    }

    @Override
    public MappedContact getContact(Contact contact){
        List<MappedContact> mpl = em.createQuery("SELECT mappedContact FROM MappedContact mappedContact WHERE mappedContact.firstName ='" + contact.getFirstName() + "' AND mappedContact.lastName ='"+ contact.getLastName() + "'").getResultList();
        return mpl.get(0);
    }

    @Override
    public void delleteContact(Contact contact) {
        List<MappedContact> mpl = em.createQuery("SELECT mappedContact FROM MappedContact mappedContact WHERE mappedContact.firstName ='" + contact.getFirstName() + "' AND mappedContact.lastName ='"+ contact.getLastName() + "'").getResultList();
        //+"' AND mappedContact.birthDate ='" + contact.getBirthDate().
        em.remove(mpl.get(0));
    }

    @Override
    public void addFriendship(Contact contact1, Contact contact2) {
        MappedContact mappedContact1 = getContact(contact1);
        MappedContact mappedContact2 = getContact(contact2);

        mappedContact1.addFriend(mappedContact2);
        mappedContact2.addFriend(mappedContact1);

        em.persist(mappedContact1);
        em.persist(mappedContact2);
    }

    @Override
    public void update(Contact contact) {
        List<MappedContact> mpl = em.createQuery("SELECT mappedContact FROM MappedContact mappedContact WHERE mappedContact.email ='" + contact.getFirstName() + "'").getResultList();
        MappedContact mp = mpl.get(0);
        mp.setFirstName(contact.getFirstName());
        mp.setLastName(contact.getLastName());
        mp.setBirthDate(contact.getBirthDate());

        Set<MappedHobby> mappedHobbySet = new HashSet<>();
        for (Hobby hoby : contact.getHobbies()){
            mappedHobbySet.add(new MappedHobby(hoby));
        }

        mp.setHobbies(mappedHobbySet);
        em.persist(mp);
    }
}