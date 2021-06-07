package com.falcozerk.chargeweave.integrations.google;

import com.falcozerk.chargeweave.beans.charger.ChargerService;
import com.falcozerk.chargeweave.beans.common.CwService;
import com.falcozerk.chargeweave.beans.user.UserService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelImporter {
    @Autowired ChargerService chargerService;
    @Autowired UserService userService;

    public static String SPREADSHEET_SPEC = "/SuperchargersVisited.xlsx";
    public static int SUPERCHARGERS_TAB_POS = 2;
    public static int COMPETITORS_TAB_POS = 2;

    int cellPos = -1;
    public void reset() {
        cellPos = -1;
    }

    public void importXslx() throws IOException {
        InputStream in = ExcelImporter.class.getResourceAsStream(SPREADSHEET_SPEC);
        Workbook workbook = new XSSFWorkbook(in);

        try {
            chargerService.importFrom(this, workbook );
            userService.importFrom(this, workbook );
        }
        finally{
            workbook.close();
            in.close();
        }
    }

    public void importSheet( ArrayList pList, Workbook workbook, CwService pService, int pTabPos ) {
        Sheet sheet = workbook.getSheetAt(pTabPos);
        Iterator<Row> iterator = sheet.iterator();

        boolean aIsFirst = true;
        while (iterator.hasNext()) {

            Row nextRow = iterator.next();
            if (aIsFirst) {
                aIsFirst = false;
                continue;
            }

            Iterator<Cell> cellIterator = nextRow.cellIterator();
            ArrayList<Cell> aCellList = new ArrayList<>();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                aCellList.add(cell);
            }

            pList.add( pService.createFrom(this, aCellList) );
        }
    }

    public Cell getNextCell(List<Cell> pRow) {
        cellPos++;
        return pRow.get(cellPos);
    }

    public String getNextString(List<Cell> pRow) {
        Cell cell = getNextCell(pRow);
        try {
            if ( cell.getCellType().equals( CellType.STRING ) ) return cell.getStringCellValue();
            if ( cell.getCellType().equals( CellType.NUMERIC ) ) return "" + cell.getNumericCellValue();
        }
        catch( Exception e ) {
            System.err.println( "Bad string value: " + cell.toString() );
        }
        return null;
    }

    public Double getNextDouble(List<Cell> pRow) {
        Cell cell = getNextCell(pRow);
        try {
            if ( cell.getCellType().equals( CellType.STRING ) ) return Double.parseDouble(cell.getStringCellValue());
            if ( cell.getCellType().equals( CellType.NUMERIC ) ) return cell.getNumericCellValue();
        }
        catch( Exception e ) {
            System.err.println( "Bad double value: " + cell.toString() );
        }
        return null;
    }

    public Integer getNextInteger(List<Cell> pRow) {
        Cell cell = getNextCell(pRow);
        try {
            if (cell.getCellType().equals(CellType.STRING)) return Integer.parseInt(cell.getStringCellValue());
            if (cell.getCellType().equals(CellType.NUMERIC)) return (int) cell.getNumericCellValue();
        }
        catch( Exception e ) {
            System.err.println( "Bad integer value: " + cell.toString() );
        }
        return null;
    }

    public Instant getNextDate(List<Cell> pRow) {
        Cell cell = getNextCell(pRow);
        try {
            if ( cell.getCellType().equals( CellType.NUMERIC ) ) cell.getDateCellValue().toInstant();

            if ( cell.getCellType().equals( CellType.STRING ) )
            {
                String aValue = cell.getStringCellValue();
                return Instant.parse(aValue);
            }
        }
        catch( Exception e ) {
            System.err.println( "Bad date value: " + cell.toString() );
        }
        return null;
    }
}
