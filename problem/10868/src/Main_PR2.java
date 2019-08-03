import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_PR2 {

	static int N, M, h;
	static int[] a, t;
	static final int INF = 1000000000;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		h = 1<<(int)Math.ceil(Math.log(N)/Math.log(2));
		a = new int[N];
		t = new int[h<<1];
		
		for (int i = 0; i < N; i++) {
			a[i] = Integer.parseInt(br.readLine());
		}
		init(1, 0, N-1);
		// print();
		
		
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine(), " ");
			int left = Integer.parseInt(st.nextToken()) - 1;
			int right = Integer.parseInt(st.nextToken()) - 1;
			
			bw.write(query(1, 0, N-1, left, right) + "\n");
		}
		bw.flush();
	}
	static void print() {
		int n = 1;
		for (int i = 1; i < h<<1; i++) {
			if (i == 1<<n) {
				System.out.print("| ");
				n++;
			}
			System.out.print(t[i] + " ");
		}
		System.out.println();
	}
	static int init(int node, int start, int end) {
		if (start == end) return t[node] = a[start];
		else {
			int mid = (start + end) / 2;
			return t[node] = Math.min(init(node*2, start, mid)
					, init(node*2 + 1, mid + 1, end));
		}
	}
	static int query(int node, int start, int end, int left, int right) {
		if (left > end || right < start) return INF;
		if (left <= start && end <= right) return t[node];
		int mid = (start + end) / 2;
		return Math.min(query(node*2, start, mid, left, right)
				, query(node*2 + 1, mid + 1, end, left, right));
	}
}