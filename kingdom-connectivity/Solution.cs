/**
 * @author ShengMin Zhang
 * @revision 1.0
 * - DFS
 */

using System;
using System.IO;
using System.Collections.Generic;

public class Solution {
	int N, M;
	IDictionary<int, ICollection<int>> cityNeighbours; // cityNeighbours[i] = list of neighbours of city i
	IDictionary<int, int> pathCount; // pathCount[i] = number of paths from city i to warfare capital if non-negative, -1 indicates infinite number of paths, -2 indicates cycle
	bool[] visited; // visited[i] is true iff city i has been visited
	const int MOD = 1000000000;

	int Dfs(int city) {
		if(city == N) return 1;
		if(pathCount.ContainsKey(city)) return pathCount[city];
		if(visited[city]) return -2; // a cycle
	
		visited[city] = true;
		int totalCount = 0;
		bool hasCycle = false;
		ICollection<int> neighbours = null;
		cityNeighbours.TryGetValue(city, out neighbours);
		
		if(neighbours == null) return 0; // no way to make to the warfare capital
		
		foreach(int neighbour in neighbours) {
			int count = Dfs(neighbour);
			if(count == -2) {
				hasCycle = true;
			} else if(count == -1) {
				totalCount = -1;
				break;
			} else {
				totalCount = (totalCount + count) % MOD;
			}
		}
		
		if(hasCycle && totalCount > 0) totalCount = -1;
		pathCount[city] = totalCount;
		
		return totalCount;
	}
	
	void Run(TextReader rd) {
		string[] ln = rd.ReadLine().Split(' ');
		N = int.Parse(ln[0]);
		M = int.Parse(ln[1]);
		visited = new bool[N + 1];
		cityNeighbours = new Dictionary<int, ICollection<int>>(N);
		pathCount = new Dictionary<int, int>(N);
		
		for(int i = 0; i < M; i++) {
			ln = rd.ReadLine().Split(' ');
			int x = int.Parse(ln[0]);
			int y = int.Parse(ln[1]);
			ICollection<int> neighbours = null;
			cityNeighbours.TryGetValue(x, out neighbours);
			if(neighbours == null) {
				neighbours = new LinkedList<int>();
				cityNeighbours[x] = neighbours;
			}
			neighbours.Add(y);
		}
		
		rd.Dispose();
		
		int result = Dfs(1);
		
		if(result == -1) {
			Console.WriteLine("INFINITE PATHS");
		} else {
			Console.WriteLine(result);
		}
	}

	public static void Main(string[] args) {
		TextReader rd = Console.In;
		new Solution().Run(rd);
	}
}
