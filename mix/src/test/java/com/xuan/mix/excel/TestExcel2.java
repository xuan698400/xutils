//package com.xuan.mix.excel;
//
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
//
//import com.xuan.mix.an.common.utils.StringUtils;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.DateUtil;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.junit.Test;
//
///**
// * @author xuan
// * @since 2020/1/17
// */
//public class TestExcel2 {
//
//    private final static String filePath = "/Users/xuan/Downloads/1.xlsx";
//
//    @Test
//    public void test() {
//        Workbook wb = readExcel(filePath);
//        Sheet sheet = wb.getSheetAt(11);
//
//        int rowNum = sheet.getPhysicalNumberOfRows();
//
//        for (int i = 1; i < rowNum; i++) {
//            Row row = sheet.getRow(i);
//
//            String finishTime = (String)getCellFormatValue(row.getCell(10));
//            finishTime = finishTime.replaceAll("/", "-");
//
//            String siteName = (String)getCellFormatValue(row.getCell(1));
//            String buildingName = (String)getCellFormatValue(row.getCell(2));
//
//            String cell3 = (String)getCellFormatValue(row.getCell(3));
//            if (StringUtils.isNotEmpty(cell3)) {
//                System.out.println(
//                    "INSERT INTO bf_room_withdrawal(room_name, finish_time, version, site_name, building_name) VALUES('"
//                        + cell3 + "', '"
//                        + finishTime + "', 'T', '" + siteName + "', '" + buildingName + "');");
//            }
//
//            String cell4 = (String)getCellFormatValue(row.getCell(4));
//            if (StringUtils.isNotEmpty(cell4)) {
//                System.out.println(
//                    "INSERT INTO bf_room_withdrawal(room_name, finish_time, version, site_name, building_name) VALUES('"
//                        + cell4 + "', '"
//                        + finishTime + "', 'T', '" + siteName + "', '" + buildingName + "');");
//            }
//
//            String cell5 = (String)getCellFormatValue(row.getCell(5));
//            if (StringUtils.isNotEmpty(cell5)) {
//                System.out.println(
//                    "INSERT INTO bf_room_withdrawal(room_name, finish_time, version, site_name, building_name) VALUES('"
//                        + cell5 + "', '"
//                        + finishTime + "', 'T', '" + siteName + "', '" + buildingName + "');");
//            }
//
//            String cell6 = (String)getCellFormatValue(row.getCell(6));
//            if (StringUtils.isNotEmpty(cell6)) {
//                System.out.println(
//                    "INSERT INTO bf_room_withdrawal(room_name, finish_time, version, site_name, building_name) VALUES('"
//                        + cell6 + "', '"
//                        + finishTime + "', 'T', '" + siteName + "', '" + buildingName + "');");
//            }
//
//            String cell7 = (String)getCellFormatValue(row.getCell(7));
//            if (StringUtils.isNotEmpty(cell7)) {
//                System.out.println(
//                    "INSERT INTO bf_room_withdrawal(room_name, finish_time, version, site_name, building_name) VALUES('"
//                        + cell7 + "', '"
//                        + finishTime + "', 'T', '" + siteName + "', '" + buildingName + "');");
//            }
//
//            String cell8 = (String)getCellFormatValue(row.getCell(8));
//            if (StringUtils.isNotEmpty(cell8)) {
//                System.out.println(
//                    "INSERT INTO bf_room_withdrawal(room_name, finish_time, version, site_name, building_name) VALUES('"
//                        + cell8 + "', '"
//                        + finishTime + "', 'T', '" + siteName + "', '" + buildingName + "');");
//            }
//        }
//    }
//
//    private static Workbook readExcel(String filePath) {
//        Workbook wb = null;
//        if (filePath == null) {
//            return null;
//        }
//        String extString = filePath.substring(filePath.lastIndexOf("."));
//        InputStream is = null;
//        try {
//            is = new FileInputStream(filePath);
//            if (".xls".equals(extString)) {
//                return wb = new HSSFWorkbook(is);
//            } else if (".xlsx".equals(extString)) {
//                return wb = new XSSFWorkbook(is);
//            } else {
//                return wb = null;
//            }
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return wb;
//    }
//
//    private static Object getCellFormatValue(Cell cell) {
//        Object cellValue = null;
//        if (cell != null) {
//            //判断cell类型
//            switch (cell.getCellType()) {
//                case Cell.CELL_TYPE_NUMERIC: {
//                    cellValue = String.valueOf(cell.getNumericCellValue());
//                    break;
//                }
//                case Cell.CELL_TYPE_FORMULA: {
//                    //判断cell是否为日期格式
//                    if (DateUtil.isCellDateFormatted(cell)) {
//                        //转换为日期格式YYYY-mm-dd
//                        cellValue = cell.getDateCellValue();
//                    } else {
//                        //数字
//                        cellValue = String.valueOf(cell.getNumericCellValue());
//                    }
//                    break;
//                }
//                case Cell.CELL_TYPE_STRING: {
//                    cellValue = cell.getRichStringCellValue().getString();
//                    break;
//                }
//                default:
//                    cellValue = "";
//            }
//        } else {
//            cellValue = "";
//        }
//        return cellValue;
//    }
//}
