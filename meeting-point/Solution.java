/**
 * @problem Grid Walking
 * @author ShengMin Zhang
 *
 * @revision 1.0
 * - median point is the meeting point
 */
 
 import java.io.*;
 import java.util.*;
 
 public class Solution {
	class Point {
		private int x, y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}	
		
		public int computeTime(Point p){
			int xdiff = (p.x > x) ? p.x - x : x - p.x;
			int ydiff = (p.y > y) ? p.y - y : y - p.y;
			return (xdiff > ydiff) ? xdiff : ydiff;
		}
		
		@Override
		public String toString(){
			return x + ", " + y;
		}
	}
 
	Point[] points;
 
	long solve(Point meeting) {
		long d = 0;
		
		// System.out.println("------------------------------");
		
		for(int i = 0; i < points.length; i++){
			Point p = points[i];
			if(p == meeting) continue;
			int t = p.computeTime(meeting);
			// System.out.println(t);
			d += t;
		}
		// System.out.println("------------------------------");
		return d;
	}
 
	void run(BufferedReader rd) throws Exception {
		int N = Integer.parseInt(rd.readLine());
		points = new Point[N];
		
		Point[] xsorted = new Point[N];
		Point[] ysorted = new Point[N];
		
		int minX = Integer.MAX_VALUE;
		int maxY = Integer.MIN_VALUE;
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(rd.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			if(x < minX) minX = x;
			if(y > maxY) maxY = y;
			
			points[i] = new Point(x, y);
			xsorted = points[i];
			ysorted = points[i];
		}
		
		final int MIN_X = minX;
		final int MAX_Y = maxY;
		
		Arrays.sort(xsorted, new Comparator<Point>(){
			public int compare(Point a, Point b){
				return a.x - b.x;
			}
		});
		
		Arrays.sort(ysorted, new Comparator<Point>(){
			public int compare(Point a, Point b){
				return a.y - b.y;
			}
		});
		
		/*
		Arrays.sort(points, new Comparator<Point>() {
			@Override
			public int compare(Point a, Point b){
				int aXDiff = a.x - MIN_X;
				int aYDiff = MAX_Y - a.y;
				
				int bXDiff = b.x - MIN_X;
				int bYDiff = MAX_Y - b.y;
				
				return Math.max(aXDiff, aYDiff) - Math.max(bXDiff, bYDiff);
			}
		});
		*/
		
		
		
		long min = 0;
		
		if(N == 1) {
			min = solve(points[0]);
		} else if(N == 0){
			return;
		} else if(N % 2 == 0) {
			System.out.println(points[N/2]);
			System.out.println(points[N/2-1]);
			min = Math.min(solve(points[N/2]), solve(points[N/2 - 1]));
		} else {
			System.out.println(points[N/2]);
			min = solve(points[N/2]);
		}
		
		/*
		long min = Long.MAX_VALUE;
		
		for(int i = 0; i < N; i++){
			long d = solve(points[i]);
			// System.out.println(d);
			if(d < min) min = d;
		}
		*/
		System.out.println(min);
		
	}
 
	public static void main(String[] args) throws Exception {
		Reader rd = (args.length == 0) ? new InputStreamReader(System.in) : new FileReader(args[0]);
		new Solution().run(new BufferedReader(rd));
	}
 }
 