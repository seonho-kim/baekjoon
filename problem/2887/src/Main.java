import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static Planet[] planet;
	static int[] p;
	static Edge[] e;
	public static void main(String[] args) throws IOException { 
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		planet = new Planet[N];
		p = new int[N];
		e = new Edge[3*N-3];
		for (int i = 0; i < N; i++) {
			int x, y, z;
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			z = Integer.parseInt(st.nextToken());
			planet[i] = new Planet(x, y, z, i);
			p[i] = -1;
		}
		
		Arrays.sort(planet, new Comparator<Planet>() {

			@Override
			public int compare(Planet o1, Planet o2) {
				if (o1.x < o2.x) return -1;
				else if (o1.x > o2.x) return 1;
				else return 0;
			}
		});
		for (int i = 0; i < N-1; i++) {
			e[i] = new Edge(planet[i].num,
					planet[i+1].num,
					Math.abs(planet[i].x - planet[i+1].x));
		}
		
		Arrays.sort(planet, new Comparator<Planet>() {

			@Override
			public int compare(Planet o1, Planet o2) {
				if (o1.y < o2.y) return -1;
				else if (o1.y > o2.y) return 1;
				else return 0;
			}
		});
		for (int i = 0; i < N-1; i++) {
			e[N-1 + i] = new Edge(planet[i].num,
					planet[i+1].num,
					Math.abs(planet[i].y - planet[i+1].y));
		}
		
		Arrays.sort(planet, new Comparator<Planet>() {

			@Override
			public int compare(Planet o1, Planet o2) {
				if (o1.z < o2.z) return -1;
				else if (o1.z > o2.z) return 1;
				else return 0;
			}
		});
		for (int i = 0; i < N-1; i++) {
			e[2*N-2 + i] = new Edge(planet[i].num,
					planet[i+1].num,
					Math.abs(planet[i].z - planet[i+1].z));
		}
		
		Arrays.sort(e);
		int result = 0, cnt = 0;
		for (int i = 0; ; i++) {
			if (union(e[i].u, e[i].v)) {
				result += e[i].w;
				if (++cnt == N-1) break;
			}
		}
		bw.write(result + "\n");
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
	static class Planet {
		int x, y, z, num;
		public Planet(int x, int y, int z, int num) {
			this.x = x;
			this.y = y;
			this.z = z;
			this.num = num;
		}
	}
}