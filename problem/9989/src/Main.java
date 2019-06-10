import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

	static int N, B;
	static final int MAX = 20;
	static ArrayList<Integer[]>[] bonus = new ArrayList[MAX];
	static int[][] students = new int[MAX][MAX]; 
	static int d[] = new int[1<<MAX];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++)
			bonus[i] = new ArrayList<Integer[]>();
			
		for (int i = 0; i < B; i++) {
			st = new StringTokenizer(br.readLine(), " ");			
			bonus[Integer.parseInt(st.nextToken())-1].add(new Integer[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
		}
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				students[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < (1<<N); i++) {
			int cnt = 0;
			int bits = i;
			while(bits > 0) {
				if ((bits & 1) == 1) cnt++;
				bits>>=1;
			}
			
			for (int j = 0; j < N; j++) {
				if ((i & (1<<j)) != 0) continue;
				
				int score = students[j][cnt];
				int total = d[i] + score;
				int bonusToAdd = 0;
				

				for (Integer[] next : bonus[cnt])
					if (next[0] <= total)
						bonusToAdd += next[1];
				total += bonusToAdd;
				d[i|(1<<j)] = Math.max(d[i|(1<<j)], total);
			}
		}
		
		bw.write(d[(1<<N)-1] + "");
		bw.flush();
	}
}