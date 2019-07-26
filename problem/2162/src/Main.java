import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static final int MAX = 3001;
	static line[] l = new line[MAX];
	static int[] p = new int[MAX];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		Arrays.fill(p, 1, N+1, -1);

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			l[i] = new line(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		for (int i = 1; i < N; i++) {
			for (int j = i+1; j <= N; j++) {
				if (IsIntersect(l[i].x1, l[i].y1, l[i].x2, l[i].y2, l[j].x1, l[j].y1, l[j].x2, l[j].y2)) {
					union(i, j);
				}
			}
		}
		
		int answer = 3001, cnt = 0;
		for (int i = 1; i <= N; i++) {
			if (p[i] < 0) {
				cnt++;
				answer = Math.min(answer, p[i]);
			}
		}
		bw.write(cnt + "\n" + (answer*-1));
		bw.flush();
	}
	static boolean IsIntersect(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
		
		int ab = ccw(x1, y1, x2, y2, x3, y3)*ccw(x1, y1, x2, y2, x4, y4);
		int cd = ccw(x3, y3, x4, y4, x1, y1)*ccw(x3, y3, x4, y4, x2, y2);

		if (ab == 0 && cd == 0) {
			if (x1 > x2) {
				int tmpx = x1;
				x1 = x2;
				x2 = tmpx;
				
				int tmpy = y1;
				y1 = y2;
				y2 = tmpy;
			} else if (x1 == x2) {
				if (y1 > y2) {
					int tmpx = x1;
					x1 = x2;
					x2 = tmpx;
					
					int tmpy = y1;
					y1 = y2;
					y2 = tmpy;
				}
			}
			
			if (x3 > x4) {
				int tmpx = x3;
				x3 = x4;
				x4 = tmpx;
				
				int tmpy = y3;
				y3 = y4;
				y4 = tmpy;
			} else if (x3 == x4) {
				if (y3 > y4) {
					int tmpx = x3;
					x3 = x4;
					x4 = tmpx;
					
					int tmpy = y3;
					y3 = y4;
					y4 = tmpy;
				}
			}
			
			boolean cb = false, ad = false;
			if (x3 < x2) {
				cb = true;
			} else if (x3 == x2) {
				if (y3 <= y2) {
					cb = true;
				}
			}
			
			if (x1 < x4) {
				ad = true;
			} else if (x1 == x4) {
				if (y1 <= y4) {
					ad = true;
				}
			}
				
			return cb && ad;
		}
		
		return ab <= 0 & cd <= 0;
	}
	static int ccw(int x1, int y1, int x2, int y2, int x3, int y3) {
		long result = 1L*(x2 - x1)*(y3 - y1) - 1L*(x3 - x1)*(y2 - y1);
		if (result > 0) return 1;
		else if (result < 0) return -1;
		else return 0;
	}
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if (a == b) return;
		p[a] += p[b];
		p[b] = a;
	}
	static int find(int n) {
		if (p[n] < 0) return n;
		p[n] = find(p[n]);
		return p[n];
	}
	static class line {
		int x1, y1, x2, y2;
		public line(int x1, int y1, int x2, int y2) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}
	}
}