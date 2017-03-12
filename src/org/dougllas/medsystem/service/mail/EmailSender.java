package org.dougllas.medsystem.service.mail;

import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

public class EmailSender {
	
	public static final String DEFAULT_PROTOCOL = "smtp";
	public static final String DEFAULT_ENCODING = "UTF-8";
	public static final int DEFAULT_PORT = -1;
	private Session session;
	private String protocol;
	private String host;
	private int port;
	private String username;
	private String password;
	private Properties mailProperties;
	
	public EmailSender() {
		port = DEFAULT_PORT;
		this.mailProperties = EmailProperties.getAllDefaultProperties();
	}
	
	public EmailSender(String host, String username, String password) {
		this(host, username, password, null);
		this.mailProperties = EmailProperties.getAllDefaultProperties();
	}
	
	public EmailSender(String host, String username, String password, Properties mailProperties) {
		this();
		this.host = host;
		this.username = username;
		this.password = password;
		this.mailProperties = mailProperties;
	}
	
	public void doSend(Email email) throws EmailException, IOException {
		
		Map<String, Throwable> failedMessages = new LinkedHashMap<String, Throwable>();
		Transport transport = null;
		
		try {
			MimeMessage mimeMessage = createMimeMessage();
			
			try {
				
				mimeMessage.setSubject( email.getSubject(), (email.getEncoding() == null || email.getEncoding().isEmpty()) ? DEFAULT_ENCODING : email.getEncoding() );
				mimeMessage.setFrom(new InternetAddress( email.getFrom() ));

				MimeMultipart multipart = new MimeMultipart("related");	

				BodyPart messageBodyPart = new MimeBodyPart();
				String contentMimeType;
				if (email.getEncoding() != null && !email.getEncoding().isEmpty()) contentMimeType = "text/html; charset=\""+email.getEncoding()+"\"";
				else contentMimeType = "text/html";
				messageBodyPart.setContent( email.getMessage() != null ? email.getMessage() : "", contentMimeType);
				multipart.addBodyPart(messageBodyPart);
				
				
				if (email.getAnexos() != null) {
					for (Map.Entry<String, byte[]> entry : email.getAnexos().entrySet()) {
						MimeBodyPart mimeBodyPart = new MimeBodyPart();
						mimeBodyPart.setDisposition("attachment");
						mimeBodyPart.setFileName(entry.getKey());
						
						InputStream is = new BufferedInputStream(new ByteArrayInputStream(entry.getValue()));
						String mimeType = URLConnection.guessContentTypeFromStream(is);
						if (mimeType == null) mimeType = "application/octet-stream";
						ByteArrayDataSource dataSource = new ByteArrayDataSource(entry.getValue(), mimeType);
						
						mimeBodyPart.setDataHandler(new DataHandler(dataSource));
						multipart.addBodyPart(mimeBodyPart);
					}
				}
				
				mimeMessage.setContent(multipart);
				
				// Configurando os destinos
				if (email.getTos() != null) {
					for (String destino : email.getTos()) {
						mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(destino));
					}
				}
				if (email.getCopies() != null) {
					for (String copia : email.getCopies()) {
						mimeMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(copia));
					}
				}
				if (email.getHiddenCopies() != null) {
					for (String copiaOculta : email.getHiddenCopies()) {
						mimeMessage.addRecipient(Message.RecipientType.BCC, new InternetAddress(copiaOculta));
					}
				}
				////////////////////////
				
				if (mimeMessage.getSentDate() == null) {
					mimeMessage.setSentDate(new Date());
				}
				
				String messageId = mimeMessage.getMessageID();
				if (messageId != null) {
					mimeMessage.setHeader("Message-ID", messageId);
				}
				
				mimeMessage.saveChanges();
				
				try {
					if (email.getProxyCredentials() != null && email.getProxyCredentials().notEmpty()) {
						getMailProperties().put(EmailProperties.PROXY_SET, "true");
						getMailProperties().put(EmailProperties.PROXY_HOST, email.getProxyCredentials().getHost());
						getMailProperties().put(EmailProperties.PROXY_PORT, email.getProxyCredentials().getPort());
					}
					
					transport = getTransport(getSession());
					transport.connect(getHost(), getPort(), getUsername(),
							getPassword());
					
					transport.sendMessage(mimeMessage,
							mimeMessage.getAllRecipients());
				} catch (AuthenticationFailedException e) {
					e.printStackTrace();
					throw new EmailException("Authentication Failed: "+e.getMessage(), e);
				} catch (MessagingException e) {
					e.printStackTrace();
					throw new EmailException("Unknown Exception: "+e.getMessage(), e);
				}
				
			} catch (MessagingException ex) {
				failedMessages.put(ex.getMessage(), ex);
			}
		} finally {
			try {
				if (transport != null) transport.close();
			} catch (MessagingException e) {
				if (!(failedMessages.isEmpty())) {
					throw new EmailException("Failed to close server connection after message failures: "+e.getMessage(), e);
				}
				throw new EmailException("Failed to close server connection after message sending: "+e.getMessage(), e);
			}

		}

		if (!(failedMessages.isEmpty()))
			throw new EmailException("Erro inesperado.");
	}
	
	public MimeMessage createMimeMessage() {
		return new MimeMessage(getSession());
	}
	
	public Charset getDefaultCharset() {
		return Charset.forName(getDefaultCharsetName());
	}
	
	public String getDefaultCharsetName() {
		return Charset.defaultCharset().name();
	}
	
	public synchronized Session getSession() {
		if (this.session == null) {
			Authenticator authenticator = new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(getUsername(),getPassword());
				}
			};
			this.session = Session.getInstance(getMailProperties(), authenticator);
		}
		return this.session;
	}
	
	public synchronized void setSession(Session session) {
		if (session == null) throw new RuntimeException("Session must not be null");
		this.session = session;
	}
	
	protected Transport getTransport(Session session) throws NoSuchProviderException {
		String protocol = getProtocol();
		if (protocol == null) {
			protocol = session.getProperty(EmailProperties.TRANSPORT_PROTOCOL.getProperty());
			if (protocol == null) {
				protocol = DEFAULT_PROTOCOL;
			}
		}
		return session.getTransport(protocol);
	}
	
	public Properties getMailProperties() {
		return mailProperties;
	}
	
	public void setMailProperties(Properties mailProperties) {
		this.mailProperties = mailProperties;
		synchronized (this) {
			this.session = null;
		}
	}
	
	public String getProtocol() {
		return protocol;
	}
	
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	
	public String getHost() {
		return host;
	}
	
	public void setHost(String host) {
		this.host = host;
	}
	
	public int getPort() {
		return port;
	}
	
	public void setPort(int port) {
		this.port = port;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
}