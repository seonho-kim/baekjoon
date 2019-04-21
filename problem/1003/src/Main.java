import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[] d = new int[41];
	static int[] z = new int[41];
	static int[] o = new int[41];
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(st.nextToken());
		
		for (int tc = 1; tc <= N; tc++) {
			Arrays.fill(d, 0);
			Arrays.fill(z, 0);
			Arrays.fill(o, 0);
			
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			
			d[0] = 1; z[0]++;
			d[1] = 1; o[1]++;
			for (int i = 2; i <= M; i++) {
				if (d[i] == 0) {
					d[i] = d[i-2] + d[i-1];
				}
				z[i] += (z[i-2] + z[i-1]);
				o[i] += (o[i-2] + o[i-1]);
			}
			bw.write(Integer.valueOf(z[M]) + " " + Integer.valueOf(o[M]) + "\n");
		}
		bw.flush();
	}
}