import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_P2 {

	static final int MAX = 1000*1000 + 1;
	static int N;
	static int[] d = new int[MAX];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		Arrays.fill(d, 0, N+1, Integer.MAX_VALUE);
		d[1] = 0;
		for (int i = 2; i <= N; i++) {
			if (i % 3 == 0) d[i] = Math.min(d[i], d[i/3] + 1);
			if (i % 2 == 0) d[i] = Math.min(d[i], d[i/2] + 1);
			d[i] = Math.min(d[i], d[i-1] + 1);
		}
		System.out.println(d[N]);
	}
}