package com.company.helper;

import com.company.entity.Developer;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.crypt.EncryptionMode;
import org.apache.poi.poifs.crypt.Encryptor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import java.util.List;

public class ExcelHelper {

    //check that file is excel or not
    public static boolean checkExelFileOrNot(MultipartFile file){

        String contentType = file.getContentType();

        if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")){
            return true;
        }else {
            return false;
        }

    }

    //convert excel to List of Developer
    public static List<Developer> convertExcelToDeveloper(InputStream is) {
        List<Developer> list = new ArrayList<>();
        try (XSSFWorkbook workbook = new XSSFWorkbook(is)) {
            XSSFSheet sheet = workbook.getSheet("developer");
            if (sheet == null) sheet = workbook.getSheetAt(0);

            int rowNumber = 0;
            for (Row row : sheet) {
                if (rowNumber == 0) { // skip header
                    rowNumber++;
                    continue;
                }
                Developer developer = new Developer();
                developer.setFName(row.getCell(0).getStringCellValue());
                developer.setLName(row.getCell(1).getStringCellValue());
                developer.setAge((int) row.getCell(2).getNumericCellValue());
                developer.setCity(row.getCell(3).getStringCellValue());
                developer.setGender(row.getCell(4).getStringCellValue());
                developer.setSalary((long) row.getCell(5).getNumericCellValue());
                developer.setYearOfBirth((int) row.getCell(6).getNumericCellValue());

                list.add(developer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    // ✅ Export DB data to Excel file
    public static void dbDevelopersToExcel(List<Developer> developers) {
        try (Workbook workbook=new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Developers");

            // Header row
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("ID");
            header.createCell(1).setCellValue("First Name");
            header.createCell(2).setCellValue("Last Name");
            header.createCell(3).setCellValue("Age");
            header.createCell(4).setCellValue("City");
            header.createCell(5).setCellValue("Gender");
            header.createCell(6).setCellValue("DeveloperId");
            header.createCell(7).setCellValue("Salary");

            // Data rows
            int rowIndex = 1;
            for (Developer dev : developers) {
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(dev.getId());
                row.createCell(1).setCellValue(dev.getFName());
                row.createCell(2).setCellValue(dev.getLName());
                row.createCell(3).setCellValue(dev.getAge());
                row.createCell(4).setCellValue(dev.getCity());
                row.createCell(5).setCellValue(dev.getGender());
                row.createCell(6).setCellValue(dev.getDeveloperId());
                row.createCell(7).setCellValue(dev.getSalary());
            }

            // Auto-size columns AFTER adding data
            for (int col = 0; col <= 6; col++) {
                sheet.autoSizeColumn(col);
            }

            // ✅ Protect the sheet (editable restriction)
           //sheet.protectSheet("dev123"); // Password for protection

            //write workbook to memory
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            workbook.write(baos);

            //for fuly protected file
            POIFSFileSystem fs = new POIFSFileSystem();
            EncryptionInfo info = new EncryptionInfo(EncryptionMode.standard);
            Encryptor enc = info.getEncryptor();
            enc.confirmPassword("dev123");  // password to open file

            try (OutputStream os = enc.getDataStream(fs)) {
                os.write(baos.toByteArray());
            }


            // Save file
            String fileName = "Developers_" + System.currentTimeMillis() + ".xlsx";

            try (FileOutputStream fos = new FileOutputStream(fileName)) {
                fs.writeFilesystem(fos);
            }

            System.out.println("✅ Data exported to " + fileName);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
