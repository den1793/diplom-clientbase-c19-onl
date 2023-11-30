package by.clientbase.diplomclientbasec19onl.service;

import by.clientbase.diplomclientbasec19onl.entity.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailService {
    @Autowired
    public JavaMailSender emailSender;

    private EmailService(JavaMailSender mailSender) {
        this.emailSender = mailSender;
    }

    public void sendMail(Email email) {


        SimpleMailMessage mailMessage  = new SimpleMailMessage();
        mailMessage.setFrom(email.getFrom());
        mailMessage.setTo(email.getTo());
        mailMessage.setSubject("Task №:" + email.getSubject() + "/");
        mailMessage.setText(email.getText());


        emailSender.send(mailMessage);
    }


}


