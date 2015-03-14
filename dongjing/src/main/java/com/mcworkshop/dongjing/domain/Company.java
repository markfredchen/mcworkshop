// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.domain;

import com.mcworkshop.common.domain.DomainObject;
import com.mcworkshop.common.sysenum.SystemEnumeration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author $Author$
 */
public class Company extends DomainObject {

    private static final long serialVersionUID = 1L;

    private Long companyID;
    /**
     * Basic Information
     */
    private String name;
    private String companyNo;
    private String commercialNo;
    private String organizationCode;
    private String docNo;
    private String registerAddress;
    private String operateAddress;
    private Double registerAsset;
    private Integer branchNumber;
    private String branches;
    private CommercialStatus commercialStatus;
    private TaxStatus taxStatus;
    private EconomyEntity economyEntity;
    private EconomyNature economyNature;
    private String industryType;
    private AffiliateBlock region;
    private TaxCollectMethod collectMethod;
    private AffiliateRegion affiliateRegion;
    private TaxNature taxNature;
    private TaxType taxType;
    private Date establishDate;
    private Date materialDate;
    private Date handleDate;
    private Date issueDate;
    private Date moveInDate;
    private Date transferDate;
    private Date taxCancellation;
    private Date commercialCancellation;
    private String taxCollector;
    private String merchat;
    private String affliateMerchat;
    private Boolean isEDeclaration;
    private Boolean isReceiptBought;
    private Boolean isNormalTaxer;

    /**
     * Detail Information
     */
    private String certificationNo;
    private String companyIdentifier;
    private Integer operateLimit;
    private Date operationStartYear;
    private Date operationEndYear;
    private Date validStartYear;
    private Date validEndYear;
    private String infoCardNo;
    private String operationMethod;
    private Date normalTaxerAuthDate;
    private String operationScope;
    private String remarkables;
    private String unifiedCode;
    private String taxMngCode;
    private String taxRegNo;
    private String purchasedBookNo;
    private String bank;
    private String bankAccount;
    private String taxAccount;

    private CompanyContact ownerContact = new CompanyContact();
    private CompanyContact otherContact = new CompanyContact();
    private CompanyContact shareholderContact = new CompanyContact();
    private String shareRatio;
    private TaxOrg taxOrg;

    // 中央技术改造（是，否，申报成功时间）
    private Boolean isA;
    private Date aDate;
    // 区级技术中心企业（是，否，申报成功时间）
    private Boolean isB;
    private Date bDate;
    // 小巨人企业（是，否，申报成功时间）
    private Boolean isC;
    private Date cDate;
    // 区产学研创新项目专项扶持资金申请企业（是，否，申报成功时间）
    private Boolean isD;
    private Date dDate;
    // 区节能技术改造申报（是，否，申报成功时间）
    private Boolean isE;
    private Date eDate;
    // 上海市科技型中小企业技术创新资金申报（是，否，申报成功时间）
    private Boolean isF;
    private Date fDate;
    // 著名商标申报（是，否，申报成功时间）
    private Boolean isG;
    private Date gDate;
    // 上海市知名品牌创建示范区（是，否，申报成功时间）
    private Boolean isH;
    private Date hDate;
    // 两化融合专项资金申报（是，否，申报成功时间）
    private Boolean isI;
    private Date iDate;
    // 专精特新申报（是，否，申报成功时间）
    private Boolean isJ;
    private Date jDate;
    // 区长质量奖申报企业（是，否，申报成功时间）
    private Boolean isK;
    private Date kDate;
    // 工业技改项目的通知（是，否，申报成功时间）
    private Boolean isL;
    private Date lDate;
    // 名牌申报企业（是，否，申报成功时间）
    private Boolean isM;
    private Date mDate;
    // 高新技术企业（是，否，申报成功时间）
    private Boolean isN;
    private Date nDate;

    // 国外专利 （是，否，件数）
    private Boolean isO;
    private Integer numberOfO;
    // 一般作品著作权（是，否，件数）
    private Boolean isP;
    private Integer numberOfP;
    // 软件著作权（是，否，件数）
    private Boolean isQ;
    private Integer numberOfQ;

