/**
 * @problem Grid Walking
 * @author ShengMin Zhang
 *
 * @revision 1.1
 * - slight optimization in memory
 *
 * @revision 1.0
 * - sort based on the distance to the median, search until first point that's non-decreasing in total time
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
 
	// Point[] xsorted;
	// Point[] ysorted;
	int N;
	Point[] points;
 
	long solve(Point meeting) {
		long d = 0;
		
		// System.out.println("------------------------------");
		
		for(int i = N - 1; i >= 0; i--){
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
		N = Integer.parseInt(rd.readLine());
		// xsorted = new Point[N];
		// ysorted = new Point[N];
		points = new Point[N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(rd.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			Point p = new Point(x, y);
			points[i] = p;
			// xsorted[i] = p;
			// ysorted[i] = p;
		}
		
		int mid = N / 2;
		double xmedian = 0.0;
		
		Arrays.sort(points, new Comparator<Point>(){
			public int compare(Point a, Point b){
				return a.x - b.x;
			}
		});
		
		if(N % 2 == 0) {
			xmedian = (points[mid].x + points[mid - 1].x) / 2;
		} else {
			xmedian = points[mid].x;
		}
		
		double ymedian = 0.0;
		
		Arrays.sort(points, new Comparator<Point>(){
			public int compare(Point a, Point b){
				return a.y - b.y;
			}
		});
	
		if(N % 2 == 0) {
			ymedian = (points[mid].y + points[mid - 1].y) / 2;
		} else {
			ymedian = points[mid].y;
		}
		
		final double X_MEDIAN = xmedian;
		final double Y_MEDIAN = ymedian;
		
		Arrays.sort(points, new Comparator<Point>(){
			public int compare(Point a, Point b){
				double axdiff = (a.x > X_MEDIAN) ? a.x - X_MEDIAN : X_MEDIAN - a.x;
				double aydiff = (a.y > Y_MEDIAN) ? a.y - Y_MEDIAN : Y_MEDIAN - a.y;
				double adiff = Math.max(axdiff, aydiff);
				
				double bxdiff = (b.x > X_MEDIAN) ? b.x - X_MEDIAN : X_MEDIAN - b.x;
				double bydiff = (b.y > Y_MEDIAN) ? b.y - Y_MEDIAN : Y_MEDIAN - b.y;
				double bdiff = Math.max(bxdiff, bydiff);
				
				if(adiff < bdiff) return -1;
				else if(adiff == bdiff) return 0;
				else return 1;
			}
		});
		
		long min = Long.MAX_VALUE;
		for(int i = 0; i < N; i++) {
			Point p = points[i];
			long d = solve(p);
			if(d <= min) min = d;
			else break;
			// System.out.printf("%s : %d", p.toString(), d);
			// System.out.println();
		}
		System.out.println(min);
	}
 
	public static void main(String[] args) throws Exception {
		Reader rd = (args.length == 0) ? new InputStreamReader(System.in) : new FileReader(args[0]);
		new Solution().run(new BufferedReader(rd));
	}
 }
 