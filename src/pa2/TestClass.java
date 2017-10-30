package pa2;

import java.util.Scanner;

public class TestClass {
	public static void main(String args[]){
		MinHash minHash = new MinHash("space", 500);		
		System.out.println("exact " + minHash.exactJaccard("space-513.txt", "space-512.txt"));
		minHash.minHashMatrix();
		System.out.println("approx. " + minHash.approximateJaccard("space-513.txt", "space-512.txt"));
		
		int[][] erer = new int[2][2];
		int[] line = {1,2};
		erer[0]=line;
//		System.out.println("erer is" + "".length());
		
		Scanner delimiterScanner = new Scanner("tmd;;;::;s'''::;bd").useDelimiter("\\s+|\\;|\\:|\\'");
//		while(delimiterScanner.hasNext()){
//			String temp = delimiterScanner.next();
//			if(!temp.isEmpty()){
//			System.out.println(temp);
//			}
//		}
	}
}
