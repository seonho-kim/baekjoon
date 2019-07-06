import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int T, x, y;
	static long answer;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine(), " ");
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			
			long square = 0, start = 0, end = 0;
			for (int i = 1; ; i++) {
				square = 1L*i*i;
				start = square - i + 1;
				end = square + 1 + i - 1;
				
				if (start <= y-x && y-x <= end) {
					if (start <= y-x && y-x <= square) answer = (i<<1) - 1;
					else answer = (i<<1);
					break;
				}
			}
			bw.append(answer + "\n");
			bw.flush();
		}
	}
}