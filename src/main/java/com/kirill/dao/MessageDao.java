package com.kirill.dao;

import com.kirill.model.Contact;
import com.kirill.model.Message;

import java.util.List;

/**
 * Created by Kirill on 08.02.2016.
 */
public interface MessageDao {
    void storeMassage(Message message);

    List<Message> getConversation(Contact contact1, Contact contact2);
}