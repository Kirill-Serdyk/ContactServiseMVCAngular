package com.kirill.controller;

import com.kirill.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Kirill on 08.02.2016.
 */
@Controller
@RequestMapping("/message")
public class SpringMessageController {

    @Autowired
    ContactService contactService;


}
