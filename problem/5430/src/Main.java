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

	static int T, N;
	static Deque<Integer> q;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		T = Integer.parseInt(st.nextToken());
		
		next:
		for (int tc = 1; tc <= T; tc++) {
			q = new LinkedList();
			st = new StringTokenizer(br.readLine());
			String order = st.nextToken();
			
			st = new StringTokenizer(br.readLine());			
			N = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine().replace("[", "").replace("]", "").replace(",", " "), " ");
			for (int i = 0 ; i < N; i++) {
				q.add(Integer.parseInt(st.nextToken()));
			}
			
			boolean direction = false;
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < order.length(); i++) {
				if (order.charAt(i) == 'R') {
					direction = !direction;
				}
				if (order.charAt(i) == 'D') {
					if (q.isEmpty()) {
						bw.write("error\n");
						continue next;
					}
					if (direction) {
						q.pollLast();
					} else {
						q.pollFirst();
					}
				}					
			}
			
			while(!q.isEmpty()) {
				if (direction) {
					sb.append(q.pollLast());
				} else {
					sb.append(q.pollFirst());
				}
				if (q.size() != 0)
					sb.append(",");
			}
			
			sb.insert(0, "[");
			sb.append("]");
			bw.write(sb.toString() + "\n");
		}
		bw.flush();
	}
}