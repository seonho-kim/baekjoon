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

	final static int MAX_V = 800;
	final static int INF = 1001;
	
	static int N, E, S, A, B;

	static int[] dist;
	static int[] distA;
	static int[] distB;
	static boolean[] visited;
	static ArrayList<Edge> adj[] = new ArrayList[MAX_V];
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		S = 0;
		for (int i = 0; i < N; i++) {
			adj[i] = new ArrayList<Edge>();
		}
		
		for (int i = 0; i < E; i++) {
			int u, v, w;
			st = new StringTokenizer(br.readLine(), " ");
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			adj[u-1].add(new Edge(v-1, w));
			adj[v-1].add(new Edge(u-1, w));
		}
		dist = new int[MAX_V];
		distA = new int[MAX_V];
		distB = new int[MAX_V];
		Arrays.fill(dist, INF);
		Arrays.fill(distA, INF);
		Arrays.fill(distB, INF);
		visited = new boolean[MAX_V];
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		
		dist[S] = 0;
		pq.add(new Edge(0, S));
		while(!pq.isEmpty()) {
			int current;
			do {
				current = pq.poll().w;
			} while(!pq.isEmpty() && visited[current]);

			if (visited[current]) break;

			visited[current] = true;
			for (Edge p : adj[current]) {
				int next = p.v, d = p.w;
				if(dist[next] > dist[current] + d){
					dist[next] = dist[current] + d;
					pq.add(new Edge(dist[next], next));
				}
			}
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		
		Arrays.fill(visited, false);
		distA[A-1] = 0;
		pq.add(new Edge(0, A-1));
		while(!pq.isEmpty()) {
			int current;
			do {
				current = pq.poll().w;
			} while(!pq.isEmpty() && visited[current]);

			if (visited[current]) break;

			visited[current] = true;
			for (Edge p : adj[current]) {
				int next = p.v, d = p.w;
				if(distA[next] > distA[current] + d){
					distA[next] = distA[current] + d;
					pq.add(new Edge(distA[next], next));
				}
			}
		}
		
		Arrays.fill(visited, false);
		distB[B-1] = 0;
		pq.add(new Edge(0, B-1));
		while(!pq.isEmpty()) {
			int current;
			do {
				current = pq.poll().w;
			} while(!pq.isEmpty() && visited[current]);

			if (visited[current]) break;

			visited[current] = true;
			for (Edge p : adj[current]) {
				int next = p.v, d = p.w;
				if(distB[next] > distB[current] + d){
					distB[next] = distB[current] + d;
					pq.add(new Edge(distB[next], next));
				}
			}
		}
		
		int answer = Math.min(dist[A-1] + distA[B-1] + distB[N-1], dist[B-1] + distB[A-1] + distA[N-1]);
		bw.write(answer >= INF || answer < 0 ? "-1" : String.valueOf(answer));
		bw.flush();
	}
}

class Edge implements Comparable<Edge> {
	int v, w;
	
	public Edge(int v, int w) {
		this.v = v;
		this.w = w;
	}
	
	@Override
	public int compareTo(Edge e) {

		return Integer.valueOf(v).compareTo(Integer.valueOf(e.v));
	}
	
}