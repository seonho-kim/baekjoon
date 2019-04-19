import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static String[] array;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(st.nextToken());
		
		array = new String[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			array[i] = st.nextToken();
		}
				
		Arrays.sort(array, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				
				if (o1.length() < o2.length())
					return -1;
				else if (o1.length() > o2.length())
					return 1;
				else { 
					return o1.compareTo(o2);
				}				
			}
		});
		
		String prev = "";
		for (int i = 0; i < N; i++) {
			if (!prev.equals(array[i]))
				bw.write(array[i] + "\n");
			prev = array[i];
		}
		bw.flush();
	}
}