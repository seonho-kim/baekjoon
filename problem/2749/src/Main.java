import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static final int mod = 1000000;
	static final int p = mod/10*15;
	static long N;
	static long[] d = new long[p];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Long.parseLong(st.nextToken());
		
		d[0] = 0;
		d[1] = 1;
		
		for (int i = 2; i < p; i++) {
			if (d[i] == 0) {
				d[i] = d[i-1] + d[i-2];
				d[i] %= mod;		
			};
		}
		bw.write(Long.valueOf(d[(int)(N%p)]) + "\n");		
		bw.flush();
	}
}