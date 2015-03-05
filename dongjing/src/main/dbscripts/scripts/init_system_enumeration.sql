INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(101, 'com.mcworkshop.common.domain.UserStatus', 'ACTIVE', 1);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(102, 'com.mcworkshop.common.domain.UserStatus', 'DEACTIVE', 2);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(103, 'com.mcworkshop.common.domain.UserStatus', 'LOCKED_OUT', 3);

INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(201, 'com.mcworkshop.dongjing.domain.AffiliateRegion', 'REGISTRATION', 1);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(202, 'com.mcworkshop.dongjing.domain.AffiliateRegion', 'LOCAL', 2);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(203, 'com.mcworkshop.dongjing.domain.AffiliateRegion', 'IS_AFFILATE', 3);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(204, 'com.mcworkshop.dongjing.domain.AffiliateRegion', 'NON_AFFILATE', 4);

INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(301, 'com.mcworkshop.dongjing.domain.CommercialStatus', 'REVOKED_NOT_SIGN_OFF', 1);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(302, 'com.mcworkshop.dongjing.domain.CommercialStatus', 'REVOKED_SIGN_OFF', 2);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(303, 'com.mcworkshop.dongjing.domain.CommercialStatus', 'TRANSFERRED', 3);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(304, 'com.mcworkshop.dongjing.domain.CommercialStatus', 'CONFIRMED', 4);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(305, 'com.mcworkshop.dongjing.domain.CommercialStatus', 'SIGN_OFF', 5);

INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(401, 'com.mcworkshop.dongjing.domain.EconomyEntity', 'INDUSTRY', 1);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(402, 'com.mcworkshop.dongjing.domain.EconomyEntity', 'COMMERCIAL', 2);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(403, 'com.mcworkshop.dongjing.domain.EconomyEntity', 'SERVICE', 3);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(404, 'com.mcworkshop.dongjing.domain.EconomyEntity', 'CONSTRUCTION', 4);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(405, 'com.mcworkshop.dongjing.domain.EconomyEntity', 'HOUSEHOLDING', 5);

INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(501, 'com.mcworkshop.dongjing.domain.EconomyNature', 'PRIVATE', 1);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(502, 'com.mcworkshop.dongjing.domain.EconomyNature', 'FOREIGN', 2);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(503, 'com.mcworkshop.dongjing.domain.EconomyNature', 'GROUP', 3);

INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(601, 'com.mcworkshop.dongjing.domain.Gender', 'MALE', 1);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(602, 'com.mcworkshop.dongjing.domain.Gender', 'FEMALE', 2);

INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(701, 'com.mcworkshop.dongjing.domain.TaxCollectMethod', 'LOOK_UP_COLLECT', 1);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(702, 'com.mcworkshop.dongjing.domain.TaxCollectMethod', 'AUDIT_COLLECT', 2);

INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(801, 'com.mcworkshop.dongjing.domain.TaxStatus', 'ABNORMAL', 1);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(802, 'com.mcworkshop.dongjing.domain.TaxStatus', 'NORMAL', 2);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(803, 'com.mcworkshop.dongjing.domain.TaxStatus', 'CERTIFICATE_INVALID', 3);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(804, 'com.mcworkshop.dongjing.domain.TaxStatus', 'SIGN_OFF', 4);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(805, 'com.mcworkshop.dongjing.domain.TaxStatus', 'SIGN_OFF_MOVE_OUT', 5);

INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(901, 'com.mcworkshop.dongjing.domain.TaxNature', 'NORMAL', 1);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(902, 'com.mcworkshop.dongjing.domain.TaxNature', 'SMALL_GROUP', 2);

INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(1001, 'com.mcworkshop.dongjing.domain.TaxType', 'VAT', 1);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(1002, 'com.mcworkshop.dongjing.domain.TaxType', 'OPERATE_TAX', 2);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(1003, 'com.mcworkshop.dongjing.domain.TaxType', 'VAT_PLUS_OPERARTE_TAX', 3);

INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(1101, 'com.mcworkshop.dongjing.domain.TaxOrg', 'TAX_ORG_6', 1);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(1102, 'com.mcworkshop.dongjing.domain.TaxOrg', 'TAX_ORG_9', 2);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(1103, 'com.mcworkshop.dongjing.domain.TaxOrg', 'TAX_ORG_12', 3);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(1104, 'com.mcworkshop.dongjing.domain.TaxOrg', 'OTHER', 4);

INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(1201, 'com.mcworkshop.dongjing.domain.ProjectStatus', 'AUDITTED', 1);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(1202, 'com.mcworkshop.dongjing.domain.ProjectStatus', 'CONFIRMED', 2);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(1203, 'com.mcworkshop.dongjing.domain.ProjectStatus', 'PLANNED', 3);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(1204, 'com.mcworkshop.dongjing.domain.ProjectStatus', 'STARTED', 4);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(1205, 'com.mcworkshop.dongjing.domain.ProjectStatus', 'FINISHED', 5);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(1206, 'com.mcworkshop.dongjing.domain.ProjectStatus', 'START_USE', 6);

INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(1301, 'com.mcworkshop.dongjing.domain.ConstructType', 'NEW', 1);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(1302, 'com.mcworkshop.dongjing.domain.ConstructType', 'UPGRAD', 2);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(1303, 'com.mcworkshop.dongjing.domain.ConstructType', 'EXPEND', 3);

INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(1401, 'com.mcworkshop.dongjing.domain.IdType', 'IDCARD', 1);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(1402, 'com.mcworkshop.dongjing.domain.IdType', 'PASSPORT', 2);

INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(1501, 'com.mcworkshop.dongjing.domain.DangerousChemistryType', 'PRODUCE', 1);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(1502, 'com.mcworkshop.dongjing.domain.DangerousChemistryType', 'OPERATE', 2);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(1503, 'com.mcworkshop.dongjing.domain.DangerousChemistryType', 'STORAGE', 3);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(1504, 'com.mcworkshop.dongjing.domain.DangerousChemistryType', 'TRANSPORTATION', 4);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(1505, 'com.mcworkshop.dongjing.domain.DangerousChemistryType', 'USAGE', 5);

INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(1601, 'com.mcworkshop.dongjing.domain.CommercialAreaType', 'STREET', 1);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(1602, 'com.mcworkshop.dongjing.domain.CommercialAreaType', 'AREA', 2);

INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(1701, 'com.mcworkshop.dongjing.domain.AffiliateBlock', 'BLOCK_1', 1);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(1702, 'com.mcworkshop.dongjing.domain.AffiliateBlock', 'BLOCK_2', 2);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(1703, 'com.mcworkshop.dongjing.domain.AffiliateBlock', 'BLOCK_3', 3);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(1704, 'com.mcworkshop.dongjing.domain.AffiliateBlock', 'BLOCK_4', 4);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(1705, 'com.mcworkshop.dongjing.domain.AffiliateBlock', 'BLOCK_5', 5);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(1706, 'com.mcworkshop.dongjing.domain.AffiliateBlock', 'BLOCK_6', 6);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(1707, 'com.mcworkshop.dongjing.domain.AffiliateBlock', 'BLOCK_7', 7);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(1708, 'com.mcworkshop.dongjing.domain.AffiliateBlock', 'BLOCK_8', 8);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(1709, 'com.mcworkshop.dongjing.domain.AffiliateBlock', 'BLOCK_9', 9);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(1710, 'com.mcworkshop.dongjing.domain.AffiliateBlock', 'BLOCK_10', 10);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(1711, 'com.mcworkshop.dongjing.domain.AffiliateBlock', 'BLOCK_11', 11);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(1712, 'com.mcworkshop.dongjing.domain.AffiliateBlock', 'BLOCK_12', 12);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(1713, 'com.mcworkshop.dongjing.domain.AffiliateBlock', 'BLOCK_13', 13);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(1714, 'com.mcworkshop.dongjing.domain.AffiliateBlock', 'BLOCK_14', 14);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(1715, 'com.mcworkshop.dongjing.domain.AffiliateBlock', 'BLOCK_15', 15);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(1716, 'com.mcworkshop.dongjing.domain.AffiliateBlock', 'BLOCK_16', 16);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(1717, 'com.mcworkshop.dongjing.domain.AffiliateBlock', 'BLOCK_17', 17);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(1718, 'com.mcworkshop.dongjing.domain.AffiliateBlock', 'BLOCK_18', 18);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(1719, 'com.mcworkshop.dongjing.domain.AffiliateBlock', 'BLOCK_19', 19);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(1720, 'com.mcworkshop.dongjing.domain.AffiliateBlock', 'BLOCK_BLANK', 20);

INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(1801, 'com.mcworkshop.dongjing.domain.LandUsage', 'COLLECT', 1);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(1802, 'com.mcworkshop.dongjing.domain.LandUsage', 'MESS_RENT', 2);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(1803, 'com.mcworkshop.dongjing.domain.LandUsage', 'GROUP', 3);

INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(1901, 'com.mcworkshop.dongjing.domain.Currency', 'CNY', 1);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(1902, 'com.mcworkshop.dongjing.domain.Currency', 'USD', 2);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(1903, 'com.mcworkshop.dongjing.domain.Currency', 'EUR', 3);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(1904, 'com.mcworkshop.dongjing.domain.Currency', 'JPY', 4);