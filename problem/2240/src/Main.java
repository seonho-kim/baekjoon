import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int T, W;
	static final int TMAX = 1001;
	static final int WMAX = 32;
	static int[][][] d = new int[2][TMAX][WMAX];
	static int answer;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		// d[나무번호][T초][W횟수]
		// d[0][T][W] = Max(d[0][T-1][W]+1, d[1][T-1][W-1]+1);
		// d[1][T][W] = Max(d[1][T-1][W]+1, d[0][T-1][W-1]+1);
		
		T = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		for (int i = 1; i <= T; i++) {
			int tree = Integer.parseInt(br.readLine());
			for (int j = 1; j <= W+1; j++) {
				if (tree == 1) {
					d[0][i][j] = Math.max(d[0][i-1][j]+1, d[1][i-1][j-1]+1);
					d[1][i][j] = Math.max(d[0][i-1][j-1], d[1][i-1][j]);
				}
				else {
					if (i == 1 && j == 1) continue;
					d[0][i][j] = Math.max(d[1][i-1][j-1], d[0][i-1][j]);
					d[1][i][j] = Math.max(d[1][i-1][j]+1, d[0][i-1][j-1]+1);
				}
			}
		}
		
		bw.write(Math.max(answer, Math.max(d[0][T][W+1], d[1][T][W+1])) + "");
		bw.flush();
	}
}