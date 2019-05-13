import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int N;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		function(1, 1);
	}
	static void function(int x, int y) {
		if (y == 1 << N - 1) {
			System.out.print(3*y - x - 1 + " ");
			return;
		}
		
		System.out.print(x + " ");
		function(x + y, 2*y);
		function(2*y + x, 2*y);
	}
}