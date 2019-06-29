import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	static int N, M;
	static int result[] = new int[4];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		for (int i = 2; i >= 0; i--) {
			result[i] = N * (M / (int)Math.pow(10, i)); 		
			M %= (int)Math.pow(10, i);
		}
		
		for (int i = 0; i < 3; i++) {
			bw.write(result[i] + "\n");
			result[3] += (result[i] * (int)Math.pow(10, i));
		}
		bw.write(result[3] + "\n");
		bw.flush();
	}
}