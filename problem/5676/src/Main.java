import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int N, K;
	static int[] a, t;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		String temp;
		while ((temp = br.readLine()) != null) {
			String[] tmp = temp.split(" ");
			N = Integer.valueOf(tmp[0]);
			K = Integer.valueOf(tmp[1]);
			
			int n = 1<<(int)Math.ceil(Math.log(N)/Math.log(2));
			a = new int[n];
			t = new int[n<<1];
			
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				a[i] = Integer.parseInt(st.nextToken());
			}
			init(1, 0, n-1);
			
			while(K-- > 0) {
				st = new StringTokenizer(br.readLine(), " ");
				char order = st.nextToken().charAt(0);
				
				if (order == 'C') {
					int index = Integer.parseInt(st.nextToken())-1;
					int value = Integer.parseInt(st.nextToken());
					a[index] = value;
					
					if (a[index] > 0) update(1, 0, n-1, index, 1);
					else if (a[index] == 0) update(1, 0, n-1, index, 0);
					else update(1, 0, n-1, index, -1);
				} else if (order == 'P') {
					int left = Integer.parseInt(st.nextToken())-1;
					int right = Integer.parseInt(st.nextToken())-1;
					
					long result = query(1, 0, n-1, left, right);
					bw.write((result > 0 ? '+' : result < 0 ? '-' : '0') + "");
				}
			}
			bw.write("\n");
		}		
		bw.flush();		
	}
	static int init(int node, int start, int end) {
		if (start == end) {
			if (a[start] > 0) t[node] = 1;
			else if (a[start] == 0) t[node] = 0;
			else t[node] = -1;
			return t[node];
		}
		else {
			return t[node] = init(node*2, start, (start+end)/2)
					* init(node*2+1, (start+end)/2+1, end);
		}
	}
	static int query(int node, int start, int end, int left, int right) {
		if (left > end || right < start) return 1;
		if (left <= start && end <= right) return t[node];
		return query(node*2, start, (start+end)/2, left, right)
				* query(node*2+1, (start+end)/2 + 1, end, left, right);
	}
	static void update(int node, int start, int end, int index, int value) {
		if (index < start || index > end) return;
		
		if (start == end) t[node] = value;
		if (start != end) {
			update(node*2, start, (start+end)/2, index, value);
			update(node*2+1, (start+end)/2 + 1, end, index, value);
			t[node] = t[node<<1]*t[node<<1|1];
		}
	}
}