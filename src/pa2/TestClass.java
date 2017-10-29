package pa2;

public class TestClass {
	public static void main(String args[]){
		MinHash minHash = new MinHash("space", 1000);		
		
		System.out.println(minHash.exactJaccard("space-338.txt", "space-339.txt"));
	}
}
