import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_P1 {

	static int N, M;
	static int[] list, output;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		list = new int[N];
		output = new int[N];
		visited = new boolean[N];
		for (int i = 1; i <= N; i++)
			list[i-1] = i;
		permutation(0, N, M);
	}
	static void permutation(int depth, int n, int r) {
		if (depth == r) {
			for (int i = 0; i < r; i++) {
				System.out.print(output[i] + " ");
			}
			System.out.println();
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