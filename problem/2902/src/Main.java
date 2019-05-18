import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String temp = br.readLine();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < temp.length(); i++) {
			if (temp.charAt(i) >= 65 &&  temp.charAt(i) <= 90)
				sb.append(temp.charAt(i));
		}
		bw.write(sb.toString());
		bw.flush();
	}
}