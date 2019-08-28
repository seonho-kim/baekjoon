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

	static final int MAXN = 1001;
	static int N, M;
	static ArrayList<Edge>[] list;
	static int[] dist;
	static boolean[] inQ;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw =  new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		list = new ArrayList[N + 1];
		dist = new int[N + 1];
		inQ = new boolean[N + 1];
		
		for (int i = 0; i < N+1; i++) {
			list[i] = new ArrayList();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from, to, value;
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			value = Integer.parseInt(st.nextToken());
					
			list[from].add(new Edge(to, value));
		}
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		st = new StringTokenizer(br.readLine(), " ");
		int s = Integer.parseInt(st.nextToken()); 
		int e = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Integer> q = new PriorityQueue<Integer>();
		q.add(s);
		dist[s] = 0;
		inQ[s] = true;
				
		while (!q.isEmpty())
	    {
	        int current = q.poll();
	        inQ[current] = false;
	 
	        for (int i = 0; i < list[current].size(); i++)
	        {
	            int next = list[current].get(i).to;
	            int cost = list[current].get(i).weight;
	 
	            if(dist[next] > dist[current] + cost)
	            {
	                dist[next] = dist[current] + cost;
	 
	                if (!inQ[next])
	                { 
	                    q.add(next);
	                    inQ[next] = true;
	                }
	            }
	        }
	    }
		
		bw.write(dist[e] + "\n");
		bw.flush();
	}
}
class Edge implements Comparable<Edge> {
	int to, weight;
	
	Edge(int to, int weight) {
		this.to = to;
		this.weight = weight;
	}
	
	@Override
	public int compareTo(Edge n) {
		if (this.to < n.to) return -1;
		else if (this.to > n.to) return 1;
		else {
			if (this.weight < n.weight) return -1;
			else if (this.weight > n.weight) return 1;
			else return 0;
		}
	}
}