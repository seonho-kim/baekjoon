import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.TreeSet;
 
public class Main {
 
    static int N;
    static final int MAX = 101;
    static TreeSet<Size> t = new TreeSet<Size>(); 
    static int[] d = new int[MAX];
    public static void main(String[] args) throws IOException {
    	System.setIn(new FileInputStream("./src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
         
        N = Integer.parseInt(br.readLine());
         
        for (int i = 1; i <= N; i++) {
            int x, y;
             
            st = new StringTokenizer(br.readLine(), " ");
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
             
            if (x <= y) {
                t.add(new Size(x, y));
            } else {
                t.add(new Size(y, x));
            }
        }
         
        Size[] a = new Size[t.size()];
        a = t.toArray(a);       
         
        d[1] = 1;
        int result = 0;
        for (int i = 1; i < a.length; i++) {
            d[i + 1]  = 1;
            for (int j = 0; j < i; j++) {
                if (a[i].x >= a[j].x && a[i].y >= a[j].y)
                    d[i + 1] = Math.max(d[i + 1], d[j + 1] + 1);
            }
            result = Math.max(result, d[i + 1]);
        }
         
        bw.write(result + "");
        bw.flush();
    }
    static class Size implements Comparable<Size> {
        int x, y;
        public Size(int x, int y) {
            this.x = x;
            this.y = y;
        }
         
        @Override
        public int compareTo(Size o) {
        	if (this.x < o.x) return -1;
        	else if (this.x > o.x) return 1;
        	else {
        		if (this.y < o.y) return -1;
            	else if (this.y > o.y) return 1;
            	else return 0;
        	}
        }
    }
}