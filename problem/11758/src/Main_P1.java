import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_P1 {

	static Point[] p = new Point[3];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		for (int i = 1; i <= 3; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			p[i-1] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		bw.write(ccw(p[0], p[1], p[2]) + "");
		bw.flush();
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