package ifmg.rad.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ifmg.rad.util.FacesMensagens;


@ManagedBean
@RequestScoped
public class LoginBean implements Serializable{
	
	private static final long serialVersionUID = 3944430934619925816L;
	private FacesContext facesContext;
	private ExternalContext externalContext;
	private HttpServletRequest request;
	private HttpServletResponse response;

	private String login;

	public LoginBean() {
		facesContext = FacesContext.getCurrentInstance();
		externalContext = facesContext.getExternalContext();
		request = (HttpServletRequest) externalContext.getRequest();
		response = (HttpServletResponse) externalContext.getResponse();
	}

	public void preRender(ComponentSystemEvent e) {
		if ("true".equals(request.getParameter("invalid"))) {
			FacesMensagens.error("Usuário ou senha inválido!");
		}
	}

	public void login() throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/j_spring_security_check");
		dispatcher.forward(request, response);

		facesContext.responseComplete();
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
}
