package org.dougllas.medsystem.service.mail;

import java.security.GeneralSecurityException;
import java.util.Properties;

import com.sun.mail.util.MailSSLSocketFactory;

public enum EmailProperties {

    PROXY_SET("proxySet", "false"),
    PROXY_HOST("ProxyHost", ""),
    PROXY_PORT("ProxyPort", ""),
	SMTP_AUTH("mail.smtp.auth", "true"),
	SMTP_USERSET("mail.smtp.userset", "true"),
	SMTP_SSL_ENABLE("mail.smtp.ssl.enable", "true"),
	SMTP_SSL_TRUST("mail.smtp.ssl.trust", "*"),
	SMTP_PORT("mail.smtp.port", "465"),
	MIME_CHARSET("mail.mime.charset", "UTF-8"),
	SMTP_REQUIRES_AUTHENTICATION("mail.smtp.requiresAuthentication", "true"),
	TRANSPORT_PROTOCOL("mail.transport.protocol", "smtps"),
	SMTP_STARTTLS_ENABLE("mail.smtp.starttls.enable", "true"),
	SMTP_SOCKET_FACTORY_FALLBACK("mail.smtp.socketFactory.fallback", "false"),
	SMTP_SOCKET_FACTORY_PORT("mail.smtp.socketFactory.port", "465"),
	DEBUG("mail.debug", "true"),
	SMTPS_SOCKET_FACTORY("mail.smtps.socketFactory", createDefaultSocketFactory());
	
	private String property;
	private Object defaultValue;
	
	private EmailProperties(String property, Object defaultValue) {
		this.property = property;
		this.defaultValue = defaultValue;
	}
	
	public static Properties getAllDefaultProperties() {
		Properties properties = new Properties();
		properties.put(SMTP_AUTH.getProperty(), SMTP_AUTH.getDefaultValue());
		properties.put(SMTP_USERSET.getProperty(), SMTP_USERSET.getDefaultValue());
		properties.put(SMTP_SSL_ENABLE.getProperty(), SMTP_SSL_ENABLE.getDefaultValue());
		properties.put(SMTP_SSL_TRUST.getProperty(), SMTP_SSL_TRUST.getDefaultValue());
		properties.put(SMTP_PORT.getProperty(), SMTP_PORT.getDefaultValue());
		properties.put(MIME_CHARSET.getProperty(), MIME_CHARSET.getDefaultValue());
		properties.put(SMTP_REQUIRES_AUTHENTICATION.getProperty(), SMTP_REQUIRES_AUTHENTICATION.getDefaultValue());
		properties.put(TRANSPORT_PROTOCOL.getProperty(), TRANSPORT_PROTOCOL.getDefaultValue());
		properties.put(SMTP_STARTTLS_ENABLE.getProperty(), SMTP_STARTTLS_ENABLE.getDefaultValue());
		properties.put(SMTP_SOCKET_FACTORY_FALLBACK.getProperty(), SMTP_SOCKET_FACTORY_FALLBACK.getDefaultValue());
		properties.put(SMTP_SOCKET_FACTORY_PORT.getProperty(), SMTP_SOCKET_FACTORY_PORT.getDefaultValue());
		properties.put(DEBUG.getProperty(), DEBUG.getDefaultValue());
		properties.put(SMTPS_SOCKET_FACTORY.getProperty(), SMTPS_SOCKET_FACTORY.getDefaultValue());
		return properties;
	}
	
	public String getProperty() {
		return property;
	}
	
	public Object getDefaultValue() {
		return defaultValue;
	}
	
	private static MailSSLSocketFactory createDefaultSocketFactory() {
		MailSSLSocketFactory socketFactory = null;
		try {
			socketFactory = new MailSSLSocketFactory();
			socketFactory.setTrustAllHosts(true);
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		}
		return socketFactory;
	}
	
}