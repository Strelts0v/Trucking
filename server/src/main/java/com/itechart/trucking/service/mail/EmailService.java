package com.itechart.trucking.service.mail;

import freemarker.template.TemplateException;

import javax.mail.MessagingException;
import java.io.IOException;

public interface EmailService {

    void sendSimpleMessage(String to,String subject, String text) throws MessagingException, IOException, TemplateException;

}
