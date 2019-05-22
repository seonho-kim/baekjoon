import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int M;
	static int s;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		M = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			String order = st.nextToken();
			
			int value = 0;
			if (!(order.equals("all") || order.equals("empty")))
				value = Integer.parseInt(st.nextToken()) - 1;
			
			switch (order) {
				case "add":
					s = (s | (1 << value));
					break;
				case "remove":
					s = (s & ~(1 << value));
					break;
				case "check":
					int check = (s & (1 << value));
					if (check == (1 << value))
						bw.write("1\n");
					else
						bw.write("0\n");
					break;
				case "toggle":
					s = (s ^ (1 << value));
					break;
				case "all":
					s = (1 << 20) - 1;
					break;
				case "empty":
					s = 0;
					break;
			}
		}
		
		bw.flush();
	}
}