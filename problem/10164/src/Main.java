import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int N, M, K;
	static final int MAXN = 16;
	static int[][] d = new int[MAXN][MAXN];
	static int midX, midY;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		if (K != 0) {
			midY = K % M == 0 ? M : K % M; 
			midX = (K - midY) / M + 1;
		} else {
			midX = N;
			midY = M;
		}
		
		d[1][1] = 1;
		for (int i = 1; i <= midX; i++) {
			for (int j = 1; j <= midY; j++) {
				if (i > 1) d[i][j] += d[i-1][j];
				if (j > 1) d[i][j] += d[i][j-1]; 
			}
		}
		d[midX][midY] = d[midX-1][midY] + d[midX][midY-1]; 
		
		
		for (int i = midX; i <= N; i++) {
			for (int j = midY; j <= M; j++) {
				if (i > midX) d[i][j] += d[i-1][j];
				if (j > midY) d[i][j] += d[i][j-1]; 
			}
		}
		d[N][M] = d[N-1][M] + d[N][M-1];
		
		bw.write(d[N][M] + "");
		bw.flush();
	}
}