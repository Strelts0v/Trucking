package com.itechart.trucking.controller;


import com.itechart.trucking.service.mail.EmailService;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.IOException;

@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    EmailService emailService;

    @RequestMapping("/send")
    public void sendMail() throws MessagingException, IOException, TemplateException {
        String to = "pulohelec@p33.org";
        String subject = "test";
        String text = "Test Text";
        emailService.sendSimpleMessage(to,subject,text);
    }


}
