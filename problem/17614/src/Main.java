import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int N, answer;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <= N; i++) {
			String tmp = String.valueOf(i);
			for (int j = 0; j < tmp.length(); j++) {
				if ((tmp.charAt(j) - '0') != 0 && (tmp.charAt(j) - '0') % 3 == 0) {
					answer++;
				}
			}
		}
		System.out.println(answer);
	}
}