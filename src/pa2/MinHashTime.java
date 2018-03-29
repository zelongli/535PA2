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
		minHash.minHashMatrix();
		long endTime = System.currentTimeMillis();
		long duration = (long)(endTime - startTime)/1000;
		System.out.printf("time of building Minhash Matrix: %d \n", duration);
		startTime = System.currentTimeMillis();
	
		for(File aFile : farray){
			for(File bFile : farray){
				minHash.approximateJaccard(aFile.getName(), bFile.getName());  //do not need to store the returned value, just test time
			}
		}
		
		endTime = System.currentTimeMillis();
		duration = (long)(endTime - startTime)/1000;
		
		System.out.printf("duration of computing approximateJaccard is %d \n", duration);
		
		startTime = System.currentTimeMillis();
		for(File aFile : farray){
			for(File bFile : farray){
				minHash.approximateJaccard(aFile.getName(), bFile.getName());
			}
		}
		endTime = System.currentTimeMillis();
		duration = (long)(endTime - startTime)/1000;
		System.out.printf("duration of computing exactJaccard is %d \n", duration);
	}
}
