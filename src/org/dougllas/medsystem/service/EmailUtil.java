package org.dougllas.medsystem.service;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;


public class EmailUtil {

    private JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

    public EmailUtil() {

        this.mailSender.setUsername("dougllasfps@gmail.com");
        this.mailSender.setPassword("Doug11a$");
        this.mailSender.setHost("smtp.seduc.ce.gov.br");

        Properties props = new Properties();

        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.put("mail.smtp.userset", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.mime.charset", "UTF-8");

        this.mailSender.setJavaMailProperties(props);
    }

    public void enviarEmail(Email mail) {
        this.enviarEmail(mail, null);
    }

    public void enviarEmail(Email mail, Map<String, String> anexos) {
        try {
            MimeMessage mimeMessage = this.mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setTo(mail.getDestino());
            mimeMessageHelper.setText(mail.getMensagem(), true);
            mimeMessageHelper.setSubject(mail.getAssunto());
            mimeMessageHelper.setFrom(mail.getUsuario());

            if(anexos != null) {
                FileSystemResource fileSystemResource = null;
                Iterator iterator = anexos.entrySet().iterator();

                while(iterator.hasNext()) {
                    Map.Entry entry = (Map.Entry)iterator.next();
                    fileSystemResource = new FileSystemResource(new File((String)entry.getValue()));
                    mimeMessageHelper.addAttachment((String)entry.getKey(), fileSystemResource);
                }
            }

            MailcapCommandMap commandMap = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
            commandMap.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
            commandMap.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
            commandMap.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
            commandMap.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
            commandMap.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
            CommandMap.setDefaultCommandMap(commandMap);
            this.mailSender.setProtocol("smtps");

            this.mailSender.send(mimeMessage);

        } catch (MessagingException ex) {
            throw new RuntimeException("Ocorreu um problema no envio de e-mail. Tente novamente mais tarde.");
        }
    }
}