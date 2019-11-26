import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_P1 {

	static int N, answer;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <= N; i++) {
			int tmp = i;
			while (tmp > 0) {
				int n = tmp % 10;
				if (n == 3 || n == 6 || n == 9)
					answer++;
				tmp /= 10;
			}
		}
		System.out.println(answer);
	}
}