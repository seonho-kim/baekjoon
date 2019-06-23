import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

import javax.print.StreamPrintService;

public class Main {

	static int N;
	static final int MAX = 101;
	static char[][] map = new char[MAX][MAX];
	static boolean[][] visited = new boolean[MAX][MAX];
	static Deque<Integer> q = new ArrayDeque<Integer>();
	static int[] dx = {0, -1, 0, 1};
	static int[] dy = {-1, 0, 1, 0};
	static int cntN, cntRG;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <= N; i++) {
			String tmp = br.readLine();
			for (int j = 1; j <= N; j++) {
				map[i][j] = tmp.charAt(j-1);
			}
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (visited[i][j] == false) {
					cntN++;
					q.offer(i); q.offer(j);
					visited[i][j] = true;
					
					while(!q.isEmpty()) {
						int curX = q.poll();
						int curY = q.poll();
						
						for (int k = 0; k < 4; k++) {
							int nextX = curX + dx[k];
							int nextY = curY + dy[k];
							
							if (nextX > 0 && nextY > 0 && nextX <= N && nextY <= N) {
								if (visited[nextX][nextY] == false && map[i][j] == map[nextX][nextY]) {
									q.offer(nextX);
									q.offer(nextY);
									visited[nextX][nextY] = true;
								}
							}
						}
					}
				}
			}
		}
		
		for (int i = 1; i <= N; i++)
			Arrays.fill(visited[i], false);
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (visited[i][j] == false) {
					cntRG++;
					q.offer(i); q.offer(j);
					visited[i][j] = true;
					
					while(!q.isEmpty()) {
						int curX = q.poll();
						int curY = q.poll();
						
						for (int k = 0; k < 4; k++) {
							int nextX = curX + dx[k];
							int nextY = curY + dy[k];
							
							if (nextX > 0 && nextY > 0 && nextX <= N && nextY <= N) {
								if (visited[nextX][nextY] == false) {
									if (map[i][j] == 'R' || map[i][j] == 'G') {
										if (map[nextX][nextY] == 'R' || map[nextX][nextY] == 'G') {
											q.offer(nextX);
											q.offer(nextY);
											visited[nextX][nextY] = true;
										}
									}
									else {
										if (map[i][j] == map[nextX][nextY]) {
											q.offer(nextX);
											q.offer(nextY);
											visited[nextX][nextY] = true;
										}
									}
								}
							}
						}
					}
				}
			}
		}

		bw.write(cntN + " " + cntRG);
		bw.flush();
	}
}