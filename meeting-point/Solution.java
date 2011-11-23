/**
 * @problem Grid Walking
 * @author ShengMin Zhang
 *
 * @revision 1.0
 * - not sure if the naive would be fast enough, basically try out all house as the meeting point, find the minimum
 */
 
 import java.io.*;
 import java.util.*;
 
 public class Solution {
	class Point {
		private long x, y;
		
		public Point(long x, long y) {
			this.x = x;
			this.y = y;
		}	
		
		public long computeTime(Point p){
			long xdiff = (p.x > x) ? p.x - x : x - p.x;
			long ydiff = (p.y > y) ? p.y - y : y - p.y;
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
		
		for(int i = points.length - 1; i >= 0; i--){
			Point p = points[i];
			long t = p.computeTime(meeting);
			// System.out.println(t);
			d += t;
		}
		// System.out.println("------------------------------");
		return d;
	}
 
	void run(BufferedReader rd) throws Exception {
		int N = Integer.parseInt(rd.readLine());
		if(N == 0) return;
		
		points = new Point[N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(rd.readLine());
			long x = Long.parseLong(st.nextToken());
			long y = Long.parseLong(st.nextToken());
			points[i] = new Point(x, y);
		}
		
		long min = Long.MAX_VALUE;
		// Point meetingPoint = null;
		
		for(int i = 0; i < N; i++){
			long d = solve(points[i]);
			//System.out.printf("%s : %d", points[i].toString(), d);
			//System.out.println();
			// System.out.println(d);
			if(d < min) {
				min = d;
				//meetingPoint = points[i];
			}
		}
		//System.out.println(meetingPoint);
		System.out.println(min);
		
	}
 
	public static void main(String[] args) throws Exception {
		Reader rd = (args.length == 0) ? new InputStreamReader(System.in) : new FileReader(args[0]);
		new Solution().run(new BufferedReader(rd));
	}
 }
 