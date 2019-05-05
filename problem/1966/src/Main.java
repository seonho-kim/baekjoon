import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

	static final int MAX = 100;
	static int T, N, M;
	static Integer[] priority = new Integer[MAX];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		T = Integer.parseInt(st.nextToken());
		
		Deque<Print> q;
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine(), " ");
			int idx = 0;
			Arrays.fill(priority, -1);
			q = new LinkedList();
			
			while(st.hasMoreTokens()) {
				int v = Integer.parseInt(st.nextToken());
				q.add(new Print(idx, v));
				priority[idx] = v;
				idx++;
			}
			
			Arrays.sort(priority, 0, idx);
		
			int answer = 0;
			while(!q.isEmpty()) {
				Print p = q.pollFirst();
				int curPriority = priority[idx-1];
				
				if (p.priority == curPriority) {
					answer++;
					idx--;
					
					if (p.idx == M)
						break;
				}
				else {
					q.addLast(p);
				}
			}
			bw.write(Integer.valueOf(answer) + "\n");
		}
		bw.flush();
	}
	
	static class Print {
		int idx;
		int priority;
		
		public Print(int idx, int priority) {
			this.idx = idx;
			this.priority = priority;
		}
	}
}