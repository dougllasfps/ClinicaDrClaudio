package org.dougllas.medsystem.service.mail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class EmailGenerator {

    private Email email;

    public EmailGenerator(Email email) {
        this.email = email;
    }

    public EmailGenerator tos(List<String> tos) {
        email.setTos(tos);
        return this;
    }

    public EmailGenerator to(String to) {
        if (email.getTos() == null) email.setTos(new ArrayList<String>());
        email.getTos().add(to);
        return this;
    }

    public EmailGenerator copies(List<String> copies) {
        email.setCopies(copies);
        return this;
    }

    public EmailGenerator copie(String copie) {
        if (email.getCopies() == null) email.setCopies(new ArrayList<String>());
        email.getCopies().add(copie);
        return this;
    }

    public EmailGenerator hiddenCopies(List<String> hiddenCopies) {
        email.setHiddenCopies(hiddenCopies);
        return this;
    }

    public EmailGenerator hiddenCopie(String hiddenCopie) {
        if (email.getHiddenCopies() == null) email.setHiddenCopies(new ArrayList<String>());
        email.getHiddenCopies().add(hiddenCopie);
        return this;
    }

    public EmailGenerator from(String from) {
        email.setFrom(from);
        return this;
    }

    public EmailGenerator encoding(String encoding) {
        email.setEncoding(encoding);
        return this;
    }

    public EmailGenerator subject(String subject) {
        email.setSubject(subject);
        return this;
    }

    public EmailGenerator message(String message) {
        email.setMessage(message);
        return this;
    }

    public EmailGenerator attachment(String fileName, byte[] bytes) {
        if (email.getAnexos() == null) email.setAnexos(new HashMap<String, byte[]>());
        email.getAnexos().put(fileName, bytes);
        return this;
    }

    public EmailGenerator property(String key, String value) {
        if (email.getMailProperties() == null) email.setMailProperties(new Properties());
        email.getMailProperties().put(key, value);
        return this;
    }

    public EmailGenerator proxy(String host, Integer port) {
        email.setProxyCredentials(new ProxyCredentials(host, port));
        return this;
    }

    public Email mail() {
        return email;
    }

}
