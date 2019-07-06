import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static final int MAX = 1000001;
	static int[] list = new int[MAX], answer = new int[MAX];
	static Stack<Integer> s = new Stack<Integer>();
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		
		for (int i = 1; i <= N; i++) {
			list[i] = Integer.parseInt(st.nextToken());
		}
		
		s.push(Integer.MAX_VALUE);
		for (int i = N; i > 0; i--) {
			while (s.peek() <= list[i]) s.pop();
			if (s.peek() >= Integer.MAX_VALUE) answer[i] = -1;
			else answer[i] = s.peek();
			s.push(list[i]);
		}
		for (int i = 1; i <= N; i++)
			bw.write(answer[i] + " ");
		bw.flush();
	}
}