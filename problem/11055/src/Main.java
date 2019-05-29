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
	static int[] d = new int[MAX];
	static int result;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++)
			a[i] = Integer.parseInt(st.nextToken());
		
		result = d[1] = a[1];
		for (int i = 2; i <= N; i++) {
			d[i] = a[i];
			for (int j = 1; j < i; j++) {
				if (a[i] > a[j] && d[i] < d[j] + a[i]) {
					d[i] = d[j] + a[i];
				}
			}
			result = Math.max(result, d[i]);
		}
		
		bw.write(result + "");
		bw.flush();
	}
}