import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_PR {

	static int N, M;
	static int[] a, tmax, tmin;
	static final int INF = 1000000000;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int h = 1<<(int)Math.ceil(Math.log(N)/Math.log(2));
		a = new int[N];
		tmax = new int[h<<1];
		tmin = new int[h<<1];
		
		for (int i = 0; i < N; i++) {
			a[i] = Integer.parseInt(br.readLine());
		}
		minInit(1, 0, N-1);
		maxInit(1, 0, N-1);
		
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine(), " ");
			int left = Integer.parseInt(st.nextToken()) - 1;
			int right = Integer.parseInt(st.nextToken()) - 1;
			
			bw.write(minQuery(1, 0, N-1, left, right) + " "
					+ maxQuery(1, 0, N-1, left, right) + "\n");
		}
		
		bw.flush();
	}
	static int minInit(int node, int start, int end) {
		if (start == end) return tmin[node] = a[start];
		else {
			int mid = (start + end) / 2;
			return tmin[node] = Math.min(minInit(node*2, start, mid)
					, minInit(node*2+1, mid+1, end));
		}
	}
	static int maxInit(int node, int start, int end) {
		if (start == end) return tmax[node] = a[start];
		else {
			int mid = (start + end) / 2;
			return tmax[node] = Math.max(maxInit(node*2, start, mid)
					, maxInit(node*2+1, mid+1, end));
		}
	}
	static int minQuery(int node, int start, int end, int left, int right) {
		if (left > end || right < start) return INF;
		if (left <= start && end <= right) return tmin[node];
		int mid = (start + end) / 2;
		return Math.min(minQuery(node*2, start, mid, left, right)
				, minQuery(node*2+1, mid+1, end, left, right));
	}
	static int maxQuery(int node, int start, int end, int left, int right) {
		if (left > end || right < start) return 1;
		if (left <= start && end <= right) return tmax[node];
		int mid = (start + end) / 2;
		return Math.max(maxQuery(node*2, start, mid, left, right)
				, maxQuery(node*2+1, mid+1, end, left, right));
	}
}