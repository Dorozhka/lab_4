/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zadorozhko.statistics.manipulation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

/**
 *
 * @author Dasha
 */
public class DataManipulation {
    private List<double[]> data = new ArrayList<>();
    XLSXFunction xlsxF = new XLSXFunction();
    private final Map<String, Double> xResults = new HashMap<>();
    private final Map<String, Double> yResults = new HashMap<>();
    private final Map<String, Double> zResults = new HashMap<>();
    private double[][] covarianceC = new double[3][3];
    
    public List<double[]> getData(){
        return this.data;
    }
    
    public void read(String path) throws IOException, InvalidFormatException{
        xlsxF.readData(path);
        data = xlsxF.getData();
    }
    
    
    @SuppressWarnings("unchecked")
    public void calculate(){
        xResults.put("geomtricMean", StatisticsFunction.geometricMean(data.get(0)));
        xResults.put("arithmeticMean", StatisticsFunction.arithmeticMean(data.get(0)));
        xResults.put("standardDeviation", StatisticsFunction.standardDeviation(data.get(0)));
        xResults.put("size", StatisticsFunction.size(data.get(0)));
        xResults.put("estimationOfVariance", StatisticsFunction.estimationOfVariance(data.get(0)));
        xResults.put("coefficientOfVariation", StatisticsFunction.coefficientOfVariation(data.get(0)));
        double[] xcInterval = StatisticsFunction.confidenceInterval(data.get(0), 0.05);
        xResults.put("confidenceLevel", xcInterval[0]);
        xResults.put("confidenceIntervalLow", xcInterval[1]);
        xResults.put("confidenceIntervalUp", xcInterval[2]);
        xResults.put("quantityElements", StatisticsFunction.quantityElements(data.get(0)));
        xResults.put("max", StatisticsFunction.max(data.get(0)));
        xResults.put("min", StatisticsFunction.min(data.get(0)));
        yResults.put("geomtricMean", StatisticsFunction.geometricMean(data.get(1)));
        yResults.put("arithmeticMean", StatisticsFunction.arithmeticMean(data.get(1)));
        yResults.put("standardDeviation", StatisticsFunction.standardDeviation(data.get(1)));
        yResults.put("size", StatisticsFunction.size(data.get(1)));
        yResults.put("estimationOfVariance", StatisticsFunction.estimationOfVariance(data.get(1)));
        yResults.put("coefficientOfVariation", StatisticsFunction.coefficientOfVariation(data.get(1)));
        double[] ycInterval = StatisticsFunction.confidenceInterval(data.get(1), 0.05);
        yResults.put("confidenceLevel", ycInterval[0]);
        yResults.put("confidenceIntervalLow", ycInterval[1]);
        yResults.put("confidenceIntervalUp", ycInterval[2]);
        yResults.put("quantityElements", StatisticsFunction.quantityElements(data.get(1)));
        yResults.put("max", StatisticsFunction.max(data.get(1)));
        yResults.put("min", StatisticsFunction.min(data.get(1)));
        zResults.put("geomtricMean", StatisticsFunction.geometricMean(data.get(2)));
        zResults.put("arithmeticMean", StatisticsFunction.arithmeticMean(data.get(2)));
        zResults.put("standardDeviation", StatisticsFunction.standardDeviation(data.get(2)));
        zResults.put("size", StatisticsFunction.size(data.get(2)));
        zResults.put("estimationOfVariance", StatisticsFunction.estimationOfVariance(data.get(2)));
        zResults.put("coefficientOfVariation", StatisticsFunction.coefficientOfVariation(data.get(2)));
        double[] zcInterval = StatisticsFunction.confidenceInterval(data.get(2), 0.05);
        zResults.put("confidenceLevel", zcInterval[0]);
        zResults.put("confidenceIntervalLow", zcInterval[1]);
        zResults.put("confidenceIntervalUp", zcInterval[2]);
        zResults.put("quantityElements", StatisticsFunction.quantityElements(data.get(2)));
        zResults.put("max", StatisticsFunction.max(data.get(2)));
        zResults.put("min", StatisticsFunction.min(data.get(2)));
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                covarianceC[i][j] = StatisticsFunction.covarianceCoefficient(data.get(i), data.get(j));
            }
        }
        
        
    }
    
    public void write(String path) throws FileNotFoundException, IOException{
        xlsxF.writeData(xResults, yResults, zResults, covarianceC, path);
    }
}
