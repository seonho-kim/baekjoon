import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {

	static int N;
	static final int MAX = 101;
	static int[][] d = new int[MAX][10];
	static int result = 0;
	static final int mod = 1000000000;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		
		Arrays.fill(d[1], 1);
		d[1][0] = 0;
		
		for (int i = 2; i <= N; i++) {
			for (int j = 0; j < 10; j++) {
				if (j == 0)
					d[i][j] = d[i-1][1] % mod;
				else if (j == 9)
					d[i][j] = d[i-1][8] % mod;
				else
					d[i][j] = (d[i-1][j-1] + d[i-1][j+1]) % mod;
			}
		}
		
		for (int i = 0; i < 10; i++) {
			result = (result + d[N][i]) % mod;
		}
		bw.write((result % mod) + "");
		bw.flush();
	}
}