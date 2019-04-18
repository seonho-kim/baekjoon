import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int T = 0;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		T = Integer.parseInt(st.nextToken());
		
		for (int tc = 1; tc <= T; tc++)
		{
			st = new StringTokenizer(br.readLine());
			char[] temp = st.nextToken().toCharArray();
			int answer = 0;
			
			int w = 1;
			for (int i = 0; i < temp.length; i++) {
				if (temp[i] == 'O')
				{
					answer += w;
					w++;
				}
				else
				{
					w = 1;
				}
			}
			bw.write(answer + "\n");
		}
		bw.flush();
	}
}
