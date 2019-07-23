import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_P2 {

	static Point[] p = new Point[4];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		for (int i = 0; i < 4; i++) {
			p[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		bw.write((IsIntersect(p[0], p[1], p[2], p[3]) ? 1 : 0) + "");
		bw.flush();
	}
	static boolean IsIntersect(Point a, Point b, Point c, Point d) {
		int ab = ccw(a, b, c)*ccw(a, b, d);
		int cd = ccw(c, d, a)*ccw(c, d, b);
		
		if (ab == 0 && cd == 0) {
			if (a.x > b.x) {
				Point tmp = a;
				a = b;
				b = a;
			} else if (a.x == b.x) {
				if (a.y > b.y) {
					Point tmp = a;
					a = b;
					b = a;
				}
			}
			
			if (c.x > d.x) {
				Point tmp = c;
				c = d;
				d = tmp;
			} else if (c.x == d.x) {
				if (c.y > d.y) {
					Point tmp = c;
					c = d;
					d = tmp;
				}
			}
			
			boolean ad = false, cb = false;
			if (a.x < b.x) ad = true;
			else if (a.x == b.x)
				if (a.y <= b.y) ad = true;
			
			if (c.x < b.x) cb = true;
			else if (c.y == d.y)
				if (c.y <= d.y) cb = true;
			
			return ad && cb;
		}
				
		return ab < 0 && cd < 0;
	}
	static int ccw(Point a, Point b, Point c) {
		long result = 1L*(b.x - a.x)*(c.y - a.y) - 1L*(c.x - a.x)*(b.y - a.y);
		if (result > 0) return 1;
		else if (result < 0) return -1;
		else return 0;
	}
	
	static class Point {
		int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}