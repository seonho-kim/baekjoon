import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {

	static int G, P;
	static final int MAX = 100001;
	static int[] p = new int[MAX];
	static int answer;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		G = Integer.parseInt(br.readLine());
		P = Integer.parseInt(br.readLine());
		
		Arrays.fill(p, 0, G+1, -1);
		for (int i = 1; i <= P; i++) {
			int a = Integer.parseInt(br.readLine());
			
			int root = find(a);
			if (root != 0) {
				union(root-1, root);
				answer++;
			}
			else break;
		}
		bw.write(answer + "");
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