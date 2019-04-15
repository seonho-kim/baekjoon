import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
		String temp = st.nextToken();
		while(!temp.isEmpty())
		{		
			
			
			if (temp.length() > 10) {
				bw.write(temp.substring(0, 10) + "\n");
				temp = temp.substring(10);
			}
			else {
				bw.write(temp + "\n");
				temp = "";
			}
		}
		
		bw.close();
		br.close();
	}
}