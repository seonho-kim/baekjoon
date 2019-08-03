import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_PR {

	static int N, M, K, h;
	static final int MOD = 1000000007;
	static long[] a, t;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		h = 1<<(int)Math.ceil(Math.log(N)/Math.log(2));
		a = new long[N];
		t = new long[h<<1];
		
		M += K;
		
		for (int i = 0; i < N; i++) {
			a[i] = Integer.parseInt(br.readLine());
		}
		init(1, 0, N-1);
		
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine(), " ");
			int order = Integer.parseInt(st.nextToken());
			
			if (order == 1) {
				int index = Integer.parseInt(st.nextToken())-1;
				long value = Long.parseLong(st.nextToken());
				a[index] = value;
				update(1, 0, N-1, index, value);
			} else if (order == 2) {
				int left = Integer.parseInt(st.nextToken())-1;
				int right = Integer.parseInt(st.nextToken())-1;
				
				bw.write(query(1, 0, N-1, left, right) + "\n");
			}
		}
		bw.flush();
	}
	static long init(int node, int start, int end) {
		if (start == end) return t[node] = a[start];
		else {
			int mid = (start + end) / 2;
			return t[node] = (init(node*2, start, mid)
					* init(node*2+1, mid+1, end))%MOD;
		}
	}
	static long update(int node, int start, int end, int index, long value) {
		if (index < start || index > end) return t[node];
		if (start == end) return t[node] = value;
		else {
			int mid = (start + end) / 2;
			return t[node] = (update(node*2, start, mid, index, value)
			* update(node*2+1, mid+1, end, index, value))%MOD;
		}
	}
	static long query(int node, int start, int end, int left, int right) {
		if (left > end || right < start) return 1;
		if (left <= start && end <= right) return t[node];
		int mid = (start + end) / 2;
		return (query(node*2, start, mid, left, right)
				* query(node*2+1, mid+1, end, left, right))%MOD;
	}
}