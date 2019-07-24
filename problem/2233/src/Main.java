import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static final int MAX = 2001;
	static final int max_level = (int)Math.floor(Math.log(MAX)/Math.log(2));
	static ArrayList<Integer>[] tree = new ArrayList[MAX];
	static int[][] ac = new int[MAX][max_level + 1];
	static int[] depth = new int[MAX];
	static int[] table = new int[MAX*2];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		for (int i = 0; i <= N; i++) {
			tree[i] = new ArrayList<Integer>();
		}
		String temp = br.readLine();
		
		int number = 1;
		Stack<Integer> s = new Stack<Integer>();
		for (int i = 1; i < temp.length(); i++) {
			int value = temp.charAt(i - 1) - '0';
			
			if (value == 0) {
				s.push(number++);
				table[i] = s.peek();
			} else {
				table[i] = s.peek();
				s.pop();
			}			
		}
		table[2*N] = 1;
		
		
		s.clear();
		number = 1;
		for (int i = 1; i <= 2*N; i++) {
			int value = table[i];
			
			if (s.isEmpty()) s.push(value);
			else {
				if (s.peek() == value) {
					s.pop();
					continue;
				}
				tree[s.peek()].add(value);
				tree[value].add(s.peek());
				s.push(value);
			}
		}

		depth[0] = -1;
		getTree(1, 0);
		
		st = new StringTokenizer(br.readLine());
		int a = table[Integer.parseInt(st.nextToken())];
		int b = table[Integer.parseInt(st.nextToken())];
		if (depth[a] != depth[b]) {
			if (depth[a] > depth[b]) {
				int tmp = a;
				a = b;
				b = tmp;
			}
			for (int i = max_level; i >= 0; i--) {
				if (depth[a] <= depth[ac[b][i]]) {
					b = ac[b][i];
				}
			}
		}
		int lca = a;
		if (a != b) {
			for (int i = max_level; i >= 0; i--) {
				if (ac[a][i] != ac[b][i]) {
					a = ac[a][i];
					b = ac[b][i];
				}
				lca = ac[a][i];
			}
		}
		for (int i = 1; i <= 2*N; i++) {
			if (lca == table[i]) bw.write(i + " ");
		}
		bw.flush();
	}
	static void getTree(int cur, int parent) {
		depth[cur] = depth[parent] + 1;
		ac[cur][0] = parent;
		
		for (int i = 1; i <= max_level; i++) {
			int tmp = ac[cur][i-1];
			ac[cur][i] = ac[tmp][i-1];
		}
		
		for (int next : tree[cur]) {
			if (next != parent) getTree(next, cur);
		}
	}
}