INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(1901, 'com.mcworkshop.dongjing.domain.Currency', 'CNY', 1);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(1902, 'com.mcworkshop.dongjing.domain.Currency', 'USD', 2);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(203, 'com.mcworkshop.dongjing.domain.AffiliateRegion', 'IS_AFFILATE', 3);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(204, 'com.mcworkshop.dongjing.domain.AffiliateRegion', 'NON_AFFILATE', 4);

ALTER TABLE `dongjing`.`company` 
ADD COLUMN `currencyID` BIGINT NULL AFTER `devBudget`;

