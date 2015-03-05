INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(1903, 'com.mcworkshop.dongjing.domain.Currency', 'EUR', 3);
INSERT INTO sysEnums(sysEnumID, type, value, sequence) VALUES(1904, 'com.mcworkshop.dongjing.domain.Currency', 'JPY', 4);

ALTER TABLE `dongjing`.`company` 
CHANGE COLUMN `registerAsset` `registerAsset` DOUBLE NULL DEFAULT NULL ,
CHANGE COLUMN `area` `area` DOUBLE NULL DEFAULT NULL ;
