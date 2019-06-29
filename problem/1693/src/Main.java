import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static final int MAX = 100001;
	static ArrayList<Integer>[] tree = new ArrayList[MAX];
	static int[][] d = new int[MAX][18];
	static int answer = Integer.MAX_VALUE;
	public static void main (String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());

		for (int i = 1; i <= N; i++)
			tree[i] = new ArrayList<Integer>();
		
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from, to;
			
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			
			tree[from].add(to);
			tree[to].add(from);
		}
		coloring(0, 1);
		
		for (int i = 1; i < 18; i++) answer = Math.min(answer, d[1][i]);
		bw.write(answer + "");
		bw.flush();
	}
	static void coloring(int prev, int cur) {
		d[cur][0] = 1000000000;
		for (int i = 1; i < 18; i++) d[cur][i] += i;
		for (int next : tree[cur]) {
			if (next == prev) continue;
			coloring(cur, next);
			
			int first = 0, second = 0;
			for (int i = 1; i < 18; i++) {
				if (d[next][second] > d[next][i]) second = i;
				if (d[next][first] > d[next][second]) {
					int tmp = first;
					first = second;
					second = tmp;
				}
			}
			for (int i = 1; i < 18; i++) {
				d[cur][i] += d[next][i != first ? first : second];
			}
		}
	}
}