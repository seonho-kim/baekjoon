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

	static int n;
	static final int MAX = 100;
	static Point[] p = new Point[MAX], q;
	static double answer;
	static int test_case = 0;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		while ((n = Integer.parseInt(br.readLine())) != 0) {
			answer = Long.MAX_VALUE;
			test_case++;
			
			for (int i = 1; i <= n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int x, y;
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				
				p[i-1] = new Point(x, y);
			}
			
			
			Arrays.sort(p, 0, n);
			for (int i = 1; i < n; i++) {
				p[i].p = p[i].x - p[0].x;
				p[i].q = p[i].y - p[0].y;
			}
			Arrays.sort(p, 1, n);
			
			Stack<Integer> s = new Stack<Integer>();
			int convex = 2;
			s.push(0); s.push(1);
			
			while (convex < n) {
				while (s.size() >= 2) {
					int first, second;
					second = s.pop();
					first = s.peek();
					
					if (ccw(p[first], p[second], p[convex]) > 0) {
						s.push(second);
						break;
					}
				}
				s.push(convex++);
			}
			
			Integer[] arr = new Integer[s.size()];
			arr = s.toArray(arr);
			q = new Point[arr.length];
			for (int i = 0; i < arr.length; i++) {
				q[i] = p[arr[i]];
			}
			
			int size = q.length;
			double length = 0;
			for (int i = 0; i < size; i++) {
				int cur = i;
				int next = (i+1)%size;
				length = 0;
				
				for (int j = 0; j < size; j++) {
					if (j == cur || j == next) continue;
					
					int a = q[next].y - q[cur].y;
					int b = -1*(q[next].x - q[cur].x);
					int c = -1*q[next].x*a -1*q[next].y*b;
					
					int h = a*q[j].x + b*q[j].y + c;
					if (h < 0) h = h*(-1);
					length = Math.max(length, (double)h/Math.sqrt((a*a + b*b)));
				}
				answer = Math.min(answer, length);
			}
			
			bw.write("Case " + test_case + ": " + String.format("%.2f", (answer + 0.00499999999))  + "\n");
			bw.flush();
		}
	}
	static int ccw (Point a, Point b, Point c) {
		long result = 1L*(c.y - a.y)*(b.x - a.x) - 1L*(c.x - a.x)*(b.y - a.y);
		if (result > 0) return 1;
		else if (result < 0) return -1;
		else return 0;
	}
	static class Point implements Comparable<Point> {
		int x, y, p, q;
		public Point(int x , int y) {
			this.x = x;
			this.y = y;
		}
		@Override
		public int compareTo(Point o) {
			if (this.q*o.p != this.p*o.q) {
				if (this.q*o.p < this.p*o.q) return -1;
				else if (this.q*o.p > this.p*o.q) return 1;
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