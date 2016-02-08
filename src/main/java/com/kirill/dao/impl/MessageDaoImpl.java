package com.kirill.dao.impl;

import com.kirill.dao.MessageDao;
import com.kirill.model.Contact;
import com.kirill.model.Message;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Kirill on 08.02.2016.
 */
@Repository
public class MessageDaoImpl implements MessageDao {


    @Override
    public void storeMassage(Message message) {

    }

    @Override
    public List<Message> getConversation(Contact contact1, Contact contact2) {
        return null;
    }
}
