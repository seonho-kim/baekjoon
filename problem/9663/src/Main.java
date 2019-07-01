import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	static int N;
	static final int MAX = 16;
	static int[] col = new int[MAX];
	static int answer;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <= N; i++) {
			col[1] = i;
			find(1);
		}
		bw.write(answer + "");
		bw.flush();
	}
	static void find(int row) {
		
		if (row == N)
			answer++;
		else {
			for (int i = 1; i <= N; i++) {
				col[row + 1] = i;
				if (isPossible(row + 1)) {
					find(row + 1);
				} else {
					col[row + 1] = 0;
				}
			}
		}
		col[row] = 0;
	}
	static boolean isPossible(int c) {
		for (int i = 1; i < c; i++ ) {
			if (col[i] == col[c]) {
				return false;
			}
			if (Math.abs(col[i] - col[c]) == Math.abs(i - c)) {
				return false;
			}
		}
		return true;
	}
}