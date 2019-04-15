import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static String answer;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(st.nextToken());
		
		
		switch (N / 10) {
			case 10:
			case 9:
				answer = "A";
				break;
			case 8:
				answer = "B";
				break;
			case 7:
				answer = "C";
				break;	
			case 6:
				answer = "D";
				break;	
			default:
				answer = "F";
				break;	
		}
				
		bw.write(answer + "\n");
		bw.flush();
	}
}