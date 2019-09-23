import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	static int V, E, K, c;
	static final int MAX = 10001;
	static Stack<Integer> stk = new Stack<Integer>();
	static ArrayList<Integer>[] vt = new ArrayList[MAX];
	static ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
	static int discover[] = new int[MAX], scc[] = new int[MAX];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		for (int i = 1; i <= V; i++) {
			vt[i] = new ArrayList<Integer>();
		}
		for (int i = 1; i <= E; i++) {
			int x, y;
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			vt[x].add(y);
		}
		
		Arrays.fill(discover, 1, V+1, -1);
		Arrays.fill(scc, 1, V+1, -1);
		for (int i = 1; i <= V; i++) {
			if (discover[i] == -1)
				dfs(i);
		}
		result.sort(new Comparator<ArrayList<Integer>>() {

			@Override
			public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
				if (o1.get(0) < o2.get(0)) return -1;
				else if (o1.get(0) > o2.get(0)) return 1;
				else return 0;
			}
		});
		bw.write(K + "\n");
		for (int i = 0; i < K; i++) {
			for (int value : result.get(i)) {
				bw.write(value + " ");
			}
			bw.write("-1\n");
		}
		bw.flush();
	}
	static int dfs(int current) {
		discover[current] = c++;
		int ret = discover[current];
		stk.push(current);
		for (int next : vt[current]) {
			if (discover[next] == -1)
				ret = Math.min(ret, dfs(next));
			else if (scc[next] == -1)
				ret = Math.min(ret,  discover[next]);
		}
		
		if (ret == discover[current]) {
			ArrayList<Integer> tmp = new ArrayList<Integer>();
			while (true) {
				int t = stk.pop();
				scc[t] = K;
				tmp.add(t);
				if (t == current) break;
			}
			tmp.sort(new Comparator<Integer>() {

				@Override
				public int compare(Integer o1, Integer o2) {
					if (o1 < o2) return -1;
					else if (o1 > o2) return 1;
					else return 0;
				}
			});
			result.add(tmp);
			K++;
		}
		return ret;
	}
}