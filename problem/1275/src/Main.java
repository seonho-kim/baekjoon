import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	static int N, Q;
	static int MAX = 100000;
	static int SEG_SIZE = (int)Math.pow(2, Math.ceil(Math.log(MAX) / Math.log(2) + 1));
	static long[] list = new long[SEG_SIZE];
	static int n, MAXN;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		n = (int)Math.pow(2, Math.ceil(Math.log(N) / Math.log(2)));
		MAXN = (int)Math.pow(2, Math.ceil(Math.log(N) / Math.log(2)) + 1);
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = n; i < n+N; i++) {
			list[i] = Long.parseLong(st.nextToken());
		}
		
		for (int i = n-1; i > 0; i--) {
			list[i] = list[i<<1] + list[i<<1|1];
		}
		
		for (int i = 1; i <= Q; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x, y, a;
			long b;
			
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			b = Long.parseLong(st.nextToken());
			
			if (x > y) {
				int tmp = y;
				y = x;
				x = tmp;
			}
			
			bw.write((query(x-1, y-1)+list[y-1+n]) + "\n");
			update(a-1, b);
		}
		bw.flush();
	}
	
	static long query(int l, int r) {
		long res = 0;
		for (l+=n, r+=n; l < r; l>>=1, r>>=1) {
			if ((l&1) == 1) res += list[l++];
			if ((r&1) == 1) res += list[--r];
		}
		return res;
	}
	
	static void update(int p, long value) {
		for (list[p+=n] = value; p > 1; p>>=1) {
			list[p>>1] = list[p] + list[p^1];
		}
	}
}