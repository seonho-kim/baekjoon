import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	static int n, k;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String temp;

		while ((temp = br.readLine()) != null) {
			String[] num = temp.split(" ");
			n = Integer.parseInt(num[0]);
			k = Integer.parseInt(num[1]);

			long tmp = k;
			long answer = 1;
			for (int i = 2; i * i <= tmp; i++) {
				int pow = 0;
				while (k % i == 0) {
					k /= i;
					pow += 1;
				}

				if (pow != 0) {
					int powN = 0;

					for (int j = i; j <= n; j *= i)
						powN += n / j;

					for (int j = 0; j < Math.min(powN, pow); j++)
						answer *= i;
				}

				if (k < i)
					break;
			}

			if (k > 1 && k <= n)
				answer *= k;

			bw.write(answer + "\n");
		}
		bw.flush();
	}
}