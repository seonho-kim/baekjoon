import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static int n, m;
	static ArrayList<Integer>[] p;
	static int[] lazy;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		p = new ArrayList[n];
		for (int i = 0; i < n; i++)
			p[i] = new ArrayList<Integer>();
		lazy = new int[n];
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < n; i++) {
			int parent = Integer.parseInt(st.nextToken())-1;
			if (i != 0) p[parent].add(i);
		}
		
		for (int i = 0 ; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int index = Integer.parseInt(st.nextToken())-1;
			int value = Integer.parseInt(st.nextToken());
			
			lazy[index] += value;
		}
		dfs(0);
		for (int i = 0; i < n; i++) {
			bw.write(lazy[i] + " ");
		}
		bw.flush();
	}
	static void dfs(int cur) {
		for (int next : p[cur]) {
			lazy[next] += lazy[cur];
			dfs(next);
		}
	}
}