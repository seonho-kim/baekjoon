import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	static final int MAX = 1001;
	static int N, M, V;
	static ArrayList<Integer>[] adj = new ArrayList[MAX];
	static boolean[] visited = new boolean[MAX];
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<Integer>(); 
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			adj[x].add(y);
			adj[y].add(x);
		}
		
		for (int i = 1; i <= N; i++) {
			Collections.sort(adj[i]);
		}
		
		DFS(V);
		Arrays.fill(visited, false);
		BFS(V);
	}
	public static void DFS(int n) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		Stack<Integer> s = new Stack<Integer>();
		s.push(n);
		
		while(!s.isEmpty()) {
			int v = s.pop();
			if (visited[v])
				continue;
			else {
				visited[v] = true;
				bw.write(Integer.valueOf(v) + " ");
				for (int i = adj[v].size() - 1; i >= 0 ; i--) {
					int next = adj[v].get(i);
					if (!visited[next]) {
						s.push(next);
					}
				}
			}
		}
		bw.write("\n");
		bw.flush();
	}
	
	public static void BFS(int n) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
				
		Deque<Integer> q = new LinkedList<Integer>();
		q.add(n);
		visited[n] = true;
		
		while(!q.isEmpty()) {
			int value = q.pollFirst();
			bw.write(Integer.valueOf(value) + " ");
			
			for (int i = 0; i < adj[value].size(); i++) {
				int next = adj[value].get(i); 
				if (!visited[next]) {
					q.add(next);
					visited[next] = true;					
				}
			}
		}
		bw.write("\n");
		bw.flush();
	}
}