import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	static int N, K;
	static final int MAX = 1001;
	static final int mod = 1000000003;
	static int[][] d = new int[MAX][MAX];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <= N; i++) {
			d[i][0] = 1;
			d[i][1] = i;
		}
		
		for (int i = 2; i <= N; i++) {
			for (int j = 2; j <= N; j++) {
				d[i][j] = (d[i-2][j-1] + d[i-1][j]) % mod;
			}
		}
		bw.write((d[N-3][K-1] + d[N-1][K])%mod + "");
		bw.flush();
	}
}
