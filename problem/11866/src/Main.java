import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, K;
	static Queue<Integer> q = new LinkedList<Integer>();
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		for (int i = 1; i <= N; i++)
			q.add(i);
		
		StringBuffer sb = new StringBuffer();
		while(!q.isEmpty()) {
			for (int i = 0; i < K-1; i++) {
				int temp = q.poll();
				q.add(temp);
			}
			
			sb.append(q.poll().toString());
			
			if (q.size() != 0)
				sb.append(", ");
		}
		sb.insert(0, "<");
		sb.append(">");
		
		bw.write(sb.toString());
		bw.flush();
	}
}