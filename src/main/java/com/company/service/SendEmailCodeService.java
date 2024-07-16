package com.company.service;

import jakarta.mail.*;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class SendEmailCodeService {

    public static void sendMail(String email, String xabar){
        Properties properties = new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");

        String myAccountEmail = "xojamqulovravshan731@gmail.com";
        String password = "mlhi ucsl xydy xjar";


        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail,password);
            }
        });

        try {
            Message message = prepareMessage(session,myAccountEmail, email,xabar);
            Transport.send(message);
        }catch (Exception e){
            e.printStackTrace();
        }


    }


    private static Message prepareMessage(Session session, String myAccountEmail, String recepient, String xabar){
        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Test Oluvchi sayt");
            message.setText(xabar);
            return message;
        } catch (AddressException e) {
            throw new RuntimeException(e);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }


}
