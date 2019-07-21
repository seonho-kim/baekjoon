import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

public class Main {

	static int N;
	static PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		
		String temp;
		while ((temp = br.readLine()) != null) {
			int value = Integer.parseInt(temp);
			
			if (value == 0) {
				if (pq.isEmpty()) bw.write("0\n");
				else bw.write(pq.poll() + "\n");
			}
			else {
				pq.offer(value);
			}
		}
		bw.flush();
	}
}