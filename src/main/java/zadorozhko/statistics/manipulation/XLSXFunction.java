/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zadorozhko.statistics.manipulation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Dasha
 */
public class XLSXFunction {
        private final List<Double> x = new ArrayList<>();
        private final List<Double> y = new ArrayList<>();
        private final List<Double> z = new ArrayList<>();
        private final List<double[]> data = new ArrayList<>();
        
        private final int numberOfSheet = 4;
        
        private File file;
    
    public List<double[]> getData(){
        return this.data;
    }
    
    public void readData(String path) throws IOException, InvalidFormatException{
        file = new File(path);
        XSSFWorkbook workbook;
        workbook = new XSSFWorkbook(file);
        XSSFSheet sheet;
        sheet = workbook.getSheetAt(numberOfSheet);
        Row row;
        Cell cell0; 
        Cell cell1;
        Cell cell2;

        for(int i = 0; i<= sheet.getLastRowNum(); i++){
            row = sheet.getRow(i);
            cell0 = row.getCell(0);
            cell1 = row.getCell(1);
            cell2 = row.getCell(2);
            if (cell0!=null&&cell0.getCellType() == CellType.NUMERIC) {
                x.add(row.getCell(0).getNumericCellValue());
            }
            if (cell1!=null&&cell1.getCellType() == CellType.NUMERIC) {
                y.add(row.getCell(1).getNumericCellValue());
            }
            if (cell2!=null&&cell2.getCellType() == CellType.NUMERIC) {
                z.add(row.getCell(2).getNumericCellValue());
            }
        }
        
        if(x.isEmpty()||y.isEmpty()||z.isEmpty()) {
            throw new IOException("Данных не хватает");
        } else {
            data.add(x.stream().mapToDouble(Double::doubleValue).toArray());
            data.add(y.stream().mapToDouble(Double::doubleValue).toArray());
            data.add(z.stream().mapToDouble(Double::doubleValue).toArray());
        }
        workbook.close();
    }
    
    public void writeData(Map<String, Double> xResults, Map<String, Double> yResults, Map<String, Double> zResults, double[][] covarianceC, String path) throws FileNotFoundException, IOException{
        XSSFWorkbook workbook;
        workbook = new XSSFWorkbook();
        FileOutputStream fileOutput = new FileOutputStream(path);
        
        Sheet xSheet = workbook.createSheet("X");
        Sheet ySheet = workbook.createSheet("Y");
        Sheet zSheet = workbook.createSheet("Z");
        Sheet cSheet = workbook.createSheet("covarianceC");
        
        Row row;
        Cell cell;
        
        String[] name = new String[]{
        "geomtricMean",
        "arithmeticMean",
        "standardDeviation",
        "size",
        "quantityElements",
        "coefficientOfVariation",
        "confidenceLevel",
        "confidenceIntervalLow",
        "confidenceIntervalUp",
        "estimationOfVariance",
        "max",
        "min"
        };
        
        for(int i = 0; i<xResults.size(); i++){
            row = xSheet.createRow(i);
            cell = row.createCell(0);
            cell.setCellValue(name[i]);
            cell = row.createCell(1);
            if(xResults.get(name[i]).isNaN()){
                cell.setCellValue(" impossible to count");
            }else{
                cell.setCellValue(xResults.get(name[i]));
            }
        }
        for(int i = 0; i<yResults.size(); i++){
            row = ySheet.createRow(i);
            cell = row.createCell(0);
            cell.setCellValue(name[i]);
            cell = row.createCell(1);
            if(yResults.get(name[i]).isNaN()){
                cell.setCellValue("impossible to count");
            }else{
                cell.setCellValue(yResults.get(name[i]));
            }
        }
        for(int i = 0; i<zResults.size(); i++){
            row = zSheet.createRow(i);
            cell = row.createCell(0);
            cell.setCellValue(name[i]);
            cell = row.createCell(1);
            if(zResults.get(name[i]).isNaN()){
                cell.setCellValue("impossible to count");
            }else{
                cell.setCellValue(zResults.get(name[i]));
            }
        }
        
        for(int i = 0; i < 3; i++){
            row = cSheet.createRow(i);
            for(int j = 0; j < 3; j++){
                
                cell = row.createCell(j);
                cell.setCellValue(covarianceC[i][j]);
            }
        }
        workbook.write(fileOutput);
        workbook.close();
    }
    
}
