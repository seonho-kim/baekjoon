import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	static int N, n;
	static int[] list, t;
	static long[] bigger, smaller;
	static long answer;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		
		n = 1 << (int)(Math.ceil(Math.log(N) / Math.log(2)));
		list = new int[N];
		t = new int[n<<1];
		bigger = new long[N];
		smaller = new long[N];
		
		for (int i = 0; i < N; i++) {
			list[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < N; i++) {
			bigger[i] = query(list[i], N-1);
			update(list[i]-1, t[list[i]-1+n]+1);
		}
		
		t = new int[n<<1];
		for (int i = N-1; i >= 0; i--) {
			smaller[i] = query(0, list[i]-2);
			update(list[i]-1, t[list[i]-1+n]+1);
		}
		
		for (int i = 0; i < N; i++) {
			answer += 1L*bigger[i]*smaller[i];
		}
		
		bw.write(answer + "");
		bw.flush();
	}
	static long query(int l, int r) {
		long result = 0;
		if (l <= r) result = t[r+n];
		for (l+=n, r+=n; l<r; l>>=1, r>>=1) {
			if ((l&1) == 1) result += t[l++];
			if ((r&1) == 1) result += t[--r];
		}
		return result;
	}
	static void update(int p, int v) {
		for (t[p+=n]=v; p>1; p>>=1) {
			t[p>>1] = t[p] + t[p^1];
		}
	}
}