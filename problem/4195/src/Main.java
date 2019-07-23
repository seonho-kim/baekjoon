import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

	static int T, F;
	static final int MAX = 200001;
	static int[] p = new int[MAX];
	static HashMap<String, Integer> relation;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			F = Integer.parseInt(br.readLine());
			Arrays.fill(p, 1, 2*F+1, -1);
			int number = 1;
			
			relation = new HashMap<String, Integer>();
			for (int i = 1; i <= F; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				String fa = st.nextToken();
				String fb = st.nextToken();
				
				if (relation.get(fa) == null) {
					relation.put(fa, number++);
				}
				if (relation.get(fb) == null) {
					relation.put(fb, number++);
				}
				
				int a = relation.get(fa);
				int b = relation.get(fb); 
				union(a, b);
				
				bw.write(p[find(a)]*-1 + "\n");
			}
		}
		
		bw.flush();
	}
	static void union(int a, int b) {
		a = find(a);
		b = find(b);	
		if (a == b) return;
		p[a] += p[b];
		p[b] = a;
	}
	static int find(int n) {
		if (p[n] < 0) return n;
		p[n] = find(p[n]);
		return p[n];
	}
}