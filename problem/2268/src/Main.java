import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static final int MAX = 1000000;
	static final int SEG_SIZE = 1<<(int)Math.ceil(Math.log(MAX)/Math.log(2) + 1);
	static long a[] = new long[MAX];
	static long t[] = new long[SEG_SIZE];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		init(1, 0, N-1);
		
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine(), " ");
			int order = Integer.parseInt(st.nextToken());
			
			if (order == 0) {
				int left = Integer.parseInt(st.nextToken()) - 1;
				int right = Integer.parseInt(st.nextToken()) - 1;
				
				if (left > right) {
					int tmp = left;
					left = right;
					right = tmp;
				}
				bw.write(query(1, 0, N-1, left, right) + "\n");
			} else if (order == 1) {
				int index = Integer.parseInt(st.nextToken()) - 1;
				long value = Long.parseLong(st.nextToken());
				
				long diff = value-a[index];
				a[index] = value;
				update(1, 0, N-1, index, diff);
			}
		}
		bw.flush();
	}
	static long init(int node, int start, int end) {
		if (start == end) return t[node] = a[start];
		else return t[node] = init(node*2, start, (start+end)/2)
				+ init(node*2+1, (start+end)/2+1, end);
	}
	static long query(int node, int start, int end, int left, int right) {
		if (left > end || right < start) return 0;
		if (left <= start && end <= right) return t[node];
		return query(node*2, start, (start+end)/2, left, right)
				+ query(node*2+1, (start+end)/2+1, end, left, right);
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