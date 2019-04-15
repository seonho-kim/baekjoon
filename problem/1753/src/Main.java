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

	final static int MAX_V = 20000;
	final static int INF = 1000000000;
	static int V, E, K;

	static int[] dist;
	static boolean[] visited;
	static ArrayList<Edge> adj[] = new ArrayList[MAX_V];

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken()) - 1;
		for (int i = 0; i < V; i++) {
			adj[i] = new ArrayList<Edge>();
		}
		
		for (int i = 0; i < E; i++) {
			int u, v, w;
			st = new StringTokenizer(br.readLine(), " ");
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			adj[u-1].add(new Edge(v-1, w));
		}
		dist = new int[MAX_V];
		Arrays.fill(dist, INF);
		visited = new boolean[MAX_V];
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		
		dist[K] = 0;
		pq.add(new Edge(0, K));
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
		
	    for (int i = 0; i < V; i++) {
	    	bw.write(dist[i] == INF ? "INF\n" : String.format("%d\n", dist[i]));
	    }
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
	public int compareTo(Edge n) {
		return Integer.valueOf(v).compareTo(Integer.valueOf(n.v));
	}
}