import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

	static final int MAX = 10001;
	static int N;
	static Deque<Integer> q = new LinkedList<Integer>();
	static enum order {
		push, pop, size, empty, front, back
	}
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			String o = st.nextToken();
			
			if (o.equals(order.push.toString())) {
				int v = Integer.parseInt(st.nextToken());
				q.add(v);
			} else if (o.equals(order.pop.toString())) {
				if (q.size() == 0) {
					bw.write("-1\n");
					continue;
				}
				
				int v = q.pollFirst();
				bw.write(v + "\n");	
			} else if (o.equals(order.size.toString())) {
				bw.write(q.size() + "\n");
			} else if (o.equals(order.empty.toString())) {
				bw.write(q.isEmpty() ? "1\n" : "0\n");
			} else if (o.equals(order.front.toString())) {
				if (q.size() == 0) {
					bw.write("-1\n");
					continue;
				}
				
				bw.write(q.peek() + "\n");
			} else if (o.equals(order.back.toString())) {
				if (q.size() == 0) {
					bw.write("-1\n");
					continue;
				}
				
				bw.write(q.peekLast() + "\n");
			}
		}
		bw.flush();
	}
}