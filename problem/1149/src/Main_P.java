import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_P {

	static int N;
	static int MAXN = 1001;
	static int[][] d = new int[MAXN][3];
	public static void main(String[] args) throws IOException  {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		// d[N][C] -- N번째 집을 C로 칠할 때  N번째 집까지 비용 최솟값
		// R : 0, G : 1, B : 2
		st = new StringTokenizer(br.readLine(), " ");
		d[1][0] = Integer.parseInt(st.nextToken());
		d[1][1] = Integer.parseInt(st.nextToken());
		d[1][2] = Integer.parseInt(st.nextToken());
		
		for (int i = 2; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			d[i][0] = Integer.parseInt(st.nextToken())
					+ Math.min(d[i-1][1], d[i-1][2]);
			d[i][1] = Integer.parseInt(st.nextToken())
					+ Math.min(d[i-1][0], d[i-1][2]);
			d[i][2] = Integer.parseInt(st.nextToken())
					+ Math.min(d[i-1][0], d[i-1][1]);
		}
		
		bw.write(Math.min(Math.min(d[N][0], d[N][1]), d[N][2]) + "\n");
		bw.flush();
	}
}