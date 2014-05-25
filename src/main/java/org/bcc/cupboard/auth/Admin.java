package org.bcc.cupboard.auth;

import java.io.Serializable;

public interface Admin extends Serializable {

	public String getUsername();
	public void setUsername(String username);
	public String getPassword();
	public void setPassword(String password);

}