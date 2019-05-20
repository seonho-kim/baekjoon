import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	static final int MAX = 301;
	static int N;
	static int[] list = new int[MAX];
	static int[][] d = new int[MAX][3];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(st.nextToken());
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			list[i] = Integer.parseInt(st.nextToken());
		}
		
		d[1][1] = list[1];
		d[2][1] = list[2];
		d[2][2] = d[1][1] + list[2];
		for (int i = 3; i <= N; i++) {
			d[i][1] = Math.max(d[i-2][1] + list[i], d[i-2][2] + list[i]);
			d[i][2] = d[i-1][1] + list[i];
		}
		bw.write(Math.max(d[N][1], d[N][2]) + "");
		bw.flush();
	}
}