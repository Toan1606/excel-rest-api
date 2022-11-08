package com.code.export.service;

import com.code.export.model.User;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

public interface IUserExcelExporter {
    public XSSFFont generateFont();

    public short getColorHeaderForCellStyle();

    public short getColorBodyForCellStyle();

    public BorderStyle getBorderForCellStyle();

    public FillPatternType getFillPatternType();

    public CellStyle getCommonCellStyle();

    public CellStyle generateCellStyleHeader();

    public void createCell(Row row, int cellIndex, Object value, CellStyle cellStyle);

    public void writeHeader();

    public void writeDataLines(List<User> userList);

    public void writeFooter();

    public void export(HttpServletResponse response, List<User> userList);
}
