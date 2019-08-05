import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	static int N, h;
	static int[] a, t;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		h = 1<<(int)Math.ceil(Math.log(N)/Math.log(2));
		
		a = new int[N];
		t = new int[h<<1];
		for (int i = 0; i < N; i++)
			update(1, 0, N-1, i, 1);
		
		for (int i = 0; i < N; i++) {
			int number = Integer.parseInt(br.readLine());
			int q = query(1, 0, N-1, number + 1);
			a[q] = i+1;
			update(1, 0, N-1, q, 0);
		}
		
		for (int i = 0; i < N; i++) {
			bw.write(a[i] + "\n");
		}
		bw.flush();
	}
	static void update(int node, int start, int end, int index, int value) {
		if (index < start || index > end) return;
		if (start == end) {
			t[node] = value;
		} else {
			int mid = (start + end) / 2;
			update(node*2, start, mid, index, value);
			update(node*2+1, mid+1, end, index, value);
			t[node] = t[node*2] + t[node*2+1];
		}
	}
	static int query(int node, int start, int end, int value) {
		if (start == end) return start;
		int mid = (start + end) / 2;
		if (t[node*2] >= value) return query(node*2, start, mid, value);
		return query(node*2+1, mid+1, end, value - t[node*2]);
	}
}