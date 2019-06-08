import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static final int MAX = 501;
	static ArrayList<Integer[]>[] list = new ArrayList[MAX];
	static int d[] = new int[MAX];
	static boolean minusCycle = false;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i <= N; i++) {
			list[i] = new ArrayList<Integer[]>();
		}
		
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from, to, weight;
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			weight = Integer.parseInt(st.nextToken());
			
			list[from].add(new Integer[] {to, weight});
		}
		
		Arrays.fill(d, 1, N+1, Integer.MAX_VALUE);
		d[1] = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				
				for (Integer[] next : list[j]) {
					if (d[j] != Integer.MAX_VALUE &&
							d[next[0]] > d[j] + next[1]) {
						d[next[0]] = d[j] + next[1];
						if (i == N) minusCycle = true;
					}
				}
			}
		}

		if (minusCycle) bw.write("-1" + "");
		else {
			for (int i = 2; i <= N; i++) {
				bw.write((d[i] != Integer.MAX_VALUE ? d[i] : -1) + "\n");
			}
		}
		bw.flush();
	}
}