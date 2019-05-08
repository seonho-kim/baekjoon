import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	static final int MAX = 100000;
	static int N;
	static Point[] arr;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(st.nextToken());
		
		arr = new Point[MAX];
		
		for (int i = 0; i < N; i++) {
			int x, y;
			st = new StringTokenizer(br.readLine(), " ");
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			arr[i] = new Point(x, y);
		}
		
		Arrays.sort(arr, 0, N);
		for (int i = 1; i < N; i++) {
			arr[i].p = arr[i].x - arr[0].x;
			arr[i].q = arr[i].y - arr[0].y;
		}
		Arrays.sort(arr, 1, N);
		
	    Stack<Integer> s = new Stack<Integer>();
	    s.push(0);
	    s.push(1);
	    
	    int next = 2;
	    while(next < N) {
	        while(s.size() >= 2) {
	            int first, second;
	            first = s.peek();
	            s.pop();
	            second = s.peek();
	            if(ccw(arr[second], arr[first], arr[next]) > 0) {
	                s.push(first);
	                break;
	            }
	        }
	        s.push(next++);
	    }
	    	    
	    bw.write(Integer.valueOf(s.size()) + "\n");
		bw.flush();
	}
	
	static long ccw(Point A, Point B, Point C) {		
		return 1L*(B.x - A.x)*(C.y - A.y) - 1L*(B.y - A.y)*(C.x - A.x); 
	}
}

class Point implements Comparable<Point>{
	int x, y;
	int p, q;
	
	Point (int x1, int y1) {
		this.x = x1;
		this.y = y1;
		this.p = 1;
		this.q = 0;
	}
	Point(int x1, int y1, int p1, int q1) {
		this.x = x1;
		this.y = y1;
		this.p = p1;
		this.q = q1;
	}

	@Override
	public int compareTo(Point p) {
		if (1L*this.q*p.p != 1L*this.p*p.q)
			return Long.valueOf(1L*this.q*p.p).compareTo(Long.valueOf(1L*this.p*p.q));
        if (this.y != p.y)
        	return Integer.valueOf(this.y).compareTo(Integer.valueOf(p.y));
	    return Integer.valueOf(this.x).compareTo(Integer.valueOf(p.x));
	}
}