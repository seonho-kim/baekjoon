import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int N, max = 0, min = 1000001;
	static int answer;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		if (N == 1) {
			int value = Integer.parseInt(br.readLine());
			answer = value*value;
		} else {
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 1; i <= N; i++) {
				int value = Integer.parseInt(st.nextToken());
				max = Math.max(max, value);
				min = Math.min(min, value);
			}
			answer = max*min;
		}
		bw.write(answer + "");
		bw.flush();
	}
}