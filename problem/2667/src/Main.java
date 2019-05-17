import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	static int N;
	static int[][] map = new int[26][26];
	static int[][] visited = new int[26][26];
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	static int[] result = new int[626];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw=  new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <= N; i++) {
			String temp = br.readLine();
			for (int j = 1; j <= N; j++) {
				map[i][j] = temp.charAt(j - 1) - 48;
			}
		}
		
		int cnt = 1;
		Queue<Point> q = new LinkedList<Point>();
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (map[i][j] == 1 && visited[i][j] == 0) {
					map[i][j] = cnt;
					q.add(new Point(i, j));
					visited[i][j] = 1;
					
					while(!q.isEmpty()) {
						Point v = q.poll();
						
						for (int k = 0; k < 4; k++) {
							int x = v.x + dx[k];
							int y = v.y + dy[k];
							
							if (x >= 1 && y >= 1 && x <= N && y <= N) {
								if (map[x][y] == 1 && visited[x][y] == 0) {
									map[x][y] = cnt;
									q.add(new Point(x, y));
									visited[x][y] = 1;
								}
							}
						}
					}
					cnt++;
				}
			}
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {				
				if (map[i][j] != 0) {
					result[map[i][j]]++;
				}
			}
		}
		Arrays.sort(result, 1, cnt);
		
		StringBuilder sb = new StringBuilder();
		sb.append((cnt - 1) + "\n");
		for (int i = 1; i < cnt; i++)
			sb.append(result[i] + "\n");
		
		bw.write(sb.toString());
		bw.flush();
	}
}