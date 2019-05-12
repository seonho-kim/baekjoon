import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static ArrayList<Node>[] arr;
	static int[] visited;
	static int answer, farthestNode;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(st.nextToken());
		
		arr = new ArrayList[N + 1];
		for (int i = 0; i < N + 1; i++) {
			arr[i] = new ArrayList<Node>();
		}
		visited = new int[N + 1];
		
		String temp = "";
		while ((temp = br.readLine()) != null) {
			st = new StringTokenizer(temp, " ");
			
			int idx = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			arr[idx].add(new Node(to, weight));
			arr[to].add(new Node(idx, weight));
		}
		
		DFS(1, 0);
		Arrays.fill(visited, 0);
		DFS(farthestNode, 0);
		
		bw.write(answer + "\n");
		bw.flush();
	}
	static void DFS(int node, int weight) {
		if (visited[node] == 1)
			return;
		
		visited[node] = 1;
		
		if (answer < weight)
        {
			answer = weight;
            farthestNode = node;
        }
		
		for (Node n : arr[node]) {
			if (visited[n.to] == 0) {
				DFS(n.to, n.weight + weight);
			}
		}
	}
}

class Node {
	int to;
	int weight;
	
	Node(int to, int weight) {
		this.to = to;
		this.weight = weight;
	}
}