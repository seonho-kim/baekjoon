import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_PR {

	static int N, M, K;
	static final int MAX = 1000000;
	static final int SEG_SIZE = 1<<(int)Math.ceil(Math.log(MAX)/Math.log(2) + 1);
	static long t[] = new long[SEG_SIZE];
	static long a[] = new long[MAX];
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
			a[i] = Long.parseLong(br.readLine());
		}
		init (1, 0, N-1);
		
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine(), " ");
			int t1;
			t1 = Integer.parseInt(st.nextToken());
			
			if (t1 == 1) {
				int t2 = Integer.parseInt(st.nextToken());
				long t3 = Long.parseLong(st.nextToken());
				t2-=1;
				long diff = t3-a[t2];
				a[t2] = t3;
				update(1, 0, N-1, t2, diff);
			} else if (t1 == 2) {
				int t2 = Integer.parseInt(st.nextToken());
				int t3 = Integer.parseInt(st.nextToken());
				
				bw.write(sum(1, 0, N-1, t2-1, t3-1) + "\n");
			}
		}
		bw.flush();
	}
	static long init(int node, int start, int end) {
		if (start == end) return t[node] = a[start];
		else {
			return t[node] = init(node*2, start, (start+end)/2)
					+ init(node*2+1, (start+end)/2+1, end);
		}
	}
	static long sum(int node, int start, int end, int left, int right) {
		if (left > end || right < start) return 0;
		
		if (left <= start && end <= right) return t[node];
		return sum(node*2, start, (start+end)/2, left, right)
				+ sum(node*2+1, (start+end)/2 + 1, end, left, right);
	}
	static void update(int node, int start, int end, int index, long diff) {
		if (index < start || index > end) return;
		t[node] += diff;
		if (start != end) {
			update(node*2, start, (start+end)/2, index, diff);
			update(node*2+1, (start+end)/2+1, end, index, diff);
		}
	}
}