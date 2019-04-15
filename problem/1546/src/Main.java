import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[] arr = new int[1001];
	static double answer;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++)
		{
			arr[i] = Integer.parseInt(st.nextToken());
			M = Math.max(M, arr[i]);
		}
		
		for (int i = 1; i <= N; i++)
		{
			answer += ((double)arr[i] / M) * 100;
		}
		
		bw.write(String.format("%.2f", answer / N) + "\n");
		bw.flush();
	}
}