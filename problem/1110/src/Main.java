import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int N, A, B, cnt;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(st.nextToken());
		
		if (N < 10)
		{
			A = 0; B = N;
		}
		else
		{
			A = N / 10; B = N % 10;
		}
		
		while(true)
		{
			int temp = B;
			
			B = (A + B) % 10;
			A = temp;
			cnt++;

			if (N == (A*10 + B))
				break;
		}
		bw.write(String.valueOf(cnt) + "\n");
		bw.flush();
	}
}