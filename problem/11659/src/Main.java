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
	static int SEG_SIZE = (int)Math.pow(2, Math.ceil(Math.log(MAX) / Math.log(2)) + 1) + 1;
	static int[] t = new int[SEG_SIZE];
	static int h, n;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		h = (int)Math.ceil(Math.log(N) / Math.log(2));
		n = (int)Math.pow(2, h);
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = n; i < n+N; i++) {
			t[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = n - 1; i > 0; i--) {
			t[i] = t[i<<1] + t[i<<1|1];
		}
		
		for (int i = 1; i <= M; i++) {
			int l, r;
			
			st = new StringTokenizer(br.readLine(), " ");
			l = Integer.parseInt(st.nextToken());
			r = Integer.parseInt(st.nextToken());
			bw.write((query(l - 1, r - 1) + t[r - 1 + n]) + "\n");
		}
		bw.close();
	}
	
	static int query(int l, int r) {
		int res = 0;
		for (l+=n, r+=n; l < r; l>>=1, r>>=1) {
			if ((l&1) == 1) res += t[l++];
			if ((r&1) == 1) res += t[--r];
		}
		return res;
	}
}