package com.kirill.services.impl;

import com.kirill.dao.ContactDao;
import com.kirill.entity.MappedContact;
import com.kirill.model.Contact;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ContactServiceImplTest {

    private ContactServiceImpl contactService;

    MappedContact c1 = new MappedContact();
    MappedContact c2 = new MappedContact();
    MappedContact c3 = new MappedContact();
    MappedContact c4 = new MappedContact();

    Contact c = new Contact();

    @Before
    public void beforeClass(){
        contactService = new ContactServiceImpl();

        ContactDao contactDaoMock = mock(ContactDao.class);
        contactService.setContactDao(contactDaoMock);

        List<MappedContact> list = new ArrayList<MappedContact>();
        list.add(c1);
        list.add(c2);
        list.add(c3);

        when(contactDaoMock.findAll()).thenReturn(list);
    }

    @Test
    public void testAddContact() throws Exception {
        ContactDao contactDaoMock = mock(ContactDao.class);
        contactService.setContactDao(contactDaoMock);

        MappedContact mappedContact = new MappedContact();
        mappedContact.setName(c.getName());
        mappedContact.setPhone(c.getPhone());
        mappedContact.setEmail(c.getEmail());

        contactService.addContact(c);
        verify(contactDaoMock).save(mappedContact);
    }

    @Test
    public void testDelleteContact() throws Exception {
        ContactDao contactDaoMock = mock(ContactDao.class);
        contactService.setContactDao(contactDaoMock);

        contactService.deleteContact(c);
        verify(contactDaoMock).delleteContact(c.getEmail());
    }

    @Test
    public void testGetAllContacts() throws Exception {
        assertEquals(3, contactService.getAllContacts().size());
    }
}