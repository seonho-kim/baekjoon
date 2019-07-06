import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int N, K;
	static final int MAX = 11;
	static int[] coin = new int[MAX];
	static int answer;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		for (int i = 1; i <= N; i++) {
			coin[i] = Integer.parseInt(br.readLine());
		}
		
		for (int i = N; i > 0; i--) {
			answer += (K/coin[i]);
			K = K%coin[i];
		}
		bw.write(answer + "");
		bw.flush();
	}
}