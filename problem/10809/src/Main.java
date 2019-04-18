import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static String words;
	static int[] alphabet = new int[26];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		Arrays.fill(alphabet, -1);
		words = st.nextToken();
		for (int i = 0; i < words.length(); i++)
		{
			if (alphabet[words.charAt(i) - 97] == -1)
				alphabet[words.charAt(i) - 97] = i;
		}
		
		for (int i = 0; i < alphabet.length; i++)
		{
			bw.write(Integer.valueOf(alphabet[i]) + " ");
		}
		bw.flush();
	}
}