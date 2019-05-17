import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int M, N;
	static int[][] map = new int[1001][1001];
	static int[][] visited = new int[1001][1001];
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		Queue<Point> q = new LinkedList<Point>();
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (map[i][j] == 1) {
					q.add(new Point(i, j));
					visited[i][j] = 1;
				}
			}
		}
		
		int result = 1;
		if (q.size() != 0) {
			while(!q.isEmpty()) {
				Point v = q.poll();
				
				for (int i = 0; i < 4; i++) {
					int x = v.x + dx[i];
					int y = v.y + dy[i];
					
					if (x >= 1 && y >= 1 && x <= N && y <= M) {
						if (map[x][y] == 0 && visited[x][y] == 0) {
							map[x][y] = map[v.x][v.y] + 1;
							result = Math.max(result, map[x][y]);
							q.add(new Point(x, y));
							visited[x][y] = 1;
						}
					}
				}
			}
			
			Check :
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= M; j++) {
					if (map[i][j] == 0) {
						result = 0;
						break Check;
					}
				}
			}
		}
		
		bw.write((result - 1) + "");
		bw.flush();
	}
}