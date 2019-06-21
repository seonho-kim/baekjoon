import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static final int MAX = 50001;
	static int max_level = (int)Math.floor(Math.log(MAX)/Math.log(2));
	static ArrayList<Integer>[] list = new ArrayList[MAX];
	static int[][] ac = new int[MAX][20];
	static int[] depth = new int[MAX];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<Integer>();
		}
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from, to;
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			
			list[from].add(to);
			list[to].add(from);
		}
		
		depth[0] = -1;
		getTree(1, 0);
		
		M = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a, b;
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			if (depth[a] != depth[b]) {
				if (depth[a] > depth[b]) {
					int tmp = a;
					a = b;
					b = tmp;
				}
				
				for (int j = max_level; j >= 0; j--) {
					if (depth[a] <= depth[ac[b][j]])
						b = ac[b][j];
				}
			}
			int lca = a;
			
			if (a != b) {
				for (int j = max_level; j >= 0; j--) {
					if (ac[a][j] != ac[b][j]) {
						a = ac[a][j];
						b = ac[b][j];
					}
					lca = ac[a][j];
				}
			}
			bw.write(lca + "\n");
			bw.flush();
		}
	}
	static void getTree(int here, int parent) {
		depth[here] = depth[parent] + 1;
		ac[here][0] = parent;
		
		for (int i = 1; i <= max_level; i++) {
			int tmp = ac[here][i-1];
			ac[here][i] = ac[tmp][i-1];
		}
		
		for (int next : list[here]) {
			if (next != parent)
				getTree(next, here);
		}
	}
}