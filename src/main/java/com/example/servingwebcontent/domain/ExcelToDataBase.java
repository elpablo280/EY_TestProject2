package com.example.servingwebcontent.domain;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Iterator;

public class ExcelToDataBase {

    private static final String url = "jdbc:mysql://localhost:3306/world?useSSL=false";
    private static final String user = "root";
    private static final String password = "1234";

    private static Connection connection;
    private static Statement stmt;

    public static void Import(String excelFilePath)
    {
//        String excelFilePath = "Trening.xlsx";

        try {
            FileInputStream inputStream = new FileInputStream(excelFilePath);

            Workbook workbook = new XSSFWorkbook(inputStream);

            Sheet firstSheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = firstSheet.iterator();

            connection = DriverManager.getConnection(url, user, password);
            connection.setAutoCommit(false);

            stmt = connection.createStatement();

            String query = "drop table if exists `world`.`message`;";
            stmt.executeUpdate(query);
            String query0 = "CREATE TABLE `message` (`id` int(40) NOT NULL,`bsch` varchar(150) NOT NULL,`vhodact` varchar(40) NOT NULL,`vhodpas` varchar(40) NOT NULL,`obordeb` varchar(40) NOT NULL,`oborkred` varchar(40) NOT NULL,`iskhact` varchar(40) NOT NULL,`iskhpas` varchar(40) NOT NULL, PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=cp1251;";
            stmt.executeUpdate(query0);
            String query1 = "INSERT INTO message(id, bsch, vhodact, vhodpas, obordeb, oborkred, iskhact, iskhpas) VALUES (?,?,?,?,?,?,?,?)";

            int id = 0;

            while (rowIterator.hasNext()) {
                Row nextRow = rowIterator.next();
                if (id == firstSheet.getLastRowNum())
                    break;
                Iterator<Cell> cellIterator = nextRow.cellIterator();
                PreparedStatement statement = connection.prepareStatement(query1);

                id++;
                statement.setInt(1, id);

                while (cellIterator.hasNext()) {
                    Cell nextCell = cellIterator.next();
                    int columnIndex = nextCell.getColumnIndex();

                    switch (columnIndex) {
                        case 0:
                            String bsch;
                            if (nextCell.getCellType() == CellType.STRING) {
                                bsch = nextCell.getStringCellValue();
                                statement.setString(2, bsch);
                            } else if (nextCell.getCellType() == CellType.NUMERIC) {
                                bsch = String.valueOf(nextCell.getNumericCellValue());
                                statement.setString(2, bsch);
                            } else
                                statement.setString(2, " ");
                        case 1:
                            String vhodact;
                            if (nextCell.getCellType() == CellType.STRING) {
                                vhodact = nextCell.getStringCellValue();
                                statement.setString(3, vhodact);
                            } else if (nextCell.getCellType() == CellType.NUMERIC) {
                                vhodact = String.valueOf(nextCell.getNumericCellValue());
                                statement.setString(3, vhodact);
                            } else
                                statement.setString(3, " ");
                        case 2:
                            String vhodpas;
                            if (nextCell.getCellType() == CellType.STRING) {
                                vhodpas = nextCell.getStringCellValue();
                                statement.setString(4, vhodpas);
                            } else if (nextCell.getCellType() == CellType.NUMERIC) {
                                vhodpas = String.valueOf(nextCell.getNumericCellValue());
                                statement.setString(4, vhodpas);
                            } else
                                statement.setString(4, " ");
                        case 3:
                            String obordeb;
                            if (nextCell.getCellType() == CellType.STRING) {
                                obordeb = nextCell.getStringCellValue();
                                statement.setString(5, obordeb);
                            } else if (nextCell.getCellType() == CellType.NUMERIC) {
                                obordeb = String.valueOf(nextCell.getNumericCellValue());
                                statement.setString(5, obordeb);
                            } else
                                statement.setString(5, " ");
                        case 4:
                            String oborkred;
                            if (nextCell.getCellType() == CellType.STRING) {
                                oborkred = nextCell.getStringCellValue();
                                statement.setString(6, oborkred);
                            } else if (nextCell.getCellType() == CellType.NUMERIC) {
                                oborkred = String.valueOf(nextCell.getNumericCellValue());
                                statement.setString(6, oborkred);
                            } else
                                statement.setString(6, " ");
                        case 5:
                            String iskhact;
                            if (nextCell.getCellType() == CellType.STRING) {
                                iskhact = nextCell.getStringCellValue();
                                statement.setString(7, iskhact);
                            } else if (nextCell.getCellType() == CellType.NUMERIC) {
                                iskhact = String.valueOf(nextCell.getNumericCellValue());
                                statement.setString(7, iskhact);
                            } else
                                statement.setString(7, " ");
                        case 6:
                            String iskhpas;
                            if (nextCell.getCellType() == CellType.STRING) {
                                iskhpas = nextCell.getStringCellValue();
                                statement.setString(8, iskhpas);
                            } else if (nextCell.getCellType() == CellType.NUMERIC) {
                                iskhpas = String.valueOf(nextCell.getNumericCellValue());
                                statement.setString(8, iskhpas);
                            } else
                                statement.setString(8, " ");
                    }
                }
                statement.execute();
                connection.commit();
            }

            workbook.close();
            connection.commit();

        } catch (IOException | SQLException ex1) {
            ex1.printStackTrace();
        }
        finally {
            try { stmt.close(); } catch (SQLException se) { /*can't do anything */ }
            try { connection.close(); } catch (SQLException se) { /*can't do anything */ }
        }
    }
}