import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_P1 {

	static int N;
	static final int MOD = 10007, MAX = 1001;
	static int[] d = new int[MAX];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		d[0] = 1;
		d[1] = 1;
		for (int i = 2; i <= N; i++) {
			d[i] = ((d[i-1] % MOD) + ((d[i-2] << 1) % MOD)) % MOD;
		}
		System.out.println(d[N]);
	}
}