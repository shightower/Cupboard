package org.bcc.cupboard.auth.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.bcc.cupboard.auth.Admin;
import org.bcc.cupboard.auth.AdminBean;

@Entity
@Table(name = "USERS")
public class AdminJpa implements Admin {
	private static final long serialVersionUID = 3834395802668802056L;

	@Id
	@Column(name = "USERNAME")
	private String username;
	
	@Column(name = "PASSWORD", nullable = false)
	private String password;
	
	public AdminJpa() {
		
	}
	
	public AdminJpa(AdminBean admin) {
		setUsername(admin.getUsername());
		setPassword(admin.getPassword());
	}
	
	/* (non-Javadoc)
	 * @see org.bcc.cupboard.auth.Admin#getUsername()
	 */
	@Override
	public String getUsername() {
		return username;
	}
	
	/* (non-Javadoc)
	 * @see org.bcc.cupboard.auth.Admin#setUsername(java.lang.String)
	 */
	@Override
	public void setUsername(String username) {
		this.username = username;
	}
	
	/* (non-Javadoc)
	 * @see org.bcc.cupboard.auth.Admin#getPassword()
	 */
	@Override
	public String getPassword() {
		return password;
	}
	
	/* (non-Javadoc)
	 * @see org.bcc.cupboard.auth.Admin#setPassword(java.lang.String)
	 */
	@Override
	public void setPassword(String password) {
		this.password = password;
	}	
	
}
