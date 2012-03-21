/**
 * @author ShengMin Zhang
 *
 * @revision 1.1
 * - save computed results
 * @revision 1.0
 * - DFS
 */

using System;
using System.IO;
using System.Collections.Generic;

public struct Natural {
	public static readonly Natural Infinity = new Natural(VALUE_INFINITY);
	private const int VALUE_INFINITY = -1;
	private int val;

	public Natural(int val) {
		this.val = val;
	}
	
	public int Value {
		get { return val; }
	}
	
	public bool IsInfinity() {
		return val == VALUE_INFINITY;
	}
	
	public static Natural operator + (Natural n1, Natural n2) {
		if(n1.IsInfinity() || n2.IsInfinity()) return Infinity;
		return n1.val + n2.val;
	}
	
	public static Natural operator % (Natural n1, Natural n2) {
		return n1.val % n2.val;
	}
	
	public static bool operator == (Natural n1, Natural n2) {
		return n1.val == n2.val;
	}
	
	public static bool operator != (Natural n1, Natural n2) {
		return n1.val != n2.val;
	}
	
	public static implicit operator Natural(int v) {
		return new Natural(v);
	}
	
	public static implicit operator int(Natural n) {
		return n.val;
	}
	
	public override string ToString() {
		return val.ToString();
	}
}

public class City {
	public int Id { get; private set; }
	public Natural PathCount { get; set; } // number of paths from this city to warfare capital
	public ICollection<City> Neighbours { get; private set; }
	
	public City(int id) {
		Id = id;
		PathCount = -2;
		Neighbours = new LinkedList<City>();
	}
	
	public bool HasValue(){
		return PathCount != -2;
	}
	
	public void Add(City c) {
		Neighbours.Add(c);
	}
	
	public static bool operator == (City c1, City c2) {
		return c1.Id == c2.Id;
	}
	
	public static bool operator != (City c1, City c2) {
		return !(c1 == c2);
	}
	
	public static implicit operator int(City c) {
		return c.Id;
	}
	
	public override bool Equals(object o) {
		return (o is City) && (City)o == this;
	}
	
	public override int GetHashCode(){
		return Id;
	}
}

public class Solution {
	int N, M;
	readonly Natural MOD = 1000000000;
	bool[] visited; 
	bool[] visited2;
	int[] reachabletb;
	Stack<City> visitedCities;
	City[] cities;
	City firstCity, lastCity;
	
	/**
	 * Test whether not or not it's reachable from city to city to
	 */
	bool IsReachable(City city, City to) {
		if(city == to) return true; // reach the destination city
		if(visited2[city]) return false; // a cycle;
		int result = reachabletb[city];
		if(result == 1) return true;
		else if(result == -1) return false;
		
		visited2[city] = true;
		
		bool anyReachable = false;
		foreach(City neighbour in city.Neighbours) {
			bool reachable = IsReachable(neighbour, to);
			if(reachable) {
				anyReachable = true;
				break;
			}
		}
		
		visited2[city] = false;
		reachabletb[city] = anyReachable ? 1 : -1;
		return anyReachable;
	}
	
	Natural CountPath(City city) {
		if(city.HasValue()) return city.PathCount;
	
		if(visited[city]) {
			// a cycle
			foreach(City c in visitedCities) {
				// if any of the city in the cycle can reach warfare capital, then there's infinite path, otherwise 0
				bool reachable = IsReachable(c, lastCity);
				if(reachable) return Natural.Infinity;
				if(c == city) break;
			}
			return 0;
		}
		
		visited[city] = true;
		visitedCities.Push(city);
		ICollection<City> neighbours = city.Neighbours;
		
		Natural totalCount = city == lastCity ? 1 : 0;
		foreach(City n in neighbours) {
			totalCount = (totalCount + CountPath(n)) % MOD;
			if(totalCount.IsInfinity()) break;
		}
		
		visitedCities.Pop();
		visited[city] = false;
		
		city.PathCount = totalCount;
		
		return totalCount;
	}
	
	
	void Run(TextReader rd) {
		string[] ln = rd.ReadLine().Split(' ');
		N = int.Parse(ln[0]);
		M = int.Parse(ln[1]);
		visited = new bool[N + 1];
		visited2 = new bool[N + 1];
		visitedCities = new Stack<City>(N);
		cities = new City[N + 1];
		reachabletb = new int[N + 1];
		
		for(int i = N; i > 0; i--) {
			City c = new City(i);
			cities[i] = c;
		}
		
		firstCity = cities[1];
		lastCity = cities[N];
		
		for(int i = 0; i < M; i++) {
			ln = rd.ReadLine().Split(' ');
			int from = int.Parse(ln[0]);
			int to = int.Parse(ln[1]);
			cities[from].Add(cities[to]);
		}
		
		rd.Dispose();
		
		Natural count = CountPath(firstCity);
		
		if(count.IsInfinity()) {
			Console.WriteLine("INFINITE PATHS");
		} else {
			Console.WriteLine(count);
		}
	}

	public static void Main(string[] args) {
		TextReader rd = Console.In;
		new Solution().Run(rd);
	}
}
