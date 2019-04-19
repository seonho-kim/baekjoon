import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static String words;
	static int answer;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		words = st.nextToken();
		
		for (int i = 0; i < words.length(); i++) {
			int ascii = (int)words.charAt(i); 
			
			if (ascii >= (int)'W')
				answer += 9;
			else if (ascii >= (int)'T')
				answer += 8;
			else if (ascii >= (int)'P')
				answer += 7;
			else if (ascii >= (int)'M')
				answer += 6;
			else if (ascii >= (int)'J')
				answer += 5;
			else if (ascii >= (int)'G')
				answer += 4;
			else if (ascii >= (int)'D')
				answer += 3;
			else if (ascii >= (int)'A')
				answer += 2;
		}
		answer += words.length();
		
		bw.write(Integer.valueOf(answer) + "\n");		
		bw.flush();
	}
}