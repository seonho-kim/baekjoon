import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MakeData {

	public static void main(String[] args) throws IOException{
		BufferedWriter bw = new BufferedWriter(new FileWriter("./out.txt"));
		
		for (int i = 1; i <= 200000; i++) {
			bw.write("10000000 -10000000\n");
			//bw.write((int)(Math.random()*20000000+1-10000000) + " " + (int)(Math.random()*20000000+1-10000000) + "\n");
			bw.flush();
		}

	}

}
