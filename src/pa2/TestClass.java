package pa2;

import java.util.Scanner;

public class TestClass {
	public static void main(String args[]){
//		MinHash minHash = new MinHash("space", 1000);	
//		minHash.allDocs();
//		minHash.minHashSig("space-256.txt");
//		System.out.println("exact " + minHash.exactJaccard("space-256.txt", "space-257.txt"));

//		minHash.minHashMatrix();
//		System.out.println("approx " + minHash.approximateJaccard("space-256.txt", "space-257.txt"));
		
		MinHashAccuracy minAcc = new MinHashAccuracy();
		minAcc.accuracy("articles", 400, 0.04);
		
//		System.out.println("num of terms " + minHash.numTerms());

	}
}
