import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int N, answer;
	static final int MAX = 100001;
	static Meeting[] list = new Meeting[MAX];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); 
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			list[i] = new Meeting(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(list, 1, N+1);
		
		PriorityQueue<Meeting> pq = new PriorityQueue<Meeting>();
		pq.offer(list[1]);
		answer++;
		for (int i = 2; i <= N; i++) {
			Meeting temp = pq.poll();
			
			if (temp.end <= list[i].start) {
				pq.offer(list[i]);
				answer++;
			}
			else { 
				pq.offer(temp);
			}
		}
		bw.write(answer + "");
		bw.flush();		
	}
	static class Meeting implements Comparable<Meeting> {
		int start, end;
		public Meeting(int start, int end) {
			this.start = start;
			this.end = end;
		}
		@Override
		public int compareTo(Meeting o) {
			if (this.end < o.end) return -1;
			else if (this.end > o.end) return 1;
			else {
				if (this.start < o.start) return -1;
				else if (this.start > o.start) return 1;
				else return 0;
			}
		}
		
	}
}