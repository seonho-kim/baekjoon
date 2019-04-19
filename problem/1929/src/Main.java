import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int M, N;
	static int[] array = new int[1000001];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		array[1] = 1;
		for (int i = 2; i <= N; i++) {
			for (int j = 2; i*j <= N ; j++) {
				if (array[i*j] == 1)
					continue;
				array[i*j] = 1;
			}
		}
		
		for (int i = M; i <= N; i++) {
			if (array[i] != 1)
				bw.write(Integer.valueOf(i) + "\n");
		}
		bw.flush();
	}
}