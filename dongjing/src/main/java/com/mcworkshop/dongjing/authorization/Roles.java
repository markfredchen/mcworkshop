// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.authorization;

import java.util.Arrays;
import java.util.List;

/**
 * @author $Author$
 */
public interface Roles {

    public static final String SYSTEM = "SYSTEM";
    public static final String USER = "USER";
    public static final String CM = "CM";
    public static final List<String> ZHDW_RIGHTS = Arrays.asList(CM);
    public static final String CM_VIEW = "CM.VIEW_COMPANY";
    public static final String CM_IMPORT = "CM.IMPORT_COMPANY";
    public static final String CM_CHANGE = "CM.CHANGE_COMPANY";
    public static final String CM_EXPORT = "CM.EXPORT_COMPANY";
    public static final String TAX = "TAX";
    public static final String TAX_CHANGE = "TAX.CHANGE";
    public static final String TAX_VIEW = "TAX.VIEW";
    public static final String TAX_IMPORT = "TAX.IMPORT";
    public static final String PM = "PM";
    public static final String PM_VIEW = "PM.VIEW";
    public static final String PM_CHANGE = "PM.CHANGE";
    public static final String RM = "RM";
    public static final String RM_BKX = "RM.BKX";
    public static final String RM_OVERALL = "RM.OVERALL";
    public static final String SECURITY = "SECURITY";
    public static final String SECURITY_VIEW = "SECURITY.VIEW";
    public static final String SECURITY_CHANGE = "SECURITY.CHANGE";
    public static final String AGRI = "AGRI";
    public static final List<String> NFGS_RIGHTS = Arrays.asList(AGRI);
    public static final String AGRI_VIEW = "AGRI.VIEW";
    public static final String AGRI_CHANGE = "AGRI.CHANGE";
    public static final String INFO = "INFO";
    public static final String INFO_SMS = "INFO.SMS";
    public static final String INFO_EMAIL = "INFO.EMAIL";
    public static final String CA = "CA";
    public static final List<String> JFB_RIGHTS = Arrays.asList(CM, TAX, PM,
            RM, SECURITY, AGRI, INFO, CA);
    public static final List<String> CZS_RIGHTS = Arrays.asList(CM, TAX, RM,
            SECURITY, AGRI, CA);
    public static final List<String> AJS_RIGHTS = Arrays.asList(CM, PM,
            SECURITY, INFO, CA);
    public static final List<String> BKX_RIGHTS = Arrays.asList(CM, TAX, PM,
            RM, SECURITY, INFO, CA);
    public static final List<String> JGS_RIGHTS = Arrays.asList(CM, TAX, RM,
            SECURITY, AGRI, CA);
    public static final List<String> MYC_RIGHTS = Arrays.asList(CA);
    public static final String CA_VIEW = "CA.VIEW";
    public static final String CA_CHANGE = "CA.CHANGE";
    public static final String JFB = "JFB";
    public static final String CZS = "CZS";
    public static final String AJS = "AJS";
    public static final String BKX = "BKX";
    public static final String JGS = "JGS";
    public static final String MYC = "MYC";
    public static final String NFGS = "NFGS";
    public static final String ZHDW = "ZHDW";

}
