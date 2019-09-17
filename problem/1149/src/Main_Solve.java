import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_Solve {

	static int N;
	static final int MAX = 1001;
	static int[][] list = new int[MAX][3];
	static int[][] d = new int[MAX][3];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			list[i][0] = Integer.parseInt(st.nextToken());
			list[i][1] = Integer.parseInt(st.nextToken());
			list[i][2] = Integer.parseInt(st.nextToken());
		}
		
		d[1][0] = list[1][0];
		d[1][1] = list[1][1];
		d[1][2] = list[1][2];
		
		for (int i = 2; i <= N; i++) {
			d[i][0] = Math.min(d[i-1][1]+list[i][0], d[i-1][2]+list[i][0]);
			d[i][1] = Math.min(d[i-1][0]+list[i][1], d[i-1][2]+list[i][1]);
			d[i][2] = Math.min(d[i-1][0]+list[i][2], d[i-1][1]+list[i][2]);
		}
		
		bw.write(Math.min(Math.min(d[N][0], d[N][1]), d[N][2]) + "");
		bw.flush();
	}
}