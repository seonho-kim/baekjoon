import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int N, RCNT, BCNT, cnt, idx, answer;
	static final int MAX = 500000;
	static char[] data = new char[MAX];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		String tmp = br.readLine();
		for (int i = 0; i < N; i++) {
			data[i] = tmp.charAt(i);
			
			if (data[i] == 'R') RCNT++;
			else BCNT++;
		}
		 
		answer = MAX;
	    while(data[idx++] == 'R' && idx < N) cnt++;
	    answer = Math.min(answer, RCNT - cnt);
	    
	    idx = N-1; cnt = 0;
	    while(data[idx--] == 'R' && idx > 0) cnt++;
	    answer = Math.min(answer, RCNT - cnt);

	    idx = 0; cnt = 0;
	    while(data[idx++] == 'B' && idx < N) cnt++;
	    answer = Math.min(answer, BCNT - cnt);
	    
	    idx = N-1; cnt = 0;
	    while(data[idx--] == 'B' && idx > 0) cnt++;
	    answer = Math.min(answer, BCNT - cnt);
	    
	    
		System.out.println(answer);
	}
}