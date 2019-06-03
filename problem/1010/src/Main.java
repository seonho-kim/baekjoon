import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int N, M, T;
	static final int MAXM = 31;
	static int[][] d = new int[MAXM][MAXM];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			
			d[1][0] = 1;
			d[1][1] = 1;
			for (int i = 2; i <= M; i++) {
				for (int j = 0; j <= i; j++) {
					if (j== 0 || j == i) d[i][j] = 1;
					else d[i][j] = d[i-1][j-1] + d[i-1][j];
				}
			}
			bw.write(d[M][N] + "\n");
		}
		bw.flush();
	}
}