package com.kirill.controller;

import com.kirill.holders.HolderContact;
import com.kirill.holders.HolderContactHobby;
import com.kirill.holders.HolderContactPlace;
import com.kirill.model.Contact;
import com.kirill.model.Hobby;
import com.kirill.model.Place;
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
@RequestMapping("/place")
public class SpringPlaceController {

    @Autowired
    ContactService contactService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public @ResponseBody List<Place> getAllPlaces(){
        return contactService.getAllPlaces();
    }

    @RequestMapping(value = "/addNew", method = RequestMethod.POST)
    public @ResponseBody void addNewPlace(@RequestBody HolderContactPlace holder) {
        contactService.addNewPalace(new Contact(holder), new Place(holder));
    }

    @RequestMapping(value = "/addExist", method = RequestMethod.POST)
    public @ResponseBody void addExistHobby(@RequestBody HolderContactPlace holder) {
        contactService.addExistPlace(new Contact(holder), new Place(holder));
    }

    @RequestMapping(value = "/getContact", method = RequestMethod.POST)
    public @ResponseBody List<Place> getContactPlaces(@RequestBody HolderContact holderContact){
        Contact contact = new Contact(holderContact);
        return contactService.getContactPlaces(contact);
    }

    @RequestMapping(value = "/getAllContactsFor", method = RequestMethod.POST)
    public @ResponseBody List<Contact> getAllContactsFor(@RequestBody Place place){
        return contactService.getAllContactsWidthPlace(place);
    }

}
