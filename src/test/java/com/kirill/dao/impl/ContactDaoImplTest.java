package com.kirill.dao.impl;

import com.kirill.entity.MappedContact;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.Mockito.verify;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@Transactional
public class ContactDaoImplTest {

    @InjectMocks
    ContactDaoImpl contactDao;

    @Mock
    Query query;

    @Mock
    EntityManager entityManager;

    MappedContact c1 = new MappedContact();
    List<MappedContact> mpl = new ArrayList<>();

    @Before
    public void before(){
        c1.setName("Name");
        c1.setPhone("Phone");
        c1.setEmail("Email");

        mpl.add(c1);
    }

    @Test
    public void testSave() throws Exception {
        contactDao.save(c1);
        verify(entityManager).persist(c1);
    }

    @Test
    public void testFindAll() throws Exception {
        when(entityManager.createQuery("SELECT mappedContact FROM MappedContact mappedContact")).thenReturn(query);
        when(query.getResultList()).thenReturn(mpl);

        assertEquals(mpl, contactDao.findAll());
    }

    @Test
    public void testGetContact() throws Exception {
        when(entityManager.createQuery("SELECT mappedContact FROM MappedContact mappedContact WHERE mappedContact.email ='" + c1.getEmail() + "'")).thenReturn(query);
        when(query.getResultList()).thenReturn(mpl);

        assertEquals(c1, contactDao.getContact(c1.getEmail()));
    }

    @Test
    public void testDelleteContact() throws Exception {
        when(entityManager.createQuery("SELECT mappedContact FROM MappedContact mappedContact WHERE mappedContact.email ='" + c1.getEmail() + "'")).thenReturn(query);
        when(query.getResultList()).thenReturn(mpl);

        contactDao.delleteContact(c1.getEmail());
        verify(entityManager).remove(c1);
    }

    @Test
    public void testUpdate() throws Exception {
        when(entityManager.createQuery("SELECT mappedContact FROM MappedContact mappedContact WHERE mappedContact.email ='" + c1.getEmail() + "'")).thenReturn(query);
        when(query.getResultList()).thenReturn(mpl);

        contactDao.update(c1.getName(), c1.getPhone(), c1.getEmail());
        verify(entityManager).persist(c1);
    }
}