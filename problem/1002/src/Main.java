import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int T;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine(), " ");
			long x1, y1, x2, y2, r1, r2;
			x1 = Integer.parseInt(st.nextToken());
			y1 = Integer.parseInt(st.nextToken());
			r1 = Integer.parseInt(st.nextToken());
			x2 = Integer.parseInt(st.nextToken());
			y2 = Integer.parseInt(st.nextToken());
			r2 = Integer.parseInt(st.nextToken());
			
			long dx, dy;
			dx = x1 - x2;
			dy = y1 - y2;
			
			if (r1 > r2) {
				long tmp = r1;
				r1 = r2;
				r2 = tmp;
			}
			long add = r1 + r2;
			long sub = r2 - r1;
			add = add*add;
			sub = sub*sub;
			
			long d = dx*dx + dy*dy;
			
			if (sub < d && d < add) bw.write("2\n");
			else if (d == add || (d == sub && d != 0)) bw.write("1\n");
			else if (d < sub || d > add) bw.write("0\n");
			else if (d == 0) {
				if (r1 == r2) bw.write("-1\n");
				else bw.write("0\n");
			}
			bw.flush();
		}
	}
}