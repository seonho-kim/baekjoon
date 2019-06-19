import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int N, K;
	static final int MAX = 5001;
	static int[] c = new int[MAX], r = new int[MAX]; 
	static boolean[][] map = new boolean[MAX][MAX];
	static boolean[][] visited = new boolean[MAX][MAX];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			c[i] = Integer.parseInt(st.nextToken());
			r[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i <= K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from, to;
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			
			map[from][to] = true;
			map[to][from] = true;
		}
		
		PriorityQueue<int[]> q = new PriorityQueue<int[]>(10, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2] - o2[2];
			}
		});
		q.add(new int[] {1, 0, 0});
		
		for (int i = 1; i <= N; i++)
			Arrays.fill(visited[i], false);
		
		int pos = 0, left = 0, sum = -1;
		while(!q.isEmpty()) {
			int[] qq = q.poll();
			pos = qq[0];
			left = qq[1];
			sum = qq[2];
			
			if (pos == N) break;
			if (visited[pos][left] == true) continue;
			visited[pos][left] = true;
			
			for (int i = 1; i <= N; i++) {
				if (map[pos][i] == false) continue;
				if (left - 1 >= 0) q.add(new int[] {i, left - 1, sum});
			}
			q.add(new int[] {pos, r[pos], sum + c[pos]});
		}
		bw.write(sum + "");
		bw.flush();
	}
}