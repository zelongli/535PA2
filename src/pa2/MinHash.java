package pa2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class MinHash {
	int numDocs;
	
	ArrayList<String> allDocsName = new ArrayList<String>(1024);
	ArrayList<ArrayList<String>> termMatrix = new ArrayList<ArrayList<String>>(1024);
	Set<String> termSet = new HashSet<String>(); 
	
	public MinHash(String folder, int numPermutations){
		String path = "./docs/";
		File f = new File(path+folder);
		File fa[] = f.listFiles();
		
		try{
			Scanner delimiterScanner = new Scanner(fa[0]);
			
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
		
//		for(int i = 0; i< fa.length; ++i){
//			++numDocs;
//			if(fa[i].isDirectory()){
//				System.out.println(fa[i].getName()+ "im a directory");
//			}else{
//				System.out.println(fa[i].getName()+ "im a file");
//			}
//		}
//		System.out.println("# of docs = " + numDocs);
	}
	
	public void allDocs(){
		
		
	}
	
	
}
