import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
 
public class Main {
 
    static int N, MAXN;
    static final int MAX = 501;
    static int[] a = new int[MAX];
    static int[] d = new int[MAX];
    public static void main(String[] args) throws IOException {
    	System.setIn(new FileInputStream("./src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
         
        N = Integer.parseInt(br.readLine().replace(" ", ""));
         
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
             
            a[from] = to;
            MAXN = Math.max(MAXN, Math.max(from, to));
        }
         
        int result = 0;
        for (int i = 1; i <= MAXN; i++) {
            if (a[i] == 0) continue;
            d[i] = 1;
            for (int j = 1; j < i; j++) {
                if (a[i] > a[j]) {
                    if (d[i] < d[j] + 1)
                        d[i] = d[j] + 1;
                }
            }
            result = Math.max(result,  d[i]);
        }
         
        bw.write(N - result + "");
        bw.flush();
    }
}