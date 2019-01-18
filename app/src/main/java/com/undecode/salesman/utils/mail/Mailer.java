package com.undecode.salesman.utils.mail;

import java.security.Security;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mailer extends javax.mail.Authenticator {
  private String mailhost = "smtp.gmail.com";
  private String user;
  private String password;
  private Session session;
  public static final String textPlain = "text/plain";
  public static final String textHTML = "text/html; charset=utf-8";

  static {
    Security.addProvider(new JSSEProvider());
  }
 
  public Mailer(String user, String password)
  {
    this.user = user;
    this.password = password;
 
    Properties props = new Properties();
    props.setProperty("mail.transport.protocol", "smtp");
    props.setProperty("mail.host", mailhost);
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.socketFactory.port", "587");
    props.put("mail.smtp.starttls.enable", "true");
    //props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    props.put("mail.smtp.socketFactory.fallback", "false");
    props.setProperty("mail.smtp.quitwait", "false");
 
    session = Session.getDefaultInstance(props, this);
  }
 
  protected PasswordAuthentication getPasswordAuthentication()
  {
    return new PasswordAuthentication(user, password);
  }
 
  public synchronized void sendMail(String subject, String body, String sender, String recipients, String type)
  {
    try
    {
      MimeMessage message = new MimeMessage(session);
      //DataHandler handler = new DataHandler(new ByteArrayDataSource(body.getBytes(), "text/plain"));
      DataHandler handler = new DataHandler(new ByteArrayDataSource(body.getBytes(), type));

      message.setSender(new InternetAddress(sender));
      message.setSubject(subject);
      message.setDataHandler(handler);

      if (recipients.indexOf(',') > 0)
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));
      else
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipients));

      Transport.send(message);
    } catch (MessagingException e)
    {
      e.printStackTrace();
    }
  }
}