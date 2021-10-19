package arrays;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class JavaSMTPsample {
    private static final String SMTP_SERVER="server/host name";
    private static final String USERNAME=" ";
    private static final String Paswword=" ";
    private static final String EMAIL_FROM=" ";
    private static final String EMAIL_TEXT_CONTENT=" ";
    private static final String EMAIL_TO=" ";
    private static final String EMAIL_TO_CC=" ";
    private static final String EMAIL_SUBJECT=" ";

    public static void main(String args[]){
        Properties pro= System.getProperties();
        pro.put("mail.smtp.host",SMTP_SERVER);
        pro.put("mail.smtp.auth","true");
        pro.put("mail.smtp.port","25"); //default port 25(only for 2-3 senders that are configured to the account and for multiple sender we can use 587

        Session session=Session.getInstance(pro,null);
        Message msg= new MimeMessage(session);

        try{
            msg.setFrom(new InternetAddress(EMAIL_FROM));
            msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(EMAIL_TO,false));
            msg.setRecipients(Message.RecipientType.CC,InternetAddress.parse(EMAIL_TO_CC,false));
            msg.setSubject(EMAIL_SUBJECT);
            msg.setText(EMAIL_TEXT_CONTENT);
            msg.setSentDate(new Date());

            Transport t=session.getTransport("smtp");
            t.connect(SMTP_SERVER,USERNAME,Paswword);
            t.sendMessage(msg,msg.getAllRecipients());
            t.close();
        }catch (MessagingException e){
            e.printStackTrace();
        }
    }

}
