import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int T, R;
	static String S;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		T = Integer.parseInt(st.nextToken());
		
		for (int i = 1; i <= T; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			R = Integer.parseInt(st.nextToken());
			S = st.nextToken();
			
			StringBuffer sb = new StringBuffer();
			for (int j = 0; j < S.length(); j++) {
				for (int k = 0; k < R; k++)
					sb.append(S.charAt(j));
			}
			bw.write(sb.toString() + "\n");
		}
		bw.flush();
	}
}