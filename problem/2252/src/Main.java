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

	static final int MAX = 32001;
	static int N, M;
	static int[] indegree = new int[MAX];
    static ArrayList<Integer>[] list = new ArrayList[MAX];
    
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<Integer>();
		}
		
		for(int i = 0; i < M; i++){
			st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            
            list[x].add(y);
            indegree[y]++;
        }
		
		Deque<Integer> q = new LinkedList<Integer>();
		for (int i = 1; i <= N; i++) {
			if (indegree[i] == 0)
				q.add(i);
		}
		
		while(!q.isEmpty()) {
			bw.write(Integer.valueOf(q.peek()) + " ");
			int cur = q.pollFirst();
			
			for (int i = 0; i < list[cur].size(); i++) {
				int next = list[cur].get(i);
				indegree[next]--;
				
				if (indegree[next] == 0) {
					q.add(next);
				}
			}
		}
		bw.flush();
	}
}