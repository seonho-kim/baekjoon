import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int C, N, cnt;
	static int[] arr = new int[1001];
	static double avg;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		C = Integer.parseInt(st.nextToken());
		
		for (int tc = 1; tc <= C; tc++)
		{
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			
			Arrays.fill(arr, 0);
			avg = 0; cnt = 0;
			for (int i = 1; i <= N; i++)
			{
				arr[i] = Integer.parseInt(st.nextToken());
				avg += arr[i];
			}
			avg /= N;
			
			for (int i = 1; i <= N; i++)
			{
				if ((double)arr[i] > avg)
					cnt++;
			}
			bw.write(String.valueOf(String.format("%.3f", (double)cnt/N * 100)) + "%\n");
		}
		
		bw.flush();
	}
}