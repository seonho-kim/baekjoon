import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int H, M;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		H = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		if (M < 45) {
			M += (60-45);
			H--;
			
			if (H < 0) H += 24;
		} else {
			M -= 45;
		}
		bw.write(H + " " + M);
		bw.flush();
	}
}