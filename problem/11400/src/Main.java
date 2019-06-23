import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

	static int V, E;
	static final int MAX = 100001;
	static ArrayList<Integer>[] list = new ArrayList[MAX];
	static int[] discovered = new int[MAX];
	static int number;
	static ArrayList<ArrayList<Integer>> isCut = new ArrayList<ArrayList<Integer>>(); 
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		for (int i = 1; i <= V; i++)
			list[i] = new ArrayList<Integer>();
		for (int i = 1; i <= E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from, to;
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			
			list[from].add(to);
			list[to].add(from);
		}
		
		for (int i = 1; i <= V; i++) {
			if (discovered[i] == 0) dfs(i, 0);
		}
		
		Collections.sort(isCut, new Comparator<ArrayList<Integer>>() {

			@Override
			public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
				
				if (o1.get(0) < o2.get(0)) return -1;
				else if (o1.get(0) > o2.get(0)) return 1;
				else {
					if (o1.get(1) < o2.get(1)) return -1;
					else if (o1.get(1) > o2.get(1)) return 1;
					else return 0;
				}
			}
		});
		
		bw.write(isCut.size() + "\n");
		for (ArrayList<Integer> a : isCut) {
			for (int value : a) {
				bw.write(value + " ");
			}
			bw.write("\n");
			bw.flush();
		}
	}
	static int dfs(int current, int parent) {
		discovered[current] = ++number;
		int ret = discovered[current];
		
		for (int next : list[current]) {
			if (next == parent) continue;
			
			if (discovered[next] != 0) {
				ret = Math.min(ret,  discovered[next]);
				continue;
			}
			
			int prev = dfs(next, current);
			
			if (prev > discovered[current]) {
				ArrayList<Integer> a = new ArrayList<Integer>();
				a.add(Math.min(next, current));
				a.add(Math.max(next, current));
				isCut.add(a);
			}
			ret = Math.min(ret, prev);
		}
		
		return ret;
	}
}
