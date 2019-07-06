import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {

	static int N, gcd;
	static final int MAX = 101;
	static int[] number = new int[MAX];
	static PriorityQueue<Integer> q = new PriorityQueue<Integer>();
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		for (int i = 1; i <= N; i++) {
			number[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(number, 1, N+1);
		
		gcd = number[2] - number[1];
		
		for (int i = 3; i <= N; i++) {
			int gcd2 = number[i] - number[i-1];
			gcd = gcd(gcd, gcd2);
		}
		q.offer(gcd);
		for (int i = 2; i <= Math.sqrt(gcd); i++) {
			if (gcd % i == 0) {
				q.offer(i);
				if (i != gcd / i) q.offer(gcd /i);
			}
		}
		
		while(!q.isEmpty()) {
			bw.write(q.poll() + " ");
		}
		bw.flush();
	}
	static int gcd(int A, int B) {
		while(true) {
			int r = A % B;
			
			if (r == 0) return B;
			A = B;
			B = r;
		}
	}
}