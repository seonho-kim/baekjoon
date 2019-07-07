import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static final int MAX = 16;
	static char[][] words = new char[5][MAX];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		for (int i = 0; i < 5; i++) {
			String temp = br.readLine();
			
			for (int j = 1; j <= temp.length(); j++) {
				words[i][j] = temp.charAt(j-1);
			}
		}

		for (int i = 1; i <= 15; i++) {
			for (int j = 0; j < 5; j++) {
				bw.write((words[j][i] == 0 ? "" : words[j][i]) + "");
			}
		}
		bw.flush();
	}
}