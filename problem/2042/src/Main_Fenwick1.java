import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_Fenwick1 {

	static int N, M, K;
	static final int MAX = 1000000;
	static long[] a = new long[MAX+1];
	static long[] t = new long[MAX+1];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		for (int i = 1; i <= N; i++) {
			a[i] = Integer.parseInt(br.readLine());
			update(t, i, a[i]);
		}

		M += K;
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine(), " ");
			int order = Integer.parseInt(st.nextToken());
			
			if (order == 1) {
				int left = Integer.parseInt(st.nextToken());
				long right = Long.parseLong(st.nextToken());
				long diff = right-a[left];
				a[left] = right;
				update(t, left, diff);	            
			} else if (order == 2) {
				int t2 = Integer.parseInt(st.nextToken());
				int t3 = Integer.parseInt(st.nextToken());
				bw.write((sum(t, t3) - sum(t, t2-1)) + "\n");
			}
			
		}
		bw.flush();
	}
	static long sum(long t[], int i) {
		long ans = 0;
		while (i > 0) {
			ans += t[i];
			i -= (i & -i);
		}
		return ans;
	}
	static void update(long[] t, int i, long diff) {
		while (i <= N) {
			t[i] += diff;
			i += (i & -i);
		}
	}
}