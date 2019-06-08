import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static final int MAX = 101;
	static ArrayList<Integer>[] map = new ArrayList[MAX];
	static boolean[] visited = new boolean[MAX]; 
	static int answer;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		for (int i = 0; i <= N; i++)
			map[i] = new ArrayList<Integer>();
		
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			map[from].add(to);
			map[to].add(from);
		}
		
		Queue<Integer> q = new ArrayDeque<Integer>();
		q.offer(1);
		visited[1] = true;
		
		while (!q.isEmpty()) {
			int node = q.poll();
			
			for (int next : map[node]) {
				if (visited[next] == false) {
					q.offer(next);
					visited[next] = true;
					answer++;
				}
			}
		}
		bw.write(answer + "");
		bw.flush();
	}
}