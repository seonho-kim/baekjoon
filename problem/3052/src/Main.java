import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	static int[] arr = new int[42];
	static int answer;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		for (int i = 0; i < 10; i++) {
			int value = Integer.parseInt(br.readLine());
			arr[value % 42]++;
			
			if (arr[value % 42] == 1) answer++;
			
		}
		bw.write(answer + "");
		bw.flush();
	}
}