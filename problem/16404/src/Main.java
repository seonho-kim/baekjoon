import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static int N, M, h, cnt;
	static boolean[] visited;
	static ArrayList<Integer>[] p;
	static long[] t, lazy;
	static int[] number, numchild;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		h = 1<<(int)Math.ceil(Math.log(N)/Math.log(2));
		
		visited = new boolean[N];
		p = new ArrayList[N];		
		for (int i = 0; i < N; i++) {
			p[i] = new ArrayList<Integer>();
		}
		number = new int[N];
		numchild = new int[N];
		t = new long[h<<1];
		lazy = new long[h<<1];
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			int parent = Integer.parseInt(st.nextToken())-1;
			if (i != 0) p[parent].add(i);
		}
		dfs(0);
		
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine(), " ");
			int order = Integer.parseInt(st.nextToken());
			
			if (order == 1) {
				int index = Integer.parseInt(st.nextToken())-1;
				long value = Long.parseLong(st.nextToken());
				update_range(1, 0, N-1, number[index]-numchild[index], number[index], value);
			} else if (order == 2) {
				int index = Integer.parseInt(st.nextToken())-1;
				bw.write(query(1, 0, N-1, number[index], number[index]) + "\n");
			}
		}
		bw.flush();
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
		number[cur] = cnt++;
		numchild[cur] = child;
		return child+1;
	}
}