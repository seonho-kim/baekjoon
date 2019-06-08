import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	static int T, N;
	static final int MAX = 3001;
	static int[][] list = new int[MAX][3];
	static boolean[] visited = new boolean[MAX];
	static int answer;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			
			for (int i = 1; i <= N; i++) {
				Arrays.fill(list[i], 0);
			}
			Arrays.fill(visited, false);
			
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				
				int x, y, r;
				list[i][0] = Integer.parseInt(st.nextToken());
				list[i][1] = Integer.parseInt(st.nextToken());
				list[i][2] = Integer.parseInt(st.nextToken());
			}
			
			Stack<Integer> s = new Stack<Integer>();
			answer = 0;
			for (int i = 1; i <= N; i++) {
				if (visited[i] == false) {
					s.add(i);
					
					answer++;
					while(!s.isEmpty()) {
						int node = s.pop();
						visited[node] = true;
						
							for (int j = 1; j <= N; j++) {
								if (list[node][2]*list[node][2] +
									2*list[node][2]*list[j][2] + 
									list[j][2]*list[j][2] >=
									(list[j][0] - list[node][0])*(list[j][0] - list[node][0]) +
									(list[j][1] - list[node][1])*(list[j][1] - list[node][1])
									&& visited[j] == false)
									s.add(j);
							}
					}
				}
			}
			bw.write(answer + "\n");
		}
		bw.flush();
	}
}