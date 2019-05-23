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
	static final int MAX = 100000;
	static int[][] parent = new int[MAX][18];	// Math.log(100000) / Math.log(2) --> log_[2] 100000(밑이 2이고 진수가 100000)
	static int[] depth = new int[MAX];
	static ArrayList<Integer>[] adj = new ArrayList[MAX];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			adj[i] = new ArrayList<Integer>();
		}
		
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int u, v;
			u = Integer.parseInt(st.nextToken()) - 1;
			v = Integer.parseInt(st.nextToken()) - 1;
			adj[u].add(v);
			adj[v].add(u);
		}
		
		for (int i = 0; i < N; i++)
			Arrays.fill(parent[i], -1);
		Arrays.fill(depth, -1);
		depth[0] = 1;
		
		MakeTreeByDFS(0);
		
		// parent배열
		for (int j = 0; j < 18; j++)
			for (int i = 1; i < N; i++)
				if (parent[i][j] != -1)
					parent[i][j + 1] = parent[parent[i][j]][j];		
		
		M = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int u, v;
			u = Integer.parseInt(st.nextToken()) - 1;
			v = Integer.parseInt(st.nextToken()) - 1;
			
			// depth[u] >= depth[v] 만족하도록 u, v 스왑
			if (depth[u] < depth[v]) {
				int tmp = u;
				u = v;
				v = tmp;
			}
			
			int diff = depth[u] - depth[v];
			
			// 깊이 차이 없애며 u이동시킴
			for (int j = 0; diff > 0; j++) {
				if ((diff & 1) == 1)
					u = parent[u][j];
				diff >>= 1;
			}
			
			// u와 v가 다르면 일정 높이만큼 위로 이동시킴
			if (u != v) {
				// 2^17, 2^16... 2, 1 순으로 시도
				for (int j = 17; j >= 0; j--) {
					if ((parent[u][j] != -1) && (parent[u][j] != parent[v][j])) {
						u = parent[u][j];
						v = parent[v][j];
					}
				}
				// 마지막엔 두 정점 u, v의 부모가 같으므로 한 번 더 올림
				u = parent[u][0];
			}
			sb.append((u + 1) + "\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
	}
	
	// DFS로 트리 만들면서 parent[i][0], depth 배열 채움
	static void MakeTreeByDFS(int curr) {
		for (int next : adj[curr]) {
			if (depth[next] == -1) {
				parent[next][0] = curr;
				depth[next] = depth[curr] + 1;
				MakeTreeByDFS(next);
			}
		}
	}
}