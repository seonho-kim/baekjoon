import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_fail {

	static int T, N, M, K;
	static final int MAX = 101;
	static final int INF =  Integer.MAX_VALUE;
	static ArrayList<Node>[] list = new ArrayList[MAX];
	static int[][] d = new int[MAX][10001];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine(), " " );
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			for (int i = 1; i <= N; i++) {
				list[i] = new ArrayList<Node>();
			}
			for (int i = 1; i <= N; i++) {
				for (int j = 0; j <= M; j++)
					d[i][j] = INF;
			}
			
			for (int i = 1; i <= K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int a, b, cost, time;
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				cost = Integer.parseInt(st.nextToken());
				time = Integer.parseInt(st.nextToken());
				
				list[a].add(new Node(b, cost, time));
			}
			
			PriorityQueue<Node> pq = new PriorityQueue<Node>();
			pq.offer(new Node(1, M, 0));
			d[1][M] = 0;
			while (!pq.isEmpty()) {
				Node temp = pq.poll();
				int v = temp.to;
				int w = temp.cost;
				int t = temp.time;
				
				if (v != 1 && d[v][w] <= t) continue;
				d[v][w] = t;
				if (v == N) break;
				for (int i = 0; i < list[v].size(); i++) {
					int there = list[v].get(i).to;
					int cost = list[v].get(i).cost;
					int time = list[v].get(i).time;
					if (w-cost >= 0 && d[there][w-cost] > d[v][w] + time)
						pq.offer(new Node(there, w-cost, d[v][w] + time));
				}
			}
			int answer = INF;
			for (int i = 0; i <= M; i++)
				answer = Math.min(answer, d[N][i]);
			if (answer == INF) bw.write("Poor KCM\n");
			else bw.write(answer + "\n");
			bw.flush();
		}
	}
	static class Node implements Comparable<Node> {
		int to, cost, time;
		public Node (int to, int cost, int time) {
			this.to= to;
			this.cost = cost;
			this.time = time;
		}
		@Override
		public int compareTo(Node o) {
			if (this.time < o.time) return -1;
			else if (this.time > o.time) return 1;
			else return 0;
		}		
	}
}