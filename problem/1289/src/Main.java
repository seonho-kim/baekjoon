import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static final int MAX = 100001;
	static final int MOD = 1000000007;
	static ArrayList<Node>[] tree = new ArrayList[MAX];
	static boolean[] visited = new boolean[MAX];
	static long answer = 0;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <= N; i++) {
			tree[i] = new ArrayList<Node>();
		}
		
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from, to, value;
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			value = Integer.parseInt(st.nextToken());
			
			tree[from].add(new Node(to, value));
			tree[to].add(new Node(from, value));
		}
		DFS(1, -1);
		
		bw.write(answer + "");
		bw.flush();
	}
	static long DFS(int node, long end) {
		long s = 1, t;
		for (Node next : tree[node]) {
			if (next.to == end) continue;
			t = DFS(next.to, node)*next.value % MOD;
			answer = (answer + t*s) % MOD;
			s = (s+t)%MOD;
		}
		return s;
	}
	static class Node {
		int to, value;
		public Node(int to, int value) {
			this.to = to;
			this.value = value;
		}
	}
}