package com.github.riese.rafael.mystreet.util;

import com.github.riese.rafael.mystreet.cache.DocumentCache;
import com.github.riese.rafael.mystreet.model.Auditable;
import com.github.riese.rafael.mystreet.model.Claim;
import com.github.riese.rafael.mystreet.model.User;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ExcelUtil <T extends Auditable> {
    private List<T> data;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public ExcelUtil(List<T> data) {
        this.data = data;
        workbook = new XSSFWorkbook();
    }

    private void writeHeader() {
        String className = data.get(0).getClass().getName();
        sheet = workbook.createSheet(className.substring(className.lastIndexOf(".") + 1, className.length()));
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        if (data.get(0) instanceof User) {
            createCell(row, 0, "ID", style);
            createCell(row, 1, "Nome", style);
            createCell(row, 2, "Email", style);
            createCell(row, 3, "CpfCnpj", style);
            createCell(row, 4, "Perfil", style);
        } else if (data.get(0) instanceof Claim) {
            createCell(row, 0, "ID", style);
            createCell(row, 1, "Título", style);
            createCell(row, 2, "Descrição", style);
            createCell(row, 3, "Estado", style);
            createCell(row, 4, "Cidade", style);
            createCell(row, 5, "Bairro", style);
            createCell(row, 6, "Usuário", style);
            createCell(row, 7, "Status", style);
        }
    }

    private void createCell(Row row, int columnCount, Object valueOfCell, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (valueOfCell instanceof Integer) {
            cell.setCellValue((Integer) valueOfCell);
        } else if (valueOfCell instanceof Long) {
            cell.setCellValue((Long) valueOfCell);
        } else if (valueOfCell instanceof String) {
            cell.setCellValue((String) valueOfCell);
        } else {
            cell.setCellValue((Boolean) valueOfCell);
        }
        cell.setCellStyle(style);
    }

    private void write() {
        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        for (var record: data) {
            Row row = sheet.createRow(rowCount++);
            if (record instanceof User) {
                User user = (User)record;
                createCell(row, 0, user.getId(), style);
                createCell(row, 1, user.getName(), style);
                createCell(row, 2, user.getEmail(), style);
                createCell(row, 3, user.getCpfCnpj(), style);
                createCell(row, 4, user.getProfile().getName(), style);
            } else if (record instanceof Claim) {
                Claim claim = (Claim)record;
                createCell(row, 0, claim.getId(), style);
                createCell(row, 1, claim.getTitle(), style);
                createCell(row, 2, claim.getDescription(), style);
                createCell(row, 3, claim.getState(), style);
                createCell(row, 4, claim.getCity(), style);
                createCell(row, 5, claim.getDistrict(), style);
                createCell(row, 6, claim.getUser().getName(), style);
                createCell(row, 7, claim.getStatus().getName(), style);
            }
        }
    }

    public String generateExcelFile() {
        writeHeader();
        write();

        String className = data.get(0).getClass().getName();
        String fileName = className.substring(className.lastIndexOf(".") + 1, className.length()) + ".xslx";
        DocumentCache.getInstance().addInCache(fileName, workbook);
        return fileName;
    }

    public void generateExcelFile(HttpServletResponse response) throws IOException {
        writeHeader();
        write();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

}
