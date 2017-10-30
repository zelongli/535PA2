package pa2;

import java.util.Scanner;

public class TestClass {
	public static void main(String args[]){
		
		long startTime = System.currentTimeMillis();
		
		MinHash minHash = new MinHash("space", 400);	
		
		System.out.printf("excat 119 118 %f \n", minHash.exactJaccard("space-933.txt", "space-933.txt"));
		long endTime = System.currentTimeMillis();
		long duration = (long)(endTime - startTime)/1000;
		System.out.printf("constructor duration is %d seconds \n", duration);
		
		
		startTime = System.currentTimeMillis();
		minHash.minHashMatrix();
		
		
		endTime = System.currentTimeMillis();
		duration = (long)(endTime - startTime)/1000;
		System.out.printf("min hash matrix duration is %d seconds \n", duration);
		
//		minHash.allDocs();
//		minHash.minHashSig("space-256.txt");
//		System.out.println("exact " + minHash.exactJaccard("space-256.txt", "space-257.txt"));

//		minHash.minHashMatrix();
//		System.out.println("approx " + minHash.approximateJaccard("space-256.txt", "space-257.txt"));
		
		startTime = System.currentTimeMillis();
		
		MinHashAccuracy minAcc = new MinHashAccuracy();
		minAcc.accuracy("space", 400, 0.04);
		
		
		endTime = System.currentTimeMillis();
		duration = (long)(endTime - startTime)/1000;
		System.out.printf("acc time is %d seconds \n ", duration);
		
//		MinHashTime timer = new MinHashTime();
//		timer.timer("space", 400);
		
//		System.out.println("num of terms " + minHash.numTerms());

	}
}
