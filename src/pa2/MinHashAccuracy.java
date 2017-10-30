package pa2;

import java.io.File;

public class MinHashAccuracy {
	int nonAccPair;
	public MinHashAccuracy(){
		this.nonAccPair = 0;
	}
	
	public void accuracy(String folder, int numPmt, double errorP){
		MinHash minHash = new MinHash(folder, numPmt);
		minHash.minHashMatrix();
		
		String path = "./docs/";
		File f = new File(path+folder);
		File[] farray = f.listFiles();
		for(File aFile : farray){
			for(File bFile : farray){
				double approx = minHash.approximateJaccard(aFile.getName(), bFile.getName());
				double exact = minHash.approximateJaccard(aFile.getName(), bFile.getName());
				if(Math.abs(approx - exact)>errorP){
					++nonAccPair;
				}
			}
		}
		System.out.println("num of approx exact too diff pairs = " + nonAccPair);
		
	}
}
