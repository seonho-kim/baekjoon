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

	static int T, n, m;
	static final int MAX = 100;
	static Point[] p, q;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine(), " ");
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			if (n == 0 && m == 0) break;
			
			p = new Point[n];
			q = new Point[m];
			for (int i = 0; i < n; i++) {
				int x, y;
				st = new StringTokenizer(br.readLine(), " ");
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				p[i] = new Point(x, y);
			}
			for (int i = 0; i < m; i++) {
				int x, y;
				st = new StringTokenizer(br.readLine(), " ");
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				q[i] = new Point(x, y);
			}
			
			Arrays.sort(p, 0, n);
			for (int i = 1; i < n; i++) {
				p[i].p = p[i].x - p[0].x;
				p[i].q = p[i].y - p[0].y;
			}
			Arrays.sort(p, 1, n);
			Arrays.sort(q, 0, m);
			for (int i = 1; i < m; i++) {
				q[i].p = q[i].x - q[0].x;
				q[i].q = q[i].y - q[0].y;
			}
			Arrays.sort(q, 1, m);
			
			Stack<Integer> s = new Stack<Integer>();
			s.push(0);
			if (p.length >= 2) {
				s.push(1);
				int next = 2;
				while(next < n) {
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
			}
			Stack<Integer> t = new Stack<Integer>();
			t.push(0);
			if (q.length >= 2) {
				t.push(1);
				int next = 2;
				while(next < m) {
					while (t.size() >= 2) {
						int first, second;
						second = t.pop();
						first = t.peek();
						if (ccw(q[first], q[second], q[next]) > 0) {
							t.push(second);
							break;
						}
					}
					t.push(next++);
				}
			}
			
			Point[] hullp, hullq;
			Integer[] arrP = new Integer[s.size()];
			arrP = s.toArray(arrP);
			hullp = new Point[arrP.length];
			for (int i = 0; i < arrP.length; i++) {
				hullp[i] = p[arrP[i]];
			}
			Integer[] arrQ = new Integer[t.size()];
			arrQ = t.toArray(arrQ);
			hullq = new Point[arrQ.length];
			for (int i = 0; i < arrQ.length; i++) {
				hullq[i] = q[arrQ[i]];
			}
			
			if(polygonIntersects(hullp, hullq))
	            bw.write("NO\n");
	        else
	        	bw.write("YES\n");
			bw.flush();
		}
	}
	static boolean polygonIntersects(Point[] p, Point[] q) {
		int n = p.length, m = q.length;
		
		if (IsInside(q, p[0]) || IsInside(p, q[0])) return true;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (segmentIntersects(p[i], p[(i+1)%n], q[j], q[(j+1)%m]))
					return true;
			}
		}
		return false;
	}
	static boolean segmentIntersects(Point a, Point b, Point c, Point d) {
		int ab = ccw(a, b, c) * ccw(a, b, d);
		int cd = ccw(c, d, a) * ccw(c, d, b);
		
		if (ab == 0 && cd == 0) {
			if (a.x > b.x) {
				Point tmp = a;
				a = b;
				b = tmp;
			} else if (a.x == b.x) {
				if (a.y > b.y) {
					Point tmp = a;
					a = b;
					b = tmp;
				}
			}
			if (c.x > d.x) {
				Point tmp = c;
				c = d;
				d = tmp;
			} else if(c.x == d.x) {
				if (c.y > d.y) {
					Point tmp = c;
					c = d;
					d = tmp;
				}
			}
			
			boolean cb = false, ad = false;
			if (c.x < b.x) { 
				cb = true;
			} else if (c.x == b.x){
				if (c.y <= b.y) {
					cb = true;
				}
			}
			
			if (a.x < d.x) {
				ad = true;
			} else if (a.x == d.x) {
				if (a.y <= d.y) {
					ad = true;
				}
			}
			return cb && ad;
		}
		return ab <= 0 && cd <= 0;
	}
	static boolean IsInside(Point[] p, Point q) {
		int cross = 0;
		
		for (int i = 0; i < p.length; i++) {
			int j = (i+1)%p.length;
						
			if (p[i].y > q.y != p[j].y > q.y) {
				double atX = (p[j].x - p[i].x) * (q.y - p[i].y) / (p[j].y - p[i].y) + p[i].x;
				if (q.x < atX) cross++;
			}
		}
		return (cross&1) == 1;
	}
	static int ccw(Point a, Point b, Point c) {
		long result = 1L*(b.x - a.x)*(c.y - a.y) - 1L*(b.y - a.y)*(c.x - a.x);
		if (result > 0) return 1;
		else if (result < 0) return -1;
		else return 0;
	}
	static class Point implements Comparable<Point> {
		int x, y, p, q;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
			
		@Override
		public int compareTo(Point o) {
			if (this.q*o.p != this.p*o.q) {
				if (this.q*o.p < this.p*o.q) return -1;
				else if (this.q*o.p > this.p*o.q) return 1;
				return 0;
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