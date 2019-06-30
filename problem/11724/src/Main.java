import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static final int MAX = 1001;
	static ArrayList<Integer>[] graph = new ArrayList[MAX];
	static boolean[] visited = new boolean[MAX];
	static int answer;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from, to;
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			
			graph[from].add(to);
			graph[to].add(from);
		}
		
//		Deque<Integer> q = new ArrayDeque<Integer>();
//		for (int i = 1; i <= N; i++) {
//			if (!visited[i]) {
//				q.offer(i);
//				visited[i] = true;
//				answer++;
//			} else continue;
//			
//			while(!q.isEmpty()) {
//				int cur = q.poll();
//				
//				for (int next : graph[cur]) {
//					if (!visited[next]) {
//						q.offer(next);
//						visited[next] = true;
//					}
//				}
//			}
//		}
		Stack<Integer> s = new Stack<Integer>();
		for (int i = 1; i <= N; i++) {
			if (!visited[i]) {
				s.push(i);
				visited[i] = true;
				answer++;
			} else continue;
						
			while(!s.isEmpty()) {
				int cur = s.pop();
				
				for (int next : graph[cur]) {
					if (next == cur) continue;
					if (!visited[next]) {
						s.push(next);
						visited[next] = true;
					}
				}
			}
		}
		bw.write(answer + " ");
		bw.flush();
	}
}