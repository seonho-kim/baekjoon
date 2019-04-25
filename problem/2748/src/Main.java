import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static final int MAX = 91;
	static int N;
	static long[] d = new long[MAX];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(st.nextToken());
		
		d[0] = 0;
		d[1] = 1;
		
		for (int i = 2; i <= N; i++) {
			if (d[i] == 0)
				d[i] = d[i-1] + d[i-2];
		}
		bw.write(Long.valueOf(d[N]) + "\n");
		bw.flush();
	}
}