package org.bcc.cupboard.auth.dao;

import org.bcc.cupboard.auth.jpa.AdminJpa;
import org.bcc.cupboard.dao.BaseDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Qualifier(value = "adminDao")
public class AdminDao extends BaseDao {

	public void persistAdmin(AdminJpa admin) {
		em.persist(admin);
	}
	
	public AdminJpa getAdmin(String username) {
		AdminJpa admin = em.find(AdminJpa.class, username);
		return admin;
	}
	
	public void updateAdmin(String username, String password) {
		AdminJpa admin = getAdmin(username);
		
		if(admin != null) {
			admin.setPassword(password);
			em.merge(admin);
		}
	}
}
