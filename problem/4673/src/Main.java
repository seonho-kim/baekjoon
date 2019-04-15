import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main {

	public static void main(String[] args) throws IOException {	
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int i = 1; i <= 10000; i++) {
			if (selfNumber(i))
				bw.write(String.valueOf(i) + "\n");
		}
		bw.flush();
	}
	
	public static boolean selfNumber(int num)
	{
		int sum = 0;
		for (int i = 1; i <= num; i++) {
			sum = 0;
			
			int temp = i;
			while(temp != 0){
				sum += temp % 10;
				temp /= 10;
	        }
			sum += i;
			
			if (sum == num) {
				return false;
			}
		}
		return true;
	}
}