import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	static int N;
	static final int MAX = 1001;
	static int[] d = new int[MAX];
	static final int MOD = 10007;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		
		d[0] = 1;
		d[1] = 1;
		for (int i = 2; i <= N; i++) {
			d[i] = (d[i-1]%MOD + d[i-2]%MOD)%MOD;
		}
		bw.write(d[N] + "");
		bw.flush();
	}
}