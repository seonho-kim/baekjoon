import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int[] results = new int[5];
	static int total = 0;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		for (int i = 0; i < results.length; i++) {
			st = new StringTokenizer(br.readLine());
			results[i] = Integer.parseInt(st.nextToken());
			
			if (results[i] < 40) results[i] = 40;
			total += results[i];
		}
		
		bw.write(Integer.valueOf(total/  5) + "\n");
		bw.flush();
	}
}