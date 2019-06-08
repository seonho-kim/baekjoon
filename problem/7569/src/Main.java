import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int M, N, H;
	static final int MAX = 101;
	static int[][][] map = new int[MAX][MAX][MAX];
	static int[][][] visited = new int[MAX][MAX][MAX];
	static int[] dz = {1, -1};
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};
	static int answer = 0;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " " );
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		for (int i = 1; i <= H; i++) {
			for (int j = 1; j <= N; j++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int k = 1; k <= M; k++) {
					map[i][j][k] = Integer.parseInt(st.nextToken());
				}
			}
		}
		
		Queue<Integer> q = new ArrayDeque<Integer>();
		for (int i = 1; i <= H; i++) {
			for (int j = 1; j <= N; j++) {
				for (int k = 1; k <= M; k++) {
					if (map[i][j][k] == 1 && visited[i][j][k] == 0) {
						q.offer(i);
						q.offer(j);
						q.offer(k);
						
						visited[i][j][k] = 1;
					}
				}
			}
		}
						
		while (!q.isEmpty()) {
			int z = q.poll();
			int y = q.poll();
			int x = q.poll();
			
			for (int l = 0; l < dz.length; l++) {
				int nextZ = z + dz[l];
				
				if (nextZ > 0 && y > 0 && x > 0 && nextZ <= H && y <= N && x <= M) {
					if (map[nextZ][y][x] == 0 && visited[nextZ][y][x] == 0) {
						q.offer(nextZ);
						q.offer(y);
						q.offer(x);
						visited[nextZ][y][x] = visited[z][y][x] + 1;
						answer = Math.max(answer, visited[nextZ][y][x]);
					}
				}
			}
				
			for (int m = 0; m < dy.length; m++) {
				int nextY = y + dy[m];
				int nextX = x + dx[m];
				
				if (z > 0 && nextY > 0 && nextX > 0 && z <= H && nextY <= N && nextX <= M) {
					if (map[z][nextY][nextX] == 0 && visited[z][nextY][nextX] == 0) {
						q.offer(z);
						q.offer(nextY);
						q.offer(nextX);
						visited[z][nextY][nextX] = visited[z][y][x] + 1;
						answer = Math.max(answer, visited[z][nextY][nextX]);
					}
				}
			}
		}
		
		check:
		for (int i = 1; i <= H; i++) {
			for (int j = 1; j <= N; j++) {
				for (int k = 1; k <= M; k++) {
					if (map[i][j][k] == 0 && visited[i][j][k] == 0) {
						answer = -1;
						break check;
					}
				}
			}
		}
		bw.write((answer > 0 ? answer - 1 : answer) + "");
		bw.flush();
	}
}