import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static int[] a, t;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String temp;
		while ((temp = br.readLine()) != null) {
			String[] tmp = temp.split(" ");
			
			n = Integer.valueOf(tmp[0]);
			int h = 1<<(int)Math.ceil(Math.log(n)/Math.log(2));
			a = new int[n];
			t = new int[h<<1];
			
			if (n == 0) break;
			for (int i = 0; i < n; i++) {
				a[i] = Integer.valueOf(tmp[i+1]);
			}
			init(1, 0, n-1);
			bw.write(largest(0, n-1) + "\n");
		}
		bw.flush();
	}
	static void init(int node, int start, int end) {
		if (start == end) t[node] = start;
		else {
			init(node*2, start, (start+end)/2);
			init(node*2+1, (start+end)/2+1, end);
			
			if (a[t[node*2]] <= a[t[node*2+1]]) t[node] = t[node*2];
			else t[node] = t[node*2+1]; 
		}
	}
	static int query(int node, int start, int end, int i, int j) {
		if (i > end || j < start) return -1;
		if (i <= start && end <= j) return t[node];
		int m1 = query(2*node, start, (start+end)/2, i, j);
		int m2 = query(2*node+1, (start+end)/2+1, end, i, j);
		
		if (m1 == -1) return m2;
		else if (m2 == -1) return m1;
		else {
			if (a[m1] <= a[m2]) return m1;
			else return m2;
		}
	}
	static long largest(int start, int end) {
		int m = query(1, 0, n-1, start, end);
		long area = (long)(end-start+1)*(long)a[m];
		
		if (start <= m-1) {
			long temp = largest(start, m-1);
			if (area < temp) area = temp;
		}
		if (m+1 <= end) {
			long temp = largest(m+1, end);
			if (area < temp) area = temp;
		}
		return area;
	}
}