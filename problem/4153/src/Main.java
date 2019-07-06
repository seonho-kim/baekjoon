import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	static int l1, l2, l3;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String temp;
		while ((temp = br.readLine()) != null) { 
			String[] value = temp.split(" ");
			
			l1 = Integer.valueOf(value[0]);
			l2 = Integer.valueOf(value[1]);
			l3 = Integer.valueOf(value[2]);
			
			if (l1 == 0 && l2 == 0 && l3 == 0) break;
			
			if (l1 < l2) {
				int tmp = l1;
				l1 = l2;
				l2 = tmp;
			}
			if (l1 < l3) {
				int tmp = l1;
				l1 = l3;
				l3 = tmp;
			}
			
			if (l1*l1 == l2*l2 + l3*l3) {
				bw.write("right\n");
			} else {
				bw.write("wrong\n");
			}
			bw.flush();
		}
	}
}