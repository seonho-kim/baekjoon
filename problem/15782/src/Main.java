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
	static ArrayList<Integer>[] p;
	static int[] a, t, lazy;
	static boolean[] visited;
	static int[] number, numchild;
	static ArrayList<Integer> add;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		p = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			p[i] = new ArrayList<Integer>();
		}
		a = new int[N+1];
		h = 1<<(int)Math.ceil(Math.log(N)/Math.log(2));
		t = new int[h<<1];
		lazy = new int[h<<1];
		visited = new boolean[N];
		number = new int[N];
		numchild = new int[N];
		add = new ArrayList<Integer>();
		
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int u = Integer.parseInt(st.nextToken())-1;
			int v = Integer.parseInt(st.nextToken())-1;
			p[u].add(v);
			p[v].add(u);
		}
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		init_dfs(0);
		init(1, 0, N-1);
		
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine(), " ");
			int order = Integer.parseInt(st.nextToken());
			
			if (order == 1) {
				int index = Integer.parseInt(st.nextToken())-1;
				bw.write(query(1, 0, N-1, number[index]-numchild[index], number[index]) + "\n");
			} else if (order == 2) {
				int index = Integer.parseInt(st.nextToken())-1;
				int value = Integer.parseInt(st.nextToken());
				update_range(1, 0, N-1, number[index]-numchild[index], number[index], value);
			}
		}
		bw.flush();
	}
	static int init_dfs(int cur) {
		visited[cur] = true;
		int child = 0;
		for (int next : p[cur]) {
			if (!visited[next])
				child += init_dfs(next);
		}
		number[cur] = add.size();
		add.add(a[cur]);
		numchild[cur] = child;
		return child + 1;
	}
	static int init(int node, int start, int end) {
		if (start == end) return t[node] = add.get(start);
		else {
			int mid = (start + end) / 2;
			return t[node] = init(node*2, start, mid)
					^ init(node*2+1, mid+1, end);
		}
	}
	static void update_lazy(int node, int start, int end) {
		if (lazy[node] != 0) {
			if (((end-start+1)&1)==1) {
				t[node] ^= lazy[node];
			}
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
			if (((end-start+1)&1)==1) {
				t[node] ^= value;
			}
			if (start != end) {
				lazy[node*2] ^= value;
				lazy[node*2+1] ^= value;
			}
			return;
		}
		int mid = (start + end) / 2;
		update_range(node*2, start, mid, left, right, value);
		update_range(node*2+1, mid+1, end, left, right, value);
		t[node] = t[node*2] ^ t[node*2+1];
	}
	static int query(int node, int start, int end, int left, int right) {
		update_lazy(node, start, end);
		if (left > end || right < start) return 0;
		if (left <= start && end <= right) return t[node];
		int mid = (start + end) / 2;
		return query(node*2, start, mid, left, right)
				^ query(node*2+1, mid+1, end, left, right);
	}
}