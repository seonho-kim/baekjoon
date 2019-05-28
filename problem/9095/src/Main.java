import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	
	static int T, N;
	static final int MAX = 12;
	static int[] d = new int[MAX];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		T = Integer.parseInt(br.readLine());
		
		d[1] = 1;
		d[2] = 2;
		d[3] = 4;
		
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			
			for (int i = 4; i <= N; i++) {
				d[i] = d[i-1] + d[i-2] + d[i-3];
			}
			bw.write(d[N] + "\n");
		}
		bw.flush();
	}
}