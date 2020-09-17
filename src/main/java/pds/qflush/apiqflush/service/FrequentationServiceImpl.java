package pds.qflush.apiqflush.service;

import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pds.qflush.apiqflush.model.Frequentation;
import pds.qflush.apiqflush.repository.FrequentationRepository;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class FrequentationServiceImpl implements ServiceImpl<Frequentation> {
	
	private Random rng = new Random();
	private double[][] data;
	private double [][] outliersData;
	private double [][] correctedData;
	private double[] levers;
 	private double moyenne;

    @Autowired
    private FrequentationRepository FrequentationRepository;


    @Override
    public Frequentation save(Frequentation Frequentation) {
        return FrequentationRepository.save(Frequentation);
    }

    @Override
    public Optional<Frequentation> findById(long id) {
        return FrequentationRepository.findById(id);
    }

    @Override
    public void delete(Frequentation Frequentation) {
        FrequentationRepository.delete(Frequentation);
    }

    @Override
    public List<Frequentation> findAll() {
        return FrequentationRepository.findAll();
    }
    
    public void initData() {
    	int [] valeurs = new int[]{3300, 2790, 2560, 2970, 3740, 4200, 3960, 3330, 3040, 3510, 4400, 4920, 4620, 3870, 3520, 4050, 5060, 5640, 5280, 4410, 4000, 4590, 5720, 6360};
    	//make aberrant value
    	valeurs[5] = valeurs[5] + 500;
    	valeurs[14] = valeurs[14] + 500;
    	
    	outliersData = new double [valeurs.length] [2];
    	correctedData = new double[valeurs.length] [2];
		
		for(int i = 0; i < valeurs.length; i++) {
			outliersData [i] [0] = i+1;
			outliersData [i] [1] = valeurs[i];
			correctedData [i] [0] = i+1;
			correctedData [i] [1] = valeurs[i];
		}
		
		levers = new double[valeurs.length];
		double gap_square_sum = 0;
		double average_x = 0;
		for (int i = 1; i<=valeurs.length; i++)
			average_x += i;
		average_x = average_x/valeurs.length;
		
		for(int i = 1; i <= valeurs.length; i++)
			gap_square_sum += (i - average_x)*(i - average_x);
		
		for(int i = 1; i <= valeurs.length; i++) {
			levers[i-1] = 1.0/valeurs.length + (i-average_x)*(i-average_x)/gap_square_sum;
		}
    }
    
    public double[][] getData() {
    	// JEU DE DONNEES ALEATOIRES DONNANT UNE COURBE CROISSANTE SUR 4 ANS
    	data = new double[48][2];
    	for(int i=0; i<48; i++) {
    		data[i][0]=i;
    		data[i][1]=(double) rng.nextInt(500)+2000;
    		if(i>11) {
    			data[i][1]=(double) rng.nextInt(1000)+2000;
    		}
    		if(i>23) {
    			data[i][1]=(double) rng.nextInt(2000)+2000;
    		}
    		if(i>35) {
    			data[i][1]=(double) rng.nextInt(3000)+2000;
    		}
    		moyenne = moyenne + data[i][1];
    	}
    	moyenne = moyenne / data.length;
		return data;
    }
    
    public double[][] getOutliersData() {
    	return outliersData;
    }
    
    public double[][] getCorrectedData() {
    	return correctedData;
    }
    
    public double getCoeffRegressionLin(double[][] data) {
    	SimpleRegression regression = new SimpleRegression();
    	regression.clear();
        regression.addData(data);
		return regression.getSlope();
    }
    
    public double getInterceptRegression(double[][] data) {
    	SimpleRegression regression = new SimpleRegression();
    	regression.clear();
        regression.addData(data);
        return regression.getIntercept();
    }
    
    public double getMoyenne() {
    	return moyenne;
    }
    
    
    public boolean outlierDetection() {
    	boolean corrected = false;
    	SimpleRegression regression = new SimpleRegression();
    	boolean correction = true;
    	
    	while(correction) {
    	
			regression.clear();
	        regression.addData(correctedData);
			double m = regression.getSlope();
			double p = regression.getIntercept();
			
			double ratio [] = new double [outliersData.length];
			
			for(int i = 0; i < ratio.length; i++) {
				ratio[i] = (m*correctedData[i][0] + p)/correctedData[i][1];
			}
			
			double weighting = 0;
			double ratioAverage [] = new double [6];
			for (int i = 0; i < ratio.length; i++) {
				ratioAverage[i%6] = ratioAverage[i%6] + ratio[i];
			}
			for (int i = 0; i < ratioAverage.length; i++ ) {
				ratioAverage[i] = ratioAverage[i]/(ratio.length/6);
				weighting = weighting + ratioAverage[i];
			}
			weighting = weighting/ratioAverage.length;
			
			//correction to have a sum equal to 1
			for (int i = 0; i < ratioAverage.length; i++ ) {
				ratioAverage[i] = ratioAverage[i]/weighting;
			}
			
			double cvs [] [] = new double [outliersData.length] [2];
			for (int i = 0; i < correctedData.length; i++) {
				cvs [i] [0] = i+1;
				cvs [i] [1] = correctedData [i][1]*ratioAverage[i%6];
			}
			
			regression.clear();
			regression.addData(cvs);
			m = regression.getSlope();
			p = regression.getIntercept();
			
			double [] yi = new double [outliersData.length];
			for (int i = 0; i < yi.length; i++) {
				yi[i] = (m*cvs[i][0] + p)/ratioAverage[i%6];
			}
			
			double [] gap = new double [outliersData.length];
			for (int i = 0; i < gap.length; i++) {
				gap [i] = correctedData[i][1] - yi[i];
			}
			
			double sigma = 0;
			for(int i = 0; i < gap.length; i++)
				sigma += gap[i]*gap[i];
			sigma = Math.sqrt(sigma/(gap.length-2));
			
			double[] max = new double [outliersData.length];
			for (int i = 0; i < max.length; i++)
				max[i] = 1.96*sigma*Math.sqrt(1+levers[i]);
			
			correction = false;
			for(int i = 0; i < max.length; i++) {
				if(Math.abs(gap[i]) > max[i]) {
					correction = true;
					correctedData[i][1] = yi[i];
					corrected = true;
					System.out.println(correctedData[i][1]);
				}
			}
    	}
    	return corrected;
    }
}
