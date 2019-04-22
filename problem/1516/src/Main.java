import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

	static final int MAX = 501;
	static int N;
	static int[] indegree = new int[MAX];
	static int[] value = new int[MAX];
	static int[] result = new int[MAX];
	static ArrayList<Integer>[] list = new ArrayList[MAX];
    
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(st.nextToken());
		
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<Integer>();
		}
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			value[i] = Integer.parseInt(st.nextToken());
			while(st.hasMoreTokens()) {
				int v = Integer.parseInt(st.nextToken());
				if (v == -1) break;
				
				list[v].add(i);
				indegree[i]++;
			}
		}
		
		Deque<Integer> q = new LinkedList<Integer>();
		for (int i = 1; i <= N; i++) {
			if (indegree[i] == 0) {
				q.add(i);
				result[i] += value[i];
			}
		}
		
		while(!q.isEmpty()) {
			int cur = q.pollFirst();
			
			for (int i = 0; i < list[cur].size(); i++) {
				int next = list[cur].get(i);
				indegree[next]--;
				
				result[next] = Math.max(result[next], result[cur] + value[next]);
				if (indegree[next] == 0) {
					q.add(next);
				}
			}
		}
		
		for (int i = 1; i <= N; i++) {
			bw.write(Integer.valueOf(result[i]) + "\n");
		}
		bw.flush();
	}
}