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
	static int[] list = new int[7], output = new int[7];
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine()); 
		for (int i = 1; i <= N; i++) {
			list[i-1] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(list, 0, N);
		rePermutation(0, N, M);
		bw.flush();
	}
	static void rePermutation(int depth, int n, int r) throws IOException {
		if (depth == r) {
			for (int i = 0; i < r; i++) {
				bw.write(output[i] + " ");
			}
			bw.write("\n");
			return;
		}

		for (int i = 0; i < n; i++) {
			output[depth] = list[i];
			rePermutation(depth + 1, n, r);
		}
	}
}