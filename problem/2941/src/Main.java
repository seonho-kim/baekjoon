import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static String[] dictionary = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};
	static String words;
	static int answer;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		words = st.nextToken();
		
		for (int i = 0; i < dictionary.length; i++) {
			words = words.replaceAll(dictionary[i], "1");
		}
		answer += words.length();
		
		bw.write(Integer.valueOf(answer) + "\n");		
		bw.flush();
	}
}