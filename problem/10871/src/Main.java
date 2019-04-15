import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int N, X;
	static int[] arr = new int[10001];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		StringBuffer sb = new StringBuffer();
		
		for (int i = 1; i <= N; i++)
		{
			if (arr[i] < X)
				sb.append(arr[i] + " ");
		}
		
		bw.write(sb.toString() + "\n");
		bw.flush();
	}
}