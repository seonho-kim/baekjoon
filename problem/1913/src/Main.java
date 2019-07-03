import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	static int N, T, x, y;
	static int[][] map;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		T = Integer.parseInt(br.readLine());
		
		map = new int[N+1][N+1];
		
		int px = N/2+1, py = N/2+1;
		int count = 0;
		int addNum = 0;
		int targetNum = 1;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				int value = (i-1)*N + j;
				if (value == T) {
					x = px; y = py;
				}
				
				if (count == 0 && value == targetNum) {
					addNum++;
					targetNum += addNum;
					count++;
				} else if (count == 1 && value == targetNum) {
					targetNum += addNum;
					count++;
				}
				
				map[px][py] = value;
				
				if (count == 2) count = 0;
				int l = count == 0 ? 1 : 0;
				if (addNum % 2 == 1) {
					px = px+dx[l];
					py = py+dy[l];
				} else {
					px = px+dx[l+2];
					py = py+dy[l+2];
				}
			}
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				bw.write(map[i][j] + " ");
			}
			bw.write("\n");
			bw.flush();
		}
		bw.write(x + " " + y);
		bw.flush();
	}

}
