import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static Point pp;
	static Point[] p;
	static int answer;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		
		pp = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		p =  new Point[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x, y;
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			
			p[i] = new Point(x, y);
		}
		
		while (true) {
			if (p.length < 3) break;
			boolean c = false;
			
			int n = p.length;
			Arrays.sort(p, 0, n);
			for (int i = 1; i < n; i++) {
				p[i].p = p[i].x - p[0].x;
				p[i].q = p[i].y - p[0].y;
			}
			Arrays.sort(p, 1, n);
			
			Stack<Integer> s = new Stack<Integer>();
			int next = 2;
			s.push(0); s.push(1);
			
			while (next < n) {
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
			
			c = s.size() > 2 ? true : false;
			
			ArrayList<Integer> arr = new ArrayList<Integer>();
			while (!s.isEmpty()) {
				int value = s.pop();
				arr.add(0, value);
			}
			int check = ccw(p[arr.get(arr.size() - 1)], p[arr.get(0)], pp);
			for (int i = 0; i < arr.size() - 1; i++) {
				if (check != ccw(p[arr.get(i)], p[arr.get(i+1)], pp)) {
					check = 0;
					break;
				}
			}
			
			if (check != 0) answer++;
			
			ArrayList<Point> temp = new ArrayList<Point>();
			boolean[] chk = new boolean[n];
			for (int i = 0; i < arr.size(); i++) {
				chk[arr.get(i)] = true;
			}

			for (int i = 0; i < n; i++) {
				if (chk[i] == false) temp.add(p[i]);
			}
			
			p = new Point[temp.size()];
			p = temp.toArray(p);
			
			if (c == false) break;
		}
		bw.write(answer + "");
		bw.flush();
	}
	
	static int ccw(Point a, Point b, Point c) {
		long temp = 1L*(c.y - a.y)*(b.x - a.x) - 1L*(b.y - a.y)*(c.x - a.x);
		
		if (temp > 0) return 1;
		else if (temp < 0) return -1;
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
			if (1L*this.q*o.p != 1L*this.p*o.q) {
				if (1L*this.q*o.p < 1L*this.p*o.q) return -1;
				else if (1L*this.q*o.p > 1L*this.p*o.q) return 1;
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