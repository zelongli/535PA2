package pa2;

public class TestClass {
	public static void main(String args[]){
		MinHash minHash = new MinHash("space", 1000);		
		minHash.minHashMatrix();
		System.out.println(minHash.exactJaccard("space-339.txt", "space-339.txt"));
		int[][] erer = new int[2][2];
		int[] line = {1,2};
		erer[0]=line;
		System.out.println("erer is" + erer[0][1]);
	}
}
