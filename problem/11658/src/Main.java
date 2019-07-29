import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static final int MAX = 1024;
	static int a[][] = new int[MAX+1][MAX+1];
	static int t[][] = new int[MAX+1][MAX+1];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= N; j++) {
				a[i][j] = Integer.parseInt(st.nextToken());
				update(t, i, j, a[i][j]);
			}
		}
		
		for (int k = 1; k <= M; k++) {
			st = new StringTokenizer(br.readLine(), " ");
			int order = Integer.parseInt(st.nextToken());
			
			if (order == 0) {				
				int i = Integer.parseInt(st.nextToken());
				int j = Integer.parseInt(st.nextToken());
				int l = Integer.parseInt(st.nextToken());
				
				int diff = sum(t, i, j) - sum(t, i-1, j) - sum(t, i, j-1) + sum(t, i-1, j-1);
				a[i][j] = l;
				
				update(t, i, j, l-diff);
			} else if (order == 1) {
				int i1 = Integer.parseInt(st.nextToken());
				int j1 = Integer.parseInt(st.nextToken());
				int i2 = Integer.parseInt(st.nextToken());
				int j2=  Integer.parseInt(st.nextToken());
				
				bw.write(sum(t, i2, j2) - sum(t, i1-1, j2) - sum(t, i2, j1-1) + sum(t, i1-1, j1-1) + "\n");
			}
		}
		bw.flush();
	}
	static void update(int[][] t, int x, int y, int diff) {
		for (int i = x; i <= N; i+=(i&-i)) {
			for (int j = y; j <= N; j+=(j&-j)) {
				t[i][j] += diff;
			}
		}
	}
	static int sum(int[][] t, int x, int y) {
		int ans = 0;
		for (int i = x; i > 0; i-=(i&-i)) {
			for (int j = y; j > 0; j-=(j&-j)) {
				ans += t[i][j];
			}
		}
		return ans;
	}
}