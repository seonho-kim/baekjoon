import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_P {

	static int N, M;
	static final int MAX = 200001;
	static final int max_level = (int)Math.floor(Math.log(MAX)/Math.log(2));;
	static int[] depth = new int[MAX];
	static int[][] ac = new int[MAX][20], maxDist = new int[MAX][20];
	static boolean[] visited = new boolean[MAX];
	static ArrayList<ToCost>[] tree = new ArrayList[MAX]; 
	static ArrayList<Edge> edges = new ArrayList<Edge>();
	static PriorityQueue<Edge> pq = new PriorityQueue<Edge>(edges);
	static int[] p = new int[MAX];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
				
		for (int i = 0; i <= N; i++) {
			tree[i] = new ArrayList<ToCost>();
			p[i] = -1;
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u, v, w;
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			edges.add(new Edge(u, v, w));
			pq.add(new Edge(u, v, w));
		}
		long minimum = kruskal_mst();
		depth[0] = -1;
		make_tree(1, 0, 0);
		
		for (int i = 0; i < edges.size(); i++) {
			int u = edges.get(i).u;
			int v = edges.get(i).v;
			int w = edges.get(i).w;
			long result = minimum + w;
			int lca = lca(u, v);
			int u_to_lca = depth[u] - depth[lca];
			int v_to_lca = depth[v] - depth[lca];
			int max = -1;
			
			for (int j = max_level; j >= 0; j--) {
				if (u_to_lca >= (1 << j)) {
					max = Math.max(max, maxDist[u][j]);
					u_to_lca -= (1 << j);
					u = ac[u][j];
				}
			}
			for (int j = max_level; j >= 0; j--) {				
				if (v_to_lca >= (1 << j)) {
					max = Math.max(max, maxDist[v][j]);
					v_to_lca -= (1 << j);
					v = ac[v][j];
				}
			}
			bw.write(result - max + "\n");
		}
		bw.flush();
	}
	static int find(int a) {
		if (p[a] < 0) return a;
		return p[a] = find(p[a]);
	}
	static boolean union(int a, int b) {
		a = find(a);
		b = find(b);
		if (a == b) return false;
		p[b] = a;
		return true;
	}
	static long kruskal_mst() {
		long cost = 0;
		int cnt = 0;
		while(!pq.isEmpty()) {
			Edge tmp = pq.poll();
			if (union(tmp.u, tmp.v)) {
				cost += tmp.w;
				tree[tmp.u].add(new ToCost(tmp.v, tmp.w));
				tree[tmp.v].add(new ToCost(tmp.u, tmp.w));
				if (++cnt == N-1) break;
			}
		}
		return cost;
	}
	static void make_tree(int cur, int parent, int cost) {
		depth[cur] = depth[parent] + 1;
		ac[cur][0] = parent;
		maxDist[cur][0] = cost;
				
		for (int i = 1; i <= max_level; i++) {
			int tmp = ac[cur][i-1];
			ac[cur][i] = ac[tmp][i-1];
			maxDist[cur][i] = Math.max(maxDist[cur][i-1], maxDist[tmp][i-1]);
		}
		
		for (ToCost next : tree[cur]) {
			if (next.v != parent) {
				make_tree(next.v, cur, next.w);
			}
		}
	}
	static int lca(int a, int b) {
		if (depth[a] != depth[b]) {
			if (depth[a] > depth[b]) {
				int tmp = a;
				a = b;
				b = tmp;
			}
			
			for (int i = max_level; i >= 0; i--) {
				if (depth[a] <= depth[ac[b][i]])
					b = ac[b][i];
			}
		}
		int lca = a;
		if (a != b) {
			for (int i = max_level; i >= 0; i--) {
				if (ac[a][i] != ac[b][i]) {
					a = ac[a][i];
					b = ac[b][i];
				}
				lca = ac[a][i];
			}
		}
		return lca;				
	}
	static class ToCost {
		int v, w;
		public ToCost(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}
	static class Edge implements Comparable<Edge> {
		int u, v, w;
		public Edge(int u, int v, int w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}
		@Override
		public int compareTo(Edge o) {
			if (this.w < o.w) return -1;
			else if (this.w > o.w) return 1;
			else return 0;
		}
	}
}