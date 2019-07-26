import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	static int T, N;
	static Point[] p;
	static Stack<Integer> s = new Stack<Integer>();
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
				
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine().trim());
			
			p = new Point[N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(),	" ");
				p[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			Arrays.sort(p, 0, N);
			for (int i = 1; i < N; i++) {
				p[i].p = p[i].x - p[0].x;
				p[i].q = p[i].y - p[0].y;
			}
			Arrays.sort(p, 1, N);
			
			s.clear();
			int next = 2;
			s.push(0); s.push(1);
			
			while (next < N) {
				while (s.size() >= 2) {
					int first, second;
					second = s.pop();
					first = s.peek();
					
					if (ccw(p[first], p[second], p[next]) > 0) {
						s.push(second);
						break;
					}
				}
				s.push(next++);
			}
			
			Integer[] arr = new Integer[s.size()];
			arr = s.toArray(arr);
			
		    int j = 1;
		    long answer = 0;
		    Point pp = new Point(0, 0), pq = new Point(0, 0);
		    for (int i = 0; i < arr.length; i++) {
		    	int ni = (i+1)%arr.length;
		    	for (;;) {
		    		int nj = (j+1)%arr.length;
		    		int v = ccw(new Point(0, 0)
		    				, new Point(p[arr[ni]].x - p[arr[i]].x, p[arr[ni]].y - p[arr[i]].y)
		    				, new Point(p[arr[nj]].x - p[arr[j]].x, p[arr[nj]].y - p[arr[j]].y));
		    		
		    		if (v > 0) j = nj;
		    		else break;
		    	}
		    	long v = (p[arr[j]].x - p[arr[i]].x)*(p[arr[j]].x - p[arr[i]].x)
		    			+ (p[arr[j]].y - p[arr[i]].y)*(p[arr[j]].y - p[arr[i]].y);
		    	if (answer < v) {
		    		answer = v;
		    		pp = p[arr[i]];
		    		pq = p[arr[j]];
		    	}
		    }
		    bw.write(pp.x + " " + pp.y + " " + pq.x + " " + pq.y + "\n");
			bw.flush();
		}
	}
	static int ccw(Point a, Point b, Point c) {
		long result = 1L*(b.x - a.x)*(c.y - a.y) - 1L*(c.x - a.x)*(b.y - a.y);
		
		if (result > 0) return 1;
		else if (result < 0) return -1;
		else return 0;
	}
	static class Point implements Comparable<Point> {
		
		long x, y, p, q;
		public Point(long x, long y) {
			this.x = x;
			this.y = y;
		}
		
		@Override
		public int compareTo(Point o) {
			if (1L*this.q*o.p != 1L*this.p*o.q) {
				if (1L*this.q*o.p < 1L*this.p*o.q) return -1;
				else if (1L*this.q*o.p > 1L*this.p*o.q) return 1;
				else return 0;
			}
			
			if (this.y < o.y) return -1;
			else if (this.y > o.y) return 1;
			else {
				if (this.x < o.x) return -1;
				else if (this.x > o.x) return 1;
				else return 0;
			}
		}
	}
}