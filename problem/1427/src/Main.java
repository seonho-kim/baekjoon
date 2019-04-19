import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[] array = new int[10];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(st.nextToken());
		
		int idx = 0;
		while(N > 0)
		{
			array[idx] = N % 10;
			N = N / 10;
			idx++;
		}
		
		Arrays.sort(array, 0, idx);
		for (int i = idx - 1; i >= 0; i--) {
			bw.write(Integer.valueOf(array[i]) + "");
		}
		bw.flush();
	}
}