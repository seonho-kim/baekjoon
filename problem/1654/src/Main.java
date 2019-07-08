import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int N, K;
	static final int MAX = 10001;
	static long[] list = new long[MAX];
	static long max;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		for (int i = 1; i <= K; i++) {
			list[i] = Long.parseLong(br.readLine());
		}
		
		long lo = 0, hi = Long.MAX_VALUE;
		
		while (lo <= hi) {
			long mid = (lo + hi) / 2;
			int result = 0;
			
			for (int i = 1; i <= K; i++) {
				result += (list[i] / mid);
			}

			
			if (result >= N) {
				lo = mid + 1;
				
				max = Math.max(max, mid);
			} else hi = mid - 1;
		}
		bw.write(max + "");
		bw.flush();
	}
}