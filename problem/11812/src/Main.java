import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static long N;
	static int K, Q;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Long.parseLong(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		while (Q-- > 0) {
			st = new StringTokenizer(br.readLine(), " ");
			long lo, hi;
			lo = Long.parseLong(st.nextToken());
			hi = Long.parseLong(st.nextToken());
			
			if (K == 1) {
				bw.write(Math.abs(lo - hi) + "\n");
				bw.flush();
				continue;
			}
			
			int len = 0;
			while (lo != hi) {
				while(lo > hi) {
					len++;
					lo = getParent(lo, K);
				}
				while (hi > lo) {
					len++;
					hi = getParent(hi, K);
				}
			}
			bw.write(len + "\n");
			bw.flush();
		}
	}
	static long getParent(long a, long k) {
		return (a + k -2) / k;
	}
}
