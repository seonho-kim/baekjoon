import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static double[][] map;
	static Edge[] e;
	static int[] p;
	public static void main(String[] args) throws IOException { 
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		
		map = new double[n][2];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			double x, y;
			x = Double.parseDouble(st.nextToken());
			y = Double.parseDouble(st.nextToken());
			map[i][0] = x;
			map[i][1] = y;
		}
				
		e = new Edge[n*(n-1)/2];
		int idx = 0;
		for (int i = 0; i < n-1; i++) {
			for (int j = i+1; j < n; j++) {
				e[idx++] = new Edge(i, j
						, Math.sqrt((map[j][0]-map[i][0])*(map[j][0]-map[i][0]) +
						(map[j][1]-map[i][1])*(map[j][1]-map[i][1])));
			}
		}
		Arrays.sort(e);
		
		p = new int[n];
		Arrays.fill(p, -1);
		double result = 0;
		int cnt = 0;
		for (int i = 0; ; i++) {
			if (union(e[i].u, e[i].v)) {
				result += e[i].w;
				if (++cnt == n-1) break;
			}
		}
		bw.write(String.format("%.2f", result));
		bw.flush();
	}
	static int find(int a) {
		if (p[a] < 0) return a;
		return p[a] = find(p[a]);
	}
	static boolean union(int a, int b) {
		a = find(a);
		b = find(b);
		if (a == b) return false;
		p[b] = a;
		return true;
	}
	static class Edge implements Comparable<Edge>{
		int u, v;
		double w;
		public Edge(int u, int v, double w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}
		@Override
		public int compareTo(Edge o) {
			if (this.w < o.w) return -1;
			else if (this.w > o.w) return 1;
			else return 0;
		}
	}
}