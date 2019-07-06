import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	static int A, B;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String temp;
		while ((temp = br.readLine()) != null) {
			String[] number = temp.split(" ");
			A = Integer.valueOf(number[0]);
			B = Integer.valueOf(number[1]);
			
			if (A == 0 && B == 0) break;
			
			if (B%A == 0)
				bw.write("factor\n");
			else if (A%B == 0)
				bw.write("multiple\n");
			else
				bw.write("neither\n");
			bw.flush();
		}
	}
}