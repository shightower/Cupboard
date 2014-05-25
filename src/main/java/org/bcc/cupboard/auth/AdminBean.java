package org.bcc.cupboard.auth;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AdminBean implements Admin {
	private static final long serialVersionUID = -3985757274029487041L;
	
	private String username;
	private String password;

	public AdminBean() {
		
	}
	
	public AdminBean(String username, String password) {
		setUsername(username);
		setPassword(password);
	}
	
	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}
}
