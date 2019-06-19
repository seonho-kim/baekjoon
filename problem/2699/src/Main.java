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

	static int P, N;
	static final int MAX = 50;
	static Point[] p = new Point[MAX];
	static Stack<Integer> s = new Stack<Integer>();
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		P = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= P; test_case++) {
			N = Integer.parseInt(br.readLine());
			
			int c = (int)Math.ceil((double)N / 5);
			int idx = 0;
			
			for (int i = 0; i < c; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				
				while(st.hasMoreTokens()) {
					int x, y;
					x = Integer.parseInt(st.nextToken());
					y = Integer.parseInt(st.nextToken());
					p[idx++] = new Point(x, y);
				}
			}
			
			Arrays.sort(p, 0, N);
			for (int i = 1; i < N; i++) {
				p[i].p = p[i].x - p[0].x;
				p[i].q = p[i].y - p[0].y;
			}
			Arrays.sort(p, 1, N);
			
			s.clear();
			s.push(0); s.push(1);
			int next = 2;
			
			while (next < N) {
				while (s.size() >= 2) {
					int first, second;
					second = s.pop();
					first = s.peek();
					
					if (ccw(p[first], p[second], p[next]) < 0) {
						s.push(second);
						break;
					}
				}
				s.push(next++);
			}
			
			int size = s.size();
			Integer[] arr = new Integer[size];
			arr = s.toArray(arr);
			
			bw.write(size + "\n");
			for (int i = 0; i < arr.length; i++) {
				bw.write(p[arr[i]].x + " " + p[arr[i]].y + "\n");
			}
			bw.flush();
		}
	}
	static int ccw(Point a, Point b, Point c) {
		long result = 1L*(c.y - a.y)*(b.x - a.x) - 1L*(b.y - a.y)*(c.x - a.x);
		
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
			if (1L*this.p*o.q != 1L*this.q*o.p) {
				if (1L*this.p*o.q < 1L*this.q*o.p) return -1;
				else if (1L*this.p*o.q > 1L*this.q*o.p) return 1;
				return 0;
			}
			
			if (this.y > o.y) return -1;
			else if (this.y < o.y) return 1;
			else {
				if (this.x < o.x) return -1;
				else if (this.x > o.x) return 1;
				return 0;
			}
		}
	}
}