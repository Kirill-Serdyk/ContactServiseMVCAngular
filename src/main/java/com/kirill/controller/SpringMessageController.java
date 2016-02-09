package com.kirill.controller;

import com.kirill.holders.HolderContactContact;
import com.kirill.holders.HolderContactMessage;
import com.kirill.model.Contact;
import com.kirill.model.Message;
import com.kirill.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Kirill on 08.02.2016.
 */
@Controller
@RequestMapping("/message")
public class SpringMessageController {

    @Autowired
    ContactService contactService;

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public @ResponseBody List<Message> getConversation(@RequestBody HolderContactContact holder){
        Contact from = new Contact(holder.getC1FirstName(), holder.getC1LastName(), holder.getC1BirthDate());
        Contact to = new Contact(holder.getC2FirstName(), holder.getC2LastName(), holder.getC2BirthDate());

        return contactService.getConversation(from, to);
    }

    @RequestMapping(value = "/store", method = RequestMethod.POST)
    public @ResponseBody void storeMessage(@RequestBody HolderContactMessage holder) {
        Message message = new Message(holder);

        contactService.storeMessage(message);
    }

}
