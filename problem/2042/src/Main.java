import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int N, M, K;
	static final int MAX = 1000000;
	static int SEG_SIZE = (int)Math.pow(2, (int)Math.ceil(Math.log(MAX) / Math.log(2)) + 1) + 1;
	static long[] seg = new long[SEG_SIZE];
	static int H;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K =  Integer.parseInt(st.nextToken());
		
		H = (int)Math.ceil(Math.log(N) / Math.log(2));
		for (int i = (int)Math.pow(2, H); i < (int)Math.pow(2, H) + N; i++) {
			seg[i] = Long.parseLong(br.readLine());
		}
		
		for (int i = (int)Math.pow(2, H) - 1; i > 0; i--) {
			seg[i] = seg[i<<1] + seg[i<<1|1];			
		}
		
		for (int i = 1; i <= M + K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int order, l;
			long r;
			
			order = Integer.parseInt(st.nextToken());
			l = Integer.parseInt(st.nextToken());
			r = Long.parseLong(st.nextToken());
			
			if (order == 1) {
				update((int)l - 1, r);
			}
			else if (order == 2) {
				bw.write((long)((query(l - 1, (int)(r - 1)) + seg[(int)(r - 1) + (int)Math.pow(2, H)])) + "\n");
			}			
		}
		bw.flush();
	}
	
	static long query(int l, int r) {
		long res = 0;
		for (l+=(int)Math.pow(2, H), r+=(int)Math.pow(2, H); l < r; l>>=1, r>>=1) {
			if ((l&1) == 1) res += seg[l++];
			if ((r&1) == 1) res += seg[--r];
		}
		return res;
	}
	
	static void update(int p, long value) {
		for (seg[p+=(int)Math.pow(2, H)] = value; p > 1; p>>=1)  {
			seg[p>>1] = seg[p] + seg[p^1];
		}
	}
}