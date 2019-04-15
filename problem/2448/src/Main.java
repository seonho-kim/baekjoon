import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int K;
	static String[] map = {"  *  ", " * * ", "*****"};
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuffer sb = new StringBuffer();
		
		K = Integer.parseInt(st.nextToken());

		map = Arrays.copyOf(map, K);


		for (int i = 1; 3 * binarySqure(i) <= K; ++i) {
			print(i, map);
		}

		for (int i = 0; i < K; ++i) {
			sb.append(map[i] + "\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
	}

	private static void print(int n, String map[]) {
		int bottom = 3 * binarySqure(n);
		int middle = bottom / 2;

		for (int i = middle; i < bottom; i++) {
			map[i] = map[i - middle] + " " + map[i -middle];
		}

		String space = "";
		while (space.length() < middle) {
			space += " ";
		}
		
		for (int i = 0; i < middle; i++) {
			map[i] = space + map[i] + space;
		}
	}
	
	private static int binarySqure(int n) {
		if (n < 1)
			return 1;
		
		int result = 2;
		for (int i = 1; i < n; i++)
			result = result << 1;
		return result;
	}
}