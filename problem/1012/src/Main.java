import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int T, M, N, K;
	static final int MAXM = 51;
	static int[][] map = new int[MAXM][MAXM];
	static boolean[][] visited = new boolean[MAXM][MAXM];
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			for (int i = 0; i < MAXM; i++) {
				Arrays.fill(map[i], 0);
				Arrays.fill(visited[i], false);
			}
				
			
			for (int i = 1; i <= K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int x, y;
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				map[y][x] = 1;
			}
			
			Queue<Integer> q = new ArrayDeque<Integer>();
			int answer = 0;
			for (int i = 0; i <= N; i++) {
				for (int j = 0; j <= M; j++) {
					if (visited[i][j] == false && map[i][j] == 1) {
						q.offer(i);
						q.offer(j);
						
						visited[i][j] = true;
						
						while(!q.isEmpty()) {
							int x = q.poll();
							int y = q.poll();
							
							for (int k = 0; k < dx.length; k++) {
								int nextX = x + dx[k];
								int nextY = y + dy[k];
								
								if (nextX >= 0 && nextY >= 0 && nextX <= N && nextY <= M) {
									if (visited[nextX][nextY] == false && map[nextX][nextY] == 1) {
										visited[nextX][nextY] = true;
										q.offer(nextX);
										q.offer(nextY);
									}
								}
							}
						}
						answer++;
					}
				}
			}
			
			bw.write(answer + "\n");
		}
		bw.flush();
	}
}