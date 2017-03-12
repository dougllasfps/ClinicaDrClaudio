package org.dougllas.medsystem.service.mail;

public class ProxyCredentials {

	private String host;
	private Integer port;
	
	public ProxyCredentials(String host, Integer port) {
		this.host = host;
		this.port = port;
	}
	
	public boolean empty() {
		return (host == null || host.equalsIgnoreCase("")) && (port == null); 
	}
	
	public boolean notEmpty() {
		return !empty();
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getPort() {
		return port;
	}
	
	public void setPort(Integer port) {
		this.port = port;
	}
	
}