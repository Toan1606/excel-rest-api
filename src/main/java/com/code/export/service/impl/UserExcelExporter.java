package com.code.export.service.impl;

import com.code.export.model.User;
import com.code.export.service.IUserExcelExporter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
public class UserExcelExporter implements IUserExcelExporter {

    private XSSFWorkbook workbook;

    private XSSFSheet sheet;

    private static int rowCount = 0;

    public UserExcelExporter() {
        workbook = new XSSFWorkbook();

        // create sheet with safe name
        String safeName = WorkbookUtil.createSafeSheetName("Users");
        sheet = workbook.createSheet(safeName);
    }

    @Override
    public XSSFFont generateFont() {
        XSSFFont font = workbook.createFont();
        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 14);
        font.setColor(IndexedColors.WHITE.getIndex());

        return font;
    }

    @Override
    public short getColorHeaderForCellStyle() {
        return IndexedColors.BLUE.getIndex();
    }

    @Override
    public short getColorBodyForCellStyle() {
        return IndexedColors.WHITE.getIndex();
    }

    @Override
    public BorderStyle getBorderForCellStyle() {
        return BorderStyle.THIN;
    }

    @Override
    public FillPatternType getFillPatternType() {
        return FillPatternType.SOLID_FOREGROUND;
    }

    @Override
    public CellStyle getCommonCellStyle() {
        // 1. get cell style
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        // 2. get properties
        //  2.1 get font
        Font font = generateFont();

        //  2.2 get border
        FillPatternType fillPatternType = getFillPatternType();
        //  2.3 get fill pattern type
        BorderStyle borderStyle = getBorderForCellStyle();

        // 3. set properties
        //  3.1 set font
        cellStyle.setFont(font);

        //  3.2 set pattern
        cellStyle.setFillPattern(fillPatternType);
        //  3.3 set border bottom
        cellStyle.setBorderBottom(borderStyle);
        return cellStyle;
    }

    @Override
    public CellStyle generateCellStyleHeader() {
        CellStyle cellStyle = getCommonCellStyle();
        short color = getColorHeaderForCellStyle();
        cellStyle.setFillBackgroundColor(color);

        return cellStyle;
    }

    @Override
    public void createCell(Row row, int cellIndex, Object value, CellStyle cellStyle) {
        Cell cell = row.createCell(cellIndex);
        // set value
        if (value instanceof Long) {
            cell.setCellValue((Long) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(cellStyle);
    }

    @Override
    public void writeHeader() {

        XSSFRow headerRow = sheet.createRow(rowCount);
        CellStyle cellStyle = generateCellStyleHeader();
        
        createCell(headerRow, 0, "ID", cellStyle);
        createCell(headerRow, 1, "E-mail", cellStyle);
        createCell(headerRow, 2, "First Name", cellStyle);
        createCell(headerRow, 3, "Middle Name", cellStyle);
        createCell(headerRow, 4, "Last Name", cellStyle);
        createCell(headerRow, 5, "Role", cellStyle);
        createCell(headerRow, 6, "Enable", cellStyle);

        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);
        sheet.autoSizeColumn(4);
        sheet.autoSizeColumn(5);
        sheet.autoSizeColumn(6);
    }

    @Override
    public void writeDataLines(List<User> userList) {
        int rowCount = 1;

        // 1. Cell style
        CellStyle cellStyle = workbook.createCellStyle();
        XSSFFont font = generateFont();
        cellStyle.setFont(font);

        // 2. write on cell
        for (User user : userList) {
            Row row = sheet.createRow(rowCount);
            createCell(row, rowCount++, user.getId(), cellStyle);
            createCell(row, rowCount++, user.getEmail(), cellStyle);
            createCell(row, rowCount++, user.getFirstName(), cellStyle);
            createCell(row, rowCount++, user.getMiddleName(), cellStyle);
            createCell(row, rowCount++, user.getLastName(), cellStyle);
            createCell(row, rowCount++, user.getRoles().toString(), cellStyle);
            createCell(row, rowCount++, user.isEnabled(), cellStyle);
        }
    }

    @Override
    public void writeFooter() {

    }

    @Transactional
    @Override
    public void export(HttpServletResponse response, List<User> userList) {
        writeHeader();
        writeDataLines(userList);
        writeFooter();

        try (ServletOutputStream outputStream = response.getOutputStream();){
            workbook.write(outputStream);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
