import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static final int MAX = 1001;
	static int[][] map = new int[MAX][MAX];
	static int[][] d = new int[MAX][MAX];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		d[1][1] = map[1][1];
		// d[N][M] = d[N-1][M-1] + a[N][M]
		// d[N][M] = d[N-1][M] + a[N][M]
		// d[N][M] = d[N][M-1] + a[N][M]
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (i > 1 && j > 1)
					d[i][j] = Math.max(d[i][j], d[i-1][j-1] + map[i][j]);
				if (i > 1)
					d[i][j] = Math.max(d[i][j], d[i-1][j] + map[i][j]);
				if (j > 1)
					d[i][j] = Math.max(d[i][j], d[i][j-1] + map[i][j]);
			}
		}
		
		bw.write(d[N][M] + "");
		bw.flush();
	}
}