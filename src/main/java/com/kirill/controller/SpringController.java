package com.kirill.controller;

import com.kirill.holders.HolderContactContact;
import com.kirill.holders.HolderContactHobby;
import com.kirill.model.Contact;
import com.kirill.model.Hobby;
import com.kirill.services.ContactService;
import com.kirill.holders.HolderContact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

/**
 * Created by Kirill on 10.01.2016.
 */

@Controller
@RequestMapping("/contact")
public class SpringController {

    @Autowired
    ContactService contactService;

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody void createContact(@RequestBody HolderContact contact) {

        contactService.createContact(contact.getFirstName(), contact.getLastName(), LocalDate.parse(contact.getBirthDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy")) );
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<Contact> getAllContacts() {
        return contactService.getAllContacts();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody void delete(@RequestBody HolderContact holderContact) {
        Contact contact = new Contact(holderContact);
        contactService.deleteContact(contact);
    }

    @RequestMapping(value = "/addNewHobby", method = RequestMethod.POST)
    public @ResponseBody void addNewHobby(@RequestBody HolderContactHobby holder) {
        contactService.addHobby( new Contact(holder), new Hobby(holder) );
    }

    @RequestMapping(value = "/addExistHobby", method = RequestMethod.POST)
    public @ResponseBody void addExistHobby(@RequestBody HolderContactHobby holder) {
        contactService.addExistHobby( new Contact(holder), new Hobby(holder) );
    }

    @RequestMapping(value = "/getAllHobbies", method = RequestMethod.GET)
    public @ResponseBody List<Hobby> getAllHobbies(){
        return contactService.getAllHobbies();
    }

    @RequestMapping(value = "/getContactHobbies", method = RequestMethod.POST)
    public @ResponseBody List<Hobby> getContactHobbies(@RequestBody HolderContact holderContact){
        Contact contact = new Contact(holderContact);
        return contactService.getContactHobbies(contact);
    }

    @RequestMapping(value = "/getAllContactsWidthHobby", method = RequestMethod.POST)
    public @ResponseBody List<Contact> getAllContactsWidthHobby(@RequestBody Hobby hobby){
        return contactService.getAllContactsWidthHobby(hobby);
    }

    @RequestMapping(value = "/getContactFriends", method = RequestMethod.POST)
    public @ResponseBody Set<Contact> getContactFriends(@RequestBody HolderContact holderContact){
        Contact contact = new Contact(holderContact);
        return contactService.getFriendList(contact);
    }

    @RequestMapping(value = "/addFriendship", method = RequestMethod.POST)
    public @ResponseBody void addFriendship(@RequestBody HolderContactContact holderContact){
        Contact contact1 = new Contact(holderContact.getC1FirstName(), holderContact.getC1LastName(), holderContact.getC1BirthDate());
        Contact contact2 = new Contact(holderContact.getC2FirstName(), holderContact.getC2LastName(), holderContact.getC2BirthDate());

        contactService.addFriendship(contact1, contact2);
    }



//    @RequestMapping(method = RequestMethod.PUT)
//    public void update(@RequestBody Contact contact) {
//        contactService.update(contact.getName(), contact.getPhone(), contact.getEmail());
//    }

//--------------------------------------------------------------------------------------------------------------------
//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public String showIndex(ModelMap modelMap){
//        modelMap.addAttribute("contacts", contactService.getAllContacts());
//        return "index";
//    }
//
//    @RequestMapping(value = "/new", method = RequestMethod.GET)
//    public String showNew(){
//        return "new";
//    }
//
//    @RequestMapping(value = "/new", method = RequestMethod.POST)
//    public String addNew(ModelMap modelMap, String name, String phone, String email){
//        Contact contact = new Contact();
//        contact.setName(name);
//        contact.setPhone(phone);
//        contact.setEmail(email);
//        contactService.addContact(contact);
//
//        modelMap.addAttribute("contacts", contactService.getAllContacts());
//
//        return "index";
//    }
//
//    @RequestMapping(value = "/dellete", method = RequestMethod.POST)
//    public String dellete(ModelMap modelMap, String submit){
//        Contact contact = new Contact();
//        contact.setName("name");
//        contact.setPhone("phone");
//        contact.setEmail(submit);
//
//        contactService.deleteContact(contact);
//        modelMap.addAttribute("contacts", contactService.getAllContacts());
//        return "index";
//    }
//
//    @RequestMapping(value = "/update", method = RequestMethod.GET)
//    public String updateIn (ModelMap modelMap, String submit){
//        Contact contact = contactService.getContact(submit);
//        modelMap.addAttribute("contact", contact);
//        return "update";
//    }
//
//    @RequestMapping(value = "/update", method = RequestMethod.POST)
//    public String updateOn (ModelMap modelMap, String name, String phone, String email){
//        contactService.update(name, phone, email);
//
//        modelMap.addAttribute("contacts", contactService.getAllContacts());
//        return "index";
//    }
}