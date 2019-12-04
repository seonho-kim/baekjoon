import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[] list = new int[8], output;
	static Set<String> answer = new LinkedHashSet<String>();
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		output = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			list[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(list, 0, N);
		combination(N, M, 0, 0);
		for (String tmp : answer) {
			bw.write(tmp + "\n");
		}
		bw.flush();
	}
	static void combination(int n, int r, int idx, int target) {
		if (r == 0) {
			sb = new StringBuilder();
			for (int i : output) {
				sb.append(i + " ");
			}
			answer.add(sb.toString());
			return;
		}
		if (target == n) return;

		output[idx] = list[target];
		combination(n, r-1, idx+1, target+1);
		combination(n, r, idx, target+1);
	}
}