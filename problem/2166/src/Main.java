import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[][] p = new int[100001][2];
	static long result;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			p[i][0] = Integer.parseInt(st.nextToken());
			p[i][1] = Integer.parseInt(st.nextToken());
		}
		
		p[N][0] = p[0][0];
		p[N][1] = p[0][1];
		
		for (int i = 0; i <= N; i++) {
			result += (1L*p[i][0]*p[i+1][1] - 1L*p[i][1]*p[i+1][0]);
		}
		
		bw.write(String.format("%.1f", (double)Math.abs(result)/2) + "");
		bw.flush();
	}	
}