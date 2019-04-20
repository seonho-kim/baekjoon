import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	static Stack<Character> stk = new Stack<Character>();
	static long result = 0;
	static int temp = 1;
	static boolean imp = false;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String words = st.nextToken();
		
		for (int i = 0; i < words.length(); i++) {
			char c = words.charAt(i);

			if (c == '(') {
				temp *= 2;
				stk.push('(');
			}
			else if (c == '[') {
				temp *= 3;
				stk.push('[');
			}
			else if (c == ')' && (stk.empty() || stk.peek() != '(')) {
				imp = true;
				break;
			}
			else if (c == ']' && (stk.empty() || stk.peek() != '[')) {
				imp = true;
				break;
			}
			else if (c == ')') {
				if (words.charAt(i - 1) == '(')
					result += temp;
				stk.pop();
				temp /= 2;
			}
			else if (c == ']') {
				if (words.charAt(i - 1) == '[')
					result += temp;
				stk.pop();
				temp /= 3;
			}
		}
		
		if (imp || !stk.empty())
            System.out.println(0);
		else
			System.out.println(result);
		
		bw.flush();
	}
}