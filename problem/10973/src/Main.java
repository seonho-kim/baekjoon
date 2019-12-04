import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[] array;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		array = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}
		
		if(prevPermutation()) {
			for (int num : array) {
				bw.write(num + " ");
			}
			bw.flush();
			return;
		}
		bw.write("-1");
		bw.flush();
	}
	static boolean prevPermutation() {
	    int i = array.length - 1;
	    while (i > 0 && array[i - 1] <= array[i]) {
	        i--;            
	    }
	 
	    if (i <= 0)
	        return false;
	 
	    int j = array.length - 1;
	    while (array[j] >= array[i - 1]) {
	        j--;            
	    }
	 
	    int temp = array[i - 1];
	    array[i - 1] = array[j];
	    array[j] = temp;
	 
	    j = array.length - 1;
	    while (i < j) {
	        temp = array[i];
	        array[i] = array[j];
	        array[j] = temp;
	        i++;
	        j--;
	    }
	 
	    return true;
	}
}