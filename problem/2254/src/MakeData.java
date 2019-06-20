import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class MakeData {

	static int N = 1000;
	public static void main(String[] args) throws IOException {
		
		BufferedWriter bw = new BufferedWriter(new FileWriter("./src/out.txt"));
		bw.write(N + " " + (int)(Math.random()*200000 + 1) + " " + (int)(Math.random()*200000 + 1) + "\n");
		for (int i = 0; i < 1000; i++) {
			bw.write((int)(Math.random()*200000 + 1) + " " + (int)(Math.random()*200000 + 1) + "\n");
			bw.flush();
		}
	}
}
