import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static final int MAX = 10000;
	static int[] stack = new int[MAX];
	static int cur = -1;
	static int N;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine(), " ");
			
			String order = st.nextToken();
			if (order.equals("push")) {
				int value = Integer.parseInt(st.nextToken());
				cur++;
				stack[cur] = value;
			}
			else if (order.equals("pop")) {
				if (cur >= 0) {
					int value = stack[cur];
					stack[cur] = 0;
					cur--;
					bw.write(Integer.valueOf(value) + "\n");
				} else {
					bw.write("-1" + "\n");
				}
			} else if (order.equals("size")) {
				bw.write(Integer.valueOf(cur + 1) + "\n");
			} else if (order.equals("empty")) {
				bw.write(cur < 0 ? "1\n" : "0\n");
			} else if (order.equals("top")) {
				if (cur >= 0) {
					bw.write(Integer.valueOf(stack[cur])+ "\n");
				} else {
					bw.write("-1" + "\n");
				}
			}
		}
		bw.flush();
	}
}