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

	static int N, M, X;
	static final int INF = Integer.MAX_VALUE;
	static ArrayList<Node>[] list;
	static boolean[] visited;
	static int[] dist, toHome;
	static PriorityQueue<Node> pq = new PriorityQueue<Node>();
	static int answer = 0;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<Node>();
		}
		visited = new boolean[N+1];
		dist = new int[N+1];
		toHome = new int[N+1];
		
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int from, to, time;
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			time = Integer.parseInt(st.nextToken());
			
			list[from].add(new Node(to, time));
			// list[to].add(new Node(from, time));
		}
		
		for (int i = 1; i <= N; i++) {
			if (i != 1) Arrays.fill(visited, false);
			Arrays.fill(dist, INF);
			
			dist[i] = 0;
			pq.add(new Node(0, i));
			
			while(!pq.isEmpty()) {
				int cur;
				do {
					cur = pq.poll().time;
				} while (!pq.isEmpty() && visited[cur]);
				
				if (visited[cur]) break;
				
				visited[cur] = true;
				for (Node node : list[cur]) {
					int next = node.to, time = node.time;
					if (dist[next] > dist[cur] + time) {
						dist[next] = dist[cur] + time;
						pq.add(new Node(dist[next], next));
					}
				}
			}
			
			toHome[i] += dist[X];
			if (i == X) {
				for (int j = 1; j <= N; j++) {
					toHome[j] += dist[j];
				}
			}
		}
		
		for (int j = 1; j <= N; j++) {
			answer = Math.max(answer, toHome[j]);
		}
		bw.write(answer + "\n");
		bw.flush();
	}
	static class Node implements Comparable<Node> {
		int to, time;
		public Node(int to, int time) {
			this.to = to;
			this.time = time;
		}
		
		@Override
		public int compareTo(Node o) {
			if (this.to < o.to) return -1;
			else if (this.to > o.to) return 1;
			else {
				if (this.time < o.time) return -1;
				else if (this.time > o.time) return 1;
				else return 0;
			}
		}
	}
}