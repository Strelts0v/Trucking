package com.itechart.trucking.service.mail;


import com.itechart.trucking.dao.LetterDao;
import com.itechart.trucking.dao.UserDao;
import com.itechart.trucking.domain.Letter;
import com.itechart.trucking.domain.User;
import freemarker.core.ParseException;
import freemarker.template.*;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    public JavaMailSender mailSender;
    @Autowired
    private Configuration freemarkerConfig;
    @Autowired
    private LetterDao letterDao;
    @Autowired
    private UserDao userDao;
    private final static String FILE_PATH = "picture/bday.png";
    private static Logger log = LoggerFactory.getLogger(EmailServiceImpl.class);

    public void sendSimpleMessage(Mail mail) {

        Optional<Letter> letterOptional = letterDao.readLetter(1);
        Letter letter = letterOptional.get();
        Map<String, Object> model = new HashMap();
        model.put("fullname", mail.getFullname());
        model.put("age", mail.getAge());
        model.put("congragulation", letter.getText());
        model.put("color", letter.getColor());

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            if(letter.getImage().length == 0){
                InputStream inputStream = getClass().getClassLoader().getResourceAsStream(FILE_PATH);
                byte[] imgBytes = IOUtils.toByteArray(inputStream);
                byte[] imgBytesAsBase64 = Base64.encodeBase64(imgBytes);
                String imgDataAsBase64 = new String(imgBytesAsBase64);
                String imgAsBase64 = "data:image/png;base64," + imgDataAsBase64;
                model.put("imagehp", imgAsBase64);
            }else {
                byte[] imgBytes = letter.getImage();
                byte[] imgBytesAsBase64 = Base64.encodeBase64(imgBytes);
                String imgDataAsBase64 = new String(imgBytesAsBase64);
                String imgAsBase64 = "data:image/png;base64," + imgDataAsBase64;
                model.put("imagehp", imgAsBase64);

            }


            Template template = freemarkerConfig.getTemplate("birthday.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

            helper.setTo(mail.getTo());

            helper.setSubject("Happy Birthday");
            helper.setText(html, true);
            mailSender.send(message);

        } catch (MessagingException e) {
            log.debug("MessaginException", e);
        } catch (TemplateNotFoundException e) {
            log.debug("TemplateNotFoundException", e);
        } catch (IOException e) {
            log.debug("IOException", e);
        } catch (TemplateException e) {
            log.debug("TemplateException", e);
        }
    }

    /**
     * runs every day at 10 o'clock
     */

    //"*/10 * * * * *" = every ten seconds.
    @Scheduled(cron = "0 0 10 * * *")
    public void checkForDay() {

        List<User> usersBirthDayList = userDao.getUserWithBirthday();

        if (!usersBirthDayList.isEmpty()) {

            for (User user : usersBirthDayList) {
                Mail mail = new Mail();
                String fullName = user.getFirstName() + " " + user.getMiddleName() + " " + user.getLastName();
                String years = user.getBirthday().toString();
                String year = years.substring(0, 4);
                String month = years.substring(5, 7);
                String day = years.substring(8, 10);
                LocalDate start = LocalDate.of(Integer.valueOf(year), Integer.valueOf(month), Integer.valueOf(day));
                LocalDate end = LocalDate.now();
                long ageL = ChronoUnit.YEARS.between(start, end);
                String age = Long.toString(ageL);
                String email = user.getEmail();
                mail.setTo(email);
                mail.setFullname(fullName);
                mail.setAge(age);
                sendSimpleMessage(mail);
            }
        }


    }
}