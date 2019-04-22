import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

	static final int MAX = 501;
	static int TC;
	static int N, M;
	static int[][] list = new int[MAX][MAX];
	static int[] order = new int[MAX];
	static int[] indegree = new int[MAX];
    
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		TC = Integer.parseInt(st.nextToken());
		
		for (int tc = 1;  tc <= TC; tc++) {
			for (int i = 0; i < MAX; i++) {
				Arrays.fill(list[i], 0);
			}
			Arrays.fill(order, 0);
			Arrays.fill(indegree, 0);
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 1; i <= N; i++) {
				order[i] = Integer.parseInt(st.nextToken());
			}
			
			for (int i = 1; i <= N; i++) {
				for (int j = i + 1; j <= N; j++) {
					list[order[i]][order[j]] = 1;
					indegree[order[j]]++;
				}
			}
			
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				if (list[x][y] == 1) {
					list[x][y] = 0;
					list[y][x] = 1;
					indegree[y]--; indegree[x]++;
				} else {
					list[y][x] = 0;
					list[x][y] = 1;
					indegree[x]--; indegree[y]++;
				}
			}
			
			Deque<Integer> q = new LinkedList<Integer>();
			for (int i = 1; i <= N; i++) {
				if (indegree[i] == 0)
					q.addLast(i);
			}
			
			boolean possible = true;
			ArrayList<Integer> answer = new ArrayList<Integer>();
			while(!q.isEmpty()) {
				if (q.size() > 1) {
					possible = false;
					break;
				}
				
				int cur = q.pollFirst();
				answer.add(cur);
				
				for (int next = 1; next <= N; next++) {
					if (list[cur][next] == 1) {
						indegree[next]--;
						if (indegree[next] == 0)
							q.add(next);
					}
				}
			}
			
			if (!possible)
				bw.write("?\n");
			else if (answer.size() == N) {
				for (int i = 0; i < N; i++) {
					bw.write(Integer.valueOf(answer.get(i)) + " ");
				}
				bw.write("\n");
			}
			else {
				bw.write("IMPOSSIBLE\n");
			}
		}
		bw.flush();
	}
}