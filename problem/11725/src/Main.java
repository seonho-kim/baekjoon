import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static ArrayList<Integer>[] arr;
	static int[] visited;
	static int[] parentTable;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(st.nextToken());
		
		arr = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			arr[i] = new ArrayList<Integer>();
		}
		visited = new int[N + 1];
		parentTable = new int[N + 1];
		
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			arr[x].add(y);
			arr[y].add(x);
		}
		
		DFS(1, 0);
		
		for (int i = 2; i <= N; i++) {
			bw.write(parentTable[i] + "\n");
		}
		bw.flush();
	}
	
	static void DFS(int node, int parent) {
		visited[node] = 1;
		parentTable[node] = parent;
		
		for (int n : arr[node]) {
			if (visited[n] == 0) {
				DFS(n, node);
			}
		}
	}
}