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
	static PriorityQueue<Integer> MAXQ;
	static PriorityQueue<Integer> MINQ;
	public static void main(String[] args) throws IOException  {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N  = Integer.parseInt(br.readLine());
		
		MAXQ = new PriorityQueue<Integer>(N, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				if (o1 > o2) return -1;
				else if (o1 < o2) return 1;
				else return 0;
			}
		});
		MINQ = new PriorityQueue<Integer>();
		
		for (int i = 1; i <= N; i++) {
			int value = Integer.parseInt(br.readLine());
			
			if (MAXQ.isEmpty()) {
				MAXQ.offer(value);
			} else if (MAXQ.size() == MINQ.size()) {
				MAXQ.offer(value);
			} else {
				MINQ.offer(value);
			}
			
			if(!MAXQ.isEmpty() && !MINQ.isEmpty() && !(MAXQ.peek() <= MINQ.peek())) {
				int a = MAXQ.poll();
				int b = MINQ.poll();
				MAXQ.offer(b);
				MINQ.offer(a);
			}
			bw.write(MAXQ.peek() + "\n");
		}
		bw.flush();
	}
}