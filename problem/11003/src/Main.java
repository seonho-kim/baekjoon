import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	static int N, L;
	static final int MAX = 5000001;
	static int[] t = new int[MAX];
	static int[] s = new int[MAX];
	static final int INF = 1000000001;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		Deque<Integer> q = new ArrayDeque<Integer>(); 
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			t[i] = Integer.parseInt(st.nextToken());
			
			while(!q.isEmpty() && t[q.peekLast()] > t[i])
				q.pollLast();
			
			q.offer(i);
			
			if (q.peekFirst() < (i - L + 1))
				q.pollFirst();
			s[i] = t[q.peekFirst()];
		}
		for (int i = 1; i <= N; i++) {
			bw.write(s[i] + " ");
		}
		bw.close();
	}
}

//	static int N, L;
//	static final int MAX = 5000000;
//	static int SEG_SIZE = (int)Math.pow(2, Math.ceil((Math.log(MAX) / Math.log(2))) + 1) + 1;
//	static int[] t = new int[SEG_SIZE];
//	static int h, n;
//	static final int INF = 1000000001;
//	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("./src/input.txt"));
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
//		StringBuilder sb = new StringBuilder();
//		
//		N = Integer.parseInt(st.nextToken());
//		L = Integer.parseInt(st.nextToken());
//		
//		h = (int)Math.ceil(Math.log(N) / Math.log(2));
//		n = (int)Math.pow(2, h);
//		
//		st = new StringTokenizer(br.readLine(), " ");
//		for (int i = n; i < n+N; i++) {
//			t[i] = Integer.parseInt(st.nextToken());
//		}
//		
//		for (int i = n+N; i < 2*n; i++) {
//			t[i] = INF;
//		}
//		
//		for (int i = n - 1; i > 0; i--) {
//			t[i] = Math.min(t[i<<1], t[i<<1|1]);
//		}
//		
////		for (int i = 1; i < 2*n; i++)
////			System.out.print(t[i] + " ");
//		
//		for (int i = 1; i < N; i++) {
//			int l, r;
//			
//			l = ((i - L + 1) < 0 ? 1 : (i - L + 1)) - 1;
//			r = i - 1;
//			sb.append(Math.min(query(l, r), t[r + n]) + " ");
//		}
//		
//		bw.write(sb.toString());
//		bw.close();
//	}
//	static int query(int l, int r) {
//		int res = INF;
//		for (l+=n, r+=n; l < r; l>>=1, r>>=1) {
//			if ((l&1) == 1) res = Math.min(res, t[l++]);
//			if ((r&1) == 1) res = Math.min(res, t[--r]);
//		}
//		return res;
//	}
//}
