import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int A, B, C;
	static int[] numbers = new int[10];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		A = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		B = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		
		int result = A * B * C;
		
		do
		{
			numbers[result % 10]++;
			result /= 10;
		} while (result > 0);
			
		for (int i = 0; i < 10; i++)
		{
			bw.write(Integer.valueOf(numbers[i]) + "\n");
		}
		bw.flush();
	}
}