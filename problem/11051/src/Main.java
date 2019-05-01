import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int N, K;
	static int[][] d;
	static final int mod = 10007;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		d = new int[N+1][N+1];
		
		d[0][0] = 1;
		d[1][0] = 1;
		d[1][1] = 1;
		
		for (int i = 2; i <= N; i++) {
			for (int j = 0; j <= i; j++) {
				if (j == 0 || j == N) d[i][j] = 1;
				else d[i][j] = (d[i-1][j-1] % mod) + (d[i-1][j] % mod);
				
				d[i][j] = d[i][j] % mod;
			}
		}
		
		bw.write(Integer.valueOf(d[N][K]) + "\n");
		bw.flush();
	}
}