import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	static final int MAX = 123456 * 2;
	static int N;
	static int[] array = new int[MAX + 1];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		array[1] = 1;
		for (int i = 2; i <= MAX; i++) {
			for (int j = 2; i*j <= MAX; j++) {
				if (array[i*j] == 1)
					continue;
				array[i*j] = 1;
			}
		}
		String temp = "";
		while ((temp = br.readLine()) != null) {
			N = Integer.parseInt(temp);
			
			if (N == 0) break;
			
			int answer = 0;
			for (int i = N + 1; i <= 2*N; i++) {
				if (array[i] != 1)
					answer++;
			}
			bw.write(Integer.valueOf(answer) + "\n");
		}
		bw.flush();
	}
}