package pa2;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class LSH {
	int [][]minHashMatrix;
	String []docNames;
	int bands; //b
	int eachBandNum; //r
	int sizeOfT;
	ArrayList<HashMap<Integer, HashSet<String>>> table = new ArrayList<HashMap<Integer, HashSet<String>>>(bands);
	
	
	public LSH(int [][]minHashMatrix, String[] docNames, int bands){
		this.minHashMatrix = minHashMatrix;
		this.docNames = docNames;
		this.bands = bands;
		this.eachBandNum = minHashMatrix.length/bands;
		this.sizeOfT = nextPrime(docNames.length);;
		LSHCaculator();
	}
	
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
	
	public void LSHCaculator(){
		
		for(int i=0,b=0; i<minHashMatrix.length; b++,i=b*eachBandNum ){
			table.add(new HashMap<Integer, HashSet<String>>());
			for(int j=0; j<docNames.length; j++){
				String entry = ":";
				for(int k=0; k<eachBandNum; k++){
					entry.concat(Integer.toString(minHashMatrix[i][j]));
					entry.concat(":");
				}
				Integer entryHashCode = entry.hashCode()%sizeOfT;
				if(!table.get(b).containsKey(entryHashCode)){
					table.get(b).put(entryHashCode, new HashSet<String>());
				}
				table.get(b).get(entryHashCode).add(docNames[j]);
			}	
		}
		System.out.println("table calculation finished");//test
	}
	

	
	public LinkedList<String> nearDuplicatesOf(String docName){
		System.out.println("the target doc is: "+ docName);
		LinkedList<String> similarDocs = new LinkedList<String>();
		
		for(int i=0; i<table.size(); i++){
			HashMap<Integer, HashSet<String>> values = table.get(i);
			Set<Integer> keys = values.keySet();
			for(Integer key: keys){
				if(values.get(key).contains(docName)){
					for(String elem: values.get(key)){
						if(!similarDocs.contains(elem)&& !elem.equals(docName)){
							similarDocs.add(elem);
						}
					}
				}
			}

		}
		return similarDocs;
	}
	
	

}
