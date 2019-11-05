import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int R, C, W, result = 0;
	static final int MAX = 31;
	static int[][] arr = new int[MAX][MAX];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < R+W-1; i++) {
			for (int j = 0; j <= i; j++) {
				if (j == 0 || j== i) arr[i][j] = 1;
				else {
					arr[i][j] = arr[i-1][j-1] + arr[i-1][j];
				}
				
				if (i >= R-1 && i <= R+W-1 && j >= C-1 && j <= C+(i-R)) {
					result += arr[i][j];
				}
			}
		}
		bw.write(result + "");
		bw.flush();
	}
}