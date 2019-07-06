import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {

	static int N;
	static final int MAX = 100001;
	static Person[] list = new Person[MAX];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		for (int i = 1; i <= N; i++) {
			String[] temp = br.readLine().split(" ");
			
			list[i] = new Person(i, Integer.valueOf(temp[0]), temp[1]);
		}
		
		Arrays.sort(list, 1, N+1);
		for (int i = 1; i <= N; i++) {
			bw.write(list[i].age + " " + list[i].name + "\n");
			bw.flush();
		}
	}
	static class Person implements Comparable<Person> {
		int n, age;
		String name;
		public Person(int n, int age, String name) {
			this.n = n;
			this.age = age;
			this.name = name;
		}
		
		@Override
		public int compareTo(Person o) {
			if (this.age < o.age) return -1;
			else if (this.age > o.age) return 1;
			else {
				if (this.n < o.n) return -1;
				else if (this.n > o.n) return 1;
				return 0;
			}
		}
	}
}