import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {

	static int N;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o2.compareTo(o1);
			}
		});
		
		N = Integer.parseInt(br.readLine());
		for (int i = 1; i <= N; i++) {
			int value = Integer.parseInt(br.readLine());
			
			if (value == 0) {
				bw.write((pq.size() == 0 ? 0 : pq.poll()) + "\n");
			} else {
				pq.add(value);
			}
		}
		
		bw.flush();
	}
}