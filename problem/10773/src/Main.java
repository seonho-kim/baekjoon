import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main {

	static int K;
	static Stack<Integer> s = new Stack<Integer>();
	static long answer;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		K = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <= K; i++) {
			int value = Integer.parseInt(br.readLine());
			
			if (value == 0) s.pop();
			else s.push(value);
		}
		
		while (!s.isEmpty()) answer += s.pop();
		bw.write(String.valueOf(answer) + "");
		bw.flush();
	}
}