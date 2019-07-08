import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int T, N, M, K;
	static final int MAX = 101;
	static ArrayList<Edge>[] graph = new ArrayList[MAX];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			for (int i = 1; i <= N; i++) {
				graph[i] = new ArrayList<Edge>();
			}
			while (K-- > 0) {
				st = new StringTokenizer(br.readLine(), " ");
				int u, v, w, t;
				u = Integer.parseInt(st.nextToken());
				v = Integer.parseInt(st.nextToken());
				w = Integer.parseInt(st.nextToken());
				t = Integer.parseInt(st.nextToken());
				graph[u].add(new Edge(v, w, t));
			}
			
			PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
			int[][] dist = new int[M + 1][N + 1];
			for (int i = 0; i <= M; i++) {
				Arrays.fill(dist[i], Integer.MAX_VALUE);
				dist[i][1] = 0;
			}
			pq.offer(new Edge(1, 0, 0));
			
			int answer = Integer.MAX_VALUE;
			while (!pq.isEmpty()) {
				Edge u = pq.poll();
				if (answer <= dist[u.price][u.to]) continue;
				
				for (Edge next : graph[u.to]) {
					int cost = u.price + next.price;
					if (cost > M) continue;
					
					int time = dist[u.price][u.to] + next.time;
					if (answer <= time) continue;
					
					if (next.to == N) {
						answer =time;
						continue;
					}
					if (time < dist[cost][next.to]) {
						if (dist[cost][next.to] == Integer.MAX_VALUE)
							pq.offer(new Edge(next.to, cost, time));
						dist[cost][next.to] = time;
					}
				}
			}
			
			if (answer == Integer.MAX_VALUE) bw.write("Poor KCM\n");
			else bw.write(answer + "\n");
			bw.flush();
		}
	}
	
	static class Edge implements Comparable<Edge> {
		int to, price, time;
		public Edge(int to, int price, int time) {
			this.to = to;
			this.price = price;
			this.time = time;
		}
		
		@Override
		public int compareTo(Edge o) {
			if (this.price < o.price) return -1;
			else if (this.price > o.price) return 1;
			else return 0;
		}
	}
}