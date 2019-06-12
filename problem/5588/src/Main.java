import java.awt.Point;
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

	static int m, n;
	static ArrayList<Point> vc = new ArrayList<Point>();
	static ArrayList<Point> p = new ArrayList<Point>();
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		m = Integer.parseInt(br.readLine());
		for (int i = 1 ; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			vc.add(new Point(Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken())));
		}
		
		n = Integer.parseInt(br.readLine());
		for (int i = 1 ; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			p.add(new Point(Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken())));
		}
		
		Collections.sort(vc, new Comparator<Point>() {

			@Override
			public int compare(Point o1, Point o2) {
				if (o1.x < o2.x) return -1;
				else if (o1.x > o2.x) return 1;
				else {
					if (o1.y <= o2.y) return -1;
					else return 1;
				}
			}
		});
		
		Collections.sort(p, new Comparator<Point>() {

			@Override
			public int compare(Point o1, Point o2) {
				if (o1.x < o2.x) return -1;
				else if (o1.x > o2.x) return 1;
				else {
					if (o1.y <= o2.y) return -1;
					else return 1;
				}
			}
		});
		
		int startx = vc.get(0).x;
		int starty = vc.get(0).y;
		
		for (int i = 0; i < n; i++) {
			int diffx = p.get(i).x - startx;
			int diffy = p.get(i).y - starty;
			
			for (int j = 0; j < m; j++) {
				vc.get(j).x += diffx;
				vc.get(j).y += diffy;
			}
			int answer = 0;
			for (int j = 0; j < m; j++) {
				Point q = vc.get(j);
				for (int k = 0; k < n; k++) {
					if (q.x == p.get(k).x && q.y == p.get(k).y)
						answer++;
				}
			}
			for (int j = 0; j < m; j++) {
				vc.get(j).x -= diffx;
				vc.get(j).y -= diffy;
			}
			
			if (answer == m) {
				System.out.println(diffx + " " + diffy + "");
				break;
			}
		}
		bw.flush();
	}
}