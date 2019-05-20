import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	static int N;
	static final int MAX = 31;
	static int[] d = new int[MAX];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		
		d[0] = 1;
		d[1] = 0;
		d[2] = 3;
		for (int i = 3; i <= N; i++) {
			if (i % 2 == 0) {
				d[i] = 3*d[i-2];
				
				for (int j = 3; i - j >= 0; j++) {
					d[i] += 2*d[i-j];
				}
			}
		}
		bw.write(d[N] + "");
		bw.flush();
	}
}