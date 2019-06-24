import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static final int MAX = 101;
	static int[] m = new int[MAX], c = new int[MAX];
	static int sum;
	static int[] d = new int[10001]; 
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++)
			m[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			c[i] = Integer.parseInt(st.nextToken());
			sum += c[i];
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = sum; j >= c[i]; j--) {
				d[j] = Math.max(d[j],  d[j - c[i]] + m[i]);
			}
		}
		
		int answer = 0;
		for (int i = 1; i < sum && d[i] < M; i++) { 
			answer = i;
		}
		bw.write(++answer + "");
		bw.flush();
	}
}