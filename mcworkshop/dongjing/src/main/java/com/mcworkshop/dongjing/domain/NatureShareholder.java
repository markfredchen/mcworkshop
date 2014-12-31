package com.mcworkshop.dongjing.domain;

public class NatureShareholder extends CompanyContact {

	private static final long serialVersionUID = 1L;
	
	private Long companyID;
	private String shareRatio;

	public Long getCompanyID() {
		return companyID;
	}

	public void setCompanyID(Long companyID) {
		this.companyID = companyID;
	}

	public String getShareRatio() {
		return shareRatio;
	}

	public void setShareRatio(String shareRatio) {
		this.shareRatio = shareRatio;
	}
}
