import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int m, n, sum;
	static int[] p;
	static Edge[] e;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String temp;
		StringTokenizer st;
		
		while ((temp = br.readLine()) != null) {
			String[] in = temp.split(" "); 
			m = Integer.parseInt(in[0]);
			n = Integer.parseInt(in[1]);
			
			if (m == 0 && n == 0) return;
			
			p = new int[m];
			e = new Edge[n];
			sum = 0;
			
			for (int i = 0; i < n; i++) {
				int u, v, w;
				st = new StringTokenizer(br.readLine());
				u = Integer.parseInt(st.nextToken());
				v = Integer.parseInt(st.nextToken());
				w = Integer.parseInt(st.nextToken());
				sum += w;
				e[i] = new Edge(u, v, w);
			}
			
			Arrays.sort(e);
			
			int result = 0, cnt = 0;
			Arrays.fill(p, -1);
			for (int i = 0; ; i++) {
				if (union(e[i].u, e[i].v)) {
					result += e[i].w;
					if(++cnt == m-1) break;
				}
			}
			bw.write(sum - result + "\n");
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