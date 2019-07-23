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
	static final int MAX = 201;
	static int[] p = new int[MAX];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		Arrays.fill(p, 1, N+1, -1);
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= N; j++) {
				int value = Integer.parseInt(st.nextToken());
				
				if (value == 1) union(i, value);
			}
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		int root = find(Integer.parseInt(st.nextToken()));
		boolean diff = false;
		for (int i = 2; i <= M; i++) {
			int value = Integer.parseInt(st.nextToken());
			if (root != find(value)) {
				diff = true;
				break;
			}
		}
		bw.write((diff ? "NO" : "YES") + "");
		bw.flush();
	}
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if (a == b) return;
		p[b] = a;
	}
	static int find(int n) {
		if (p[n] < 0) return n;
		p[n] = find(p[n]);
		return p[n];
	}
}