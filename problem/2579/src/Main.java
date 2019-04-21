import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static final int MAX_STAIR = 301;
	static int N;
	static int[][] d = new int[MAX_STAIR][3];
	static int[] v = new int[MAX_STAIR]; 
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(st.nextToken());
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			v[i] = Integer.parseInt(st.nextToken());
		}
		
		d[1][1] = v[1];
		d[2][1] = v[2];
		d[2][2] = d[1][1] + v[2];
		for (int i = 3; i <= N; i++) {
			d[i][1] = Math.max(d[i-2][1] + v[i], d[i-2][2] + v[i]);
			d[i][2] = d[i-1][1] + v[i];
		}
		bw.write(Integer.valueOf(Math.max(d[N][1], d[N][2])) + "\n");
		bw.flush();
	}
}