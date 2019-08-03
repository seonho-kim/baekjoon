import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int N, M, h;
	static int[] a, t, lazy;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		h = 1<<(int)Math.ceil(Math.log(N)/Math.log(2));
		a = new int[N];
		t = new int[h<<1];
		lazy = new int[h<<1];
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		init(1, 0, N-1);
		
		M = Integer.parseInt(br.readLine());
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine(), " ");
			int order = Integer.parseInt(st.nextToken());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			
			if (left > right) {
				int temp = left;
				left = right;
				right = temp;
			}
			if (order == 1) {
				int value = Integer.parseInt(st.nextToken());
				update_range(1, 0, N-1, left, right, value);
			} else if (order == 2) {
				bw.write(query(1, 0, N-1, left, right) + "\n");
			}
		}
		bw.flush();
	}
	static int init(int node, int start, int end) {
		if (start == end) return t[node] = a[start];
		else {
			int mid = (start + end) / 2;
			return t[node] = init(node*2, start, mid)
					^init(node*2+1, mid+1, end);
		}
	}
	static void update_lazy(int node, int start, int end) {
		if (lazy[node] != 0) {
			t[node] ^= (lazy[node]*((end-start+1)%2));
			if (start != end) {
				lazy[node*2] ^= lazy[node];
				lazy[node*2+1] ^= lazy[node];
			}
			lazy[node] = 0;
		}
	}
	static void update_range(int node, int start, int end, int left, int right, int value) {
		update_lazy(node, start, end);
		if (left > end || right < start) return;
		if (left <= start && end <= right) {
			t[node] ^= (value*((end-start+1)%2));
			if (start != end) {
				lazy[node*2] ^= value;
				lazy[node*2+1] ^= value;
			}
			return;
		}
		int mid = (start + end) / 2;
		update_range(node*2, start, mid, left, right, value);
		update_range(node*2+1, mid+1, end, left, right, value);
		t[node] = t[node*2]^t[node*2+1];
	}
	static int query(int node, int start, int end, int left, int right) {
		update_lazy(node, start, end);
		if (left > end || right < start) return 0;
		if (left <= start && end <= right) return t[node];
		int mid = (start + end) / 2;
		return query(node*2, start, mid, left, right)
				^query(node*2+1, mid+1, end, left, right);
	}
}