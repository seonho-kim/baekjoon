import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, n, answer;
	static final int MAX = 500000;
	static final int SEG_SIZE = 1<<(int)Math.ceil(Math.log(MAX)/Math.log(2) + 1);
	static int[] t = new int[SEG_SIZE];
	static Student[] s = new Student[MAX];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			s[Integer.parseInt(st.nextToken())-1] = new Student(i+1);
		}
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			s[Integer.parseInt(st.nextToken())-1].y = i+1;
		}
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			s[Integer.parseInt(st.nextToken())-1].z = i+1;
		}		
		
		n = 1<<(int)Math.ceil(Math.log(N)/Math.log(2));
		Arrays.sort(s, 0, N);
				
		for (int i = 0; i < N; i++)
			update(i, Integer.MAX_VALUE);
		
		for (int i = 0; i < N; i++) {
			if (query(0, s[i].y-1) > s[i].z) answer++;
			update(s[i].y-1 , s[i].z);
		}
		bw.write(answer + "");
		bw.flush();
	}
	static void update(int p, int v) {
		for (t[p+=n]=v; p > 1; p>>=1) {
			t[p>>1] = Math.min(t[p], t[p^1]);
		}
	}
	static int query(int l, int r) {
		int res = Integer.MAX_VALUE;
		if (l<=r) res = Math.min(res, t[r+n]);
		for (l+=n, r+=n; l < r; l>>=1, r>>=1) {
			if ((l&1)==1) res = Math.min(res, t[l++]);
			if ((r&1)==1) res = Math.min(res, t[--r]);
		}
		return res;
	}
	static class Student implements Comparable<Student> {
		int x, y, z;
		public Student(int x) {
			this.x = x;
		}
		@Override
		public int compareTo(Student o) {
			if (this.x < o.x) return -1;
			else if (this.x > o.x) return 1;
			else return 0;
		}
	}
}