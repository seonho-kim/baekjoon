import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	static final int MAX = 100000;
	static int N;
	static Stack<Integer> stk = new Stack<Integer>();
	static int last = 0 ;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(st.nextToken());
		
		StringBuffer sb = new StringBuffer();
		int start = 1;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			
			while (start <= v) {
				stk.push(start);
				sb.append("+\n");
				start++;
			}
			
			if (!stk.isEmpty() && v != stk.peek()) {
				sb = new StringBuffer();
				sb.append("NO\n");
				break;
			}
			
			while (!stk.isEmpty() && v <= stk.peek()) {
				stk.pop();
				sb.append("-\n");
			}
		}
		bw.write(sb.toString());
		bw.flush();
	}
}