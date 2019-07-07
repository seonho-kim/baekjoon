import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int N, first;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		first = Integer.parseInt(st.nextToken());
		
		for (int i = 2; i <= N; i++) {
			int value = Integer.parseInt(st.nextToken());
			int gcd = gcd(first, value);
			
			bw.write(first/gcd + "/" + value/gcd + "\n");
			bw.flush();
		}
	}
	static int gcd(int a, int b) {
		while(true) {
			int r = a % b;
			
			if (r == 0) return b;
			
			a = b;
			b = r;
		}
	}
}