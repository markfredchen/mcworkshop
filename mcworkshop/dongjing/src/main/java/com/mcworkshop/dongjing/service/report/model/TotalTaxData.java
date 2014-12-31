// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.service.report.model;

import com.mcworkshop.common.domain.DomainObject;

/**
 * @author $Author$
 * 
 */
public class TotalTaxData extends DomainObject {

	private static final long serialVersionUID = 1L;

	private int vat;
	private int operationTax;
	private int incomingTax;
	private int personalIncomingTax;
	private int constructTax;
	private int otherTax;

	public int getVat() {
		return vat;
	}

	public int getOperationTax() {
		return operationTax;
	}

	public int getIncomingTax() {
		return incomingTax;
	}

	public int getPersonalIncomingTax() {
		return personalIncomingTax;
	}

	public int getConstructTax() {
		return constructTax;
	}

	public int getOtherTax() {
		return otherTax;
	}

	public void setVat(int vat) {
		this.vat = vat;
	}

	public void setOperationTax(int operationTax) {
		this.operationTax = operationTax;
	}

	public void setIncomingTax(int incomingTax) {
		this.incomingTax = incomingTax;
	}

	public void setPersonalIncomingTax(int personalIncomingTax) {
		this.personalIncomingTax = personalIncomingTax;
	}

	public void setConstructTax(int constructTax) {
		this.constructTax = constructTax;
	}

	public void setOtherTax(int otherTax) {
		this.otherTax = otherTax;
	}

}
