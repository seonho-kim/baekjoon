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

	static int C;
	static int MAX = 100001;
	static Point[] p = new Point[MAX];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		C = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < C; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			p[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(p, 0, C);
		
		for (int i = 1; i < C; i++) {
			p[i].p = p[i].x - p[0].x;
			p[i].q = p[i].y - p[0].y;
		}
		Arrays.sort(p, 1, C);
		
		Stack<Integer> s = new Stack<Integer>();
		int next = 2;
		s.push(0); s.push(1);
		
		while (next < C) {
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

		double result = 0;
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				result = Math.max(result, Math.sqrt((p[arr[i]].x - p[arr[j]].x)*(p[arr[i]].x - p[arr[j]].x)
						+ (p[arr[i]].y - p[arr[j]].y)*(p[arr[i]].y - p[arr[j]].y))); 
			}
		}
		bw.write(String.format("%.10f", result) + "");
		bw.flush();
	}
	static int ccw(Point a, Point b, Point c) {
		long result = 0;
		result = 1L*(c.y - a.y)*(b.x - a.x)-1L*(b.y - a.y)*(c.x - a.x);
		
		if (result > 0) return 1;
		else if (result < 0) return -1;
		else return 0;
	}
	static class Point implements Comparable<Point> {
		int x, y, p ,q;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		@Override
		public int compareTo(Point o) {
			if (1L*o.p*this.q != 1L*this.p*o.q) {
				if (1L*o.p*this.q < 1L*this.p*o.q) return -1;
				else if(1L*o.p*this.q > 1L*this.p*o.q) return 1;
				else return 0;
			}
			
			if (this.y < o.y) return -1;
			else if (this.y > o.y) return 1;
			else {
				if (this.x > o.x) return -1;
				else if (this.x < o.x) return 1;
				else return 0;
			}
		}
	}
}