import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	static int K;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		K = Integer.parseInt(br.readLine());
		
		int size = 1 << (K+1), left, right;
		int[] tree= new int[size], path = new int[size], sum = new int[size];
		
		String line[] = br.readLine().split(" ");
		for (int i = 2; i < size; i++)
			tree[i] = Integer.parseInt(line[i - 2]);
		
		for (int i = 1 << K; --i > 0;) {
			left = i << 1; right = left + 1;
			path[i] = Math.max(path[left] + tree[left], path[right] + tree[right]);
			sum[i] = sum[left] + sum[right] + (path[i] - path[left]) + (path[i] - path[right]);
		}
			
		bw.write(sum[1] + "");
		bw.flush();
	}
}