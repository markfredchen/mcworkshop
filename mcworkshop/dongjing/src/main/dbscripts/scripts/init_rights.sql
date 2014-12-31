INSERT INTO rights VALUES (1, NULL, 'SYSTEM');
INSERT INTO rights VALUES (200, 1, 'CM');
INSERT INTO rights VALUES (201, 200, 'CM.CHANGE_COMPANY');
INSERT INTO rights VALUES (221, 200, 'CM.VIEW_COMPANY');
INSERT INTO rights VALUES (222, 200, 'CM.IMPORT_COMPANY');
INSERT INTO rights VALUES (223, 200, 'CM.EXPORT_COMPANY');
INSERT INTO rights VALUES (202, 200, 'CM.BLOCK1');
INSERT INTO rights VALUES (203, 200, 'CM.BLOCK2');
INSERT INTO rights VALUES (204, 200, 'CM.BLOCK3');
INSERT INTO rights VALUES (205, 200, 'CM.BLOCK4');
INSERT INTO rights VALUES (206, 200, 'CM.BLOCK5');
INSERT INTO rights VALUES (207, 200, 'CM.BLOCK6');
INSERT INTO rights VALUES (208, 200, 'CM.BLOCK7');
INSERT INTO rights VALUES (209, 200, 'CM.BLOCK8');
INSERT INTO rights VALUES (210, 200, 'CM.BLOCK9');
INSERT INTO rights VALUES (211, 200, 'CM.BLOCK10');
INSERT INTO rights VALUES (212, 200, 'CM.BLOCK11');
INSERT INTO rights VALUES (213, 200, 'CM.BLOCK12');
INSERT INTO rights VALUES (214, 200, 'CM.BLOCK13');
INSERT INTO rights VALUES (215, 200, 'CM.BLOCK14');
INSERT INTO rights VALUES (216, 200, 'CM.BLOCK15');
INSERT INTO rights VALUES (217, 200, 'CM.BLOCK16');
INSERT INTO rights VALUES (218, 200, 'CM.BLOCK17');
INSERT INTO rights VALUES (219, 200, 'CM.BLOCK18');
INSERT INTO rights VALUES (220, 200, 'CM.BLOCK19');

INSERT INTO rights VALUES (300, 1, 'TAX');
INSERT INTO rights VALUES (301, 300, 'TAX.CHANGE');
INSERT INTO rights VALUES (302, 300, 'TAX.VIEW');
INSERT INTO rights VALUES (303, 300, 'TAX.IMPORT');

INSERT INTO rights VALUES (400, 1, 'PM');
INSERT INTO rights VALUES (401, 400, 'PM.VIEW');
INSERT INTO rights VALUES (402, 400, 'PM.CHANGE');

INSERT INTO rights VALUES (500, 1, 'RM');
INSERT INTO rights VALUES (501, 500, 'RM.BKX');
INSERT INTO rights VALUES (502, 500, 'RM.OVERALL');

INSERT INTO rights VALUES (600, 1, 'SECURITY');
INSERT INTO rights VALUES (601, 600, 'SECURITY.VIEW');
INSERT INTO rights VALUES (602, 600, 'SECURITY.CHANGE');

INSERT INTO rights VALUES (700, 1, 'AGRI');
INSERT INTO rights VALUES (701, 700, 'AGRI.VIEW');
INSERT INTO rights VALUES (702, 700, 'AGRI.CHANGE');

INSERT INTO rights VALUES (800, 1, 'INFO');
INSERT INTO rights VALUES (801, 800, 'INFO.SMS');
INSERT INTO rights VALUES (802, 800, 'INFO.EMAIL');

INSERT INTO rights VALUES (900, 1, 'CA');
INSERT INTO rights VALUES (901, 900, 'CA.VIEW');
INSERT INTO rights VALUES (902, 900, 'CA.CHANGE');

INSERT INTO rights VALUES (1001, 1, 'JFB');
INSERT INTO rights VALUES (1002, 1, 'CZS');
INSERT INTO rights VALUES (1003, 1, 'AJS');
INSERT INTO rights VALUES (1004, 1, 'BKX');
INSERT INTO rights VALUES (1005, 1, 'JGS');
INSERT INTO rights VALUES (1006, 1, 'MYC');
INSERT INTO rights VALUES (1007, 1, 'NFGS');
INSERT INTO rights VALUES (1008, 1, 'ZHDW');


INSERT INTO roles VALUES (1, '系统管理员');

INSERT INTO role_right VALUES (1, 1);

INSERT INTO contacts (contactID, fullName, phone, cellPhone, emailAddress)
VALUES (1, '系统管理员', '', '', '');

INSERT INTO users (userID, contactID, userStatusID, username, password, createdDate)
  VALUE (1, 1, 101, 'admin', '$2a$10$SsfFdqAhwLLMlGmzn76y/u8RpSfxkudaT0VHca16UY/VQ8c66onYC', now());

INSERT INTO user_role VALUES (1, 1);