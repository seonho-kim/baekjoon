import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.io.FileInputStream;
import java.io.IOException;

public class Main_PR {

	static int N, M;
	static final int MAX = 100000;
	static final int SEG_SIZE = 1<<(int)Math.ceil(Math.log(MAX)/Math.log(2) + 1);
	static int t[] = new int[SEG_SIZE];
	static int a[] = new int[MAX];
	static final int INF = 1000000000;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++) {
			a[i] = Integer.parseInt(br.readLine());
		}
		init(1, 0, N-1);
			
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine(), " ");
			int t1 = Integer.parseInt(st.nextToken());
			int t2 = Integer.parseInt(st.nextToken());
			bw.write(query(1, 0, N-1, t1-1, t2-1) + "\n");
		}
		bw.flush();
	}
	static int init(int node, int start, int end) { 
		if (start == end) return t[node] = a[start];
		else {
			return t[node] = Math.min(init(node*2, start, (start+end)/2)
					, init(node*2+1, (start+end)/2+1, end));
		}
	}
	static long query(int node, int start, int end, int left, int right) {
		if (left > end || right < start) return INF;
		if (left <= start && end <= right) return t[node];
		return Math.min(query(node*2, start, (start+end)/2, left, right)
				, query(node*2+1, (start+end)/2+1, end, left, right));
	}
}