package com.xuan.mix.excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.xuan.moho.base.utils.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

/**
 * @author xuan
 * @since 2020/1/17
 */
public class TestExcel3 {

    private final static String filePath = "/Users/xuan/Downloads/3.xlsx";

    @Test
    public void test() {
        Workbook wb = readExcel(filePath);
        Sheet sheet = wb.getSheetAt(11);

        int rowNum = sheet.getPhysicalNumberOfRows();

        for (int i = 1; i < rowNum; i++) {
            Row row = sheet.getRow(i);
            String finishTime = (String)getCellFormatValue(row.getCell(10));
            finishTime = DateUtils.date2String(DateUtils.string2Date(finishTime, "yyyy/M/dd"), "yyyy-MM-dd");
            String chargeUnitName = row.getCell(9).getRichStringCellValue().toString();
            Object amount = getCellFormatValue(row.getCell(25));
            System.out.println(String.format("%s,%s,%s", finishTime.replaceAll("/", "-"), chargeUnitName, amount));
        }
    }

    private static Workbook readExcel(String filePath) {
        Workbook wb = null;
        if (filePath == null) {
            return null;
        }
        String extString = filePath.substring(filePath.lastIndexOf("."));
        InputStream is = null;
        try {
            is = new FileInputStream(filePath);
            if (".xls".equals(extString)) {
                return wb = new HSSFWorkbook(is);
            } else if (".xlsx".equals(extString)) {
                return wb = new XSSFWorkbook(is);
            } else {
                return wb = null;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }

    private static Object getCellFormatValue(Cell cell) {
        Object cellValue = null;
        if (cell != null) {
            //判断cell类型
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC: {
                    cellValue = String.valueOf(cell.getNumericCellValue());
                    break;
                }
                case Cell.CELL_TYPE_FORMULA: {
                    //判断cell是否为日期格式
                    if (DateUtil.isCellDateFormatted(cell)) {
                        //转换为日期格式YYYY-mm-dd
                        cellValue = cell.getDateCellValue();
                    } else {
                        //数字
                        cellValue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                case Cell.CELL_TYPE_STRING: {
                    cellValue = cell.getRichStringCellValue().getString();
                    break;
                }
                default:
                    cellValue = "";
            }
        } else {
            cellValue = "";
        }
        return cellValue;
    }
}
