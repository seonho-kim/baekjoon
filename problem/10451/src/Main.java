import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	static int T, N;
	static final int MAX = 1001;
	static int[] list = new int[MAX];
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
			
			Arrays.fill(list, 0);
			Arrays.fill(visited, false);
		
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 1; i <= N; i++) {
				list[i] = Integer.parseInt(st.nextToken());
			}
			
			answer = 0;
			Stack<Integer> s = new Stack<Integer>();
			for (int i = 1; i <= N; i++) {
				if (visited[i] == false) {
					s.add(i);
					
					answer++;
					while(!s.isEmpty()) {
						int node = s.pop();
						visited[node] = true;
						
						int next = list[node];
						if (visited[next] == false) {
							s.add(next);
							
						}
					}
				}
				
			}
			bw.write(answer + "\n");
		}
		bw.flush();
	}
}