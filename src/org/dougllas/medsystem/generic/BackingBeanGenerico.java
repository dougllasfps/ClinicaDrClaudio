package org.dougllas.medsystem.generic;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class BackingBeanGenerico {

	protected final static String ADD_STATE = "new";
	protected final static String EDIT_STATE = "edit";
	protected final static String SEARCH_STATE = "search";

	protected String state = ADD_STATE;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public boolean isAdicionando() {
		return getState().equals(ADD_STATE);
	}

	public boolean isEditando() {
		return getState().equals(EDIT_STATE);
	}

	public boolean isPesquisando() {
		return getState().equals(SEARCH_STATE);
	}

	public static FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	public static void adicionaMensagemDeErro(String mensagem) {
		adicionaMensagem(mensagem, FacesMessage.SEVERITY_ERROR);
	}

	public static void adicionaMensagemDeInformacao(String mensagem) {
		adicionaMensagem(mensagem, FacesMessage.SEVERITY_INFO);
	}
	
	private static void adicionaMensagem(String mensagem, Severity severity){
		getFacesContext().addMessage(null, new FacesMessage(severity, mensagem, mensagem));
	}
	
	public HttpSession getSession(){
		return (HttpSession) getFacesContext().getExternalContext().getSession(false);
	}

}
