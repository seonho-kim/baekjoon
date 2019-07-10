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

	static int N, L, cnt;
	static double answer;
	static Point[] p = new Point[1000];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt") );
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			p[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(p, 0, N);
		for (int i = 1; i < N; i++) {
			p[i].p = p[i].x - p[0].x;
			p[i].q = p[i].y - p[0].y;
		}
		Arrays.sort(p, 1, N);
		
		Stack<Integer> s = new Stack<Integer>();
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
		
		cnt = s.size();
		Integer[] arr = new Integer[s.size()];
		arr = s.toArray(arr);
		for (int i = 0; i < cnt; i++) {
			int cur = arr[i];
			int nex = arr[(i+1)%cnt];
			
			answer += distance(p[cur], p[nex]); 
		}

		answer += L*2*Math.PI;
		bw.write(String.format("%.0f", answer) + "");
		bw.flush();
	}
	static double distance(Point a, Point b) {
		int dx, dy;
		dx = b.x - a.x;
		dy = b.y - a.y;
		
		return Math.sqrt(dx*dx + dy*dy);
	}
	static int ccw(Point a, Point b, Point c) {
		long result = 1L*(c.y - a.y)*(b.x - a.x) - 1L*(c.x - a.x)*(b.y - a.y);
		
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