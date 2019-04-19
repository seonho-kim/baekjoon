import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static final int MIN = 4;
	static final int MAX = 10000;
	static int T, N;
	static int[] array = new int[MAX + 1];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		T = Integer.parseInt(st.nextToken());
		
		array[1] = 1;
		for (int i = 2; i <= MAX; i++) {
			for (int j = 2; i*j <= MAX; j++) {
				if (array[i*j] == 1)
					continue;
				array[i*j] = 1;
			}
		}
		
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			int a = 0, b = 0;
			for (int i = 0; i < N; i++) {
				if (array[i] != 1) {
					if (array[N - i] != 1) {
						if (i > N-i) break;
						
						if (a != 0 && b != 0) {
							if (Math.abs(b - a) > Math.abs(i - (N-i))) {
								a = i;
							    b = N-i;
							}
						}
						else {
							a = i;
							b = N - i;
						}
					}
				} else {
					continue;
				}
			}
			bw.write(Integer.valueOf(a) + " " + Integer.valueOf(b) + "\n");
		}
		bw.flush();
	}
}