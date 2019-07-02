import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main {

	static Stack<Character> s;
	static boolean isNoBalnce;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String temp;
		out:
		while ((temp = br.readLine()) != null) {
			s = new Stack<Character>();
			isNoBalnce = false;
			
			char pop;
			for (int i = 0; i < temp.length(); i++) {
				char value = temp.charAt(i);
				if (i == 0 && value == '.') break out;
				
				switch (value) {
					case '(':
					case '[':
						s.push(value);
						break;
					case ')':
						if (s.size() == 0) {
							isNoBalnce = true;
							break;
						}
						pop = s.pop();
						if (pop != '(') {
							isNoBalnce = true;
							break;
						}
						break;
					case ']':	
						if (s.size() == 0) {
							isNoBalnce = true;
							break;
						}
						pop = s.pop();
						if (pop != '[') {
							isNoBalnce = true;
							break;
						}
						break;
				}
				
				if (isNoBalnce == true) break;
			}
			
			if (!isNoBalnce && s.size() == 0)
				bw.write("yes\n");
			else
				bw.write("no\n");
			bw.flush();
		}
	}
}