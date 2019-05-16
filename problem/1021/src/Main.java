import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static LinkedList<Integer> q = new LinkedList();
	static ArrayList<Integer> f = new ArrayList<Integer>();
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
				
		for (int i = 1; i <= N; i++) {
			q.add(i);
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < M; i++) {			
			f.add(Integer.parseInt(st.nextToken()));
		}
		
		int cnt = 0;
		for (int i = 0; i < f.size(); i++) {
			int idx = q.indexOf(f.get(i));

			int size = q.size();
			if (idx <= size - idx) {
				while (q.indexOf(f.get(i)) != 0) {
					q.addLast(q.pollFirst());
					cnt++;
				}
				q.pollFirst();
			} else {
				while (q.indexOf(f.get(i)) != q.size() - 1) {
					q.addFirst(q.pollLast());
					cnt++;
				}
				q.addFirst(q.pollLast());
				cnt++;
				q.pollFirst();
			}
		}
		
		bw.write(cnt + "");
		bw.flush();
	}
}