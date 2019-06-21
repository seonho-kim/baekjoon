import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static final int MAX = 40001;
	static int max_level = (int)Math.floor(Math.log(MAX)/Math.log(2));
	static ArrayList<Node>[] graph = new ArrayList[MAX];
	static int[][] ac = new int[MAX][max_level + 1];
	static int[] depth = new int[MAX]; 
	static int[] dist = new int[MAX];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<Node>(); 
		}
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
		
		M = Integer.parseInt(br.readLine());
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a, b, p, q;
			p = a = Integer.parseInt(st.nextToken());
			q = b = Integer.parseInt(st.nextToken());
			
			if (depth[a] != depth[b]) {
				if (depth[a] > depth[b]) {
					int tmp = a;
					a = b;
					b = tmp;
				}
				
				for (int j = max_level; j >= 0; j--) {
					if (depth[a] <= depth[ac[b][j]]) {
						b = ac[b][j];
					}
				}
			}
			int lca = a;
			
			if (a != b) {
				for (int j = max_level; j >= 0; j--) {
					if (ac[a][j] != ac[b][j]) {
						a = ac[a][j];
						b = ac[b][j];
					}
					lca = ac[a][j];
				}
			}
			
			bw.write(dist[p] + dist[q] - 2*dist[lca] + "\n");
			bw.flush();
		}
		
	}
	static void getTree(int current, int parent, int value) {
		dist[current] = dist[parent] + value; 
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
			this.to= to;
			this.weight = weight;
		}
	}
}