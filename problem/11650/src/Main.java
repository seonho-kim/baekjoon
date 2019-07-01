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
	static final int MAX = 100001;
	static Point[] map = new Point[MAX];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		for (int i = 1 ; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			map[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(map, 1, N+1);
		for (int i = 1; i <= N; i++) {
			bw.write(map[i].x + " " + map[i].y + "\n");
			bw.flush();
		}
	}
	static class Point implements Comparable<Point> {
		int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		@Override
		public int compareTo(Point o) {
			if (this.x < o.x) return -1;
			else if (this.x > o.x) return 1;
			else {
				if (this.y < o.y) return -1;
				else if (this.y > o.y) return 1;
				else return 0;
			}
		}
	}
}