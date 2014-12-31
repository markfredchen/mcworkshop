// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.common.web.report;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * @author $Author$
 * 
 */
public class CellStyleWrapper {

    private CellStyle cs;
    private Font      font;
    private Workbook wb;

    public CellStyleWrapper(Workbook wb) {
        this.wb = wb;
        this.cs = wb.createCellStyle();
        this.font = wb.createFont();
        // Default font
        font.setFontName("黑体");
        font.setFontHeightInPoints((short) 9);
        cs.setFont(font);
        
        
    }

    public CellStyle done() {
        return cs;
    }

    public CellStyleWrapper bold() {
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        return this;
    }

    public CellStyleWrapper fontSize(short height) {
        font.setFontHeightInPoints(height);
        return this;
    }

    public CellStyleWrapper alignCenter() {
        cs.setAlignment(CellStyle.ALIGN_CENTER);
        return this;
    }

    public CellStyleWrapper alignLeft() {
        cs.setAlignment(CellStyle.ALIGN_LEFT);
        return this;
    }

    public CellStyleWrapper alignRight() {
        cs.setAlignment(CellStyle.ALIGN_RIGHT);
        return this;
    }

    public CellStyleWrapper alignTop() {
        cs.setVerticalAlignment(CellStyle.VERTICAL_TOP);
        return this;
    }

    public CellStyleWrapper alignMiddle() {
        cs.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        return this;
    }

    public CellStyleWrapper alignBottom() {
        cs.setVerticalAlignment(CellStyle.VERTICAL_BOTTOM);
        return this;
    }

    public CellStyleWrapper setBorder(short border) {
        cs.setBorderTop(border);
        cs.setBorderRight(border);
        cs.setBorderBottom(border);
        cs.setBorderLeft(border);
        return this;
    }
    
    public CellStyleWrapper setDataFormat(String format) {
        cs.setDataFormat(wb.createDataFormat().getFormat(format));
        return this;
    }

    public CellStyleWrapper setBorder(short border, boolean top, boolean right,
            boolean bottom, boolean left) {
        if (top) {
            cs.setBorderTop(border);
        }
        if (right) {
            cs.setBorderRight(border);
        }
        if (bottom) {
            cs.setBorderBottom(border);
        }
        if (left) {
            cs.setBorderLeft(border);
        }
        return this;
    }

}
