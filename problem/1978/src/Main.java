import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int N, answer;
	static int[] array = new int[1001];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine(), " ");
		
		array[1] = 1;
		for (int i = 2; i <= 1000; i++) {
			if ((i != 2 && i % 2 == 0) || array[i] == 1) {
				if (i % 2 == 0) array[i] = 1;
				continue;
			}
			
			int cnt = 1;
			while (i * cnt <= 1000) {
				if (cnt > 1)
					array[i * cnt] = 1;
				cnt++;
			}
		}
		
		for (int i = 0; i < N; i++) {
			int temp = Integer.parseInt(st.nextToken());
			
			if (array[temp] == 0)
				answer++;
		}
		bw.write(Integer.valueOf(answer) + "\n");
		bw.flush();
	}
}