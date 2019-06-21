import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static int N, K;
	static final int MAX = 100001;
	static int max_level = (int)Math.floor(Math.log(MAX)/Math.log(2));
	static ArrayList<Node>[] graph = new ArrayList[MAX];
	static int[][] ac = new int[MAX][max_level + 1];
	static int[] depth = new int[MAX];
	static int[][] min = new int[MAX][max_level + 1];
	static int[][] max = new int[MAX][max_level + 1];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		for (int i = 1; i <= N; i++)
			graph[i] = new ArrayList<Node>();
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from, to, weight;
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			weight = Integer.parseInt(st.nextToken());
			
			graph[from].add(new Node(to, weight));
			graph[to].add(new Node(from, weight));
		}
		
		depth[0] = -1;
		getTree(1, 0, 0);
		minMax();
		
		K = Integer.parseInt(br.readLine());
		for (int i = 1; i <= K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a, b;
			int minAnswer = 987654321, maxAnswer = -1;
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			
			if (depth[a] != depth[b]) {
				if (depth[a] > depth[b]) {
					int tmp = a;
					a = b;
					b = tmp;
				}
				
				for (int j = max_level; j >= 0; j--) {
					if (depth[a] <= depth[ac[b][j]]) {
						minAnswer = Math.min(minAnswer, min[b][j]);
						maxAnswer = Math.max(maxAnswer, max[b][j]);
						b = ac[b][j];
					}
				}
			}
			int lca = a;
			
			if (a != b) {
				for (int j = max_level; j >= 0; j--) {
					if (ac[a][j] != ac[b][j]) {
						minAnswer = Math.min(minAnswer, Math.min(min[a][j], min[b][j]));
						maxAnswer = Math.max(maxAnswer, Math.max(max[a][j], max[b][j]));
						a = ac[a][j];
						b = ac[b][j];
					}
					lca = ac[a][j];
				}
			}
			
			if (a != lca) {
				minAnswer = Math.min(minAnswer, Math.min(min[a][0], min[b][0]));
				maxAnswer = Math.max(maxAnswer, Math.max(max[a][0], max[b][0]));
			}

			bw.write(minAnswer + " " + maxAnswer + "\n");
			bw.flush();
		}
	}
	static void minMax() {
		for (int level = 1; level <= max_level; level++) {
			for (int current = 1; current <= N; current++) {
				int tmp = ac[current][level-1];
				min[current][level] = Math.min(min[current][level-1], min[tmp][level-1]);
				max[current][level] = Math.max(max[current][level-1], max[tmp][level-1]);
			}
		}
	}
	static void getTree(int current, int parent, int value) {
		min[current][0] = value;
		max[current][0] = value;
		depth[current] = depth[parent] + 1;
		ac[current][0] = parent;
		
		for (int i = 1; i <= max_level; i++) {
			int tmp = ac[current][i-1];
			ac[current][i] = ac[tmp][i-1];
		}
		
		for (Node next : graph[current]) {
			if (next.to != parent) {
				getTree(next.to, current, next.weight);
			}
		}
	}
	static class Node {
		int to, weight;
		public Node(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}
}