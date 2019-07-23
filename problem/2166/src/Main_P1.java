import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_P1 {

	static int N;
	static final int MAX = 10000;
	static Point[] p = new Point[MAX + 1];
	static long answer;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			p[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		p[N] = new Point(p[0].x, p[0].y);
		
		for (int i = 0; i < N; i++) {
			answer += (1L*p[i].x*p[i+1].y - 1L*p[i].y*p[i+1].x);
		}
		if (answer < 0) answer *= -1;
		bw.write(String.format("%.1f", (double)answer/2) + "");
		bw.flush();
	}
	static class Point {
		int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}