    // 发明专利（申请，授权）
    private Boolean isR;
    private Date rDate;
    private Boolean isS;
    private Date sDate;
    // 实用新型专利（申请，授权）
    private Boolean isT;
    private Date tDate;
    private Boolean isU;
    private Date uDate;
    // 外观专利（申请，授权）
    private Boolean isV;
    private Date vDate;
    private Boolean isW;
    private Date wDate;

    private List<CompanyMember> members = new ArrayList<CompanyMember>();
    private List<PartyMember> partyMembers = new ArrayList<PartyMember>();
    private String partyName;
    private String partyLeader;

    private Double area;
    private String ownership;
    private Integer sleepingRoomNumbers;
    private Double usageArea;
    private Double rentArea;
    private Double spareArea;
    private Double companyArea;
    private Integer localEmployeeNumber;
    private Integer employeeNumber;

    private LandUsage landUsage;

    private Boolean isRent;
    private Double factoryArea;
    private Double factoryActualArea;
    private Double factoryUsageArea;
    private Double factoryRentArea;
    private Double factoryControlArea;

    private Date passDate;
    private String noOfDeveloper;
    private String devBudget;
    private List<AnnualOutput> outputs = new ArrayList<AnnualOutput>();
    private List<NatureShareholder> natureShareholders = new ArrayList<NatureShareholder>();
    private List<CompanyShareholder> companyShareholders = new ArrayList<CompanyShareholder>();
    private List<RentStatus> rentees = new ArrayList<RentStatus>();
    private List<RentStatus> rentors = new ArrayList<RentStatus>();

    private Currency currency;

    public Long getCompanyID() {
        return companyID;
    }

