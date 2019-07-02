import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	static int X;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		X = Integer.parseInt(br.readLine());
		
		int i = 0;
		for (i = 0; i*(i+1)/2-X < 0; i++);
		
		i--;
		int num = X-(i*(i+1)/2);

		int a, b;
		if ((i&1) == 1) {
			a = 1; b = i;
			a = a + num -1;
			b = b + 1 - num + 1;
		} else {
			a = i; b = 1;
			a = a + 1 - num + 1;
			b = b + num - 1;
		}
		bw.write(a + "/" + b);
		bw.flush();
	}
}