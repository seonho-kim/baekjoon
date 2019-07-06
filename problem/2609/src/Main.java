import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int A, B, gcd;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(),	" ");
		
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		
		gcd = gcd(A, B);
		bw.write(gcd + "\n" + (A*B)/gcd);
		bw.flush();
	}
	static int gcd(int A, int B) {		
		while(true) {
			int r = A % B;
			
			if (r == 0) {
				return B;
			}
			
			A = B;
			B = r;
		}
	}
}