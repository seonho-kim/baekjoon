import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	static int T, K, N;
	static final int MAX = 15;
	static int[][] house = new int[MAX][MAX];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		T = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			K = Integer.parseInt(br.readLine());
			N = Integer.parseInt(br.readLine());
			
			for (int i = 0; i <= K; i++) {
				for (int j = 1; j <= N; j++) {
					if (i == 0) house[i][j] = j;
					else {
						if (j == 1) house[i][j] = house[i-1][j];
						else house[i][j] = house[i][j-1] + house[i-1][j];
					}
				}
			}
			bw.write(house[K][N] + "\n");
			bw.flush();
		}
	}
}