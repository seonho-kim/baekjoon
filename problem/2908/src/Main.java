import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int A, B;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		
		int toA = 0, toB = 0;
		
		for (int i = 0; i < 3; i++)
		{
			toA += (A % 10) * (int)Math.pow(10, 2-i);
			toB += (B % 10) * (int)Math.pow(10, 2-i);
			
			A = A/10;
			B = B/10;
		}
		
		bw.write(Integer.valueOf(Math.max(toA, toB)) + "\n");		
		bw.flush();
	}
}