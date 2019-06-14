import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int[][] p = new int[4][2];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		for (int i = 0; i < 4; i++) {
			p[i][0] = Integer.parseInt(st.nextToken());
			p[i][1] = Integer.parseInt(st.nextToken());
		}		
		
		bw.write(intersect(p[0][0], p[0][1], p[1][0], p[1][1], p[2][0], p[2][1], p[3][0], p[3][1]) + "");
		bw.flush();
	}
	
	static int ccw(int x1, int y1, int x2, int y2, int x3, int y3) {
		long result = 0;
		
		result = 1L*x1*y2 + 1L*x2*y3 + 1L*x3*y1 - (1L*x2*y1 + 1L*x3*y2 + 1L*x1*y3);
		
		if (result > 0) return 1;
		else if (result < 0) return -1;
		else return 0;
	}
	
	static int intersect(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
		
		int ab = ccw(x1, y1, x2, y2, x3, y3)*ccw(x1, y1, x2, y2, x4, y4); 
		int cd = ccw(x3, y3, x4, y4, x1, y1)*ccw(x3, y3, x4, y4, x2, y2);
		
		if (ab == 0 && cd == 0) {
			if (x1 > x2) {
				int tmp = x1;
				x1 = x2;
				x2 = tmp;
				
				tmp = y1;
				y1 = y2;
				y2 = tmp;
			} else if (x1 == x2) {
				if (y1 > y2) {
					int tmp = x1;
					x1 = x2;
					x2 = tmp;
					
					tmp = y1;
					y1 = y2;
					y2 = tmp;
				}
			}
			
			if (x3 > x4) {
				int tmp = x3;
				x3 = x4;
				x4 = tmp;
				
				tmp = y3;
				y3 = y4;
				y4 = tmp;
			} else if (x3 == x4) {
				if (y3 > y4) {
					int tmp = x3;
					x3 = x4;
					x4 = tmp;
					
					tmp = y3;
					y3 = y4;
					y4 = tmp;
				}
			}
			
			boolean cb = false, ad = false;
			
			if (x3 < x2) {
				cb = true;
			} else if (x3 == x2) {
				if (y3 <= y2) {
					cb = true;
				}
			}
			
			if (x1 < x4) {
				ad = true;
			} else if(x1 == x4) {
				if (y1 <= y4) {
					ad = true;
				}
			}
				
			return (cb && ad) == true ? 1 : 0;
		}
		return (ab < 0 && cd < 0) == true ? 1 : 0;
	}
}
