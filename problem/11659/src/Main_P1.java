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
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		n = 1<<((int)Math.ceil(Math.log(N)/Math.log(2)));
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			tree[n+i-1] = Integer.parseInt(st.nextToken());
			
		}
		for (int i = n-1; i > 0; i--) {
			tree[i] = tree[i<<1] + tree[i<<1|1];
		}
		
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			bw.write(query(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1) + "\n");
		}
		bw.flush();
	}
	static int query(int l, int r) {
		int result = 0;
		if (l <= r) result = tree[r+n];
		for (l+=n, r+=n; l < r; l>>=1, r>>=1) {
			if ((l&1)==1) result += tree[l++];
			if ((r&1)==1) result += tree[--r];
		}
		return result;
	}
}