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

	static int N;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(st.nextToken());
		
		Deque<Integer> q = new LinkedList();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			String order = st.nextToken();
			int value = 0;
			
			switch (order) {
				case "push_front" :
					value = Integer.parseInt(st.nextToken());
					q.addFirst(value);
					break;
				case "push_back" :
					value = Integer.parseInt(st.nextToken());
					q.addLast(value);
					break;
				case "pop_front" :
					if (q.isEmpty()) {
						sb.append("-1\n");
						break;
					}
					value = q.pollFirst();
					sb.append(value + "\n");
					break;
				case "pop_back" :
					if (q.isEmpty()) {
						sb.append("-1\n");
						break;
					}
					value = q.pollLast();
					sb.append(value + "\n");
					break;
				case "size" :
					sb.append(q.size() + "\n");
					break;
				case "empty" :
					if (q.isEmpty()) {
						sb.append("1\n");
					}
					else {
						sb.append("0\n");
					}
					break;
				case "front" :
					if (q.isEmpty()) {
						sb.append("-1\n");
						break;
					}
					sb.append(q.peekFirst() + "\n");
					break;
				case "back" :
					if (q.isEmpty()) {
						sb.append("-1\n");
						break;
					}
					sb.append(q.peekLast() + "\n");
					break;
			}
		}
		bw.write(sb.toString());
		bw.flush();
	}
}