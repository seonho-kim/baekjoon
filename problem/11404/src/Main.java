import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int n, m;
	static final int NMAX = 101;
	static final int MMAX = 100001;
	static int[][] list = new int[NMAX][NMAX];
	static int[][] map = new int[NMAX][NMAX];
	static final int INF = 100000000;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (i == j) list[i][j] = 0;
				else list[i][j] = INF;
			}
		}
		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from, to, weight;
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			weight = Integer.parseInt(st.nextToken());
			
			list[from][to] = Math.min(list[from][to], weight);
		}
		
		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					list[i][j] = Math.min(list[i][j], list[i][k] + list[k][j]); 
				}
			}
		}
		
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (i == j || list[i][j] == INF) bw.write("0 ");
				else bw.write(list[i][j] + " ");
			}
			bw.write("\n");
		}
		bw.flush();
	}
}