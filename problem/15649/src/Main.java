import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static LinkedList<Integer> list = new LinkedList<Integer>();
	static boolean[] visited = new boolean[8];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		permutation(N, M);
	}
	static void permutation(int n, int r) {
		if (list.size() == r) {
			for (int i : list) {
				System.out.print((i + 1) + " ");
			}
			System.out.println();
			return;
		}

		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				list.add(i);
				visited[i] = true;
				permutation(n, r);
				visited[i] = false;
				list.removeLast();
			}
		}
	}
}