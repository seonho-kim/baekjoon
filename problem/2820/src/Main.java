import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static int N, M, h;
	static long[] a, t, lazy;
	static ArrayList<Integer>[] p;
	static boolean[] visited;
	static int[] number, numchild;
	static ArrayList<Long> add;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		h = 1<<(int)Math.ceil(Math.log(N)/Math.log(2));
		
		a = new long[N];
		t = new long[h<<1];
		lazy = new long[h<<1];
		
		p = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			p[i] = new ArrayList<Integer>();
		}
		visited = new boolean[N];
		number = new int[N];
		numchild = new int[N];
		add = new ArrayList<Long>();
		
		a[0] = Integer.parseInt(br.readLine());
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			long value = Long.parseLong(st.nextToken());
			int parent = Integer.parseInt(st.nextToken())-1;
			p[parent].add(i);
			a[i] = value;
		}
		dfs(0);
		init(1, 0, N-1);
		
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine(), " ");
			char order = st.nextToken().charAt(0);
			int index = Integer.parseInt(st.nextToken())-1;
			
			if (order == 'p') {
				long value = Long.parseLong(st.nextToken());
				update_range(1, 0, N-1, number[index]-numchild[index], number[index]-1, value);
			} else if (order == 'u') {
				bw.write(query(1, 0, N-1, number[index], number[index]) + "\n");
			}
		}
		bw.flush();
	}
	static long init(int node, int start, int end) {
		if (start == end) return t[node] = add.get(start);
		else {
			int mid = (start + end) / 2;
			return t[node] = init(node*2, start, mid)
					+ init(node*2+1, mid+1, end);
		}
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
	static void update_range(int node, int start, int end, int left, int right, long value) {
		update_lazy(node, start, end);
		if (left > end || right < start) return;
		if (left <= start && end <= right) {
			t[node] += (end-start+1)*value;
			if (start != end) {
				lazy[node*2] += value;
				lazy[node*2+1] += value;
			}
			return;
		}
		int mid = (start + end) / 2;
		update_range(node*2, start, mid, left, right, value);
		update_range(node*2+1, mid+1, end, left, right, value);
		t[node] = t[node*2] + t[node*2+1];
	}
	static long query(int node, int start, int end, int left, int right) {
		update_lazy(node, start, end);
		if (left > end || right < start) return 0;
		if (left <= start && end <= right) return t[node];
		int mid = (start + end) / 2;
		return query(node*2, start, mid, left, right)
				+ query(node*2+1, mid+1, end, left, right);
	}
	static int dfs(int cur) {
		visited[cur] = true;
		int child = 0;
		for (int next : p[cur]) {
			if (!visited[next])
				child += dfs(next); 
		}
		number[cur] = add.size();
		add.add(a[cur]);
		numchild[cur] = child;
		return child+1;
	}
}