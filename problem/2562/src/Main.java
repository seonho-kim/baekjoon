import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {

	static final int MAX = 101;
	static int[] ori = new int[MAX], sorted = new int[9];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		for (int i = 0; i < 9; i++) {
			int value = Integer.parseInt(br.readLine());
			ori[value] = i+1;
			sorted[i] = value;
		}
		Arrays.sort(sorted);
		bw.write(sorted[8] + "\n");
		bw.write(ori[sorted[8]] + "\n");
		bw.flush();
	}
}