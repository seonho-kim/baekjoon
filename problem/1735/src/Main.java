import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int a, b, c, d, A, B, C;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken()); 
			
		A = a*d + b*c; 
		B = b*d;
		C = gcd(A, B);
		bw.write(A/C + " " + B/C);
		bw.flush();
	}
	static int gcd(int a, int b) {
		int r = 0;
		while(b != 0) {
			r = b;
			b = a % b;
			a = r;
		}
		return a;
	}
}