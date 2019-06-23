import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static int V, E;
	static final int MAX = 10001;
	static ArrayList<Integer>[] list = new ArrayList[MAX];
	static int[] discovered = new int[MAX];
	static boolean[] isCut = new boolean[MAX];
	static int number = 0;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		for (int i = 1; i <= V; i++) {
			list[i] = new ArrayList<Integer>();
		}
		
		for (int i = 1; i <= E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from, to;
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			
			list[from].add(to);
			list[to].add(from);
		}
		
		for (int i = 1; i <= V; i++)
			if (discovered[i] == 0)
				dfs(i, true);
		
		int cnt = 0;
		for (int i = 1; i <= V; i++)
			if (isCut[i]) cnt++;
		
		bw.write(cnt + "\n");
		for (int i = 1; i <= V; i++)
			if (isCut[i])
				bw.write(i + " ");
		bw.flush();
	}
	
	static int dfs(int current, boolean isRoot) {
		discovered[current] = ++number;
		int ret = discovered[current];
		
		int child = 0;
		for (int next : list[current]) {
			if (discovered[next] != 0) {
				ret = Math.min(ret, discovered[next]);
				continue;
			}
			child++;
			
			int prev = dfs(next, false);
			if (!isRoot && prev >= discovered[current]) {
				isCut[current] = true;
			}
			ret = Math.min(ret, prev);
		}
		
		if (isRoot) isCut[current] = (child >= 2);
		
		return ret;
	}
}