import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int x, y;
	static int total = 0;
	static int[] table = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	static String[] answer = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		
		for (int i = 1; i < x; i++)
		{
			total += table[i-1];
		}
		total += y;
		
		bw.write(answer[total % 7]);
		
		bw.close();
		br.close();
	}
}