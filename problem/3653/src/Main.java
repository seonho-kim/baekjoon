import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int T, N, M;
	static final int MAX = 100000;
	static final int SEG_SIZE = (int)Math.pow(2, Math.ceil(Math.log(2*MAX) / Math.log(2)) + 1);
	
	static int[] t = new int[SEG_SIZE];
	static int[] idx = new int[MAX + 1];
	static int h, n, MAXN;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			Arrays.fill(t, 0);
			Arrays.fill(idx, 0);
			
			h = (int)Math.ceil(Math.log(N + M) / Math.log(2));
			n = (int)Math.pow(2, h);
			MAXN = (int)Math.pow(2, h + 1);
			for (int i = n + M; i < n + M + N; i++) {
				t[i] = 1;
				idx[i - (n + M) + 1] = i - n;
			}

			for (int i = n - 1; i > 0; i--) {
				t[i] = t[i<<1] + t[i<<1|1];
			}			
			
			st = new StringTokenizer(br.readLine(), " ");
			int pushIdx = M - 1;
			for (int i = 1; i <= M; i++) {
				int limit = Integer.parseInt(st.nextToken());
				int result = 0;
				bw.write(query(0, idx[limit]) + " ");
				update(idx[limit], 0);
				update(pushIdx, 1);
				idx[limit] = pushIdx--;
			}
			bw.write("\n");
		}
		bw.flush();
	}

	static int query(int l, int r) {
		int res = 0;
		for (l+=n, r+=n; l<r; l>>=1, r>>=1) {
			if ((l&1) == 1) res += t[l++];
			if ((r&1) == 1) res += t[--r];
		}
		return res;
	}
	static void update(int idx, int v) {
		for (t[idx+=n] = v; idx > 1; idx>>=1) {
			t[idx>>1] = t[idx] + t[idx^1];
		}
	}
}