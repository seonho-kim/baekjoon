import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static final int MAX = 1001;
	static int[] list = new int[MAX];
	static int[] d = new int[MAX];
	static int result = 1;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			list[i] = Integer.parseInt(st.nextToken());
		}
		
		d[1] = 1;
		for (int i = 2; i <= N; i++) {
			d[i] = 1;
			
			for (int j = 1; j <= i; j++) {
				if (list[i] > list[j] && d[i] < d[j] + 1)
					d[i] = d[j] + 1;
			}
			result = Math.max(result, d[i]);
		}
		
		bw.write(result + "");
		bw.flush();
	}
}