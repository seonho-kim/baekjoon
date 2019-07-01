import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static final int MAX = 1001;
	static int[] a = new int[MAX];
	static int[] d = new int[MAX], r = new int[MAX];
	static int result, decResult, increaseIdx, decreaseIdx;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++)
			a[i] = Integer.parseInt(st.nextToken());
		
		lis(a, d, N);
		reverseLis(a, r, N);
		
		bw.write(find(d, r, N) + "");
		bw.flush();
	}
	static void lis(int[] a, int[] d, int n) {
		for (int i = 1; i <= n; i++) {
			d[i] = 1;
			for (int j = 1; j <= i; j++)
				if (a[j] < a[i] && d[i] < d[j] + 1)
					d[i]++;
		}
	}
	static void reverseLis(int[] a, int[] r, int n) {
		for (int i = n; i > 0; i--) {
			r[i] = 1;
			for (int j = n; j >=i; j--) {
				if (a[j] < a[i] && r[i] < r[j] + 1)
					r[i]++;
			}
		}
	}
	static int find(int[] d, int[] r, int n) {
		int res = 0;
		for (int i = 1; i <= n; i++)
			if (res < d[i] + r[i])
				res = d[i] + r[i];
		return res-1;
	}
}