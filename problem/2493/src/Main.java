import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {

	static int N;
	static final int MAX = 500001;
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
			int value = Integer.parseInt(st.nextToken()); 
			list[i] = value;
			s.push(value);
		}
		
		for (int i = N; i > 0; i--) {
			int cur = s.pop();
			
			if (i-1 > 0) {
				for (int j = i-1; j > 0; j--) {
					if (list[j] > cur) {
						answer[i] = j;
						break;
					}
				}
			}
		}
		for (int i = 1; i <= N; i++) {
			bw.write(answer[i] + " ");
		}
		bw.flush();
	}
}