package pa2;

import java.util.Scanner;

public class TestClass {
	public static void main(String args[]){
		MinHash minHash = new MinHash("space", 1000);	
//		minHash.allDocs();
//		minHash.minHashSig("space-256.txt");
		System.out.println("exact " + minHash.exactJaccard("space-256.txt", "space-257.txt"));

		minHash.minHashMatrix();
		System.out.println("approx " + minHash.approximateJaccard("space-256.txt", "space-257.txt"));
		
		System.out.println("num of terms " + minHash.numTerms());
		int[][] erer = new int[2][2];
		int[] line = {1,2,3};
		erer[0]=line;
		System.out.println("erer is " + erer[0][2]);
		MinHashAccuracy minAcc = new MinHashAccuracy();
		minAcc.accuracy("Space", 1000, 0.04);
	}
}
