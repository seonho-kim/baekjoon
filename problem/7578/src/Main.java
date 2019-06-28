import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

	static int N, n;
	static int[] tree;
	static long answer;
	static HashMap<Integer, Integer> indexTb = new HashMap<Integer, Integer>();
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		  
		N = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			indexTb.put(Integer.parseInt(st.nextToken()), i);
		}
				
		n = 1 << (int)(Math.ceil(Math.log(N) / Math.log(2)));
		tree = new int[n << 1];

		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			int idx =  indexTb.get(Integer.parseInt(st.nextToken()));
			answer += query(idx, n-1);
			update(idx, 1);
		}
		bw.write(answer + "");
		bw.flush();
	}
	static void print() {
		for (int i = 1; i < (n<<1); i++)
			System.out.printf("%2d ", tree[i]);
		System.out.println();
	}
	static long query(int l, int r) {
		long result = tree[r+n];
		for (l+=n, r+=n; l<r; l>>=1, r>>=1) {
			if ((l&1) == 1) result += tree[l++];
			if ((r&1) == 1) result += tree[--r];
		}
		return result;
	}
	static void update(int p, int v) {
		for (tree[p+=n] = v; p > 1;  p>>=1) {
			tree[p>>1] = tree[p] + tree[p^1];
		}
	}
}