package org.dougllas.medsystem.service.mail;

import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Email {

	private String subject;
	private String from;
	private List<String> tos;
	private List<String> copies;
	private List<String> hiddenCopies;
	private String encoding;
	private String message;
	private Map<String, byte[]> anexos;
	private Properties mailProperties;
	private ProxyCredentials proxyCredentials;
	private EmailGenerator generator;
	
	public Email() {
		generator = new EmailGenerator(this);
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public List<String> getTos() {
		return tos;
	}

	public void setTos(List<String> tos) {
		this.tos = tos;
	}

	public List<String> getCopies() {
		return copies;
	}

	public void setCopies(List<String> copies) {
		this.copies = copies;
	}

	public List<String> getHiddenCopies() {
		return hiddenCopies;
	}

	public void setHiddenCopies(List<String> hiddenCopies) {
		this.hiddenCopies = hiddenCopies;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, byte[]> getAnexos() {
		return anexos;
	}
	
	public void setAnexos(Map<String, byte[]> anexos) {
		this.anexos = anexos;
	}

	public Properties getMailProperties() {
		return mailProperties;
	}

	public void setMailProperties(Properties mailProperties) {
		this.mailProperties = mailProperties;
	}

	public ProxyCredentials getProxyCredentials() {
		return proxyCredentials;
	}
	
	public void setProxyCredentials(ProxyCredentials proxyCredentials) {
		this.proxyCredentials = proxyCredentials;
	}
	
	public static EmailGenerator create() {
		return new Email().getGenerator();
	}
	
	public EmailGenerator getGenerator() {
		return generator;
	}
	
}