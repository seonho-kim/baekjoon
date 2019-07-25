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
	static final int SEG_SIZE = 1<<(int)Math.ceil(Math.log(MAX)/Math.log(2) + 1);
	static int[] t = new int[SEG_SIZE];
	static int n, MAXN = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		n = 1<<(int)Math.ceil(Math.log(N)/Math.log(2));
		
		for (int i = n; i < n + N; i++) {
			t[i] = Integer.parseInt(br.readLine());
		}
		for (int i = n + N; i < n<<1; i++) {
			t[i] = MAXN;
		}
		
		for (int i = n-1; i > 0; i--) {
			t[i] = Math.min(t[i<<1], t[i<<1|1]);
		}
		
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			bw.write(query(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1) + "\n");
		}	
		bw.flush();
	}
	static int query (int l, int r) {
		int result = MAXN;
		if (l < r) {
			result = Math.min(result, t[n + r]);
		}
		for (l+=n, r+=n; l < r; l>>=1, r>>=1) {
			if((l&1) == 1) result = Math.min(result, t[l++]);
			if((r&1) == 1) result = Math.min(result, t[--r]);
		}
		return result;
	}
}