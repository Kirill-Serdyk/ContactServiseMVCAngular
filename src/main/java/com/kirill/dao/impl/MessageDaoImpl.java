package com.kirill.dao.impl;

import com.kirill.dao.ContactDao;
import com.kirill.dao.MessageDao;
import com.kirill.entity.MappedContact;
import com.kirill.entity.MappedHobby;
import com.kirill.entity.MappedMessage;
import com.kirill.model.Contact;
import com.kirill.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kirill on 08.02.2016.
 */
@Repository
public class MessageDaoImpl implements MessageDao {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private ContactDao contactDao;

    @Override
    public void storeMassage(Message message) {

        MappedContact mappedContact1 = contactDao.getContact(message.getFrom());
        MappedContact mappedContact2 = contactDao.getContact(message.getTo());

        MappedMessage mappedMessage = new MappedMessage(message, mappedContact1, mappedContact2);
        em.persist(mappedMessage);
    }

    @Override
    public List<Message> getConversation(Contact from, Contact to) {
        MappedContact mappedContact1 = contactDao.getContact(from);
        MappedContact mappedContact2 = contactDao.getContact(to);

        //"SELECT mappedContact FROM MappedContact mappedContact"
        //"SELECT mappedMessage FROM MappedMessage mappedMessage WHERE mappedMessage.from ='" + mappedContact1 + "' AND mappedMessage.to ='"+ mappedContact2 + "'"
        List<MappedMessage> mappedMessageList = em.createQuery("SELECT mappedMessage FROM MappedMessage mappedMessage").getResultList();
        List<Message> messageList = new ArrayList<>();

        for (MappedMessage mappedMessage : mappedMessageList){
            if( (mappedMessage.getFrom().equals(mappedContact1) || mappedMessage.getFrom().equals(mappedContact2) ) && ( mappedMessage.getTo().equals(mappedContact1) || mappedMessage.getTo().equals(mappedContact2)) ) {
                messageList.add(new Message(mappedMessage));
            }
        }
        return messageList;
    }
}