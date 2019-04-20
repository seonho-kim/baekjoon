import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static String pop = "";
	static int N;
	static Stack<Character> stk = new Stack<Character>();
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(st.nextToken());
		
		for (int i = 1; i <= N; i++) {
			stk = new Stack<Character>();
			st = new StringTokenizer(br.readLine());
			String ps = st.nextToken();
			
			for (int j = 0; j < ps.length(); j++) {
				char c = ps.charAt(j);
				
				if (c == '(') {
					stk.push('(');
					
					if (j == (ps.length() - 1)) {
						bw.write("NO\n");
					}
				} else {
					if (stk.size() == 0) {
						bw.write("NO\n");
						break;
					}
					
					if (stk.peek() == '(') {
						stk.pop();
						if (j == (ps.length() - 1)) {
							if (stk.size() == 0) {
								bw.write("YES\n");
							} else {
								bw.write("NO\n");
							}
						}
					}
				}
			}
			bw.flush();
		}
	}
}