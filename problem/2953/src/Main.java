import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static Chef[] c = new Chef[5];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		for (int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine());
			int total = 0;
			while(st.hasMoreTokens()) {
				total += Integer.parseInt(st.nextToken());
			}
			c[i] = new Chef(i+1, total);
		}
		
		Arrays.sort(c);
		bw.write(c[0].number + " " + c[0].total + "\n");
		bw.flush();
	}
	static class Chef implements Comparable<Chef>{
		int number, total;
		public Chef(int number, int total) {
			this.number = number;
			this.total = total;
		}
	
		@Override
		public int compareTo(Chef o) {
			if (this.total > o.total) return -1;
			else if (this.total < o.total) return 1;
			return 0;
		}
	}
}