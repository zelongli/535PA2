package pa2;

import java.util.Scanner;

public class TestClass {
	public static void main(String args[]){
		
		long startTime = System.currentTimeMillis();
		MinHash minHash = new MinHash("space", 400);	

		long endTime = System.currentTimeMillis();
		long duration = (long)(endTime - startTime)/1000;
		System.out.printf("Read all files duration is %d seconds \n", duration);
		
		startTime = System.currentTimeMillis();
		minHash.minHashMatrix();		
		
		endTime = System.currentTimeMillis();
		duration = (long)(endTime - startTime)/1000;
		System.out.printf("MinHash matrix construction time is %d seconds \n", duration);
		//accuracy time
		startTime = System.currentTimeMillis();
		
		MinHashAccuracy minAcc = new MinHashAccuracy();
		minAcc.accuracy("space", 400, 0.04);
	
		endTime = System.currentTimeMillis();
		duration = (long)(endTime - startTime)/1000;
		System.out.printf("acc time is %d seconds \n", duration);
		
		//Here is the timer
		MinHashTime timer = new MinHashTime();
		timer.timer("articles", 400);
		
//		System.out.println("num of terms " + minHash.numTerms());

	}
}
