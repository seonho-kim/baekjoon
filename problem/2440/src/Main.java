import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int N;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuffer sb = new StringBuffer();		
		N = Integer.parseInt(st.nextToken());
		
		for (int i = 1; i <= N; i++)
			sb.append("*");
		
		for(int i = N; i > 0; i--)
		{
			bw.write(sb.toString() + "\n");
			sb.delete(i-1, i);
		}
		
		br.close();
		bw.close();
	}
}