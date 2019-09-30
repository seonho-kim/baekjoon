import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int n, m, k;
	static Edge[] max, min;
	static int[] p;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		String temp;
		
		while ((temp = br.readLine()) != null) {
			String[] in = temp.split(" ");
			n = Integer.parseInt(in[0]);
			m = Integer.parseInt(in[1]);
			k = Integer.parseInt(in[2]);
			
			if (n == 0 && m == 0 && k == 0) return;
			
			max = new Edge[m];
			min = new Edge[m];
			
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				char color = st.nextToken().charAt(0);
				
				int u, v;
				u = Integer.parseInt(st.nextToken());
				v = Integer.parseInt(st.nextToken());
				
				if (color == 'B') {
					max[i] = new Edge(u-1, v-1, 0);
					min[i] = new Edge(u-1, v-1, 1);
				} else {
					max[i] = new Edge(u-1, v-1, 1);
					min[i] = new Edge(u-1, v-1, 0);
				}
			}
			
			Arrays.sort(max);
			Arrays.sort(min);
			
			p = new int[n];
			Arrays.fill(p, -1);
			int maximum = 0, cnt = 0;
			for (int i = 0; ; i++) {
				if (union(max[i].u, max[i].v)) {
					if (max[i].w == 0) maximum++;
					if (++cnt == n-1) break;
				}
			}
			
			p = new int[n];
			Arrays.fill(p, -1);
			int minimum = 0; cnt = 0;
			for (int i = 0; ; i++) {
				if (union(min[i].u, min[i].v)) {
					if (min[i].w == 1) minimum++;
					if (++cnt == n-1) break;
				}
			}
			
			if (maximum >= k && minimum <= k)
				bw.write("1\n");
			else
				bw.write("0\n");
			bw.flush();
		}
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
	static class Edge implements Comparable<Edge> {
		int u, v, w;
		public Edge(int u, int v, int w) {
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