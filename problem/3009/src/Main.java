import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int[] x = new int[2];
	static int[] y = new int[2];
	static int x1, y1, x2, y2;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		x1 = Integer.parseInt(st.nextToken());
		y1 = Integer.parseInt(st.nextToken());
		
		x[0] = 1;
		y[0] = 1;
		
		for (int i = 1; i <= 2; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int tmpX1, tmpY1;
			tmpX1 = Integer.parseInt(st.nextToken());
			tmpY1 = Integer.parseInt(st.nextToken());
			
			if (x1 == tmpX1) {
				x[0]++;
			} else {
				x[1]++;
				x2 = tmpX1;
			}
			
			if (y1 == tmpY1) {
				y[0]++;
			} else {
				y[1]++;
				y2 = tmpY1;
			}
		}
		
		if (x[0] == 1) {
			bw.write(x1 + " ");
		} else {
			bw.write(x2 + " ");
		}
		if (y[0] == 1) {
			bw.write(y1 + "");
		} else {
			bw.write(y2 + "");
		}
		bw.flush();
	}
}