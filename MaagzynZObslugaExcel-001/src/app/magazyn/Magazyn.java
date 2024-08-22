package app.magazyn;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Magazyn {
	  private String filePath = "magazyn.xlsx";

	    // Dodawanie produktu
	    public void dodajProdukt(Produkt produkt) throws IOException {
	        FileInputStream file = new FileInputStream(filePath);
	        Workbook workbook = new XSSFWorkbook(file);
	        Sheet sheet = workbook.getSheetAt(0);

	        int rowCount = sheet.getLastRowNum();
	        Row row = sheet.createRow(++rowCount);
	        row.createCell(0).setCellValue(produkt.getNazwa());
	        row.createCell(1).setCellValue(produkt.getIlosc());
	        row.createCell(2).setCellValue(produkt.getKategoria());

	        FileOutputStream outputStream = new FileOutputStream(filePath);
	        workbook.write(outputStream);
	        workbook.close();
	        outputStream.close();
	    }

	    // Wydanie produktu
	    public void wydajProdukt(String nazwa, int ilosc) throws IOException {
	        FileInputStream file = new FileInputStream(filePath);
	        Workbook workbook = new XSSFWorkbook(file);
	        Sheet sheet = workbook.getSheetAt(0);

	        for (Row row : sheet) {
	            if (row.getCell(0).getStringCellValue().equals(nazwa)) {
	                int currentQty = (int) row.getCell(1).getNumericCellValue();
	                row.getCell(1).setCellValue(currentQty - ilosc);
	                break;
	            }
	        }

	        FileOutputStream outputStream = new FileOutputStream(filePath);
	        workbook.write(outputStream);
	        workbook.close();
	        outputStream.close();
	    }

	    // Usunięcie produktu
	    public void usunProdukt(String nazwa) throws IOException {
	        FileInputStream file = new FileInputStream(filePath);
	        Workbook workbook = new XSSFWorkbook(file);
	        Sheet sheet = workbook.getSheetAt(0);
	        List<Row> rowsToRemove = new ArrayList<>();

	        for (Row row : sheet) {
	            if (row.getCell(0).getStringCellValue().equals(nazwa)) {
	                rowsToRemove.add(row);
	            }
	        }

	        for (Row row : rowsToRemove) {
	            int rowIndex = row.getRowNum();
	            sheet.removeRow(row);
	        }

	        FileOutputStream outputStream = new FileOutputStream(filePath);
	        workbook.write(outputStream);
	        workbook.close();
	        outputStream.close();
	    }

	    // Generowanie raportów (wyświetlenie wszystkich produktów)
	    public void generujRaport() throws IOException {
	        FileInputStream file = new FileInputStream(filePath);
	        Workbook workbook = new XSSFWorkbook(file);
	        Sheet sheet = workbook.getSheetAt(0);

	        for (Row row : sheet) {
	            System.out.println("Produkt: " + row.getCell(0).getStringCellValue() + 
	                               ", Ilość: " + (int)row.getCell(1).getNumericCellValue() + 
	                               ", Kategoria: " + row.getCell(2).getStringCellValue());
	        }

	        workbook.close();
	    }
}
