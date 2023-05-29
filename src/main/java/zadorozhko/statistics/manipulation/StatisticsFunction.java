/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zadorozhko.statistics.manipulation;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.stat.correlation.Covariance;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import org.apache.commons.math3.stat.descriptive.moment.Variance;
import org.apache.commons.math3.stat.interval.ConfidenceInterval;


/**
 *
 * @author Dasha
 */
public class StatisticsFunction {
    
    public static double geometricMean(double[] sample){
        
        return StatUtils.geometricMean(sample);
        
    }
    
    public static double arithmeticMean(double[] sample){
        
        return StatUtils.mean(sample);
        
    }
    
    public static double standardDeviation(double[] sample){
        
        StandardDeviation sd = new StandardDeviation();
        return sd.evaluate(sample);
        
    }
    
    public static double size(double[] sample){
        
        return StatUtils.max(sample) - StatUtils.min(sample);
        
    }
    
    public static double covarianceCoefficient(double[] sample1, double[] sample2){
        
        Covariance c = new Covariance();
        return c.covariance(sample1,sample2);
        
    }
    
    public static double quantityElements(double[] sample){
        
        return sample.length;
        
    }
    
    public static double coefficientOfVariation(double[] sample){
        
        StandardDeviation sd = new StandardDeviation();
        return sd.evaluate(sample) / StatUtils.mean(sample);
        
    }
    
    public static double[] confidenceInterval(double[] sample, double alpha){
        
        StandardDeviation sd = new StandardDeviation();
        double mean = StatUtils.mean(sample);
        double standartDeviation = sd.evaluate(sample);
        NormalDistribution nd = new NormalDistribution();
        double inverseProbability = nd.inverseCumulativeProbability(1.0 - alpha / 2.0);
        double marginOfError = inverseProbability * standartDeviation / Math.sqrt(sample.length);
        ConfidenceInterval confidenceInterval = new ConfidenceInterval(mean - marginOfError, mean + marginOfError, 1.0 - alpha);
        double[] results = new double[3];
        results[0] = confidenceInterval.getConfidenceLevel();
        results[1] = confidenceInterval.getLowerBound();
        results[2] = confidenceInterval.getUpperBound();
        return results;
        
    }
    
    public static double estimationOfVariance(double[] sample){
        
        Variance variance = new Variance();
        return variance.evaluate(sample);
        
    }
    
    public static double max(double[] sample){
        
        return StatUtils.max(sample);
        
    }
    
    public static double min(double[] sample){
        
        return StatUtils.min(sample);
        
    }
}
