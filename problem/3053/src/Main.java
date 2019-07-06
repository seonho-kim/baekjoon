import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	static int R;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		R = Integer.parseInt(br.readLine());
		
		bw.write(String.format("%.6f\n", Math.PI*R*R));
		bw.write(String.format("%.6f\n", (double)2*R*R));
		bw.flush();
	}
}