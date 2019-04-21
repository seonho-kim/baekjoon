import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static final int MAX = 1000001;
	static int N;
	static int[] d = new int[MAX];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(st.nextToken());
		
		Arrays.fill(d, 1000001);
		d[1] = 0;
		for (int i = 2; i <= N; i++) {
			d[i] = Math.min(d[i], d[i - 1] + 1);
			if (i % 3 == 0)
				d[i] = Math.min(d[i], d[i / 3] + 1);
			if (i % 2 == 0)
				d[i] = Math.min(d[i], d[i / 2] + 1);
		}
		bw.write(Integer.valueOf(d[N]) + "\n");
		bw.flush();
	}
}