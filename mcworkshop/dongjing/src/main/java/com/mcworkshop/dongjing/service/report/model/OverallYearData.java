// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.service.report.model;

/**
 * @author $Author$
 * 
 */
public class OverallYearData extends OverallMonthData {

	private static final long serialVersionUID = 1L;

	private double totalSale;
	private double totalVAT;
	private double totalOperateTax;
	private double totalIncomingTax;
	private double totalPersonalIncomingTax;
	private double totalConstructTax;
	private double totalOtherTax;

	public double getTotalSale() {
		return totalSale;
	}

	public double getTotalVAT() {
		return totalVAT;
	}

	public double getTotalOperateTax() {
		return totalOperateTax;
	}

	public double getTotalIncomingTax() {
		return totalIncomingTax;
	}

	public double getTotalPersonalIncomingTax() {
		return totalPersonalIncomingTax;
	}

	public double getTotalConstructTax() {
		return totalConstructTax;
	}

	public double getTotalOtherTax() {
		return totalOtherTax;
	}

	public void setTotalSale(double totalSale) {
		this.totalSale = totalSale;
	}

	public void setTotalVAT(double totalVAT) {
		this.totalVAT = totalVAT;
	}

	public void setTotalOperateTax(double totalOperateTax) {
		this.totalOperateTax = totalOperateTax;
	}

	public void setTotalIncomingTax(double totalIncomingTax) {
		this.totalIncomingTax = totalIncomingTax;
	}

	public void setTotalPersonalIncomingTax(double totalPersonalIncomingTax) {
		this.totalPersonalIncomingTax = totalPersonalIncomingTax;
	}

	public void setTotalConstructTax(double totalConstructTax) {
		this.totalConstructTax = totalConstructTax;
	}

	public void setTotalOtherTax(double totalOtherTax) {
		this.totalOtherTax = totalOtherTax;
	}

}
