import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int cnt, answer, min = 10001;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		
		for (int i = N; i <= M; i++) {
			for (int j = 2; j < i; j++)
			{
				if (i % j == 0)
					cnt++;
			}
			
			if (cnt == 0 && i != 1) {
				answer += i;
				if (min > i)
					min = i;
			}
			cnt = 0;
		}
		
		if (min == 10001)
			bw.write("-1\n");
		else
			bw.write(Integer.valueOf(answer) + "\n" + Integer.valueOf(min) + "\n");
		bw.flush();
	}
}