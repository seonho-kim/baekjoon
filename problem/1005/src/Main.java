import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int T, N, K, W;
	static int[] time;
	static int[][] adj;
	static boolean[] visited;
	static int[] accTime;
	static int[] trackParent;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			time = new int[N + 1];
			adj = new int[N + 1][N + 1];
			visited = new boolean[N + 1];
			trackParent = new int[N + 1];
			accTime = new int[N + 1];
            
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 1; i <= N; i++)
				accTime[i] = time[i] = Integer.parseInt(st.nextToken());
			
			for (int i = 1; i <= K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int u, v;
				u = Integer.parseInt(st.nextToken());
				v = Integer.parseInt(st.nextToken());
				
				adj[u][v] = 1;
				adj[0][v]++;
				adj[u][0]++;
			}
			
			W = Integer.parseInt(br.readLine());

			boolean flag = true;
			Check:
			while (flag) {
				for (int i = 1; i <= N; i++) {
					if (adj[0][i] == 0 && visited[i] == false) {
						visited[i] = true;
						
						for (int j = 1; j <= N; j++)
							if (adj[i][j] == 1) {
								if (adj[0][j] > 0) {
									adj[0][j]--;
									
									if (accTime[trackParent[j]] < accTime[i]) {
										trackParent[j] = i;
										accTime[j] = accTime[trackParent[j]] + time[j];
									}
								}
							}
					}
				}

				for (int i = 1; i <= N; i++)
					if (visited[i] == false) continue Check;
				
				flag = false;
			}
			sb.append(accTime[W] + "\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}
}