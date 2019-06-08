import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static final int MAX = 10001;
//	static ArrayList<Character>[] list = new ArrayList[MAX];
	static char[][] list = new char[MAX][450];
	static boolean[] visited = new boolean[MAX];
	static char[] visitCount = new char[MAX]; 
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		
		long start = System.currentTimeMillis();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
//		for (int i = 0; i <= N; i++)
//			list[i] = new ArrayList<Character>();
		
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			char from, to;
			from = (char)Integer.parseInt(st.nextToken());
			to = (char)Integer.parseInt(st.nextToken());
			
//			list[to].add((char)from);
			list[to][0]++;
			list[to][list[to][0]] = from;
		}
		
		Queue<Character> q = new ArrayDeque<Character>();
		char max = 0;
		for (int i = 1; i <= N; i++) {
			initVisit();
			q.offer((char)i);
			visited[i] = true;
			visitCount[i] = 1;
			
			while(!q.isEmpty()) {
				int curr = q.poll();
				
//				for (int next : list[curr]) {
//					if (visited[next] == false) {
//						q.offer((char)next);
//						visited[next] = true;
//						visitCount[i]++;
//					}
//				}
				if (list[curr][0] != 0) {
					for (int j = 1; j <= list[curr][0]; j++) {
						if (visited[list[curr][j]] == false) {
							q.offer((char)list[curr][j]);
							visited[list[curr][j]] = true;
							visitCount[i]++;
						}
					}
				}
			}
			if (max < visitCount[i]) max = visitCount[i];
		}
		
		for (int i = 1; i <= N; i++) {
			if (visitCount[i] == max) sb.append(i + " ");
		}
		bw.write(sb.toString());
		long end = System.currentTimeMillis();
		bw.write("\n" + (end - start)/1000 );
		bw.flush();
	}
	static void initVisit() {
		for (int i = 1; i <= N; i++)
			visited[i] = false;
	}
}