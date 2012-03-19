import java.io.*;
import java.util.*;

public class Solution {
	Set<String> dict;
	int N;
	char[] choices;
	boolean[] used;
	char[] word;
	
	void dfs(int level) {
		if(level == N) {
			String w = new String(word);
			if(dict.contains(w)) {
				dict.remove(w);
				System.out.println(word);
			}
			return;
		}
		
		for(int i = 0; i < choices.length; i++) {
			if(used[i]) continue;
			used[i] = true;
			word[level] = choices[i];
			dfs(level + 1);
			used[i] = false;
		}
	}
	
	
	void run(Set<String> dict) throws Exception {
		this.dict = dict;
		
		BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(rd.readLine());
		choices = rd.readLine().toCharArray();
		rd.close();
		
		used = new boolean[choices.length];
		word = new char[N];
		
		dfs(0);
	}

	public static void main(String[] args) throws Exception {
		// args[0] = path of the dictionary
		// stdin is the input file
		
		String dictPath = (args.length == 0) ? "dict" : args[0];
		Set<String> dict = new HashSet<String>(100000);
		
		BufferedReader rd = new BufferedReader(new FileReader(dictPath));
		for(String word = rd.readLine(); word != null; word = rd.readLine()) {
			dict.add(word);
		}
		
		rd.close();
		
		new Solution().run(dict);
	}
}