import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_PR {

	static int N, Q, h;
	static long[] a, t; 
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		h = 1<<(int)Math.ceil(Math.log(N)/Math.log(2));
		
		a = new long[N];
		t = new long[h<<1];
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			a[i] = Long.parseLong(st.nextToken());
		}
		init(1, 0, N-1);
		
		while (Q-- > 0) {
			st = new StringTokenizer(br.readLine(), " ");
			int left = Integer.parseInt(st.nextToken())-1;
			int right = Integer.parseInt(st.nextToken())-1;
			if (left > right) {
				int tmp = left;
				left = right;
				right = tmp;
			}
			bw.write(query(1, 0, N-1, left, right) + "\n");
			
			int index = Integer.parseInt(st.nextToken())-1;
			long value = Long.parseLong(st.nextToken());
			long diff = value - a[index];
			a[index] = value;
			update(1, 0, N-1, index, diff);
		}
		bw.flush();
	}
	static long init(int node, int start, int end) {
		if (start == end) return t[node] = a[start];
		else {
			int mid = (start + end) / 2;
			return t[node] = init(node*2, start, mid)
					+ init(node*2+1, mid+1, end);
		}
	}
	static void update(int node, int start, int end, int index, long diff) {
		if (index < start || index > end) return;
		t[node] += diff;
		if (start != end) {
			int mid = (start + end) / 2;
			update(node*2, start, mid, index, diff);
			update(node*2+1, mid+1, end, index, diff);
		}
	}
	static long query(int node, int start, int end, int left, int right) {
		if (left > end || right < start) return 0;
		if (left <= start && end <= right) return t[node];
		int mid = (start + end) / 2;
		return query(node*2, start, mid, left, right)
				+ query(node*2+1, mid+1, end, left, right);
	}
}