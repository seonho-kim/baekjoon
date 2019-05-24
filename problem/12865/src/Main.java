import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int N, K;
	static final int NMAX = 100;
	static final int KMAX = 100000;
	static int[][] d = new int[NMAX + 1][KMAX + 1];
	static int[][] profit = new int[NMAX + 1][2];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			profit[i][0] = Integer.parseInt(st.nextToken());
			profit[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= K; j++) {
				if (j < profit[i][0])
					d[i][j] = d[i-1][j];
				else
					d[i][j] = Math.max(d[i-1][j], d[i-1][j - profit[i][0]] + profit[i][1]);
			}
		}
		bw.write(d[N][K] + "");
		bw.flush();
	}
}