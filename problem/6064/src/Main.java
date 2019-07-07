import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int T, M, N, x, y;
	static int gcd, lcm;
	static long answer;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			
			gcd = gcd(M, N);
			lcm = M*N/gcd;
			answer = x % (M + 1);
			
			int temp = x;
			for (int i = 0; i < N; i++) {
				int ty = temp % N == 0 ? N : temp % N;
				if (ty == y) {
					break;
				}
				
				temp = ty + M;
				answer += M;
			}
			bw.write((answer > lcm ? "-1" : answer) + "\n");
			bw.flush();
		}
	}
	static int gcd(int a, int b) {
		while(true) {
			int r = a%b;
			
			if (r == 0) return b;
			
			a = b;
			b = r;
		}
	}
}