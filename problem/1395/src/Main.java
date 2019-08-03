import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int N, M, h;
	static int[] t, lazy;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		h = 1<<(int)Math.ceil(Math.log(N)/Math.log(2));
		t = new int[h<<1];
		lazy = new int[h<<1];
		
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine(), " ");
			int order = Integer.parseInt(st.nextToken());
			int left = Integer.parseInt(st.nextToken()) - 1;
			int right = Integer.parseInt(st.nextToken()) - 1;
			
			if (order == 0) {
				update_range(1, 0, N-1, left, right);
			} else if (order == 1) {
				bw.write(query(1, 0, N-1, left, right) + "\n");
			}
		}
		bw.flush();
	}
	static void update_lazy(int node, int start, int end) {
		if (lazy[node] != 0) {
			t[node] = (end-start+1)-t[node];
			if (start != end) {
				lazy[node*2] = (lazy[node*2]^1) == 1 ? 1 : 0;
				lazy[node*2+1] = (lazy[node*2+1]^1) == 1 ? 1 : 0;
			}
			lazy[node] = 0;
		}
	}
	static void update_range(int node, int start, int end, int left, int right) {
		update_lazy(node, start, end);
		if (left > end || right < start) return;
		if (left <= start && end <= right) {
			t[node] = (end-start+1)-t[node];
			if (start != end) {
				lazy[node*2] = (lazy[node*2]^1) == 1 ? 1 : 0;
				lazy[node*2+1] = (lazy[node*2+1]^1) == 1 ? 1 : 0;
			}
			return;
		}
		
		int mid = (start + end) / 2;
		update_range(node*2, start, mid, left, right);
		update_range(node*2+1, mid+1, end, left, right);
		t[node] = t[node*2] + t[node*2+1];
	}
	static int query(int node, int start, int end, int left, int right) {
		update_lazy(node, start, end);
		if (left > end || right < start) return 0;
		if (left <= start && end <= right) return t[node];
		
		int mid = (start + end) / 2;
		return query(node*2, start, mid, left, right)
				+ query(node*2+1, mid+1, end, left, right);
	}
}