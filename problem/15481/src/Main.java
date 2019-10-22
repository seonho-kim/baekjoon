import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static final int MAX = 200001;
	static int[] p = new int[MAX];
	static Pair[][] ac = new Pair[MAX][19];
	static ArrayList<Pair>[] adj = new ArrayList[MAX];
	static Edge[] e = new Edge[MAX];
	static long[] ans = new long[MAX];
	static int[] depth = new int[MAX];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u, v, w;
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			e[i] = new Edge(i, u, v, w);
		}
		Arrays.sort(e, 0, M);
		
		for (int i = 0; i <= N; i++) {
			adj[i] = new ArrayList<Pair>();
			p[i] = -1;
		}
		
		long mst_total = 0;
		int cnt = 0;//, idx = 0;
		for(int i = 0; i < M; i++) {
			if (union(e[i].u, e[i].v)) {
				mst_total += e[i].w;
				adj[e[i].u].add(new Pair(e[i].v, e[i].w));
				adj[e[i].v].add(new Pair(e[i].u, e[i].w));
				if (++cnt == N-1) break;
			}
		}
//		while (cnt < N - 1) {
//		    Edge tmp = e[idx++];
//		    if(!union(tmp.u, tmp.v)) continue;
//		    mst_total += tmp.w;
//		    cnt++;
//		    adj[tmp.u].add(new Pair(tmp.v, tmp.w));
//		    adj[tmp.v].add(new Pair(tmp.u, tmp.w));
//	    }
		
		dfs(1, 0, 0);
		for (int i = 0; i < M; i++) {
			int mx = lca(e[i].u, e[i].v);
			ans[e[i].idx] = mst_total - mx + e[i].w;
		}
		for (int i = 0; i < M; i++) {
			bw.write(ans[i] + "\n");
		}
		bw.flush();
	}
	static int lca(int a, int b) {
		if (depth[a] > depth[b]) {
			int tmp = a;
			a = b;
			b = tmp;
		}
		int lca = 0;
		for (int i = 18; i >= 0; i--) {
			if (depth[a] <= depth[ac[b][i].first]) {
				lca = Math.max(lca, ac[b][i].second);
				b = ac[b][i].first;
			}
		}
		if (a == b) return lca;
		if (a != b) {
			for (int j = 18; j >= 0; j--) {				
				if (ac[a][j].first != 0 && ac[a][j].first != ac[b][j].first) {
					lca = Math.max(lca, ac[a][j].second);
					lca = Math.max(lca, ac[b][j].second);
					a = ac[a][j].first;
					b = ac[b][j].first;
				}
			}
			lca = Math.max(lca, ac[a][0].second);
			lca = Math.max(lca, ac[b][0].second);
			return lca;
		}
		return -1;
	}
	static void dfs(int cur, int p, int w) {
		depth[cur] = depth[p] + 1;
		ac[cur][0] = new Pair(p, w);
		
		for (int i = 1; i <= 18; i++) {
			Pair tmp = ac[cur][i-1];
			if (ac[tmp.first][i-1] == null) ac[tmp.first][i-1] = new Pair(0, 0);
			ac[cur][i] = new Pair(ac[tmp.first][i-1].first, Math.max(tmp.second, ac[tmp.first][i-1].second));
		}
		for (Pair next : adj[cur]) {
			if (next.first != p)
				dfs(next.first, cur, next.second);
		}
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
	static class Edge implements Comparable<Edge> {
		int idx, u, v, w;
		public Edge(int idx, int u, int v, int w) {
			this.idx = idx;
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
	static class Pair {
		int first, second;
		public Pair(int first, int second) {
			this.first = first;
			this.second = second;
		}
	}
}