import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int A, B, C;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		bw.write(String.valueOf((A+B)%C) + "\n");
		bw.write(String.valueOf(((A%C) + (B%C))%C) + "\n");
		bw.write(String.valueOf((A*B)%C) + "\n");
		bw.write(String.valueOf(((A%C) * (B%C))%C) + "\n");
		
		bw.close();
		br.close();
		// ù° �ٿ� (A+B)%C, ��° �ٿ� (A%C + B%C)%C, ��° �ٿ� (A��B)%C, ��° �ٿ� (A%C �� B%C)%C�� ����Ѵ�.
	}
}