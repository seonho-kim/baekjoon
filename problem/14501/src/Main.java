import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static final int MAX = 16;
	static int[] d = new int[MAX];
	static int[][] list = new int[MAX][2];
	static int answer;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			list[i][0] = Integer.parseInt(st.nextToken());
			list[i][1] = Integer.parseInt(st.nextToken());
			
			d[i] = list[i][1];
		}
		
		for (int i = 2; i <= N; i++) {
			for (int j = 1; j < i; j++) {
				if (i-j >= list[j][0]) {
					d[i] = Math.max(d[i], d[j]+list[i][1]);
				}
			}
		}
		for (int i = 1; i <= N; i++) {
			if (i + list[i][0] <= N+1) {
				answer = Math.max(answer, d[i]);
			}
		}
		bw.write(answer + "");
		bw.flush();
	}
}