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
	static int[] alphabet = new int[26];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(st.nextToken());
		
		int answer = 0;
		for (int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			String words = st.nextToken();
			
			Arrays.fill(alphabet, 0);
			int prev = -1;
			for (int j = 0; j < words.length(); j++) {
				int id = (int)words.charAt(j) - 97;
				
				if (alphabet[id] != 0) {
					if (j > 0 && prev != id)
						break;
				}
				
				prev = id;
				alphabet[id]++;
				if (j == words.length() - 1)
					answer++;
			}
		}
		bw.write(answer + "\n");
		bw.flush();
	}
}