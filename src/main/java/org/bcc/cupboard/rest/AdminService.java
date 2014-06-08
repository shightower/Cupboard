package org.bcc.cupboard.rest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.bcc.cupboard.auth.AdminBean;
import org.bcc.cupboard.auth.dao.AdminDao;
import org.bcc.cupboard.auth.jpa.AdminJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Path("/admin")
public class AdminService {
	private static final Logger Log = Logger.getLogger(AdminService.class);

	@Autowired
	@Qualifier("adminDao")
	private AdminDao adminDao;
	
	@POST
	@Path("authenticate")
	@Consumes({MediaType.APPLICATION_JSON})
	public Response authenticateAdmin(AdminBean adminBean) {
		ResponseBuilder rb = Response.status(Status.OK);
		
		if(adminBean != null) {
			Log.info("Retrieving admin with username " + adminBean.getUsername());
			AdminJpa adminJpa = adminDao.getAdmin(adminBean.getUsername());
			boolean isAdmin = false;
			
			if(adminJpa != null) {
				isAdmin = isAuthenticated(adminBean.getPassword(), adminJpa.getPassword());
			}
			
			if(!isAdmin) {
				Log.error("Invalid password recieved for user " + adminBean.getUsername());
				rb = Response.status(Status.UNAUTHORIZED);
				rb.entity("Incorrect Username/Password Provided");
			}			
		} else {
			Log.error("Invalid admin parameter, value was null");
			rb = Response.status(Status.UNAUTHORIZED);
			rb.entity("Incorrect Username/Password Provided");
		}
		
		return rb.build();
	}
	
	@POST
	@Path("update")
	@Consumes({MediaType.APPLICATION_JSON})
	public Response updateAdmin(@QueryParam("user") AdminBean adminBean, @QueryParam("password") String password) {
		ResponseBuilder rb = Response.status(Status.OK);
		
		if(adminBean != null) {
			Log.info("Retrieving admin with username " + adminBean.getUsername());
			AdminJpa adminJpa = adminDao.getAdmin(adminBean.getUsername());
			boolean isAdmin = false;
			
			if(adminJpa != null) {
				isAdmin = isAuthenticated(adminBean.getPassword(), adminJpa.getPassword());
			}
			
			if(!isAdmin) {
				Log.error("Invalid password recieved for user " + adminBean.getUsername());
				rb = Response.status(Status.UNAUTHORIZED);
				rb.entity("Incorrect Username/Password Provided");
			} else {
				String hash = getHash(password);
				adminJpa.setPassword(hash);
				
				Log.info("Update password for user " + adminJpa.getUsername());
				adminDao.updateAdmin(adminJpa.getUsername(), password);
			}
		} else {
			Log.error("Invalid username - " + adminBean.getUsername());
			rb = Response.status(Status.UNAUTHORIZED);
			rb.entity("Incorrect Username/Password Provided");
		}
		
		return rb.build();
	}
	
	private boolean isAuthenticated(String password, String hash) {
		String hashtext = getHash(password);		
		return hash.equalsIgnoreCase(hashtext);
	}
	
	private String getHash(String clearText) {
		StringBuffer sb = new StringBuffer();
		try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(clearText.getBytes());
            byte[] data = md.digest();
            
            
            for(byte byt : data) {
            	sb.append(Integer.toString((byt & 0xff) + 0x100, 16).substring(1));
            }
        }
        catch (NoSuchAlgorithmException e) {
           Log.error("Error getting hash algorithm", e);
           sb = new StringBuffer();
        }
		
		return sb.toString();
	}
}
