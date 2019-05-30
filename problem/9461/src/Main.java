import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {

	static int T, N;
	static final int MAX = 101;
	static long[] d = new long[MAX];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		T = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			
			Arrays.fill(d, 0);
			
			d[1] = 1;
			d[2] = 1;
			d[3] = 1;
			d[4] = 2;
			d[5] = 2;
			for (int i = 6; i <= N; i++) {
				d[i] = d[i - 1] + d[i - 5];
			}
			bw.write(d[N] + "\n");
		}
		bw.flush();
	}
}