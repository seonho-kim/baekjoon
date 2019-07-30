import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int N, M, K;
	static final int MAX = 1000000;
	static final int SEG_SIZE = 1<<(int)Math.ceil(Math.log(MAX)/Math.log(2) + 1);
	static long[] t = new long[SEG_SIZE], lazy = new long[SEG_SIZE];;
	static long[] a = new long[MAX];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		M += K;
		for (int i = 0; i < N; i++) {
			a[i] = Integer.parseInt(br.readLine());
		}
		init(1, 0, N-1);
		
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine(), " ");
			int t1 = Integer.parseInt(st.nextToken());
			
			if (t1 == 1) {
				int t2 = Integer.parseInt(st.nextToken());
				int t3 = Integer.parseInt(st.nextToken());
				long v = Long.parseLong(st.nextToken());
				update_range(1, 0, N-1, t2-1, t3-1, v);
			} else if (t1 == 2) {
				int t2 = Integer.parseInt(st.nextToken());
				int t3 = Integer.parseInt(st.nextToken());
				
				bw.write(query(1, 0, N-1, t2-1, t3-1) + "\n");
			}
		}
		bw.flush();
	}
	static long init(int node, int start, int end) {
		if (start == end) return t[node] = a[start];
		else return t[node] = init(node*2, start, (start+end)/2)
				+ init(node*2+1, (start+end)/2+1, end);
	}
	static void update_lazy(int node, int start, int end) {
		if (lazy[node] != 0) {
			t[node] += (end-start+1)*lazy[node];
			if (start != end) {
				lazy[node*2] += lazy[node];
				lazy[node*2+1] += lazy[node];
			}
			lazy[node] = 0;
		}
	}
	static void update_range(int node, int start, int end, int left, int right, long diff) {
		update_lazy(node, start, end);
		if (left > end || right < start) return;
		if (left <= start && end <= right) {
			t[node] += (end-start+1)*diff;
			if (start != end) {
				lazy[node*2] += diff;
				lazy[node*2+1] += diff;
			}
			return;
		}
		update_range(node*2, start, (start+end)/2, left, right, diff);
		update_range(node*2+1, (start+end)/2+1, end, left, right, diff);
		t[node] = t[node*2] + t[node*2+1];
	}
	static long query(int node, int start, int end, int left, int right) {
		update_lazy(node, start, end);
		if (left > end || right < start) return 0;
		if (left <= start && end <= right) return t[node];
		return query(node*2, start, (start+end)/2, left, right)
				+ query(node*2+1, (start+end)/2+1, end, left, right);
	}
}