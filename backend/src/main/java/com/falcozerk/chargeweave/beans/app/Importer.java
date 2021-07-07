package com.falcozerk.chargeweave.beans.app;

import lombok.Data;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
public class Importer {
    private static final Logger logger = LoggerFactory.getLogger(Importer.class);

    int cellPos = -1;
    ArrayList<String> headerList = new ArrayList<>();
    Sheet sheet;
    Iterator<Row> rowIterator;

    public void init( Workbook pWorkbook, int pTabPos ) {
        cellPos = -1;
        headerList.clear();
        sheet = pWorkbook.getSheetAt(pTabPos);
        rowIterator = sheet.rowIterator();

        importHeaders();
    }

    public boolean importRow( ArrayList<Cell> pCellList ) {
        pCellList.clear();
        if ( !rowIterator.hasNext() ) return false;

        Row nextRow = rowIterator.next();
        Iterator<Cell> cellIterator = nextRow.cellIterator();

        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            pCellList.add(cell);
        }

        cellPos = -1;

        return true;
    }

    public void importHeaders() {
        ArrayList<Cell> aCellList = new ArrayList<>();
        if ( !importRow( aCellList ) ) return;

        for( Cell cell : aCellList ) {
            if ( cell.getCellType() != CellType.STRING ) continue;
            headerList.add( cell.getStringCellValue() );
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

    public Long getNextLong(List<Cell> pRow) {
        Cell cell = getNextCell(pRow);
        try {
            if (cell.getCellType().equals(CellType.STRING)) return Long.parseLong(cell.getStringCellValue());
            if (cell.getCellType().equals(CellType.NUMERIC)) return (long) cell.getNumericCellValue();
        }
        catch( Exception e ) {
            System.err.println( "Bad integer value: " + cell.toString() );
        }
        return null;
    }

    public Instant getNextDate(List<Cell> pRow) {
        Cell cell = getNextCell(pRow);
        try {
            if ( cell.getCellType().equals( CellType.NUMERIC ) ) return cell.getDateCellValue().toInstant();

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
