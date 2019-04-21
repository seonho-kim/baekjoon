import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static final int MAX = 501;
	static int N;
	static int[][] d = new int[MAX][MAX];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		d[1][1] = Integer.parseInt(st.nextToken());
		for (int i = 2; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int j = 1;
			while(st.hasMoreTokens()) {
				int v = Integer.parseInt(st.nextToken());
				d[i][j] = Math.max(d[i-1][j-1] + v, d[i-1][j] + v);
				j++;
			}
		}
		
		int answer = 0;
		for (int i = 1; i <= N; i++) {
			answer = Math.max(answer, d[N][i]);
		}
		bw.write(Integer.valueOf(answer) + "\n");
		bw.flush();
	}
}