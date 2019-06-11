import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static final int MAX = 50001;
	static int[] list = new int[MAX];
	static int[][] d = new int[4][MAX];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			int temp = Integer.parseInt(st.nextToken());
			list[i] = list[i-1] + temp;
		}
		M = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <= 3; i++)
			for (int j = i*M; j <= N; j++)
				d[i][j] = Math.max(d[i][j-1], (list[j] - list[j-M]) + d[i-1][j-M]);
		
		bw.write(d[3][N] + "");
		bw.flush();
	}
}