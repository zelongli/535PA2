package pa2;

import java.io.File;
import java.util.Timer;

public class MinHashTime {
	public MinHashTime(){
		
	}
	
	public void timer(String folder, int numPmt){
		MinHash minHash = new MinHash(folder, numPmt);
				
		String path = "./docs/";
		File f = new File(path+folder);
		File[] farray = f.listFiles();
		long startTime = System.currentTimeMillis();
		
		for(File aFile : farray){
			for(File bFile : farray){
//				double approx = minHash.approximateJaccard(aFile.getName(), bFile.getName());
				double exact = minHash.approximateJaccard(aFile.getName(), bFile.getName());
			}
		}
		long endTime = System.currentTimeMillis();
		long duration = (long)(endTime - startTime)/1000;
		
		startTime = System.currentTimeMillis();
		
		minHash.minHashMatrix();
		endTime = System.currentTimeMillis();
		duration = (long)(endTime - startTime)/1000;
		
		startTime = System.currentTimeMillis();
		for(File aFile : farray){
			for(File bFile : farray){
				double approx = minHash.approximateJaccard(aFile.getName(), bFile.getName());
//				double exact = minHash.approximateJaccard(aFile.getName(), bFile.getName());
			}
		}
		endTime = System.currentTimeMillis();
		duration = (long)(endTime - startTime)/1000;
	}
}
