// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package examples;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import org.apache.poi.hssf.eventusermodel.FormatTrackingHSSFListener;
import org.apache.poi.hssf.eventusermodel.HSSFEventFactory;
import org.apache.poi.hssf.eventusermodel.HSSFListener;
import org.apache.poi.hssf.eventusermodel.HSSFRequest;
import org.apache.poi.hssf.eventusermodel.MissingRecordAwareHSSFListener;
import org.apache.poi.hssf.eventusermodel.dummyrecord.LastCellOfRowDummyRecord;
import org.apache.poi.hssf.eventusermodel.dummyrecord.MissingCellDummyRecord;
import org.apache.poi.hssf.record.BOFRecord;
import org.apache.poi.hssf.record.BlankRecord;
import org.apache.poi.hssf.record.BoolErrRecord;
import org.apache.poi.hssf.record.BoundSheetRecord;
import org.apache.poi.hssf.record.LabelRecord;
import org.apache.poi.hssf.record.LabelSSTRecord;
import org.apache.poi.hssf.record.NumberRecord;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.SSTRecord;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 * @author $Author$
 *
 */
public class TaxXLSHandler implements HSSFListener {
    private int minColumns;
    private POIFSFileSystem fs;
    private PrintStream output;

    private int lastRowNumber;
    private int lastColumnNumber;


    // Records we pick up as we process
    private SSTRecord sstRecord;
    private FormatTrackingHSSFListener formatListener;
    
    /** So we known which sheet we're on */
    private int sheetIndex = -1;
    private BoundSheetRecord[] orderedBSRs;
    private ArrayList boundSheetRecords = new ArrayList();

    // For handling formulas with string results
    private int nextRow;
    private int nextColumn;
    private boolean outputNextStringRecord;

    /**
     * Creates a new XLS -> CSV converter
     * @param fs The POIFSFileSystem to process
     * @param output The PrintStream to output the CSV to
     * @param minColumns The minimum number of columns to output, or -1 for no minimum
     */
    public TaxXLSHandler(POIFSFileSystem fs, PrintStream output, int minColumns) {
        this.fs = fs;
        this.output = output;
        this.minColumns = minColumns;
    }

    /**
     * Creates a new XLS -> CSV converter
     * @param filename The file to process
     * @param minColumns The minimum number of columns to output, or -1 for no minimum
     * @throws IOException
     * @throws FileNotFoundException
     */
    public TaxXLSHandler(String filename, int minColumns) throws IOException, FileNotFoundException {
        this(
                new POIFSFileSystem(new FileInputStream(filename)),
                System.out, minColumns
        );
    }

    /**
     * Initiates the processing of the XLS file to CSV
     */
    public void process() throws IOException {
        MissingRecordAwareHSSFListener listener = new MissingRecordAwareHSSFListener(this);
        formatListener = new FormatTrackingHSSFListener(listener);

        HSSFEventFactory factory = new HSSFEventFactory();
        HSSFRequest request = new HSSFRequest();

        factory.processWorkbookEvents(request, fs);
    }

    /**
     * Main HSSFListener method, processes events, and outputs the
     *  CSV as the file is processed.
     */
    public void processRecord(Record record) {
        if(!(sheetIndex == -1 || sheetIndex == 0)) {
            return;
        }
        int thisRow = -1;
        int thisColumn = -1;
        String thisStr = null;

        switch (record.getSid())
        {
        case BOFRecord.sid:
            BOFRecord br = (BOFRecord)record;
            if(br.getType() == BOFRecord.TYPE_WORKSHEET) {
                sheetIndex++;
            }
            break;

        case SSTRecord.sid:
            sstRecord = (SSTRecord) record;
            break;

        case BlankRecord.sid:
            BlankRecord brec = (BlankRecord) record;

            thisRow = brec.getRow();
            thisColumn = brec.getColumn();
            thisStr = "";
            break;
        case BoolErrRecord.sid:
            BoolErrRecord berec = (BoolErrRecord) record;

            thisRow = berec.getRow();
            thisColumn = berec.getColumn();
            thisStr = "";
            break;

        case LabelRecord.sid:
            LabelRecord lrec = (LabelRecord) record;

            thisRow = lrec.getRow();
            thisColumn = lrec.getColumn();
            thisStr = lrec.getValue();
            break;
        case LabelSSTRecord.sid:
            LabelSSTRecord lsrec = (LabelSSTRecord) record;

            thisRow = lsrec.getRow();
            thisColumn = lsrec.getColumn();
            if(sstRecord == null) {
                thisStr = "";
            } else {
                thisStr = sstRecord.getString(lsrec.getSSTIndex()).toString();
            }
            break;
        case NumberRecord.sid:
            NumberRecord numrec = (NumberRecord) record;

            thisRow = numrec.getRow();
            thisColumn = numrec.getColumn();

            // Format
            thisStr = formatListener.formatNumberDateCell(numrec);
            break;
        default:
            break;
        }

        // Handle new row
        if(thisRow != -1 && thisRow != lastRowNumber) {
            lastColumnNumber = -1;
        }

        // Handle missing column
        if(record instanceof MissingCellDummyRecord) {
            MissingCellDummyRecord mc = (MissingCellDummyRecord)record;
            thisRow = mc.getRow();
            thisColumn = mc.getColumn();
            thisStr = "";
        }
        
        // If we got something to print out, do so
        if(thisRow > 0) {
            if(thisStr != null) {
                if(thisColumn > 0) {
                    System.out.print(',');
                }
                System.out.print(thisStr);
            }
        }

        // Update column and row count
        if(thisRow > -1)
            lastRowNumber = thisRow;
        if(thisColumn > -1)
            lastColumnNumber = thisColumn;

        // Handle end of row
        if(record instanceof LastCellOfRowDummyRecord) {
            // Print out any missing commas if needed
            if(minColumns > 0) {
                // Columns are 0 based
                if(lastColumnNumber == -1) { lastColumnNumber = 0; }
                for(int i=lastColumnNumber; i<(minColumns); i++) {
                    System.out.print(',');
                }
            }

            // We're onto a new row
            lastColumnNumber = -1;
            // End the row
            System.out.println();
        }
    }

}
