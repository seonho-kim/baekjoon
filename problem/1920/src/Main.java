import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static final int MAX = 100001;
	static int[] list = new int[MAX];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		
		for (int i = 1; i <= N; i++) {
			list[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(list, 1, N+1);
		
		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		while (M-- > 0) {
			int find = Integer.parseInt(st.nextToken());
			
			int lo = 1, hi = N;
			long answer = Long.MAX_VALUE;
			
			while (lo <= hi) {
				int mid = (int)(1L*lo+1L*hi)/2;
				
				if (find == list[mid]) {
					answer = find;
					break;
				} else if (list[mid] > find) hi = mid - 1;
				else lo = mid + 1;
			}
			if (answer == find) {
				bw.write("1\n");
			} else {
				bw.write("0\n");
			}
			bw.flush();
		}
	}
}