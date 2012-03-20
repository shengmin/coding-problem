import java.io.*;
import java.util.*;

class City {
	int pathCount = -2;
}	

public class Solution {
	int N, M;
	boolean[] visited;
	boolean[] visited2;
	City[] cities;
	Set<City> visited;

	void run(BufferedReader rd) throws Exception {
		StringTokenizer st = new StringTokenizer(rd.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
		new Solution().run(rd);
		rd.close();
	}
}