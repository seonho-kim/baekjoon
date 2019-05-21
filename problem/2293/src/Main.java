import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int N, K;
	static final int NMAX = 101;
	static final int KMAX = 10001;
	static int[] coin = new int[NMAX];
	static int[] d = new int[KMAX];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		BufferedWriter bw=  new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		for (int i = 1; i <= N; i++) {
			coin[i] = Integer.parseInt(br.readLine());
		}
		
		d[0] = 1;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= K; j++) {
				if (j - coin[i] >= 0)
					d[j] += d[j - coin[i]];
			}
		}
		
		bw.write(d[K] + "");
		bw.flush();
	}
}