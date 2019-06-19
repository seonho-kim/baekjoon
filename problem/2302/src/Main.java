import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	static int N, M;
	static final int MAX = 41;
	static boolean list[] = new boolean[MAX];
	static int[] d = new int[MAX];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		long result = 1;
		int prev = 0, curr = 0;
		
		for (int i = 1; i <= M; i++) {
			list[Integer.parseInt(br.readLine())] = true;
		}
		
		d[0] = 1;
		d[1] = 1;
		for (int i = 2; i <= N; i++) {
			d[i] = d[i-1] + d[i-2];
		}
		
		for (int i = 1; i <= N; i++) {
			if (list[i] == true) {
				prev = curr;
				curr = i;
								
				result *= d[(curr - prev - 1)];
			} else if (i == N) {
				prev = curr;
				curr = i + 1;
				
				result *= d[(curr - prev - 1)];
			}
		}
		
		bw.write((result == 0 ? 1 : result) + "");
		bw.flush();
	}
}