import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	static int N;
	static final int MAX = 65;
	static int[][] arr = new int[MAX][MAX];
	static StringBuffer sb = new StringBuffer();
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());

		char[] low = null;
		for (int i = 1; i <= N; i++) {
			low = br.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				arr[i][j + 1] = Integer.parseInt(low[j] + "");
			}
		}

		solve(1, 1, N);
		bw.write(sb.toString());
		bw.flush();
	}

	static void solve(int x, int y, int size) {
		int temp = arr[x][y];
		boolean isSame = true;
		for (int i = x; (i < x + size) && isSame; i++) {
			for (int j = y; (j < y + size) && isSame; j++) {
				if (temp != arr[i][j])
					isSame = false;
			}
		}
		if (isSame)
			sb.append(temp);
		else {
			sb.append("(");
			solve(x, y, size / 2);
			solve(x, y + size / 2, size / 2);
			solve(x + size / 2, y, size / 2);
			solve(x + size / 2, y + size / 2, size / 2);
			sb.append(")");
		}
	}
}