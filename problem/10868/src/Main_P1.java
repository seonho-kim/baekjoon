import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_P1 {

	static int N, M, n;
	static final int MAX = 100001;
	static final int SEG_SIZE = 1<<((int)Math.ceil(Math.log(MAX)/Math.log(2) + 1));
	static int[] tree = new int[SEG_SIZE];
	static final int INF = 1000000001;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		n = 1<<((int)Math.ceil(Math.log(N)/Math.log(2)));
		
		for (int i = n; i < n + N; i++) {
			tree[i] = Integer.parseInt(br.readLine());
		}
		for (int i = n + N; i < n<<1; i++) {
			tree[i] = INF;
		}
		for (int i = n-1; i > 0; i--) {
			tree[i] = Math.min(tree[i<<1], tree[i<<1|1]);  
		}
		
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			bw.write(query(a-1, b-1) + "\n");
		}
		bw.flush();
	}
	static long query(int l, int r) {
		long result = INF;
		if (l <= r) result = Math.min(result, tree[r+n]);
		for (l+=n, r+=n; l < r; l>>=1, r>>=1) {
			if ((l&1)==1) result = Math.min(result, tree[l++]);
			if ((r&1)==1) result = Math.min(result, tree[--r]);
		}
		return result;
	}
}