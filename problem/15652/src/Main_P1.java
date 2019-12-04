import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_P1 {

	static int N, M;
	static int[] output;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		output = new int[M]; 
		reCombination(N, M, 0, 0);
		bw.flush();
	}
	static void reCombination(int n, int r, int idx, int target) throws IOException {
		if (r == 0) {
			for (int i : output) {
				bw.write((i + 1) + " ");
			}
			bw.write("\n");
			return;
		}
		if (target == n) return;

		output[idx] = target;
		reCombination(n, r-1, idx+1, target);
		reCombination(n, r, idx, target+1);
	}
}