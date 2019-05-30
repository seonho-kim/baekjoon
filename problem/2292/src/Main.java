import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	
	static int N;
	static final int MAX = 1000000000;
	static int[] t = new int[18260];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		
		t[1] = 1;
		for (int i = 2; i < t.length; i++) {
			t[i] = 3*i*(i-3) + 8;
		}
	        
		int l = 1;
		int r = t.length - 1;
		while (l < r) {
			int m = (l + r) >> 1;
			
			if (N >= t[m]) l = m + 1; 
			else r = m;
		}
		
		bw.write(l - 1 + "");
		bw.flush();
				
		/*
		  2 3 4  5  6
2 8 20 38 62
 6 12 18 24


bn = 6n

a2 = 2
a3 = a2 + 6
a4 = a3 + 12
a5 = a4 + 18


an = 2 + sigma 6n [1 to n-2]

an = 2 + 6 * [(n-2)(n-1)  /2]
an = 2 + 3n^2 - 9n +6

an = 3n^2 - 9n + 8 [n>=2]

a1 = 1
a2 = 12 - 18 + 8 = 2
a3 = 27 - 27 + 8 = 8
a4 = 48 - 36 + 8 = 20

3n^2 - 9n + 8 >= 10¾ï
3n^2 - 9n - 999999992 >= 0

-b +- root [b^2 - 4ac]
--
2a


9 + root[81 + 11999999904]
---
6

		 */
	}
}
