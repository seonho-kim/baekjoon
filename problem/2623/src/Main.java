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
	static final int MAXN = 1001;
	static ArrayList<Integer>[] list = new ArrayList[MAXN];
	static int[] indegree = new int[MAXN];
	static String answer = "";
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i <= N; i++) {
			list[i] = new ArrayList<Integer>();
		}
		
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from, to, tmp;
			
			int iter = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			for (int j = 1; j < iter; j++) {
				tmp = to;
				to = Integer.parseInt(st.nextToken());
				from = tmp;
				
				list[from].add(to);
				indegree[to]++;
			}
		}
		
		Queue<Integer> q = new ArrayDeque<Integer>();
		for (int i = 1; i <= N; i++) {
			if (indegree[i] == 0)
				q.offer(i);
		}
		
		while(!q.isEmpty()) {
			int v = q.poll();
			sb.append(v + "\n");
			
			for (int next : list[v]) {
				indegree[next]--;
				
				if (indegree[next] == 0)
					q.offer(next);
			}
		}

		for (int i = 1; i <= N; i++) {
			if (indegree[i] != 0) {
				sb = new StringBuilder("0");
			}
		}
		bw.write(sb.toString());		
		bw.flush();
	}
}