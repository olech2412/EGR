package com.example.application.email;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Mailer {

    /**
     * Sends an email to the given address with the given subject and content.
     * For Activation
     * @param firstName
     * @param emailTarget
     * @param activationCode
     * @param deactivationCode
     * @throws MessagingException
     */
    public void sendActivationEmail(String firstName, String emailTarget, String activationCode, String deactivationCode) throws MessagingException, IOException {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", false);
        prop.put("mail.smtp.host", "localhost");
        prop.put("mail.smtp.port", "25");

        Message message = new MimeMessage(Session.getInstance(prop));
        message.setFrom(new InternetAddress("noreply_essensgetter@olech2412.de"));
        message.setRecipients(
                Message.RecipientType.TO, InternetAddress.parse(emailTarget));
        message.setSubject("Aktivierung deines Essensgetter-Newsletter-Accounts");

        String msg = getActivationText(firstName, activationCode, deactivationCode);

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);

        Transport.send(message);
    }

    /**
     * Sends an email to the given address with the given subject and content.
     * For Deactivation
     * @param firstName
     * @param emailTarget
     * @throws MessagingException
     */
    public void sendDeactivationEmail(String firstName, String emailTarget) throws MessagingException {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", false);
        prop.put("mail.smtp.host", "localhost");
        prop.put("mail.smtp.port", "25");

        Message message = new MimeMessage(Session.getInstance(prop));
        message.setFrom(new InternetAddress("noreply_essensgetter@olech2412.de"));
        message.setRecipients(
                Message.RecipientType.TO, InternetAddress.parse(emailTarget));
        message.setSubject("Deaktivierung deines Essensgetter-Newsletter-Accounts");

        String msg = getDeactivationText(firstName);

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);

        Transport.send(message);
    }

    private String getActivationText(String firstName, String activationCode, String deactivationCode) {
        String activateUrl = "https://egr.olech2412.de/activate?code=" + activationCode;
        String deactivateUrl = "https://egr.olech2412.de/deactivate?code=" + deactivationCode;
        String msg = StaticEmailText.ACTIVATION_TEXT;
        msg = msg.replaceFirst("%s", firstName);
        msg = msg.replaceFirst("%s", activateUrl);
        msg = msg.replaceFirst("%s", deactivateUrl);

        return msg;
    }

    private String getDeactivationText(String firstName) {
        String msg = StaticEmailText.DEACTIVATION_TEXT;
        msg = msg.replaceFirst("%s", firstName);
        msg = msg.replaceFirst("%s", "https://egr.olech2412.de");

        return msg;
    }
}
