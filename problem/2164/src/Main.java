import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {

	static int N;
	static Deque<Integer> q = new ArrayDeque<Integer>();
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
				
		N = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <= N; i++) {
			q.offer(i);
		}
		
		while (q.size() > 1) {
			q.pop();
			int value = q.poll();
			q.offer(value);
		}
		bw.write(q.poll() + "");
		bw.flush();
	}
}