import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int V, E;
	static int[] uf = new int[1000];
	static Edge[] list = new Edge[100000];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		for (int i = 0 ; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			list[i] = new Edge(u-1, v-1, w);
		}
		
		uf = new int[V + 1];
		for (int i = 1; i <= V; i++) {
			uf[i] = i;
		}
		
		Arrays.sort(list, 0, E);
		
		int result = 0, cnt = 0;
		Arrays.fill(uf, -1);
		for (int i = 0; i < list.length; i++) {
			if (union(list[i].u, list[i].v)) {
				result += list[i].w;
				if (++cnt == V-1) break;
			}
		}
		bw.write(result + "");
		bw.flush();
	}
	
	static boolean union(int a, int b) {
		a = find(a);
		b = find(b);
		if (a == b) return false;
		uf[b] = a;
		return true;
	}
	static int find(int a) {
		if (uf[a] < 0) return a;
		return uf[a] = find(uf[a]);
	}
}
class Edge implements Comparable<Edge> {
	int u, v, w;
	
	Edge() {
		this.u = -1;
		this.v = -1;
		this.w = 0;
	}
	Edge(int u, int v, int w) {
		this.u = u;
		this.v = v;
		this.w = w;
	}
	
	@Override
	public int compareTo(Edge O) {
		return Integer.valueOf(w).compareTo(Integer.valueOf(O.w));
	}
}