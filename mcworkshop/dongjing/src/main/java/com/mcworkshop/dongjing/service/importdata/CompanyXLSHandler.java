// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.service.importdata;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.wicket.util.string.Strings;

import com.mcworkshop.dongjing.domain.AffiliateBlock;
import com.mcworkshop.dongjing.domain.AffiliateRegion;
import com.mcworkshop.dongjing.domain.CommercialStatus;
import com.mcworkshop.dongjing.domain.Company;
import com.mcworkshop.dongjing.domain.EconomyEntity;
import com.mcworkshop.dongjing.domain.EconomyNature;
import com.mcworkshop.dongjing.domain.TaxOrg;
import com.mcworkshop.dongjing.service.DJService;

/**
 * @author $Author$
 * 
 */
public class CompanyXLSHandler extends BaseXLSHandler<Company> {

	private DJService service;

	private List<String> failedCompanies = new ArrayList<String>();

	private static final int COMPANY_NO = 0;
	private static final int COMPANY_NAME = 1;
	private static final int REGISTER_ASSETS = 2;
	private static final int COMMERCIAL_NO = 3;
	private static final int ORG_CODE = 4;
	private static final int OWNER_FULLNAME = 5;
	private static final int OWNER_IDCARD = 6;
	private static final int OWNER_ADDRESS = 7;
	private static final int OWNER_PHONE = 8;
	private static final int OWNER_CELLPHONE = 9;
	private static final int SHAREHOLDER_FULLNAME = 10;
	private static final int SHAREHOLDER_IDCARD = 11;
	private static final int SHAREHOLDER_ADDRESS = 12;
	private static final int SHARE_RATIO = 13;
	private static final int ESTABLISH_DATE = 14;
	private static final int TAX_REG_NO = 15;
	private static final int TAX_MNG_NO = 16;
	private static final int BRANCH_ORG = 17;
	private static final int OPERATE_ADDRESS = 18;
	private static final int SHAREHOLDER_PHONE = 19;
	private static final int POSTALCODE = 20;
	private static final int OPERATE_SCOPE = 21;
	private static final int ECONOMY_ENTITY = 22;
	private static final int INDUSTRY_TYPE = 23;
	private static final int AFFILIATION_REGION = 24;
	private static final int ECONOMY_NATURE = 25;
	private static final int TAX_ORG = 26;
	private static final int COMMERCIAL_STATUS = 27;
	private static final int AFFILIATE_BLOCK = 28;

	private static String DATE_PATTERN = "mm/dd/yy";
	private SimpleDateFormat df = new SimpleDateFormat(DATE_PATTERN);

	public CompanyXLSHandler(InputStream input, DJService service)
			throws Exception {
		this.fs = new POIFSFileSystem(input);
		this.service = service;
		this.data = new Company();
	}

