// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package examples;

import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler.SheetContentsHandler;

/**
 * @author $Author$
 *
 */
public class TaxXLSXHandler implements SheetContentsHandler{

    @Override
    public void startRow(int rowNum) {
        System.out.println(rowNum);
    }

    @Override
    public void endRow() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void cell(String cellReference, String formattedValue) {
        System.out.println(cellReference + formattedValue);
        
    }

    @Override
    public void headerFooter(String text, boolean isHeader, String tagName) {
        System.out.println(text + isHeader + tagName);
    }
    
}
