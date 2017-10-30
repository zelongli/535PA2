package pa2;

import java.io.File;
import java.util.concurrent.ExecutorService;  
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;  

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
		
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(16);
		System.out.println("let find too diff");
		for(File aFile : farray){
			fixedThreadPool.execute(new Runnable(){
				public void run(){
				    for(File bFile : farray){//for each doc A, a thread gives nonAccPair num per all Doc
						double approx = minHash.approximateJaccard(aFile.getName(), bFile.getName());
						double exact = minHash.exactJaccard(aFile.getName(), bFile.getName());

//						System.out.println("diff is" + diff);						
						if(Math.abs(approx - exact) > errorP){
							synchronized (this){   
								// synchronized keyword on block of  code
								++nonAccPair;
							}
						}
				    }
				}
			});
		
		}//end of outter for-loop
		
		fixedThreadPool.shutdown();
		
		try {
			fixedThreadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
			} catch (InterruptedException e) {
			  e.printStackTrace();
			}
		
		System.out.println("num of approx exact too diff pairs = " + nonAccPair);
	}
}
