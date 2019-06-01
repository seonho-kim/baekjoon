import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int T, A, B;
	static final int MAX = 5000001;
	static int[] d1 = new int[MAX];
	static int[] d2 = new int[MAX];
	static int result;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		T = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		
		d1[0] = 1;
		for (int i = 0; i <= T; i++) {
			if (d1[i] == 0) continue;
			else {
				if (i+A <= T) {
					d1[i+A] = 1;
				}
				if (i+B <= T) {
					d1[i+B] = 1;
				}
				d2[i/2] = 1;
			}
		}
		for (int i = 0; i <= T; i++) {
			if (d2[i] == 0) continue;
			else {
				if (i+A <= T) d1[i+A] = 1;
				if (i+B <= T) d1[i+B] = 1;
			}
		}
		for (int i = 0; i <= T; i++) {
			if (d1[i] == 0) continue;
			else {
				if (i+A <= T) d1[i+A] = 1;
				if (i+B <= T) d1[i+B] = 1;
			}
		}
		
		for (int i = T; i > 0; i--) {
			if (d1[i] != 0 || d2[i] != 0) {
				result = i;
				break;
			}
		}
		
		bw.write(result + "");
		bw.flush();
	}
}