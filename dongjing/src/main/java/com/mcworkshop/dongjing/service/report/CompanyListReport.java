package com.mcworkshop.dongjing.service.report;

import com.mcworkshop.common.configuration.Config;
import com.mcworkshop.common.sysenum.SysEnum;
import com.mcworkshop.common.web.report.BaseReport;
import com.mcworkshop.common.web.report.CellStyleWrapper;
import com.mcworkshop.common.web.util.WicketMessageUtil;
import com.mcworkshop.dongjing.configuration.DJConfigurationKey;
import com.mcworkshop.dongjing.domain.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.wicket.util.file.File;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Markfred
 */
public class CompanyListReport extends BaseReport<List<Company>> {

    private CellStyle contentStyle = new CellStyleWrapper(workbook)
            .alignLeft().alignMiddle().setBorder((short) 1).done();
    private SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");

    public CompanyListReport(List<Company> data) {
        super(data);
        prepareDirectory();
    }

    public static void main(String[] args) {
        CompanyListReport report = new CompanyListReport(new ArrayList<Company>());
        try {
            report.generateReportByFile(true);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void constructReport() {
        Sheet sheet = workbook.createSheet();
        Row titleRow = sheet.createRow(0);
        Row subtitleRow = sheet.createRow(1);
        createMergedContentCell(titleStyle, sheet, titleRow, "A1:A2", 0, "企业名称");
        createMergedContentCell(titleStyle, sheet, titleRow, "B1:B2", 1, "企业卡号");
        createMergedContentCell(titleStyle, sheet, titleRow, "C1:C2", 2, "机构代码");
        createMergedContentCell(titleStyle, sheet, titleRow, "D1:D2", 3, "工商注册号");
        createMergedContentCell(titleStyle, sheet, titleRow, "E1:E2", 4, "税务登记证号");
        createMergedContentCell(titleStyle, sheet, titleRow, "F1:F2", 5, "注册地址");
        createMergedContentCell(titleStyle, sheet, titleRow, "G1:G2", 6, "经营地址");
        createMergedContentCell(titleStyle, sheet, titleRow, "H1:H2", 7, "注册资本(万)");
        createMergedContentCell(titleStyle, sheet, titleRow, "I1:I2", 8, "所属税务所");
        createMergedContentCell(titleStyle, sheet, titleRow, "J1:J2", 9, "行业分类");
        createMergedContentCell(titleStyle, sheet, titleRow, "K1:K2", 10, "所属区块");
        createMergedContentCell(titleStyle, sheet, titleRow, "L1:L2", 11, "工商状态");
        createMergedContentCell(titleStyle, sheet, titleRow, "M1:M2", 12, "税务状态");
        createMergedContentCell(titleStyle, sheet, titleRow, "N1:N2", 13, "经济实体");
        createMergedContentCell(titleStyle, sheet, titleRow, "O1:O2", 14, "经济性质");
        createMergedContentCell(titleStyle, sheet, titleRow, "P1:P2", 15, "征收方式");
        createMergedContentCell(titleStyle, sheet, titleRow, "Q1:Q2", 16, "属地分类");
        createMergedContentCell(titleStyle, sheet, titleRow, "R1:R2", 17, "纳税性质");
        createMergedContentCell(titleStyle, sheet, titleRow, "S1:S2", 18, "纳税种类");
        createMergedContentCell(titleStyle, sheet, titleRow, "T1:T2", 19, "成立日期");
        createMergedContentCell(titleStyle, sheet, titleRow, "U1:U2", 20, "经营年限");
        createMergedContentCell(titleStyle, sheet, titleRow, "V1:V2", 21, "经营范围");
        createMergedContentCell(titleStyle, sheet, titleRow, "W1:W2", 22, "备注");
        createMergedContentCell(titleStyle, sheet, titleRow, "X1:AE1", 23, "法定代表人");
        createContentCell(titleStyle, subtitleRow, 23, "姓名");
        createContentCell(titleStyle, subtitleRow, 24, "性别");
        createContentCell(titleStyle, subtitleRow, 25, "身份证号");
        createContentCell(titleStyle, subtitleRow, 26, "家庭地址");
        createContentCell(titleStyle, subtitleRow, 27, "手机号码");
        createContentCell(titleStyle, subtitleRow, 28, "电话号码");
        createContentCell(titleStyle, subtitleRow, 29, "传真号码");
        createContentCell(titleStyle, subtitleRow, 30, "电子邮箱");
        createMergedContentCell(titleStyle, sheet, titleRow, "AF1:AH1", 31, "联系人");
        createContentCell(titleStyle, subtitleRow, 31, "姓名");
        createContentCell(titleStyle, subtitleRow, 32, "职务");
        createContentCell(titleStyle, subtitleRow, 33, "电话号码");
        createMergedContentCell(titleStyle, sheet, titleRow, "AI1:AO1", 34, "自然人股东");
        createContentCell(titleStyle, subtitleRow, 34, "股东名称");
        createContentCell(titleStyle, subtitleRow, 35, "身份证号");
        createContentCell(titleStyle, subtitleRow, 36, "家庭地址");
        createContentCell(titleStyle, subtitleRow, 37, "手机号码");
        createContentCell(titleStyle, subtitleRow, 38, "电话号码");
        createContentCell(titleStyle, subtitleRow, 39, "电子邮箱");
        createContentCell(titleStyle, subtitleRow, 40, "出资比例");
        createMergedContentCell(titleStyle, sheet, titleRow, "AP1:AU1", 41, "企业股东");
        createContentCell(titleStyle, subtitleRow, 41, "股东名称");
        createContentCell(titleStyle, subtitleRow, 42, "证件类型");
        createContentCell(titleStyle, subtitleRow, 43, "证件号码");
        createContentCell(titleStyle, subtitleRow, 44, "地址");
        createContentCell(titleStyle, subtitleRow, 45, "电话号码");
        createContentCell(titleStyle, subtitleRow, 46, "出资比例");
        createMergedContentCell(titleStyle, sheet, titleRow, "AV1:AV2", 47, "职工人数");
        createMergedContentCell(titleStyle, sheet, titleRow, "AW1:AW2", 48, "本市员工人数");
        createMergedContentCell(titleStyle, sheet, titleRow, "AX1:BC1", 49, "高级技术人员信息");
        createContentCell(titleStyle, subtitleRow, 49, "成员姓名");
        createContentCell(titleStyle, subtitleRow, 50, "职务");
        createContentCell(titleStyle, subtitleRow, 51, "产生方式");
        createContentCell(titleStyle, subtitleRow, 52, "证件类型");
        createContentCell(titleStyle, subtitleRow, 53, "证件号码");
        createContentCell(titleStyle, subtitleRow, 54, "国家认可职称");
        createMergedContentCell(titleStyle, sheet, titleRow, "BD1:BD2", 55, "经营面积");
        createMergedContentCell(titleStyle, sheet, titleRow, "BE1:BE2", 56, "所属产权");
        createMergedContentCell(titleStyle, sheet, titleRow, "BF1:BF2", 57, "土地性质");
        createContentCell(titleStyle, titleRow, 58, "宾馆");
        createContentCell(titleStyle, subtitleRow, 58, "客房间数");
        createMergedContentCell(titleStyle, sheet, titleRow, "BH1:BJ1", 59, "楼宇");
        createContentCell(titleStyle, subtitleRow, 59, "自用面积");
        createContentCell(titleStyle, subtitleRow, 60, "出租面积");
        createContentCell(titleStyle, subtitleRow, 61, "空置面积");
        createContentCell(titleStyle, titleRow, 62, "企业");
        createContentCell(titleStyle, subtitleRow, 62, "占地面积");
        createMergedContentCell(titleStyle, sheet, titleRow, "BL1:BT1", 63, "自用厂房面积");
        createContentCell(titleStyle, subtitleRow, 63, "占地面积");
        createContentCell(titleStyle, subtitleRow, 64, "建筑面积");
        createContentCell(titleStyle, subtitleRow, 65, "自用面积");
        createContentCell(titleStyle, subtitleRow, 66, "出租面积");
        createContentCell(titleStyle, subtitleRow, 67, "空置面积");
        createContentCell(titleStyle, subtitleRow, 68, "承租方");
        createContentCell(titleStyle, subtitleRow, 69, "租用面积");
        createContentCell(titleStyle, subtitleRow, 70, "地址");
        createContentCell(titleStyle, subtitleRow, 71, "租赁年限");
        createMergedContentCell(titleStyle, sheet, titleRow, "BU1:BX1", 72, "租用厂房面积");
        createContentCell(titleStyle, subtitleRow, 72, "出租方");
        createContentCell(titleStyle, subtitleRow, 73, "租用面积");
        createContentCell(titleStyle, subtitleRow, 74, "地址");
        createContentCell(titleStyle, subtitleRow, 75, "租赁年限");
        createMergedContentCell(titleStyle, sheet, titleRow, "BY1:CA1", 76, "年产值记录");
        createContentCell(titleStyle, subtitleRow, 76, "年份");
        createContentCell(titleStyle, subtitleRow, 77, "产值");
        createContentCell(titleStyle, subtitleRow, 78, "利润");
        createMergedContentCell(titleStyle, sheet, titleRow, "CB1:CC1", 79, "党支部信息");
        createContentCell(titleStyle, subtitleRow, 79, "党支部名称");
        createContentCell(titleStyle, subtitleRow, 80, "党支部书记");
        createMergedContentCell(titleStyle, sheet, titleRow, "CD1:CF1", 81, "党支部成员");
        createContentCell(titleStyle, subtitleRow, 81, "党员姓名");
        createContentCell(titleStyle, subtitleRow, 82, "性别");
        createContentCell(titleStyle, subtitleRow, 83, "文化程度");
        createMergedContentCell(titleStyle, sheet, titleRow, "CG1:CG2", 84, "申请通过时间");
        createMergedContentCell(titleStyle, sheet, titleRow, "CH1:CH2", 85, "研发人员数量");
        createMergedContentCell(titleStyle, sheet, titleRow, "CI1:CI2", 86, "研发经费");
        createMergedContentCell(titleStyle, sheet, titleRow, "CJ1:CK1", 87, "中央技术改造");
        createContentCell(titleStyle, subtitleRow, 87, "是否申报");
        createContentCell(titleStyle, subtitleRow, 88, "申报日期");
        createMergedContentCell(titleStyle, sheet, titleRow, "CL1:CM1", 89, "区级技术中心企业");
        createContentCell(titleStyle, subtitleRow, 89, "是否申报");
        createContentCell(titleStyle, subtitleRow, 90, "申报日期");
        createMergedContentCell(titleStyle, sheet, titleRow, "CN1:CO1", 91, "小巨人企业");
        createContentCell(titleStyle, subtitleRow, 91, "是否申报");
        createContentCell(titleStyle, subtitleRow, 92, "申报日期");
        createMergedContentCell(titleStyle, sheet, titleRow, "CP1:CQ1", 93, "区产学研创新项目专项扶持资金申请企业");
        createContentCell(titleStyle, subtitleRow, 93, "是否申报");
        createContentCell(titleStyle, subtitleRow, 94, "申报日期");
        createMergedContentCell(titleStyle, sheet, titleRow, "CR1:CS1", 95, "区节能技术改造申报");
        createContentCell(titleStyle, subtitleRow, 95, "是否申报");
        createContentCell(titleStyle, subtitleRow, 96, "申报日期");
        createMergedContentCell(titleStyle, sheet, titleRow, "CT1:CU1", 97, "上海市科技型中小企业技术创新资金申报");
        createContentCell(titleStyle, subtitleRow, 97, "是否申报");
        createContentCell(titleStyle, subtitleRow, 98, "申报日期");
        createMergedContentCell(titleStyle, sheet, titleRow, "CV1:CW1", 99, "著名商标申报");
        createContentCell(titleStyle, subtitleRow, 99, "是否申报");
        createContentCell(titleStyle, subtitleRow, 100, "申报日期");
        createMergedContentCell(titleStyle, sheet, titleRow, "CX1:CY1", 101, "上海市知名品牌创建示范区");
        createContentCell(titleStyle, subtitleRow, 101, "是否申报");
        createContentCell(titleStyle, subtitleRow, 102, "申报日期");
        createMergedContentCell(titleStyle, sheet, titleRow, "CZ1:DA1", 103, "两化整合专项资金申报");
        createContentCell(titleStyle, subtitleRow, 103, "是否申报");
        createContentCell(titleStyle, subtitleRow, 104, "申报日期");
        createMergedContentCell(titleStyle, sheet, titleRow, "DB1:DC1", 105, "专精特新申报");
        createContentCell(titleStyle, subtitleRow, 105, "是否申报");
        createContentCell(titleStyle, subtitleRow, 106, "申报日期");
        createMergedContentCell(titleStyle, sheet, titleRow, "DD1:DE1", 107, "区长质量奖申报企业");
        createContentCell(titleStyle, subtitleRow, 107, "是否申报");
        createContentCell(titleStyle, subtitleRow, 108, "申报日期");
        createMergedContentCell(titleStyle, sheet, titleRow, "DF1:DG1", 109, "工业技改项目的通知");
        createContentCell(titleStyle, subtitleRow, 109, "是否申报");
        createContentCell(titleStyle, subtitleRow, 110, "申报日期");
        createMergedContentCell(titleStyle, sheet, titleRow, "DH1:DI1", 111, "名牌申报企业");
        createContentCell(titleStyle, subtitleRow, 111, "是否申报");
        createContentCell(titleStyle, subtitleRow, 112, "申报日期");
        createMergedContentCell(titleStyle, sheet, titleRow, "DJ1:DK1", 113, "高新技术企业");
        createContentCell(titleStyle, subtitleRow, 113, "是否申报");
        createContentCell(titleStyle, subtitleRow, 114, "申报日期");
        createMergedContentCell(titleStyle, sheet, titleRow, "DL1:DO1", 115, "发明专利");
        createContentCell(titleStyle, subtitleRow, 115, "是否申报");
        createContentCell(titleStyle, subtitleRow, 116, "申报日期");
        createContentCell(titleStyle, subtitleRow, 117, "是否授权");
        createContentCell(titleStyle, subtitleRow, 118, "授权日期");
        createMergedContentCell(titleStyle, sheet, titleRow, "DP1:DS1", 119, "实用新型专利");
        createContentCell(titleStyle, subtitleRow, 119, "是否申报");
        createContentCell(titleStyle, subtitleRow, 120, "申报日期");
        createContentCell(titleStyle, subtitleRow, 121, "是否授权");
        createContentCell(titleStyle, subtitleRow, 122, "授权日期");
        createMergedContentCell(titleStyle, sheet, titleRow, "DT1:DW1", 123, "外观专利");
        createContentCell(titleStyle, subtitleRow, 123, "是否申报");
        createContentCell(titleStyle, subtitleRow, 124, "申报日期");
        createContentCell(titleStyle, subtitleRow, 125, "是否授权");
        createContentCell(titleStyle, subtitleRow, 126, "授权日期");
        createMergedContentCell(titleStyle, sheet, titleRow, "DX1:DY1", 127, "国外专利");
        createContentCell(titleStyle, subtitleRow, 127, "是否申报");
        createContentCell(titleStyle, subtitleRow, 128, "申报日期");
        createMergedContentCell(titleStyle, sheet, titleRow, "DZ1:EA1", 129, "一般作品著作权");
        createContentCell(titleStyle, subtitleRow, 129, "是否申报");
        createContentCell(titleStyle, subtitleRow, 130, "申报日期");
        createMergedContentCell(titleStyle, sheet, titleRow, "EB1:EC1", 131, "软件著作权");
        createContentCell(titleStyle, subtitleRow, 131, "是否申报");
        createContentCell(titleStyle, subtitleRow, 132, "申报日期");


        contentTextWrapperStyle.setWrapText(true);
        for (int i = data.size() - 1; i >= 0; i--) {
            Row row = sheet.createRow(2 + i);
            Company c = data.get(i);
            createContentCell(contentStyle, row, 0, c.getName());
            createContentCell(contentStyle, row, 1, c.getCompanyNo());
            createContentCell(contentStyle, row, 2, c.getOrganizationCode());
            createContentCell(contentStyle, row, 3, c.getCommercialNo());
            createContentCell(contentStyle, row, 4, c.getTaxRegNo());
            createContentCell(contentStyle, row, 5, c.getRegisterAddress());
            createContentCell(contentStyle, row, 6, c.getOperateAddress());
            if (c.getRegisterAsset() != null) {
                createContentCell(contentStyle, row, 7, formatDouble(c.getRegisterAsset()) + " " + getSysEnumMsgn(c.getCurrency()));
            } else {
                createContentCell(contentStyle, row, 7, "");
            }

            createContentCell(contentStyle, row, 8, getSysEnumMsgn(c.getTaxOrg()));
            createContentCell(contentStyle, row, 9, c.getIndustryType());
            createContentCell(contentStyle, row, 10, getSysEnumMsgn(c.getRegion()));
            createContentCell(contentStyle, row, 11, getSysEnumMsgn(c.getCommercialStatus()));
            createContentCell(contentStyle, row, 12, getSysEnumMsgn(c.getTaxStatus()));
            createContentCell(contentStyle, row, 13, getSysEnumMsgn(c.getEconomyEntity()));
            createContentCell(contentStyle, row, 14, getSysEnumMsgn(c.getEconomyNature()));
            createContentCell(contentStyle, row, 15, getSysEnumMsgn(c.getCollectMethod()));
            createContentCell(contentStyle, row, 16, getSysEnumMsgn(c.getAffiliateRegion()));
            createContentCell(contentStyle, row, 17, getSysEnumMsgn(c.getTaxNature()));
            createContentCell(contentStyle, row, 18, getSysEnumMsgn(c.getTaxType()));
            createContentCell(contentStyle, row, 19, c.getEstablishDate());
            createContentCell(contentStyle, row, 20, formatDateRange(c.getOperationStartYear(), c.getOperationEndYear()));
            createContentCell(contentStyle, row, 21, c.getOperationScope());
            createContentCell(contentStyle, row, 22, c.getRemarkables());
            if (c.getOwnerContact() != null) {
                createContentCell(contentStyle, row, 23, c.getOwnerContact().getFullName());
                createContentCell(contentStyle, row, 24, getSysEnumMsgn(c.getOwnerContact().getGender()));
                createContentCell(contentStyle, row, 25, c.getOwnerContact().getIdCard());
                createContentCell(contentStyle, row, 26, c.getOwnerContact().getAddress());
                createContentCell(contentStyle, row, 27, c.getOwnerContact().getCellPhone());
                createContentCell(contentStyle, row, 28, c.getOwnerContact().getPhone());
                createContentCell(contentStyle, row, 29, c.getOwnerContact().getFax());
                createContentCell(contentStyle, row, 30, c.getOwnerContact().getEmailAddress());
            } else {
                createContentCell(contentStyle, row, 23, "");
                createContentCell(contentStyle, row, 24, "");
                createContentCell(contentStyle, row, 25, "");
                createContentCell(contentStyle, row, 26, "");
                createContentCell(contentStyle, row, 27, "");
                createContentCell(contentStyle, row, 28, "");
                createContentCell(contentStyle, row, 29, "");
                createContentCell(contentStyle, row, 30, "");
            }
            if (c.getOtherContact() != null) {
                createContentCell(contentStyle, row, 31, c.getOtherContact().getFullName());
                createContentCell(contentStyle, row, 32, c.getOtherContact().getPostalCode());
                createContentCell(contentStyle, row, 33, c.getOtherContact().getPhone());
            } else {
                createContentCell(contentStyle, row, 31, "");
                createContentCell(contentStyle, row, 32, "");
                createContentCell(contentStyle, row, 33, "");
            }
            constructNatureShareholders(c, row);
            constructCompanyShareholder(c, row);

            createContentCell(contentStyle, row, 47, formatInteger(c.getEmployeeNumber()));
            createContentCell(contentStyle, row, 48, formatInteger(c.getLocalEmployeeNumber()));

            constructEngineerInfo(c, row);

            createContentCell(contentStyle, row, 55, formatDouble(c.getArea()));
            createContentCell(contentStyle, row, 56, c.getOwnership());
            createContentCell(contentStyle, row, 57, getSysEnumMsgn(c.getLandUsage()));
            createContentCell(contentStyle, row, 58, c.getSleepingRoomNumbers());
            createContentCell(contentStyle, row, 59, formatDouble(c.getUsageArea()));
            createContentCell(contentStyle, row, 60, formatDouble(c.getRentArea()));
            createContentCell(contentStyle, row, 61, formatDouble(c.getSpareArea()));
            createContentCell(contentStyle, row, 62, formatDouble(c.getCompanyArea()));
            createContentCell(contentStyle, row, 63, formatDouble(c.getFactoryArea()));
            createContentCell(contentStyle, row, 64, formatDouble(c.getFactoryActualArea()));
            createContentCell(contentStyle, row, 65, formatDouble(c.getFactoryUsageArea()));
            createContentCell(contentStyle, row, 66, formatDouble(c.getFactoryRentArea()));
            createContentCell(contentStyle, row, 67, formatDouble(c.getFactoryControlArea()));

            constructRentees(c, row, 68);
            constructRentors(c, row, 72);
            constructAnnualOutput(c, row, 76);
            createContentCell(contentStyle, row, 79, c.getPartyName());
            createContentCell(contentStyle, row, 80, c.getPartyLeader());
            constructPartyMember(c, row, 81);
            createContentCell(contentStyle, row, 84, formatDate(f, c.getPassDate()));
            createContentCell(contentStyle, row, 85, getString(c.getNoOfDeveloper()));
            createContentCell(contentStyle, row, 86, getString(c.getDevBudget()));

            createContentCell(contentStyle, row, 87, formatBoolean(c.getIsA()));
            createContentCell(contentStyle, row, 89, formatBoolean(c.getIsB()));
            createContentCell(contentStyle, row, 91, formatBoolean(c.getIsC()));
            createContentCell(contentStyle, row, 93, formatBoolean(c.getIsD()));
            createContentCell(contentStyle, row, 95, formatBoolean(c.getIsE()));
            createContentCell(contentStyle, row, 97, formatBoolean(c.getIsF()));
            createContentCell(contentStyle, row, 99, formatBoolean(c.getIsG()));
            createContentCell(contentStyle, row, 101, formatBoolean(c.getIsH()));
            createContentCell(contentStyle, row, 103, formatBoolean(c.getIsI()));
            createContentCell(contentStyle, row, 105, formatBoolean(c.getIsJ()));
            createContentCell(contentStyle, row, 107, formatBoolean(c.getIsK()));
            createContentCell(contentStyle, row, 109, formatBoolean(c.getIsL()));
            createContentCell(contentStyle, row, 111, formatBoolean(c.getIsM()));
            createContentCell(contentStyle, row, 113, formatBoolean(c.getIsN()));
            createContentCell(contentStyle, row, 115, formatBoolean(c.getIsR()));
            createContentCell(contentStyle, row, 117, formatBoolean(c.getIsS()));
            createContentCell(contentStyle, row, 119, formatBoolean(c.getIsT()));
            createContentCell(contentStyle, row, 121, formatBoolean(c.getIsU()));
            createContentCell(contentStyle, row, 123, formatBoolean(c.getIsV()));
            createContentCell(contentStyle, row, 125, formatBoolean(c.getIsW()));
            createContentCell(contentStyle, row, 127, formatBoolean(c.getIsO()));
            createContentCell(contentStyle, row, 129, formatBoolean(c.getIsP()));
            createContentCell(contentStyle, row, 131, formatBoolean(c.getIsQ()));

            createContentCell(contentStyle, row, 88, formatDate(f, c.getaDate()));
            createContentCell(contentStyle, row, 90, formatDate(f, c.getbDate()));
            createContentCell(contentStyle, row, 92, formatDate(f, c.getcDate()));
            createContentCell(contentStyle, row, 94, formatDate(f, c.getdDate()));
            createContentCell(contentStyle, row, 96, formatDate(f, c.geteDate()));
            createContentCell(contentStyle, row, 98, formatDate(f, c.getfDate()));
            createContentCell(contentStyle, row, 100, formatDate(f, c.getgDate()));
            createContentCell(contentStyle, row, 102, formatDate(f, c.gethDate()));
            createContentCell(contentStyle, row, 104, formatDate(f, c.getiDate()));
            createContentCell(contentStyle, row, 106, formatDate(f, c.getjDate()));
            createContentCell(contentStyle, row, 108, formatDate(f, c.getkDate()));
            createContentCell(contentStyle, row, 110, formatDate(f, c.getlDate()));
            createContentCell(contentStyle, row, 112, formatDate(f, c.getmDate()));
            createContentCell(contentStyle, row, 114, formatDate(f, c.getnDate()));
            createContentCell(contentStyle, row, 116, formatDate(f, c.getrDate()));
            createContentCell(contentStyle, row, 118, formatDate(f, c.getsDate()));
            createContentCell(contentStyle, row, 120, formatDate(f, c.gettDate()));
            createContentCell(contentStyle, row, 122, formatDate(f, c.getuDate()));
            createContentCell(contentStyle, row, 124, formatDate(f, c.getvDate()));
            createContentCell(contentStyle, row, 126, formatDate(f, c.getwDate()));
            createContentCell(contentStyle, row, 128, formatInteger(c.getNumberOfO()));
            createContentCell(contentStyle, row, 130, formatInteger(c.getNumberOfP()));
            createContentCell(contentStyle, row, 132, formatInteger(c.getNumberOfQ()));
            data.remove(c);
        }

        for (int i = 23; i < 133; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    private void constructNatureShareholders(Company c, Row row) {
    	List<NatureShareholder> shareholders = c.getNatureShareholders();
        if (shareholders!=null&&shareholders.size() > 0) {
            StringBuilder name = new StringBuilder();
            StringBuilder idCard = new StringBuilder();
            StringBuilder address = new StringBuilder();
            StringBuilder cellPhone = new StringBuilder();
            StringBuilder phone = new StringBuilder();
            StringBuilder email = new StringBuilder();
            StringBuilder ratio = new StringBuilder();
            for (int j = 0; j < shareholders.size(); j++) {
                NatureShareholder ns = shareholders.get(j);
                if (j != 0) {
                    name.append("\n");
                    idCard.append("\n");
                    address.append("\n");
                    cellPhone.append("\n");
                    phone.append("\n");
                    email.append("\n");
                    ratio.append("\n");
                }
                name.append(getString(ns.getFullName()));
                idCard.append(getString(ns.getIdCard()));
                address.append(getString(ns.getAddress()));
                cellPhone.append(getString(ns.getCellPhone()));
                phone.append(getString(ns.getPhone()));
                email.append(getString(ns.getEmailAddress()));
                ratio.append(getString(ns.getShareRatio()));
            }
            createContentCell(contentTextWrapperStyle, row, 34, name.toString());
            createContentCell(contentTextWrapperStyle, row, 35, idCard.toString());
            createContentCell(contentTextWrapperStyle, row, 36, address.toString());
            createContentCell(contentTextWrapperStyle, row, 37, cellPhone.toString());
            createContentCell(contentTextWrapperStyle, row, 38, phone.toString());
            createContentCell(contentTextWrapperStyle, row, 39, email.toString());
            createContentCell(contentTextWrapperStyle, row, 40, ratio.toString());
        } else {
            createContentCell(contentStyle, row, 34, "");
            createContentCell(contentStyle, row, 35, "");
            createContentCell(contentStyle, row, 36, "");
            createContentCell(contentStyle, row, 37, "");
            createContentCell(contentStyle, row, 38, "");
            createContentCell(contentStyle, row, 39, "");
            createContentCell(contentStyle, row, 40, "");
        }
    }

    private void constructCompanyShareholder(Company c, Row row) {
    	List<CompanyShareholder> shareholders = c.getCompanyShareholders();
        if (shareholders!=null&&shareholders.size() > 0) {
            StringBuilder name = new StringBuilder();
            StringBuilder idType = new StringBuilder();
            StringBuilder idCard = new StringBuilder();
            StringBuilder address = new StringBuilder();
            StringBuilder phone = new StringBuilder();
            StringBuilder ratio = new StringBuilder();
            for (int i = 0; i < shareholders.size(); i++) {
                CompanyShareholder cs = shareholders.get(i);
                if (i != 0) {
                    name.append("\n");
                    idType.append("\n");
                    idCard.append("\n");
                    address.append("\n");
                    phone.append("\n");
                    ratio.append("\n");
                }
                name.append(getString(cs.getFullName()));
                idType.append(getString(cs.getIdType()));
                idCard.append(getString(cs.getIdCard()));
                address.append(getString(cs.getAddress()));
                phone.append(getString(cs.getPhone()));
                ratio.append(getString(cs.getShareRatio()));
            }
            createContentCell(contentTextWrapperStyle, row, 41, name.toString());
            createContentCell(contentTextWrapperStyle, row, 42, idType.toString());
            createContentCell(contentTextWrapperStyle, row, 43, idCard.toString());
            createContentCell(contentTextWrapperStyle, row, 44, address.toString());
            createContentCell(contentTextWrapperStyle, row, 45, phone.toString());
            createContentCell(contentTextWrapperStyle, row, 46, ratio.toString());
        } else {
            createContentCell(contentStyle, row, 41, "");
            createContentCell(contentStyle, row, 42, "");
            createContentCell(contentStyle, row, 43, "");
            createContentCell(contentStyle, row, 44, "");
            createContentCell(contentStyle, row, 45, "");
            createContentCell(contentStyle, row, 46, "");
        }
    }

    private void constructEngineerInfo(Company c, Row row) {
        List<CompanyMember> members = c.getMembers();
        if (members!=null&&members.size() > 0) {
            StringBuilder name = new StringBuilder();
            StringBuilder position = new StringBuilder();
            StringBuilder produceMethod = new StringBuilder();
            StringBuilder idType = new StringBuilder();
            StringBuilder idCard = new StringBuilder();
            StringBuilder certificate = new StringBuilder();
            for (int i = 0; i < members.size(); i++) {
                CompanyMember m = members.get(i);
                if (i != 0) {
                    name.append("\n");
                    position.append("\n");
                    produceMethod.append("\n");
                    idType.append("\n");
                    idCard.append("\n");
                    certificate.append("\n");
                }
                name.append(getString(m.getName()));
                position.append(getString(m.getPosition()));
                produceMethod.append(getString(m.getProduceMethod()));
                idType.append(getString(getSysEnumMsgn(m.getIdType())));
                idCard.append(getString(m.getIdNo()));
                certificate.append(getString(m.getCertificate()));
            }
            createContentCell(contentTextWrapperStyle, row, 49, name.toString());
            createContentCell(contentTextWrapperStyle, row, 50, position.toString());
            createContentCell(contentTextWrapperStyle, row, 51, produceMethod.toString());
            createContentCell(contentTextWrapperStyle, row, 52, idType.toString());
            createContentCell(contentTextWrapperStyle, row, 53, idCard.toString());
            createContentCell(contentTextWrapperStyle, row, 54, certificate.toString());
        } else {
            createContentCell(contentStyle, row, 49, "");
            createContentCell(contentStyle, row, 50, "");
            createContentCell(contentStyle, row, 51, "");
            createContentCell(contentStyle, row, 52, "");
            createContentCell(contentStyle, row, 53, "");
            createContentCell(contentStyle, row, 54, "");
        }
    }

    private void constructRentees(Company c, Row row, int startIndex) {
        List<RentStatus> rentees = c.getRentees();
        if (rentees != null && rentees.size() > 0) {
            StringBuilder name = new StringBuilder();
            StringBuilder area = new StringBuilder();
            StringBuilder address = new StringBuilder();
            StringBuilder rentDuration = new StringBuilder();
            for (int i = 0; i < rentees.size(); i++) {
                RentStatus rs = rentees.get(i);
                if (i != 0) {
                    name.append("\n");
                    area.append("\n");
                    address.append("\n");
                    rentDuration.append("\n");
                }
                name.append(getString(rs.getRentee().getName()));
                area.append(formatDouble(rs.getArea()));
                address.append(getString(rs.getAddress()));
                rentDuration.append(formatDateRange(rs.getStartDate(), rs.getEndDate()));
            }
            createContentCell(contentTextWrapperStyle, row, startIndex++, name.toString());
            createContentCell(contentTextWrapperStyle, row, startIndex++, area.toString());
            createContentCell(contentTextWrapperStyle, row, startIndex++, address.toString());
            createContentCell(contentTextWrapperStyle, row, startIndex++, rentDuration.toString());
        } else {
            createContentCell(contentStyle, row, startIndex++, "");
            createContentCell(contentStyle, row, startIndex++, "");
            createContentCell(contentStyle, row, startIndex++, "");
            createContentCell(contentStyle, row, startIndex++, "");
        }
    }

    private void constructRentors(Company c, Row row, int startIndex) {
        List<RentStatus> rentors = c.getRentors();
        if (rentors != null && rentors.size() > 0) {
            StringBuilder name = new StringBuilder();
            StringBuilder area = new StringBuilder();
            StringBuilder address = new StringBuilder();
            StringBuilder rentDuration = new StringBuilder();
            for (int i = 0; i < rentors.size(); i++) {
                RentStatus rs = rentors.get(i);
                if (i != 0) {
                    name.append("\n");
                    area.append("\n");
                    address.append("\n");
                    rentDuration.append("\n");
                }
                if (rs.getRentor() == null) {
                    name.append(getString(rs.getNonDJCompany()));
                } else {
                    name.append(rs.getRentor().getName());
                }
                area.append(formatDouble(rs.getArea()));
                address.append(getString(rs.getAddress()));
                rentDuration.append(formatDateRange(rs.getStartDate(), rs.getEndDate()));
            }
            createContentCell(contentTextWrapperStyle, row, startIndex++, name.toString());
            createContentCell(contentTextWrapperStyle, row, startIndex++, area.toString());
            createContentCell(contentTextWrapperStyle, row, startIndex++, address.toString());
            createContentCell(contentTextWrapperStyle, row, startIndex++, rentDuration.toString());
        } else {
            createContentCell(contentStyle, row, startIndex++, "");
            createContentCell(contentStyle, row, startIndex++, "");
            createContentCell(contentStyle, row, startIndex++, "");
            createContentCell(contentStyle, row, startIndex++, "");
        }
    }

    private void constructAnnualOutput(Company c, Row row, int startIndex) {
        List<AnnualOutput> outputs = c.getOutputs();
        if (outputs != null && outputs.size() > 0) {
            StringBuilder s1 = new StringBuilder();
            StringBuilder s2 = new StringBuilder();
            StringBuilder s3 = new StringBuilder();
            for (int i = 0; i < outputs.size(); i++) {
                AnnualOutput o = outputs.get(i);
                if (i != 0) {
                    s1.append("\n");
                    s2.append("\n");
                    s3.append("\n");
                }
                if (o.getYear() == null) {
                    s1.append("");
                } else {
                    s1.append(o.getYear());
                }
                s2.append(formatDouble(o.getOutput()));
                s3.append(formatDouble(o.getProfit()));
            }
            createContentCell(contentTextWrapperStyle, row, startIndex++, s1.toString());
            createContentCell(contentTextWrapperStyle, row, startIndex++, s2.toString());
            createContentCell(contentTextWrapperStyle, row, startIndex++, s3.toString());

        } else {
            createContentCell(contentStyle, row, startIndex++, "");
            createContentCell(contentStyle, row, startIndex++, "");
            createContentCell(contentStyle, row, startIndex++, "");
        }
    }

    private void constructPartyMember(Company c, Row row, int startIndex) {
        List<PartyMember> members = c.getPartyMembers();
        if (members != null && members.size() > 0) {
            StringBuilder s1 = new StringBuilder();
            StringBuilder s2 = new StringBuilder();
            StringBuilder s3 = new StringBuilder();
            for (int i = 0; i < members.size(); i++) {
                PartyMember m = members.get(i);
                if (i != 0) {
                    s1.append("\n");
                    s2.append("\n");
                    s3.append("\n");
                }
                s1.append(getString(m.getName()));
                s2.append(getSysEnumMsgn(m.getGender()));
                s3.append(getString(m.getEducation()));
            }
            createContentCell(contentTextWrapperStyle, row, startIndex++, s1.toString());
            createContentCell(contentTextWrapperStyle, row, startIndex++, s2.toString());
            createContentCell(contentTextWrapperStyle, row, startIndex++, s3.toString());
        } else {
            createContentCell(contentStyle, row, startIndex++, "");
            createContentCell(contentStyle, row, startIndex++, "");
            createContentCell(contentStyle, row, startIndex++, "");
        }
    }

    @Override
    protected String getReportFilePath() {
        String path = Config.getConfig(DJConfigurationKey.REPORT_DIR);
        path += "/company/CompanyList.xlsx";
//        return "D:/CompanyList.xlsx";
        return path;
    }

    protected void prepareDirectory() {
        StringBuilder outputDirectoryPath = new StringBuilder();
        outputDirectoryPath.append(Config
                .getConfig(DJConfigurationKey.REPORT_DIR));
        outputDirectoryPath.append("/");
        outputDirectoryPath.append("company");
        File outputDirectory = new File(outputDirectoryPath.toString());
        outputDirectory.mkdirs();
    }

    private String getSysEnumMsgn(SysEnum sysEnum) {
        if (sysEnum == null) {
            return "";
        } else {
            return WicketMessageUtil.getResourceString(sysEnum.getMessageKey());
        }
    }

    private String formatDouble(Double d) {
        if (d == null) {
            return "";
        } else {
            return String.format("%1$,.2f", d);
        }
    }

    private String getString(String text) {
        if (text == null) {
            return "";
        } else {
            return text;
        }
    }

    private String formatInteger(Integer i) {
        if (i == null) {
            return "";
        } else {
            return i.toString();
        }
    }

    private String formatBoolean(Boolean b) {
        if (b == null) {
            return "";
        }
        if (b) {
            return "是";
        } else {
            return "否";
        }
    }

    private String formatDateRange(Date startDate, Date endDate) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        if (startDate == null || endDate == null) {
            return "";
        } else {
            return f.format(startDate) + " 至 " + f.format(endDate);
        }
    }

}
