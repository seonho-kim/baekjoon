import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {

	static int A, B;
	static TreeSet<Integer> a = new TreeSet<Integer>();
	static int answerA, answerB;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= A; i++) {
			a.add(Integer.parseInt(st.nextToken()));
		}
		answerA = a.size();
		
		Integer[] arr = new Integer[a.size()];
		arr = a.toArray(arr);
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= B; i++) {
			int value = Integer.parseInt(st.nextToken());
			if (Arrays.binarySearch(arr, value) > -1) answerA--;
			else answerB++;
		}
		
		bw.write(answerA + answerB + "");
		bw.flush();
	}
}