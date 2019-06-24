import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	static String N;
	static int[] number = new int[9];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = br.readLine();
		int length = N.length();
		
		for (int i = 0; i < length; i++) {
			int n = N.charAt(i) - 48;
			if (n == 9)
				number[6]++;
			else
				number[n]++;
		}
		
		int max = -1;
		int idx = -1;
		
		for (int i = 0; i < 9; i++) {
			if (i == 6) continue;
			if (max < number[i]) {
				max = number[i];
				idx = i;
			}		
		}
		
		int answer = (int)((double)number[6]/2+0.5);
		if (max > answer) answer = max;
		
		bw.write(answer + "");
		bw.flush();
	}
}