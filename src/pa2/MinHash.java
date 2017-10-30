package pa2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
	ArrayList<HashSet<Integer>> termMatrix = new ArrayList<HashSet<Integer>>(1000);//term in digit
	HashMap<String,Integer> termHashMap = new HashMap<String,Integer>(); 
	private int[][] minHashMatrix; //each row is the MinHashSig of a doc
	
	int numPmt;
	
	public MinHash(String folder, int numPermutations){
		this.numPmt = numPermutations;
		String path = "./docs/";
		File f = new File(path+folder);
		File[] farray = f.listFiles();
				
		for(int i = 0, length = farray.length; i < length; ++i){
			
//			System.out.println(farray[i].getName());
			allDocsName.add(farray[i].getName());
			termMatrix.add(new HashSet<Integer>()); // termMatrix is a arraylist of HashSet

			String thisLine;
			try{
				BufferedReader br = new BufferedReader(new FileReader(farray[i]));
				
				while ((thisLine = br.readLine()) != null){
					String[] temp = thisLine.split("[\\s,.;:']+");
			        for(String aword : temp){//foreach word in this line
			        	aword.toLowerCase();
			        	if(aword.length()<3 || temp.equals("the")){
			        		continue;
							}
			        	 
			        	if(termHashMap.get(aword) == null){
							termHashMap.put(aword, termHashMap.size());//e.g first element, size = 0, map(firstelement -> 0)
							}
							termMatrix.get(i).add(termHashMap.get(aword));
		//				System.out.println(aword);
			         }
			       } // end while 
				br.close();
				
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		setABP();
	}
	
	public String[] allDocs(){
//		System.out.println(allDocsName.toArray(new String[allDocsName.size()]).length);
		return allDocsName.toArray(new String[allDocsName.size()]);
	}
	
	public double exactJaccard(String file1, String file2){
		int file1index = allDocsName.indexOf(file1);
		int file2index = allDocsName.indexOf(file2);
		
		HashSet<Integer> intersection = new HashSet<Integer>(termMatrix.get(file1index));
		HashSet<Integer> union = new HashSet<Integer>(termMatrix.get(file1index));
		
		intersection.retainAll(termMatrix.get(file2index));
//		System.out.println("intersection size : " + intersection.size());
		
		union.addAll(termMatrix.get(file2index));
//		System.out.println("union size : " + union.size());

		return (double)intersection.size()/(double)union.size();		
	}
	
	public int[] minHashSig(String fileName){
		int index = allDocsName.indexOf(fileName);
	
		int[] docMinHashSig = new int[numPmt];
		
		for(int i = 0; i < numPmt; ++i){
			int currentMin = prime;
			HashSet<Integer> arow = termMatrix.get(index);
			for(Integer term : arow){ //get x for ax+b
				if (currentMin > (valueAB[i][0]*term + valueAB[i][1])%prime){
					currentMin = (valueAB[i][0]*term + valueAB[i][1])%prime;
//					System.out.println("current min = " + currentMin);				
					}
			}
			docMinHashSig[i] = currentMin;
		}
		return docMinHashSig;
	}
	
	public int[][] minHashMatrix(){
		int numAllDocs = allDocsName.size();
		minHashMatrix = new int[numAllDocs][numPmt];//global variable
		
		for(int i = 0, k = numAllDocs; i < k ; ++i){
//			System.out.println("this doc name " + allDocsName.get(i));
			minHashMatrix[i] = minHashSig(allDocsName.get(i));
		}
		//for KxN transpose
		int[][] minHashMatrixKxN = new int [numPmt][allDocsName.size()];
		for(int i = 0 ; i < numPmt ; ++i){
			for(int j = 0 ; j < numAllDocs; ++j){
				minHashMatrixKxN[i][j] = minHashMatrix[j][i];
			}
		}	
		return minHashMatrixKxN;
	}
	
	public double approximateJaccard(String file1, String file2){
		int match = 0;

		for(int i = 0, file1index = allDocsName.indexOf(file1), file2index = allDocsName.indexOf(file2) ; i < numPmt; ++i){
			if(minHashMatrix[file1index][i] == minHashMatrix[file2index][i]){
				++match;
			}
		}
//		System.out.println("match size " + match);
		return (double)match/(double)numPmt;
	}
	
	
	public int numTerms(){
		return termHashMap.size();
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
		prime = nextPrime(termHashMap.size());
//		System.out.println(termHashMap.size());//test
//		System.out.println(prime);//test
		valueAB = new int[numPmt][2];
		Random random = new Random();
		for(int i=0; i<numPmt; i++){
			valueAB[i][0] = Math.abs(random.nextInt() % prime);
			valueAB[i][1] = Math.abs(random.nextInt() % prime);
		}
	}
	
}
