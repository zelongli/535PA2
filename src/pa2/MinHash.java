package pa2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class MinHash {
	int numDocs;
	
	ArrayList<String> allDocsName = new ArrayList<String>(1024);
	ArrayList<ArrayList<Integer>> termMatrix = new ArrayList<ArrayList<Integer>>(1024);
	HashMap<String,Integer> termMap = new HashMap<String,Integer>(); 
	
	public MinHash(String folder, int numPermutations){
		String path = "./docs/";
		File f = new File(path+folder);
		File fa[] = f.listFiles();
		
		for(int i = 0, length = fa.length; i < 10; ++i){
			try{
				Scanner delimiterScanner = new Scanner(fa[i]);
				delimiterScanner.useDelimiter("[\\s,.:;']+");
				termMatrix.add(new ArrayList<Integer>());
				
				
				allDocsName.add(fa[i].getName());
				System.out.println(fa[i].getName());
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
		System.out.println(allDocsName.indexOf("space-735.txt"));
		return 1.1;
	}
	
	
}
