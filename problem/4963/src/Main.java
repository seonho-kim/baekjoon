import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	static int w, h;
	static final int MAX = 51;
	static int[][] map;
	static boolean[][] visited;
	static int number;
	static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
	static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		String temp = "";
		while((temp = br.readLine()) != null) {
			String[] input = temp.split(" ");
			w = Integer.parseInt(input[0]);
			h = Integer.parseInt(input[1]);
			
			if (w == 0 && h == 0) break;
			
			map = new int[h+1][w+1];
			visited = new boolean[h+1][w+1];
			number = 0;
			Deque<Integer> q = new ArrayDeque<Integer>();
			
			for (int i = 1; i <= h; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 1; j <= w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = 1; i <= h; i++) {
				for (int j = 1; j <= w; j++) {
					if (!visited[i][j] && map[i][j] == 1) {
						q.offer(i); q.offer(j);
						visited[i][j] = true;
						number++;
					} else {
						continue;
					}
					
					while(!q.isEmpty()) {
						int curX = q.poll();
						int curY = q.poll();
						
						for (int k = 0; k < 8; k++) {
							int nextX = curX + dx[k];
							int nextY = curY + dy[k];
							
							if (nextX > 0 && nextY > 0 && nextX <= h && nextY <= w) {
								if (!visited[nextX][nextY] && map[nextX][nextY] == 1) {
									q.offer(nextX); q.offer(nextY);
									visited[nextX][nextY] = true;
								}
							}
						}
					}
				}
			}
			bw.write(number + "\n");
			bw.flush();
		}
	}
}