import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int K, N, R;
	static int[] list = new int[49];
	static int[] result = new int[49];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		while ((K = Integer.parseInt(st.nextToken())) != 0) {
			
			for (int i = 0; i < K; i++) {
				list[i] = Integer.parseInt(st.nextToken());
			}
			N = K;
			R = 6;
			
			combination(result, N, R, 0, 0, list);
			System.out.println();
			st = new StringTokenizer(br.readLine(), " ");
		}
	}
	static void combination(int[] result, int n, int r, int idx, int t, int[] list) {	
		if (r == 0) {
			for (int i = 0; i < idx; i++) System.out.print(list[result[i]] + " ");
			System.out.println();
		} else if (t == n) {
			return;
		} else {
			result[idx] = t;
			combination(result, n, r-1, idx+1, t+1, list);
			combination(result, n, r, idx, t+1, list);
		}
	}
}