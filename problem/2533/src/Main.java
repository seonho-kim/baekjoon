import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static final int MAXN = 1000001;
	static int N;
	static boolean[] visited;// = new boolean[MAXN];
	static int[][] dp;// = new int[MAXN][2];
	static ArrayList<Integer>[] list;// = new ArrayList[MAXN];
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		visited = new boolean[N+1];
		dp = new int[N+1][2];
		list = new ArrayList[N+1];
		
		for (int i = 1; i <= N; i++)
			list[i] = new ArrayList<Integer>();
		
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			list[from].add(to);
			list[to].add(from);
		}
		
		int s = 1;
		dfs(s);
		bw.write(Math.min(dp[s][0], dp[s][1]) + "\n");
		bw.flush();
	}
	public static void dfs(int node) {
		visited[node] = true;
		dp[node][0] = 0;
		dp[node][1] = 1;
		
		for (int next : list[node]) {
			if (!visited[next]) {
				dfs(next);
				dp[node][0] += dp[next][1];
				dp[node][1] += Math.min(dp[next][0], dp[next][1]);
			}
		}
	}
}