package org.bcc.cupboard.rest.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RaceReportDto implements Serializable {
	private static final long serialVersionUID = -4743670287709894902L;

	private String race;
	private long total;
	
	public String getRace() {
		return race;
	}
	public void setRace(String race) {
		this.race = race;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
}
