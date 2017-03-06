package org.dougllas.medsystem.backingbean;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.dougllas.medsystem.model.Usuario;


public class LoginPhaseListener implements PhaseListener {

	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent event) {

		FacesContext facesContext = event.getFacesContext();

		boolean isLoginPage = ((HttpServletRequest) facesContext
				.getExternalContext().getRequest()).getRequestURI().endsWith(
				"login.xhtml");

		HttpSession session = (HttpSession) facesContext.getExternalContext()
				.getSession(true);
		Usuario usuarioLogado = (Usuario) session
				.getAttribute("usuario");

		if (!isLoginPage && usuarioLogado == null) {
			NavigationHandler navigation = facesContext.getApplication()
					.getNavigationHandler();
			navigation.handleNavigation(facesContext, null, "login");
		}
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
