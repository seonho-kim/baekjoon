import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	enum id {
		ascending,
		descending,
		mixed
	}
	static String[] numbers;
	static int flag;
	static int answer;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		numbers = br.readLine().split(" ");
		
		flag = Integer.parseInt(numbers[0]) == 8 ? -1 : (Integer.parseInt(numbers[0]) == 1 ? 1 : 0);
		
		if (flag == 0) {
			bw.write(id.mixed + "\n");
		} else {
			for (int i = 1; i < numbers.length; i++) {
				if (flag != numbers[i].charAt(0) - numbers[i - 1].charAt(0)) {
					flag = 0;
					break;
				}
			}
			
			if (flag == -1) {
				bw.write(id.descending + "\n");
			} else if (flag == 1) {
				bw.write(id.ascending+ "\n");
			} else {
				bw.write(id.mixed + "\n");
			}
		}
		bw.flush();
	}
}