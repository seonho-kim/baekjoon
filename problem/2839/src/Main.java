import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int big = 5, small = 3;	
	static int A, B;
	static int answer = 0;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(st.nextToken());
				
		answer = Integer.MAX_VALUE;
		for(int i = 0; i <= 1000; i++)
		{
			if (N < big*i)
				break;
			
			if ((N-(big*i))%small == 0)
			{
				A = i;
				B = (N-(big*i)) / small;
				
				answer = Math.min(answer, A+B);
			}
		}
			
		if (answer == Integer.MAX_VALUE)
		{
			answer = -1;
		}
		
		bw.write(String.valueOf(answer));
		
		bw.close();
		br.close();
	}
}