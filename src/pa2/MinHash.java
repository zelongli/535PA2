package pa2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class MinHash {
	int numDocs;
	
	ArrayList<String> allDocsName = new ArrayList<String>(1024);
	ArrayList<HashSet<Integer>> termMatrix = new ArrayList<HashSet<Integer>>(1024);
	HashMap<String,Integer> termMap = new HashMap<String,Integer>(); 
	
	int numP;
	
	public MinHash(String folder, int numPermutations){
		this.numP = numPermutations;
		String path = "./docs/";
		File f = new File(path+folder);
		File fa[] = f.listFiles();
		
		for(int i = 0, length = fa.length; i < 5; ++i){
			try{
				Scanner delimiterScanner = new Scanner(fa[i]);
				delimiterScanner.useDelimiter("[\\s,.:;']+");
				
				allDocsName.add(fa[i].getName());
				System.out.println(fa[i].getName());
				termMatrix.add(new HashSet<Integer>());
							
				
				while(delimiterScanner.hasNext()){
					String temp = delimiterScanner.next();
					
					if(termMap.get(temp) == null){
						termMap.put(temp, termMap.size());
					}
					termMatrix.get(i).add(termMap.get(temp));
				}

				delimiterScanner.close();	
				
			}catch(IOException e){
				e.printStackTrace();
			}
			
		}
	}
	
	public String[] allDocs(){
		return allDocsName.toArray(new String[allDocsName.size()]);
	}
	
	public double exactJaccard(String file1, String file2){
		int file1index = allDocsName.indexOf(file1);
		int file2index = allDocsName.indexOf(file2);
		
		HashSet<Integer> intersection = new HashSet<Integer>(termMatrix.get(1));
		HashSet<Integer> union = new HashSet<Integer>(termMatrix.get(1));
		
		intersection.retainAll(termMatrix.get(2));
		System.out.println("intersection size : " + intersection.size());
		
		union.addAll(termMatrix.get(2));
		System.out.println("union size : " + union.size());

		return (double)intersection.size()/(double)union.size();
		
	}
	
	public int[] minHashSig(){
		
		return null;
	}
	
}
