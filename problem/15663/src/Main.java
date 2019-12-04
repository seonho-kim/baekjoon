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
	static int[] list = new int[8], output = new int[8];
	static boolean[] visited = new boolean[8];
	static Set<String> answer = new LinkedHashSet<String>();
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			list[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(list, 0, N);
		permutation(0, N, M);
		for (String tmp : answer) {
			bw.write(tmp + "\n");
		}
		bw.flush();
	}
	static void permutation(int depth, int n, int r) {
		if (depth == r) {
			sb = new StringBuilder();
			for (int i = 0; i < r; i++) {
				sb.append(output[i] + " ");
			}
			answer.add(sb.toString());
			return;
		}

		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				visited[i] = true;
				output[depth] = list[i];
				permutation(depth + 1, n, r);
				visited[i] = false;
			}
		}
	}
}