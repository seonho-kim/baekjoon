import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	static int N;
	static final int MAX = 91;
	static long[][] d = new long[MAX][2];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		
		d[1][0] = 0;
		d[1][1] = 1;
		d[2][0] = 1;
		d[2][1] = 0;
		
		for (int i = 3; i <= N; i++) {
			d[i][0] = d[i-1][0] + d[i-1][1];
			d[i][1] = d[i-2][0] + d[i-2][1];
			
		}
		
		bw.write((d[N][0] + d[N][1]) + "");
		bw.flush();
	}
}