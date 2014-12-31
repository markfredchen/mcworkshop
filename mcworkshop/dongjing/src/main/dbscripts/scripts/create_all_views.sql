CREATE VIEW checkedTaxData AS 
SELECT * FROM companySaleTaxData b WHERE NOT EXISTS (
SELECT * FROM companySaleTaxData a WHERE
(a.vat IS NULL OR a.vat = 0) AND
(a.operateTax IS NULL OR a.operateTax = 0) AND
(a.expenseTax IS NULL OR a.expenseTax = 0) AND
(a.domesticIncomingTax IS NULL OR a.domesticIncomingTax = 0) AND
(a.foreignIncomingTax IS NULL OR a.foreignIncomingTax = 0) AND
(a.housingTax IS NULL OR a.housingTax = 0) AND
(a.stampTax IS NULL OR a.stampTax = 0) AND
(a.trafficTax IS NULL OR a.trafficTax = 0) AND
(a.landVAT IS NULL OR a.landVAT = 0) AND
(a.landUseTax IS NULL OR a.landUseTax = 0) AND
(a.personalIncomingTax IS NULL OR a.personalIncomingTax = 0) AND
(a.constructionTax IS NULL OR a.constructionTax = 0) AND
(a.veichleTax IS NULL OR a.veichleTax = 0) AND
(a.riverTax IS NULL OR a.riverTax = 0) AND
(a.educationTax IS NULL OR a.educationTax = 0) AND
(a.cultureTax IS NULL OR a.cultureTax = 0) AND
(a.otherTax IS NULL OR a.otherTax = 0) AND a.year = b.year AND a.month = b.month AND a.companyID = b.companyID)  

