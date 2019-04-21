import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static final int MAX =  1001;
	static int N;
	static int[][] d = new int[MAX][3];
	enum value { R, G, B };
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine(), " ");
		d[1][value.R.ordinal()] = Integer.parseInt(st.nextToken());
		d[1][value.G.ordinal()] = Integer.parseInt(st.nextToken());
		d[1][value.B.ordinal()] = Integer.parseInt(st.nextToken());
		for (int i = 2; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int[] v = new int[3];
			for (int j = 0; j < 3; j++)
				v[j] = Integer.parseInt(st.nextToken());
			
			d[i][value.R.ordinal()] = Math.min(v[value.R.ordinal()] + d[i-1][value.G.ordinal()]
												, v[value.R.ordinal()] + d[i-1][value.B.ordinal()]);
			d[i][value.G.ordinal()] = Math.min(v[value.G.ordinal()] + d[i-1][value.R.ordinal()]
												, v[value.G.ordinal()] + d[i-1][value.B.ordinal()]);
			d[i][value.B.ordinal()] = Math.min(v[value.B.ordinal()] + d[i-1][value.R.ordinal()]
												, v[value.B.ordinal()] + d[i-1][value.G.ordinal()]);			
		}
		bw.write(Integer.valueOf(Math.min(d[N][value.R.ordinal()], Math.min(d[N][value.G.ordinal()], d[N][value.B.ordinal()]))) + "\n");
		bw.flush();
	}
}