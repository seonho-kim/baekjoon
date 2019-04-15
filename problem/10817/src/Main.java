import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int A, B, C;
	static int answer;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		if (A > B)
		{
			if (A > C)
			{
				answer = Math.max(B, C);
			}
			else
			{
				answer = A;
			}
		}
		else
		{
			if (B > C)
			{
				answer = Math.max(A, C);
			}
			else
			{
				answer = B;
			}
		}
		
		bw.write(String.valueOf(answer) + "\n");
		bw.flush();	
	}
}