	@Override
	protected void onCell(int thisColumn, String thisStr) {
		switch (thisColumn) {
		case COMPANY_NO:
			data.setCompanyNo(thisStr);
			break;
		case COMPANY_NAME:
			data.setName(thisStr.trim());
			break;
		case REGISTER_ASSETS:
			if (!Strings.isEmpty(thisStr)) {
				try {
					data.setRegisterAsset(Double.valueOf(thisStr));
				} catch (Throwable e) {

				}
			}
			break;
		case COMMERCIAL_NO:
			data.setCommercialNo(thisStr);
			break;
		case ORG_CODE:
			data.setOrganizationCode(thisStr);
			break;
		case OWNER_FULLNAME:
			data.getOwnerContact().setFullName(thisStr);
			break;
		case OWNER_IDCARD:
			data.getOwnerContact().setIdCard(thisStr);
			break;
		case OWNER_ADDRESS:
			data.getOwnerContact().setAddress(thisStr);
			break;
		case OWNER_PHONE:
			data.getOwnerContact().setPhone(thisStr);
			break;
		case OWNER_CELLPHONE:
			data.getOwnerContact().setCellPhone(thisStr);
			break;
		case SHAREHOLDER_FULLNAME:
			data.getShareholderContact().setFullName(thisStr);
			break;
		case SHAREHOLDER_IDCARD:
			data.getShareholderContact().setIdCard(thisStr);
			break;
		case SHAREHOLDER_ADDRESS:
			data.getShareholderContact().setAddress(thisStr);
			break;
		case SHARE_RATIO:
			data.setShareRatio(thisStr);
			break;
		case ESTABLISH_DATE:
			try {
				data.setEstablishDate(df.parse(thisStr));
			} catch (ParseException e) {
			}
			break;
		case TAX_REG_NO:
			data.setTaxRegNo(thisStr);
			break;
		case TAX_MNG_NO:
			data.setTaxMngCode(thisStr);
			break;
		case BRANCH_ORG:
			data.setBranches(thisStr);
			break;
		case OPERATE_ADDRESS:
			data.setOperateAddress(thisStr);
			break;
		case SHAREHOLDER_PHONE:
			data.getShareholderContact().setPhone(thisStr);
			break;
		case POSTALCODE:
			data.getShareholderContact().setPostalCode(thisStr);
			break;
		case OPERATE_SCOPE:
			data.setOperationScope(thisStr);
			break;
		case ECONOMY_ENTITY:
			if (thisStr.equals("ST")) {
				data.setEconomyEntity(EconomyEntity.INDUSTRY);
			} else if (thisStr.equals("SM")) {
				data.setEconomyEntity(EconomyEntity.COMMERCIAL);
			} else if (thisStr.equals("QT")) {
				data.setEconomyEntity(EconomyEntity.SERVICE);
			} else if (thisStr.equals("JZ")) {
				data.setEconomyEntity(EconomyEntity.CONSTRUCTION);
			} else if (thisStr.equals("HD")) {
				data.setEconomyEntity(EconomyEntity.HOUSEHOLDING);
			}
			break;
		case INDUSTRY_TYPE:
			data.setIndustryType(thisStr);
			break;
		case AFFILIATION_REGION:
			if (thisStr.toUpperCase().equals("ZD")) {
				data.setAffiliateRegion(AffiliateRegion.LOCAL);
			} else {
				data.setAffiliateRegion(AffiliateRegion.REGISTRATION);
			}
			break;
		case ECONOMY_NATURE:
			if (thisStr.toUpperCase().equals("NZ")) {
				data.setEconomyNature(EconomyNature.PRIVATE);
			} else if (thisStr.toUpperCase().equals("WZ")) {
				data.setEconomyNature(EconomyNature.FOREIGN);
			} else if (thisStr.toUpperCase().equals("JT")) {
				data.setEconomyNature(EconomyNature.GROUP);
			}
		case TAX_ORG:
			if (thisStr.equals("6")) {
				data.setTaxOrg(TaxOrg.TAX_ORG_6);
			} else if (thisStr.equals("9")) {
				data.setTaxOrg(TaxOrg.TAX_ORG_9);
			} else if (thisStr.equals("12")) {
				data.setTaxOrg(TaxOrg.TAX_ORG_12);
			}
		case COMMERCIAL_STATUS:
			if (thisStr.toUpperCase().equals("DXWZX")) {
				data.setCommericalStatus(CommercialStatus.REVOKED_NOT_SIGN_OFF);
			} else if (thisStr.toUpperCase().equals("DXYZX")) {
				data.setCommericalStatus(CommercialStatus.REVOKED_SIGN_OFF);
			} else if (thisStr.toUpperCase().equals("QY")) {
				data.setCommericalStatus(CommercialStatus.TRANSFERRED);
			} else if (thisStr.toUpperCase().equals("QL")) {
				data.setCommericalStatus(CommercialStatus.CONFIRMED);
			} else if (thisStr.toUpperCase().equals("ZX")) {
				data.setCommericalStatus(CommercialStatus.SIGN_OFF);
			}
		case AFFILIATE_BLOCK:
			data.setRegion(AffiliateBlock.fromValue(thisStr));
		default:
			break;
		}
	}

	@Override
	protected void importData(Company data) {
		try {
			this.service.importCompany(data);
		} catch (Throwable e) {
			failedCompanies.add(data.getName());
		}
	}

	@Override
	protected void resetData() {
		this.data = new Company();
	}

}
