import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_P1 {

	static int N, M;
	static LinkedList<Integer> list = new LinkedList<Integer>();
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		rePermutation(N, M);
		bw.flush();
	}
	static void rePermutation(int n, int r) throws IOException {
		if (list.size() == r) {
			for (int i : list) {
				bw.write((i + 1) + " ");
			}
			bw.write("\n");
			return;
		}

		for (int i = 0; i < n; i++) {
			list.add(i);
			rePermutation(n, r);
			list.removeLast();
		}
	}
}