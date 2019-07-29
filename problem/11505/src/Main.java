import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int N, M, K, n;
	static final int MAX = 1000000;
	static int SEG_SIZE = 1<<(int)(Math.ceil(Math.log(MAX)/Math.log(2) + 1));
	static long t[] = new long[SEG_SIZE]; 
	static final int MOD = 1000000007;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		n = 1<<(int)(Math.ceil(Math.log(N)/Math.log(2)));
		
		for (int i = n; i < n+N; i++) {
			t[i] = Integer.parseInt(br.readLine());
		}
		for (int i = n+N; i < n<<1; i++) {
			t[i] = 1;
		}
		
		for (int i = n-1; i > 0; i--) {
			t[i] = ((t[i<<1]%MOD) * (t[i<<1|1]%MOD))%MOD; 
		}
		
		for (int i = 1; i <= M+K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int order = Integer.parseInt(st.nextToken());
			
			switch (order) {
			case 1:
				update(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()));
				break;
			case 2:
				bw.write(query(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1) + "\n");
				break;
			}
		}
		bw.flush();
	}
	static long query(int l, int r) {
		long result = 1;
		if (l <= r) result = t[r + n];
		
		for (l+=n, r+=n; l < r; l>>=1, r>>=1) {
			if ((l&1) == 1) result = ((result%MOD) * (t[l++]%MOD))%MOD;
			if ((r&1) == 1) result = ((result%MOD) * (t[--r]%MOD))%MOD;
		}
		
		return result;
	}
	static void update(int p, int v) {
		for (t[p+=n] = v; p > 1; p>>=1) {
			t[p>>1] = ((t[p]%MOD) * (t[p^1]%MOD))%MOD;
		}
	}
}