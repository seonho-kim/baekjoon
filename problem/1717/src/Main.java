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
	static final int MAX = 1000001;
	static int[] p = new int[MAX];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		Arrays.fill(p, 0, N+1, -1);
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int order = Integer.parseInt(st.nextToken());
			
			switch (order) {
				case 0:
					union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
					break;
				case 1:
					int a = find(Integer.parseInt(st.nextToken()));
					int b = find(Integer.parseInt(st.nextToken()));
					bw.write((a==b ? "YES" : "NO") + "\n");
					break;
			}
		}
		
		bw.flush();
	}
	static int find(int n) {
		if (p[n] < 0) return n;
		p[n] = find(p[n]);
		return p[n];
	}
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if (a == b) return;
		p[b] = a;
	}
}