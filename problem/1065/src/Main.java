import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int N, cnt;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(st.nextToken());
		
		boolean returnFlag = false;
		for (int i = 1; i <= N; i++) {
			int temp = i, check = 0, difference = 0;
			
			check = temp % 10;
			temp = temp / 10;
			difference = check - (temp % 10);
			
			while(temp != 0 && String.valueOf(temp).toString().length() != 1) {
				check = temp % 10;
				temp = temp / 10;
				if (difference == check - (temp % 10))
					continue;
				else {
					returnFlag = true;
					break;
				}
			}
			
			if (returnFlag) {
				returnFlag = false;
				continue;
			}
			cnt++;
		}
		bw.write(String.valueOf(cnt) + "\n");
		bw.flush();
	}
}