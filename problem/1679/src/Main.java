import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

	static int N, K;
	static final int MAX = 100001;
	static int[] map = new int[MAX];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(),  " ");
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map[N] = 0;
		ArrayDeque<Integer> q = new ArrayDeque<Integer>();
		if (N == K) {
			bw.write("0");
		} else {
			q.offer(N);
		}
		while (!q.isEmpty()) {
			int cur = q.poll();
			
			if (cur-1 == K || cur+1 == K || 2*cur == K) {
				bw.write((map[cur] + 1) + "");
				break;
			}
			
			if (cur-1 < MAX && cur-1 >= 0 && map[cur-1] == 0) {
				map[cur-1] = map[cur] + 1;
				q.offer(cur-1);
			}
			if (cur+1 < MAX && map[cur+1] == 0) {
				map[cur+1] = map[cur] + 1;
				q.offer(cur+1);
			}
			if (2*cur < MAX && map[2*cur] == 0) {
				map[2*cur] = map[cur] + 1;
				q.offer(2*cur);
			}
		}
		bw.flush();
	}

}
