package org.bcc.cupboard.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class BaseDao {

	@PersistenceContext(name = "cupboardPU")
	protected EntityManager em;
	
	protected EntityManager getEntityManager() {
		return em;
	}
	
	protected void setEntityManager(EntityManager em) {
		this.em = em;
	}
}
