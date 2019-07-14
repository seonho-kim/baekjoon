import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static final int NMAX = 1001;
	static final int MMAX = 100001;
	static int[] uf = new int[NMAX];
	static Edge[] list = new Edge[MMAX];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a, b, c;
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			list[i] = new Edge(a, b, c);
		}
		Arrays.sort(list, 1, M+1);
		
		int result = 0, cnt = 0;
		Arrays.fill(uf, 1, N+1, -1);
		for (int i = 1; ; i++) {
			if (union(list[i].from, list[i].to)) {
				result += list[i].weight;
				if (++cnt == N-1) break;
			}
		}
		bw.write(result + "");
		bw.flush();
	}
	static int find(int a) {
		if(uf[a] < 0) return a;
		return uf[a] = find(uf[a]);
	}
	static boolean union(int a, int b) {
		a = find(a);
		b = find(b);
		if (a == b) return false;
		uf[b] = a;
		return true;
	}
	static class Edge implements Comparable<Edge> {
		int from, to, weight;
		public Edge (int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) {
			if (this.weight < o.weight) return -1;
			else if (this.weight > o.weight) return 1;
			else return 0;
		}
	}
}