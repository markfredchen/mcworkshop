// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package examples;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.model.StylesTable;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * @author $Author$
 *
 */
public class ReportMain {

    public static void main(String[] args) throws Exception {
        OPCPackage pkg = OPCPackage.open("/Users/mchen1/MCWorkshop/Projects/DongJing/Docs/test.xlsx");
        XSSFReader reader = new XSSFReader(pkg);
        StylesTable styles = reader.getStylesTable();
        ReadOnlySharedStringsTable sharedStrings = new ReadOnlySharedStringsTable(pkg);
        ContentHandler handler = new XSSFSheetXMLHandler(styles, sharedStrings, new TaxXLSXHandler(), true);

        XMLReader parser = XMLReaderFactory.createXMLReader();
        parser.setContentHandler(handler);
        parser.parse(new InputSource(reader.getSheetsData().next()));
//        new TaxXLSHandler("/Users/mchen1/MCWorkshop/Projects/DongJing/Docs/tax.xls", -1).process();
    }

}
