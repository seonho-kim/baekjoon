import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	static String words, answer;
	static Alphabet[] alphabet = new Alphabet[26];
	static int max = 0;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		for (int i = 0; i < alphabet.length; i++)
			alphabet[i] = new Alphabet(i, 0);
		
		words = st.nextToken();
		for(int i = 0; i < words.length(); i++) {
			alphabet[(Character.toUpperCase(words.charAt(i)) - 65)].v++;
		}
		
		Arrays.sort(alphabet);
		
		max = alphabet[0].v;
		for (int i = 1; i < alphabet.length; i++) {
			if (alphabet[i].v == max) {
				answer = "?";
			} else break;
		}
		
		if (answer == "?")
			bw.write(answer + "\n");
		else
			bw.write((char)(alphabet[0].k + 65) + "\n");
		bw.flush();
	}
}

class Alphabet implements Comparable<Alphabet> {
	int k;
	int v;
	
	public Alphabet(int k, int v) {
		this.k = k;
		this.v = v;
	}

	@Override
	public int compareTo(Alphabet o) {
		return v > o.v ? -1 : 1;
	}
}