    public void setCompanyID(Long companyID) {
        this.companyID = companyID;
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public String getCommercialNo() {
        return commercialNo;
    }

    public void setCommercialNo(String commercialNo) {
        this.commercialNo = commercialNo;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocNo() {
        return docNo;
    }

    public void setDocNo(String docNo) {
        this.docNo = docNo;
    }

    public String getRegisterAddress() {
        return registerAddress;
    }

    public void setRegisterAddress(String registerAddress) {
        this.registerAddress = registerAddress;
    }

    public String getOperateAddress() {
        return operateAddress;
    }

    public void setOperateAddress(String operateAddress) {
        this.operateAddress = operateAddress;
    }

    public Double getRegisterAsset() {
        return registerAsset;
    }

    public void setRegisterAsset(Double registerAsset) {
        this.registerAsset = registerAsset;
    }

    public Integer getBranchNumber() {
        return branchNumber;
    }

    public void setBranchNumber(Integer branchNumber) {
        this.branchNumber = branchNumber;
    }

    public CommercialStatus getCommercialStatus() {
        return commercialStatus;
    }

    public void setCommericalStatus(CommercialStatus commercialStatus) {
        this.commercialStatus = commercialStatus;
    }

    public Long getCommercialStatusID() {
        return SystemEnumeration.getInstance()
                .getIdByKey(this.commercialStatus);
    }

    public void setCommercialStatusID(Long commercialStatus) {
        this.commercialStatus = SystemEnumeration.getInstance().getKeyById(
                CommercialStatus.class, commercialStatus);
    }

    public TaxStatus getTaxStatus() {
        return taxStatus;
    }

    public void setTaxStatus(TaxStatus taxStatus) {
        this.taxStatus = taxStatus;
    }

    public Long getTaxStatusID() {
        return SystemEnumeration.getInstance().getIdByKey(this.taxStatus);
    }

    public void setTaxStatusID(Long taxStatusID) {
        this.taxStatus = SystemEnumeration.getInstance().getKeyById(
                TaxStatus.class, taxStatusID);
    }

    public EconomyEntity getEconomyEntity() {
        return economyEntity;
    }

    public void setEconomyEntity(EconomyEntity economyEntity) {
        this.economyEntity = economyEntity;
    }

    public void setEconomyEntityID(Long economyEntityID) {
        this.economyEntity = SystemEnumeration.getInstance().getKeyById(
                EconomyEntity.class, economyEntityID);
    }

    public Long getEconomyEntityID() {
        return SystemEnumeration.getInstance().getIdByKey(this.economyEntity);
    }

    public EconomyNature getEconomyNature() {
        return economyNature;
    }

    public void setEconomyNature(EconomyNature economyNature) {
        this.economyNature = economyNature;
    }

    public Long getEconomyNatureID() {
        return SystemEnumeration.getInstance().getIdByKey(this.economyNature);
    }

    public void setEconomyNatureID(Long economyNatureID) {
        this.economyNature = SystemEnumeration.getInstance().getKeyById(
                EconomyNature.class, economyNatureID);
    }

    public String getIndustryType() {
        return industryType;
    }

    public void setIndustryType(String industryType) {
        this.industryType = industryType;
    }

    public AffiliateBlock getRegion() {
        return region;
    }

    public void setRegion(AffiliateBlock region) {
        this.region = region;
    }

    public Long getRegionID() {
        return SystemEnumeration.getInstance().getIdByKey(region);
    }

    public void setRegionID(Long regionID) {
        this.region = SystemEnumeration.getInstance().getKeyById(
                AffiliateBlock.class, regionID);
    }

    public TaxCollectMethod getCollectMethod() {
        return collectMethod;
    }

    public void setCollectMethod(TaxCollectMethod collectMethod) {
        this.collectMethod = collectMethod;
    }

    public Long getCollectMethodID() {
        return SystemEnumeration.getInstance().getIdByKey(this.collectMethod);
    }

    public void setCollectMethodID(Long collectMethodID) {
        this.collectMethod = SystemEnumeration.getInstance().getKeyById(
                TaxCollectMethod.class, collectMethodID);
    }

    public AffiliateRegion getAffiliateRegion() {
        return affiliateRegion;
    }

    public void setAffiliateRegion(AffiliateRegion affiliateRegion) {
        this.affiliateRegion = affiliateRegion;
    }

    public void setAffiliateRegionID(Long affiliateRegionID) {
        this.affiliateRegion = SystemEnumeration.getInstance().getKeyById(
                AffiliateRegion.class, affiliateRegionID);
    }

    public Long getAffiliateRegionID() {
        return SystemEnumeration.getInstance().getIdByKey(this.affiliateRegion);
    }

    public TaxNature getTaxNature() {
        return taxNature;
    }

    public void setTaxNature(TaxNature taxNature) {
        this.taxNature = taxNature;
    }

    public Long getTaxNatureID() {
        return SystemEnumeration.getInstance().getIdByKey(this.taxNature);
    }

    public void setTaxNatureID(Long taxNatureID) {
        this.taxNature = SystemEnumeration.getInstance().getKeyById(
                TaxNature.class, taxNatureID);
    }

    public TaxType getTaxType() {
        return taxType;
    }

    public void setTaxType(TaxType taxType) {
        this.taxType = taxType;
    }

    public Long getTaxTypeID() {
        return SystemEnumeration.getInstance().getIdByKey(this.taxType);
    }

    public void setTaxTypeID(Long taxTypeID) {
        this.taxType = SystemEnumeration.getInstance().getKeyById(
                TaxType.class, taxTypeID);
    }

    public Date getEstablishDate() {
        return establishDate;
    }

    public void setEstablishDate(Date establishDate) {
        this.establishDate = establishDate;
    }

    public Date getMaterialDate() {
        return materialDate;
    }

    public void setMaterialDate(Date materialDate) {
        this.materialDate = materialDate;
    }

    public Date getHandleDate() {
        return handleDate;
    }

    public void setHandleDate(Date handleDate) {
        this.handleDate = handleDate;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getMoveInDate() {
        return moveInDate;
    }

    public void setMoveInDate(Date moveInDate) {
        this.moveInDate = moveInDate;
    }

    public Date getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(Date transferDate) {
        this.transferDate = transferDate;
    }

    public Date getTaxCancellation() {
        return taxCancellation;
    }

    public void setTaxCancellation(Date taxCancellation) {
        this.taxCancellation = taxCancellation;
    }

    public Date getCommercialCancellation() {
        return commercialCancellation;
    }

    public void setCommercialCancellation(Date commercialCancellation) {
        this.commercialCancellation = commercialCancellation;
    }

    public String getTaxCollector() {
        return taxCollector;
    }

    public void setTaxCollector(String taxCollector) {
        this.taxCollector = taxCollector;
    }

    public String getMerchat() {
        return merchat;
    }

    public void setMerchat(String merchat) {
        this.merchat = merchat;
    }

    public String getAffliateMerchat() {
        return affliateMerchat;
    }

    public void setAffliateMerchat(String affliateMerchat) {
        this.affliateMerchat = affliateMerchat;
    }

    public Boolean isEDeclaration() {
        return isEDeclaration;
    }

    public void seteDeclaration(Boolean isEDeclaration) {
        this.isEDeclaration = isEDeclaration;
    }

    public Boolean isReceiptBought() {
        return isReceiptBought;
    }

    public void setReceiptBought(Boolean isReceiptBought) {
        this.isReceiptBought = isReceiptBought;
    }

    public Boolean isNormalTaxer() {
        return isNormalTaxer;
    }

    public void setNormalTaxer(Boolean isNormalTaxer) {
        this.isNormalTaxer = isNormalTaxer;
    }

    public String getCertificationNo() {
        return certificationNo;
    }

    public void setCertificationNo(String certificationNo) {
        this.certificationNo = certificationNo;
    }

    public String getCompanyIdentifier() {
        return companyIdentifier;
    }

    public void setCompanyIdentifier(String companyIdentifier) {
        this.companyIdentifier = companyIdentifier;
    }

    public Integer getOperateLimit() {
        return operateLimit;
    }

    public void setOperateLimit(Integer operateLimit) {
        this.operateLimit = operateLimit;
    }

    public Date getOperationStartYear() {
        return operationStartYear;
    }

    public void setOperationStartYear(Date operationStartYear) {
        this.operationStartYear = operationStartYear;
    }

    public Date getOperationEndYear() {
        return operationEndYear;
    }

    public void setOperationEndYear(Date operationEndYear) {
        this.operationEndYear = operationEndYear;
    }

    public Date getValidStartYear() {
        return validStartYear;
    }

    public void setValidStartYear(Date validStartYear) {
        this.validStartYear = validStartYear;
    }

    public Date getValidEndYear() {
        return validEndYear;
    }

    public void setValidEndYear(Date validEndYear) {
        this.validEndYear = validEndYear;
    }

    public String getInfoCardNo() {
        return infoCardNo;
    }

    public void setInfoCardNo(String infoCardNo) {
        this.infoCardNo = infoCardNo;
    }

    public String getOperationMethod() {
        return operationMethod;
    }

    public void setOperationMethod(String operationMethod) {
        this.operationMethod = operationMethod;
    }

    public Date getNormalTaxerAuthDate() {
        return normalTaxerAuthDate;
    }

    public void setNormalTaxerAuthDate(Date normalTaxerAuthDate) {
        this.normalTaxerAuthDate = normalTaxerAuthDate;
    }

    public String getOperationScope() {
        return operationScope;
    }

    public void setOperationScope(String operationScope) {
        this.operationScope = operationScope;
    }

    public String getRemarkables() {
        return remarkables;
    }

    public void setRemarkables(String remarkables) {
        this.remarkables = remarkables;
    }

    public String getUnifiedCode() {
        return unifiedCode;
    }

    public void setUnifiedCode(String unifiedCode) {
        this.unifiedCode = unifiedCode;
    }

    public String getTaxMngCode() {
        return taxMngCode;
    }

    public void setTaxMngCode(String taxMngCode) {
        this.taxMngCode = taxMngCode;
    }

    public String getTaxRegNo() {
        return taxRegNo;
    }

    public void setTaxRegNo(String taxRegNo) {
        this.taxRegNo = taxRegNo;
    }

    public String getPurchasedBookNo() {
        return purchasedBookNo;
    }

    public void setPurchasedBookNo(String purchasedBookNo) {
        this.purchasedBookNo = purchasedBookNo;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getTaxAccount() {
        return taxAccount;
    }

    public void setTaxAccount(String taxAccount) {
        this.taxAccount = taxAccount;
    }

    public CompanyContact getOwnerContact() {
        return ownerContact;
    }

    public void setOwnerContact(CompanyContact ownerContact) {
        this.ownerContact = ownerContact;
    }

    public CompanyContact getOtherContact() {
        return otherContact;
    }

    public void setOtherContact(CompanyContact otherContact) {
        this.otherContact = otherContact;
    }

    public CompanyContact getShareholderContact() {
        return shareholderContact;
    }

    public void setShareholderContact(CompanyContact shareholderContact) {
        this.shareholderContact = shareholderContact;
    }

    public String getShareRatio() {
        return shareRatio;
    }

    public void setShareRatio(String shareRatio) {
        this.shareRatio = shareRatio;
    }

    public Long getTaxOrgID() {
        return SystemEnumeration.getInstance().getIdByKey(taxOrg);
    }

    public void setTaxOrgID(Long taxOrgID) {
        this.taxOrg = SystemEnumeration.getInstance().getKeyById(TaxOrg.class,
                taxOrgID);
    }

    public TaxOrg getTaxOrg() {
        return taxOrg;
    }

    public void setTaxOrg(TaxOrg taxOrg) {
        this.taxOrg = taxOrg;
    }

    public String getBranches() {
        return branches;
    }

    public void setBranches(String branches) {
        this.branches = branches;
    }

    public Boolean getIsA() {
        return isA;
    }

    public void setIsA(Boolean isA) {
        this.isA = isA;
    }

    public Date getaDate() {
        return aDate;
    }

    public void setaDate(Date aDate) {
        this.aDate = aDate;
    }

    public Boolean getIsB() {
        return isB;
    }

    public void setIsB(Boolean isB) {
        this.isB = isB;
    }

    public Date getbDate() {
        return bDate;
    }

    public void setbDate(Date bDate) {
        this.bDate = bDate;
    }

    public Boolean getIsC() {
        return isC;
    }

    public void setIsC(Boolean isC) {
        this.isC = isC;
    }

    public Date getcDate() {
        return cDate;
    }

    public void setcDate(Date cDate) {
        this.cDate = cDate;
    }

    public Boolean getIsD() {
        return isD;
    }

    public void setIsD(Boolean isD) {
        this.isD = isD;
    }

    public Date getdDate() {
        return dDate;
    }

    public void setdDate(Date dDate) {
        this.dDate = dDate;
    }

    public Boolean getIsE() {
        return isE;
    }

    public void setIsE(Boolean isE) {
        this.isE = isE;
    }

    public Date geteDate() {
        return eDate;
    }

    public void seteDate(Date eDate) {
        this.eDate = eDate;
    }

    public Boolean getIsF() {
        return isF;
    }

    public void setIsF(Boolean isF) {
        this.isF = isF;
    }

    public Date getfDate() {
        return fDate;
    }

    public void setfDate(Date fDate) {
        this.fDate = fDate;
    }

    public Boolean getIsG() {
        return isG;
    }

    public void setIsG(Boolean isG) {
        this.isG = isG;
    }

    public Date getgDate() {
        return gDate;
    }

    public void setgDate(Date gDate) {
        this.gDate = gDate;
    }

    public Boolean getIsH() {
        return isH;
    }

    public void setIsH(Boolean isH) {
        this.isH = isH;
    }

    public Date gethDate() {
        return hDate;
    }

    public void sethDate(Date hDate) {
        this.hDate = hDate;
    }

    public Boolean getIsI() {
        return isI;
    }

    public void setIsI(Boolean isI) {
        this.isI = isI;
    }

    public Date getiDate() {
        return iDate;
    }

    public void setiDate(Date iDate) {
        this.iDate = iDate;
    }

    public Boolean getIsJ() {
        return isJ;
    }

    public void setIsJ(Boolean isJ) {
        this.isJ = isJ;
    }

    public Date getjDate() {
        return jDate;
    }

    public void setjDate(Date jDate) {
        this.jDate = jDate;
    }

    public Boolean getIsK() {
        return isK;
    }

    public void setIsK(Boolean isK) {
        this.isK = isK;
    }

    public Date getkDate() {
        return kDate;
    }

    public void setkDate(Date kDate) {
        this.kDate = kDate;
    }

    public Boolean getIsL() {
        return isL;
    }

    public void setIsL(Boolean isL) {
        this.isL = isL;
    }

    public Date getlDate() {
        return lDate;
    }

    public void setlDate(Date lDate) {
        this.lDate = lDate;
    }

    public Boolean getIsM() {
        return isM;
    }

    public void setIsM(Boolean isM) {
        this.isM = isM;
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    public Boolean getIsN() {
        return isN;
    }

    public void setIsN(Boolean isN) {
        this.isN = isN;
    }

    public Date getnDate() {
        return nDate;
    }

    public void setnDate(Date nDate) {
        this.nDate = nDate;
    }

    public Boolean getIsO() {
        return isO;
    }

    public void setIsO(Boolean isO) {
        this.isO = isO;
    }

    public Integer getNumberOfO() {
        return numberOfO;
    }

    public void setNumberOfO(Integer numberOfO) {
        this.numberOfO = numberOfO;
    }

    public Boolean getIsP() {
        return isP;
    }

    public void setIsP(Boolean isP) {
        this.isP = isP;
    }

    public Integer getNumberOfP() {
        return numberOfP;
    }

    public void setNumberOfP(Integer numberOfP) {
        this.numberOfP = numberOfP;
    }

    public Boolean getIsQ() {
        return isQ;
    }

    public void setIsQ(Boolean isQ) {
        this.isQ = isQ;
    }

    public Integer getNumberOfQ() {
        return numberOfQ;
    }

    public void setNumberOfQ(Integer numberOfQ) {
        this.numberOfQ = numberOfQ;
    }

    public Boolean getIsR() {
        return isR;
    }

    public void setIsR(Boolean isR) {
        this.isR = isR;
    }

    public Date getrDate() {
        return rDate;
    }

    public void setrDate(Date rDate) {
        this.rDate = rDate;
    }

    public Boolean getIsS() {
        return isS;
    }

    public void setIsS(Boolean isS) {
        this.isS = isS;
    }

    public Date getsDate() {
        return sDate;
    }

    public void setsDate(Date sDate) {
        this.sDate = sDate;
    }

    public Boolean getIsT() {
        return isT;
    }

    public void setIsT(Boolean isT) {
        this.isT = isT;
    }

    public Date gettDate() {
        return tDate;
    }

    public void settDate(Date tDate) {
        this.tDate = tDate;
    }

    public Boolean getIsU() {
        return isU;
    }

    public void setIsU(Boolean isU) {
        this.isU = isU;
    }

    public Date getuDate() {
        return uDate;
    }

    public void setuDate(Date uDate) {
        this.uDate = uDate;
    }

    public Boolean getIsV() {
        return isV;
    }

    public void setIsV(Boolean isV) {
        this.isV = isV;
    }

    public Date getvDate() {
        return vDate;
    }

    public void setvDate(Date vDate) {
        this.vDate = vDate;
    }

    public Boolean getIsW() {
        return isW;
    }

    public void setIsW(Boolean isW) {
        this.isW = isW;
    }

    public Date getwDate() {
        return wDate;
    }

    public void setwDate(Date wDate) {
        this.wDate = wDate;
    }

    public List<CompanyMember> getMembers() {
        return members;
    }

    public void setMembers(List<CompanyMember> members) {
        this.members = members;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public String getPartyLeader() {
        return partyLeader;
    }

    public void setPartyLeader(String partyLeader) {
        this.partyLeader = partyLeader;
    }

    public List<PartyMember> getPartyMembers() {
        return partyMembers;
    }

    public void setPartyMembers(List<PartyMember> partyMembers) {
        this.partyMembers = partyMembers;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public String getOwnership() {
        return ownership;
    }

    public void setOwnership(String ownership) {
        this.ownership = ownership;
    }

    public Integer getSleepingRoomNumbers() {
        return sleepingRoomNumbers;
    }

    public void setSleepingRoomNumbers(Integer sleepingRoomNumbers) {
        this.sleepingRoomNumbers = sleepingRoomNumbers;
    }

    public Double getUsageArea() {
        return usageArea;
    }

    public void setUsageArea(Double usageArea) {
        this.usageArea = usageArea;
    }

    public Double getRentArea() {
        return rentArea;
    }

    public void setRentArea(Double rentArea) {
        this.rentArea = rentArea;
    }

    public Double getSpareArea() {
        return spareArea;
    }

    public void setSpareArea(Double spareArea) {
        this.spareArea = spareArea;
    }

    public Double getCompanyArea() {
        return companyArea;
    }

    public void setCompanyArea(Double companyArea) {
        this.companyArea = companyArea;
    }

    public Integer getLocalEmployeeNumber() {
        return localEmployeeNumber;
    }

    public void setLocalEmployeeNumber(Integer localEmployeeNumber) {
        this.localEmployeeNumber = localEmployeeNumber;
    }

    public Integer getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(Integer employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public LandUsage getLandUsage() {
        return landUsage;
    }

    public void setLandUsage(LandUsage landUsage) {
        this.landUsage = landUsage;
    }

    public Long getLandUsageID() {
        return SystemEnumeration.getInstance().getIdByKey(this.landUsage);
    }

    public void setLandUsageID(Long landUsageID) {
        this.landUsage = SystemEnumeration.getInstance().getKeyById(
                LandUsage.class, landUsageID);
    }

    public Boolean getIsRent() {
        return isRent;
    }

    public void setIsRent(Boolean isRent) {
        this.isRent = isRent;
    }

    public Double getFactoryArea() {
        return factoryArea;
    }

    public void setFactoryArea(Double factoryArea) {
        this.factoryArea = factoryArea;
    }

    public Double getFactoryActualArea() {
        return factoryActualArea;
    }

    public void setFactoryActualArea(Double factoryActualArea) {
        this.factoryActualArea = factoryActualArea;
    }

    public Double getFactoryUsageArea() {
        return factoryUsageArea;
    }

    public void setFactoryUsageArea(Double factoryUsageArea) {
        this.factoryUsageArea = factoryUsageArea;
    }

    public Double getFactoryRentArea() {
        return factoryRentArea;
    }

    public void setFactoryRentArea(Double factoryRentArea) {
        this.factoryRentArea = factoryRentArea;
    }

    public Double getFactoryControlArea() {
        return factoryControlArea;
    }

    public void setFactoryControlArea(Double factoryControlArea) {
        this.factoryControlArea = factoryControlArea;
    }

    public Date getPassDate() {
        return passDate;
    }

    public void setPassDate(Date passDate) {
        this.passDate = passDate;
    }

    public String getNoOfDeveloper() {
        return noOfDeveloper;
    }

    public void setNoOfDeveloper(String noOfDeveloper) {
        this.noOfDeveloper = noOfDeveloper;
    }

    public String getDevBudget() {
        return devBudget;
    }

    public void setDevBudget(String devBudget) {
        this.devBudget = devBudget;
    }

    public List<AnnualOutput> getOutputs() {
        return outputs;
    }

    public void setOutputs(List<AnnualOutput> outputs) {
        this.outputs = outputs;
    }

    public List<NatureShareholder> getNatureShareholders() {
        return natureShareholders;
    }

    public void setNatureShareholders(List<NatureShareholder> natureShareholders) {
        this.natureShareholders = natureShareholders;
    }

    public List<CompanyShareholder> getCompanyShareholders() {
        return companyShareholders;
    }

    public void setCompanyShareholders(List<CompanyShareholder> companyShareholders) {
        this.companyShareholders = companyShareholders;
    }

    public List<RentStatus> getRentees() {
        return rentees;
    }

    public void setRentees(List<RentStatus> rentees) {
        this.rentees = rentees;
    }

    public List<RentStatus> getRentors() {
        return rentors;
    }

    public void setRentors(List<RentStatus> rentors) {
        this.rentors = rentors;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Long getCurrencyID() {
        return SystemEnumeration.getInstance().getIdByKey(this.currency);
    }

    public void setCurrencyID(Long currencyID) {
        this.currency = SystemEnumeration.getInstance().getKeyById(Currency.class, currencyID);
    }

    public Double getActualArea() {
        Double d = new Double(0D);
        if (this.factoryUsageArea != null) {
            d += this.factoryUsageArea;
        }
        if (this.factoryControlArea != null) {
            d += this.factoryControlArea;
        }
        for (RentStatus rentee : this.getRentees()) {
            if (rentee.getArea() != null) {
                d += rentee.getArea();
            }
        }
        return d;
    }

}
