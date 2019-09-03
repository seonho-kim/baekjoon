import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static final int MAX = 500000;
	static int N;
	static int[] array = new int[MAX];
	static int[] visited = new int[8001];
	static int[] answer = new int[4];
	
	static double sum;
	static int max = -4000, min = 4000;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			array[i] = Integer.parseInt(st.nextToken());
			sum += array[i];
			visited[array[i] + 4000]++;
			max = Math.max(max, array[i]);
			min = Math.min(min, array[i]);
		}
		
        double avg = sum / N;
        if (avg >= 0) {
        	answer[0] = (int)(avg + 0.5);
        } else {
        	answer[0] = (int)(avg - 0.5);
        }
        
        Arrays.sort(array, 0, N);
        answer[1] = array[N / 2];
                
        int num = -1;
        int cur = 0;
        boolean second = false;
        for(int i = 0; i < 8001; i++) {
        	if (cur < visited[i]) {
                cur = visited[i];
                num = i;
                second = false;
        	}
        	else if (cur == visited[i] && !second) {
                num = i;
                second = true;
        	}
        }
        answer[2] = num - 4000;
		answer[3] = max - min;
		
		for (int i = 0; i < 4; i ++) {
			bw.write(answer[i] + "\n");
		}				
		bw.flush();
	}
}