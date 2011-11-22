/**
 * @problem Grid Walking
 * @author ShengMin Zhang
 *
 * @revision 1.0
 * - not sure if the naive would be fast enough, basically try out all house as the meeting point, find the minimum
 */
 
 import java.io.*;
 import java.util.*;
 
 public class Sample {
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
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(rd.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			points[i] = new Point(x, y);
		}
		
		long min = Long.MAX_VALUE;
		
		for(int i = 0; i < N; i++){
			long d = solve(points[i]);
			// System.out.println(d);
			if(d < min) min = d;
		}
		
		System.out.println(min);
		
	}
 
	public static void main(String[] args) throws Exception {
		Reader rd = (args.length == 0) ? new InputStreamReader(System.in) : new FileReader(args[0]);
		new Sample().run(new BufferedReader(rd));
	}
 }
 