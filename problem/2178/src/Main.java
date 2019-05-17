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

	static int N, M;
	static int[][] map = new int[101][101];
	static int[][] visited = new int[101][101];
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for (int i = 1; i <= N; i++) {
			String temp = br.readLine();
			for (int j = 1; j <= M; j++) {
				map[i][j] = temp.charAt(j - 1) - 48;
			}
		}
		
		Queue<Point> q = new LinkedList<Point>();
		q.add(new Point(1, 1));
		visited[1][1] = 1;
		
		while(!q.isEmpty()) {
			Point v = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int x = v.x + dx[i];
				int y = v.y + dy[i];
				
				if (x >= 1 && y >= 1 && x <= N && y <= M) {
					if (map[x][y] == 1 && visited[x][y] == 0) {
						visited[x][y] = visited[v.x][v.y] + 1;
						q.add(new Point(x, y));
					}
				}
			}
		}
		
		bw.write(visited[N][M] + "");
		bw.flush();
	}
}