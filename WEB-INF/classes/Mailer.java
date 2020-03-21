import java.util.*;  
import java.io.*;
import javax.mail.*;  
import javax.mail.internet.InternetAddress;  
import javax.mail.internet.MimeMessage;  
  
public class Mailer {  
public static void send(String to,String subject,String msg){  
  
String user="abbhinav.nomulla656@gmail.com";//change accordingly  
String pass="9542120253";  
  
//1st step) Get the session object    
Properties props = new Properties();
props.put("mail.smtp.user", user);  
props.put("mail.smtp.host", "smtp.gmail.com");//change accordingly  
props.put("mail.smtp.port", "465"); 
props.put("mail.smtp.auth", "true");  
props.put("mail.smtp.socketFactory.port", "465");
props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
props.put("mail.smtp.socketFactory.fallback", "false");
//props.put("mail.smtp.starttls.enable", "true");
SecurityManager security=System.getSecurityManager();
try{
 Session session = Session.getInstance(props,  
 new javax.mail.Authenticator() {  
  protected PasswordAuthentication getPasswordAuthentication() {  
   return new PasswordAuthentication(user,pass);  
   }  
});  
//2nd step)compose message 
 MimeMessage message = new MimeMessage(session);  
 message.setFrom(new InternetAddress(user));  
 message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
 message.setSubject(subject);  
 message.setText(msg);  
   
 //3rd step)send message  
 Transport.send(message);   
  
 } catch (Exception e) {  
    e.printStackTrace();  
 }  
 
}
} 