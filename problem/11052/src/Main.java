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
	static int[] card = new int[MAX];
	static int[] d = new int[MAX];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			card[i] = Integer.parseInt(st.nextToken());
		}
		
		d[1] = card[1];
		for (int i = 2; i <= N; i++) {
			for (int j = 1; j <= i; j++) {
				d[i] = Math.max(d[i], d[i-j]+card[j]);
			}
		}
		
		bw.write(d[N] + "");
		bw.flush();
	}
}