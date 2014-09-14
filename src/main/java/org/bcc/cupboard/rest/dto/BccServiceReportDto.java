package org.bcc.cupboard.rest.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BccServiceReportDto implements Serializable {
	private static final long serialVersionUID = -622641551344825769L;

	private String service;
	private long serviceCount;
	
	public String getService() {
		return service;
	}
	
	public void setService(String service) {
		this.service = service;
	}
	
	public long getServiceCount() {
		return serviceCount;
	}
	
	public void setServiceCount(long serviceCount) {
		this.serviceCount = serviceCount;
	}
	
}
