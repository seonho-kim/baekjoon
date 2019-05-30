import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static final int MAX = 101;
	static int[][] map = new int[MAX][MAX];
	static long[][] d = new long[MAX][MAX];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		d[1][1] = 1;
		// d[N][N] += d[N-i][N]
		// d[N][N] += d[N][N-i]
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i + map[i][j] <= N && map[i + map[i][j]][j] != 0)
					d[i + map[i][j]][j] += d[i][j]; 
				if (j + map[i][j] <= N && map[i][j + map[i][j]] != 0)
					d[i][j + map[i][j]] += d[i][j];
			}		
		}
		
		for (int i = 1; i < N; i++) {
			if (i + map[i][N] == N)
				d[N][N] += d[i][N];
			if (i + map[N][i] == N)
				d[N][N] += d[N][i];
		}
		
		bw.write(d[N][N] + "");
		bw.flush();
	}
}