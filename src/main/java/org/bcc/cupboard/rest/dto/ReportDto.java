package org.bcc.cupboard.rest.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ReportDto implements Serializable {
	private static final long serialVersionUID = -3164787108473530740L;

	private long totalFamilies;
	private long totalKids;
	private long totalAdults;
	private long totalPounds;
	private long totalBccAttendees;
	private long totalNonBccAttendees;
	private List<BccServiceReportDto> bccServiceReports;
	private List<EthnicityReportDto> raceReports;
	
	public ReportDto() {
		bccServiceReports = new ArrayList<BccServiceReportDto>();
		raceReports = new ArrayList<EthnicityReportDto>();
	}
	
	public long getTotalFamilies() {
		return totalFamilies;
	}
	
	public void setTotalFamilies(long total) {
		this.totalFamilies = total;
	}
	
	public long getTotalKids() {
		return totalKids;
	}
	
	public void setTotalKids(long totalKids) {
		this.totalKids = totalKids;
	}
	
	public long getTotalAdults() {
		return totalAdults;
	}
	
	public void setTotalAdults(long totalAdults) {
		this.totalAdults = totalAdults;
	}
	
	public long getTotalPounds() {
		return totalPounds;
	}
	
	public void setTotalPounds(long totalPounds) {
		this.totalPounds = totalPounds;
	}
	
	public List<EthnicityReportDto> getRaceReports() {
		return raceReports;
	}
	
	public void addRaceReports(EthnicityReportDto raceReport) {
		raceReports.add(raceReport);
	}

	public long getTotalBccAttendees() {
		return totalBccAttendees;
	}

	public void setTotalNonBccAttendees(long totalNonBccAttendees) {
		this.totalNonBccAttendees = totalNonBccAttendees;
	}
	
	public long getTotalNonBccAttendees() {
		return totalNonBccAttendees;
	}

	public void setTotalBccAttendees(long totalBccAttendees) {
		this.totalBccAttendees = totalBccAttendees;
	}

	public List<BccServiceReportDto> getBccServiceReports() {
		return bccServiceReports;
	}

	public void addBccServiceReport(BccServiceReportDto bccServiceReport) {
		bccServiceReports.add(bccServiceReport);
	}

	public void setBccServiceReports(List<BccServiceReportDto> bccServiceReports) {
		this.bccServiceReports = bccServiceReports;
	}

	public void setRaceReports(List<EthnicityReportDto> raceReports) {
		this.raceReports = raceReports;
	}
	
}
