// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.common.web.report;

import com.mcworkshop.common.web.util.WicketMessageUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author $Author$
 */
abstract public class BaseReport<T extends Object> {

    protected T data;
    protected Workbook workbook = new XSSFWorkbook();
    protected CellStyle titleStyle = new CellStyleWrapper(workbook).alignLeft()
            .alignMiddle().setBorder((short) 1).done();
    protected CellStyle contentStyle = new CellStyleWrapper(workbook)
            .alignCenter().alignMiddle().done();
    protected CellStyle contentTextWrapperStyle = new CellStyleWrapper(workbook)
            .alignCenter().alignMiddle().setBorder((short) 1).done();
    protected CellStyle intStyle = new CellStyleWrapper(workbook).alignRight()
            .alignMiddle().setDataFormat("###0").setBorder((short) 1).done();
    protected CellStyle dataStyle = new CellStyleWrapper(workbook).alignRight()
            .alignMiddle().setDataFormat("###0.00").setBorder((short) 1).done();
    protected CellStyle percentageContentStyle = new CellStyleWrapper(workbook)
            .alignCenter().alignMiddle().setBorder((short) 1)
            .setDataFormat("0.00%").done();
    private DecimalFormat df = new DecimalFormat("######0.00");
    private SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");

    public BaseReport(T data) {
        this.data = data;
    }

    abstract protected void constructReport();

    /**
     * method for download
     */
    public byte[] generateReportByByteArray() throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        workbook.write(os);
        constructReport();
        return os.toByteArray();
    }

    public File generateReportByFile(boolean generateReport)
            throws FileNotFoundException, IOException {
        File file = new File(getReportFilePath());
        if (!generateReport) {
            return file;
        }
        constructReport();
        workbook.write(new FileOutputStream(getReportFilePath()));
        return file;
    }

    abstract protected String getReportFilePath();

    protected String formatDate(SimpleDateFormat f, Date date) {
        if (date == null) {
            return "";
        } else {
            return f.format(date);
        }
    }

    protected String getMessage(String msgKey) {
        return WicketMessageUtil.getResourceString(this.getClass()
                .getSimpleName() + "." + msgKey);
    }

    protected String getMessage(String msgKey, Object... parameters) {
        return WicketMessageUtil.getResourceString(this.getClass()
                .getSimpleName() + "." + msgKey, parameters);
    }

    protected String getDoubleForDisplay(Double d) {

        if (d == null) {
            return "";
        } else if (d == 0) {
            return "";
        } else {
            return df.format(d);
        }
    }

    protected void createContentCell(CellStyle contentStyle, Row row,
                                     int cellIndex, String text) {
        if (text == null) {
            text = "";
        }
        Cell cell = row.createCell(cellIndex);
        cell.setCellValue(text);
        cell.setCellStyle(contentStyle);
    }

    protected void createContentCell(CellStyle contentStyle, Row row,
                                     int cellIndex, Date text) {
        Cell cell = row.createCell(cellIndex);
        cell.setCellValue(formatDate(f, text));
        cell.setCellStyle(contentStyle);
    }

    protected void createContentCell(CellStyle contentStyle, Row row,
                                     int cellIndex, long text) {
        Cell cell = row.createCell(cellIndex);
        cell.setCellValue(text);
        cell.setCellStyle(contentStyle);
    }

    protected void createContentCell(CellStyle contentStyle, Row row,
                                     int cellIndex, Integer data) {
        Cell cell = row.createCell(cellIndex);
        if (data == null) {
            cell.setCellValue(0);
        } else {
            cell.setCellValue(data);
        }
        cell.setCellStyle(contentStyle);
    }

    protected void createContentCell(CellStyle contentStyle, Row row,
                                     int cellIndex, Long data) {
        Cell cell = row.createCell(cellIndex);
        if (data == null) {
            cell.setCellValue(0);
        } else {
            cell.setCellValue(data);
        }
        cell.setCellStyle(contentStyle);
    }

    protected void createContentCell(CellStyle contentStyle, Row row,
                                     int cellIndex, Double data) {
        Cell cell = row.createCell(cellIndex);
        if (data != null) {
            cell.setCellValue(data);
        } else {
            cell.setCellValue(0D);
        }
        cell.setCellStyle(contentStyle);
    }

    protected void createFormulaCell(CellStyle contentStyle, Row row,
                                     int cellIndex, String formula) {
        Cell cell = row.createCell(cellIndex);
        cell.setCellStyle(contentStyle);
        cell.setCellType(XSSFCell.CELL_TYPE_FORMULA);
        cell.setCellFormula(formula);
    }

    protected void createMergedContentCell(CellStyle contentStyle, Sheet sheet,
                                           Row row, String region, int cellIndex, String text) {
        createMergedContentCell(contentStyle, sheet, row, region, cellIndex,
                text, true);
    }

    protected void createMergedContentCell(CellStyle contentStyle, Sheet sheet,
                                           Row row, String region, int cellIndex, String text,
                                           boolean hasBorder) {
        Cell cell = row.createCell(cellIndex);
        cell.setCellValue(text);
        cell.setCellStyle(contentStyle);
        sheet.addMergedRegion(CellRangeAddress.valueOf(region));
        if (hasBorder) {
            ReportUtil.setBorder(1, CellRangeAddress.valueOf(region), sheet,
                    workbook);
        }
    }

    protected void createMergedContentCell(CellStyle contentStyle, Sheet sheet,
                                           Row row, String region, int cellIndex, int data) {
        Cell cell = row.createCell(cellIndex);
        cell.setCellValue(data);
        cell.setCellStyle(contentStyle);
        sheet.addMergedRegion(CellRangeAddress.valueOf(region));
        ReportUtil.setBorder(1, CellRangeAddress.valueOf(region), sheet,
                workbook);
    }
}
