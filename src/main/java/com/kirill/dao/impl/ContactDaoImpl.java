package com.kirill.dao.impl;


import com.kirill.dao.ContactDao;
import com.kirill.entity.MappedContact;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ContactDaoImpl implements ContactDao {

    @PersistenceContext
    private EntityManager em;


    @Override
    public void save(MappedContact contact) {
        em.persist(contact);
    }

    @Override
    public List<MappedContact> findAll() {
        return em.createQuery("SELECT mappedContact FROM MappedContact mappedContact").getResultList();
    }

    @Override
    public MappedContact getContact(String email){
        List<MappedContact> mpl = em.createQuery("SELECT mappedContact FROM MappedContact mappedContact WHERE mappedContact.email ='" + email + "'").getResultList();
        return mpl.get(0);
//        MappedContact mp = (MappedContact) em.createQuery("SELECT mappedContact FROM MappedContact mappedContact WHERE mappedContact.email ='" + email + "'").getSingleResult();
//        return mp;
    }

    @Override
    public void delleteContact(String email) {
        List<MappedContact> mpl = em.createQuery("SELECT mappedContact FROM MappedContact mappedContact WHERE mappedContact.email ='" + email + "'").getResultList();
        em.remove(mpl.get(0));
    }

    @Override
    public void update(String name, String phone, String email) {
        List<MappedContact> mpl = em.createQuery("SELECT mappedContact FROM MappedContact mappedContact WHERE mappedContact.email ='" + email + "'").getResultList();
        MappedContact mp = mpl.get(0);
        mp.setName(name);
        mp.setPhone(phone);
        em.persist(mp);
    }
}