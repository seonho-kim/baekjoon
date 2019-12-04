import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[] output;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		output = new int[M]; 
		combination(N, M, 0, 0);
	}
	static void combination(int n, int r, int idx, int target) {
		if (r == 0) {
			for (int i : output) {
				System.out.print((i + 1) + " ");
			}
			System.out.println();
			return;
		}
		if (target == n) return;

		output[idx] = target;
		combination(n, r-1, idx+1, target+1);
		combination(n, r, idx, target+1);
	}
}