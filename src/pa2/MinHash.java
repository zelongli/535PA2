package pa2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class MinHash {
	int numDocs;
	int[][] valueAB;
	int prime;
	
	ArrayList<String> allDocsName = new ArrayList<String>(1000);
	ArrayList<HashSet<Integer>> termMatrix = new ArrayList<HashSet<Integer>>(1000);
	HashMap<String,Integer> termMap = new HashMap<String,Integer>(); 
	private int[][] minHashMatrix;
	
	int numPmt;
	
	public MinHash(String folder, int numPermutations){
		this.numPmt = numPermutations;
		String path = "./docs/";
		File f = new File(path+folder);
		File farray[] = f.listFiles();
				
		for(int i = 0, length = farray.length; i < length; ++i){
			try{
				Scanner delimiterScanner = new Scanner(farray[i]);
				delimiterScanner.useDelimiter("[\\s,.;:']+");
				
				allDocsName.add(farray[i].getName());
//				System.out.println(farray[i].getName());
				
				termMatrix.add(new HashSet<Integer>()); // termMatrix is a arraylist of HashSet
											
				while(delimiterScanner.hasNext()){
					String temp = delimiterScanner.next().toLowerCase();
					
					
					
					if(temp.length()<3 || temp.equals("the")){
						continue;
					}
		//			System.out.println(temp);
					if(termMap.get(temp) == null){
						termMap.put(temp, termMap.size());//e.g first element, size = 0, map(firstelement -> 0)
					}
					termMatrix.get(i).add(termMap.get(temp));
				}
				delimiterScanner.close();	
				
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		setABP();
	}
	
	public String[] allDocs(){
		return allDocsName.toArray(new String[allDocsName.size()]);
	}
	
	public double exactJaccard(String file1, String file2){
		int file1index = allDocsName.indexOf(file1);
		int file2index = allDocsName.indexOf(file2);
		
		HashSet<Integer> intersection = new HashSet<Integer>(termMatrix.get(file1index));
		HashSet<Integer> union = new HashSet<Integer>(termMatrix.get(file1index));
		
		intersection.retainAll(termMatrix.get(file2index));
		System.out.println("intersection size : " + intersection.size());
		
		union.addAll(termMatrix.get(file2index));
		System.out.println("union size : " + union.size());

		return (double)intersection.size()/(double)union.size();
		
	}
	
	public int[] minHashSig(String fileName){
		int index = allDocsName.indexOf(fileName);
	
		int[] docMinHashSig = new int[numPmt];
		
		for(int i = 0; i < numPmt; ++i){
			int currentMin = prime;
			for(Integer element : termMatrix.get(index)){ //get x for ax+b
				if (currentMin > (valueAB[i][0]*element+valueAB[i][1])%prime){
					currentMin = (valueAB[i][0]*element+valueAB[i][1])%prime;
				}
			}
			docMinHashSig[i] = currentMin;
		}
		return docMinHashSig;
	}
	
	public int[][] minHashMatrix(){
		minHashMatrix = new int[allDocsName.size()][numPmt];
		
		for(int i = 0; i < allDocsName.size(); ++i){
//			System.out.println("this doc name " + allDocsName.get(i));
			minHashMatrix[i] = minHashSig(allDocsName.get(i));
		}
		//for KxN transpose
		int[][] minHashMatrixKxN = new int [numPmt][allDocsName.size()];
		for(int i = 0 ; i < numPmt ; ++i){
			for(int j = 0 ; j < allDocsName.size(); ++j){
				minHashMatrixKxN[i][j] = minHashMatrix[j][i];
			}
		}	
		return minHashMatrixKxN;
	}
	
	public double approximateJaccard(String file1, String file2){
		int match = 0;

		for(int i = 0 ; i < numPmt; ++i){
			if(minHashMatrix[allDocsName.indexOf(file1)][i] == minHashMatrix[allDocsName.indexOf(file2)][i]){
				++match;
			}
		}
		System.out.println("match size " + match);
		return (double)match/(double)numPmt;
	}
	
	
	public int numTerms(){
		return termMap.size();
	}
	
	public int numPermutations(){
		return numPmt;
	} 
	
	//helper functions
	
	boolean isPrime(int n) {
	    //check if n is a multiple of 2
	    if (n%2==0) return false;
	    //if not, then just check the odds
	    for(int i=3;i*i<=n;i+=2) {
	        if(n%i==0)
	            return false;
	    }
	    return true;
	}
	
	public int nextPrime(int n){
		if(isPrime(n)){
			return n;
		}
		int m = n;
		while(!isPrime(m)){
			m++;
		}
		return m;
	}
	
	public void setABP(){
		prime = nextPrime(termMap.size());
//		System.out.println(termMap.size());//test
//		System.out.println(prime);//test
		valueAB = new int[numPmt][2];
		Random random = new Random();
		for(int i=0; i<numPmt; i++){
			valueAB[i][0] = Math.abs(random.nextInt() % prime);
			valueAB[i][1] = Math.abs(random.nextInt() % prime);
		}
	}
	
}
