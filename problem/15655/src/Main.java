import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[] list, output;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		list = new int[N];
		output = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			list[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(list);
		combination(N, M, 0, 0);
		bw.flush();
	}
	static void combination(int n, int r, int idx, int target) throws IOException {
		if (r == 0) {
			for (int i : output) {
				bw.write(i + " ");
			}
			bw.write("\n");
			return;
		}
		if (target == n) return;

		output[idx] = list[target];
		combination(n, r-1, idx+1, target+1);
		combination(n, r, idx, target+1);
	}
}