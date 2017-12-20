package com.itechart.trucking.service.mail;

import freemarker.template.TemplateException;

import javax.mail.MessagingException;
import java.io.IOException;

/**
 * author Vlad Sytyi
 * 20.12.2017
 */
public interface EmailService {

    void sendSimpleMessage(Mail mail);

    void checkForDay();

}
