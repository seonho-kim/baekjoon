import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int N, K;
	static long[] d;
	static final int mod = 1000000007;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		d = new long[N+1];
		
		d[0] = 1;
        d[1] = 1;
        
        for (int i = 2; i <= N; i++)
        	d[i] = (d[i-1]*i) % mod;
        long denominator = (d[K]*d[N-K]) % mod;

        long index = mod-2;
        long fermatNum = 1;
        while(index > 0) {
            if(index%2==1){
                fermatNum *= denominator;
                fermatNum %= mod;
            }
            denominator = (denominator * denominator) % mod;
            index /= 2;
        }
        long result = ((d[N] % mod)*(fermatNum % mod)) % mod;
		
		bw.write(Long.valueOf(result) + "\n");
		bw.flush();
	}
}