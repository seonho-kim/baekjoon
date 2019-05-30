import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static final int MAX = 100000;
	static final int SEG_SIZE = (int)Math.pow(2, Math.ceil(Math.log(MAX) / Math.log(2)) + 1);
	static int[] maxt = new int[SEG_SIZE];
	static int[] mint = new int[SEG_SIZE];
	static int h, n, MAXN;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		h = (int)Math.ceil(Math.log(N) / Math.log(2));
		n = (int)Math.pow(2, h);
		MAXN = (int)Math.pow(2, h + 1);
		for (int i = n; i < n + N; i++) {
			maxt[i] = mint[i] = Integer.parseInt(br.readLine());
		}
		for (int i = n + N; i < MAXN; i++) {
			mint[i] = 1000000001;
		}
		
		for (int i = n - 1; i > 0; i--) {
			maxt[i] = Math.max(maxt[i<<1], maxt[i<<1|1]);
			mint[i] = Math.min(mint[i<<1], mint[i<<1|1]);
		}
		
		for (int i = 1; i <= M; i++) {
			int l, r;
			st = new StringTokenizer(br.readLine(), " ");
			
			l = Integer.parseInt(st.nextToken());
			r = Integer.parseInt(st.nextToken());
			
			bw.write(Math.min(minQuery(l - 1, r - 1), mint[r + n - 1]) + " " + Math.max(maxQuery(l - 1, r - 1), maxt[r + n - 1]) + "\n");
		}
		
		bw.flush();
	}
	static int minQuery(int l, int r) {
		int res = 1000000001;
		for (l+=n, r+=n; l<r; l>>=1, r>>=1) {
			if ((l&1) == 1) res = Math.min(res, mint[l++]);
			if ((r&1) == 1) res = Math.min(res, mint[--r]);
		}
		return res;
	}
	static int maxQuery(int l, int r) {
		int res = 0;
		for (l+=n, r+=n; l<r; l>>=1, r>>=1) {
			if ((l&1) == 1) res = Math.max(res, maxt[l++]);
			if ((r&1) == 1) res = Math.max(res, maxt[--r]);
		}
		return res;
	}
}