import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_R {

	static int N, K;
	static final int MAX = 1001;
	static final int INF = 987654321;

	static Data pos[][][] = new Data[2][MAX][MAX];
	static ArrayList<Integer> con[] = new ArrayList[MAX];
	static ArrayList<Integer> edge[] = new ArrayList[MAX];
	static int W;
	static int dap[] = new int[MAX];
	static int visit[] = new int[MAX];
	static int d[][][] = new int[2][MAX][MAX];
	static int par[] = new int[MAX];
	static int num[] = new int[MAX];
	static int d2[][][] = new int[2][MAX][MAX];
	
	public static void main(String[] args) throws IOException  { 
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < MAX; i++) {
			con[i] = new ArrayList<Integer>();
			edge[i] = new ArrayList<Integer>();
		}
		
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a, b, c;
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			con[a].add(b); con[b].add(a);
			edge[a].add(c); edge[b].add(c);
		}
	    for (int i = 0; i <= N; i++) {
	    	for (int j = 0; j <= N; j++) {
	    		d[0][i][j] = d[1][i][j] = INF;
	    	}
	    }
	    dfs(1);
	    bw.write(Math.min(d[0][1][K], d[1][1][K]) + "\n");
	    
	    if (d[0][1][K] < d[1][1][K]) path(1, 0, K);
	    else path(1, 1, K);
	    
	    Arrays.sort(dap, 1, W+1);
	    for(int i = 1; i <= W; i++) bw.write(dap[i] + " ");
	    bw.flush();
	}
	static void dfs(int x)
	{
	    int i, j, k;
	    visit[x]=1;
	    num[x]++;
	    d[0][x][0] = d[1][x][1] = 0;
	    for(i=0; i < con[x].size(); i++)
	    {
	        if (visit[con[x].get(i)] != 0) {
	        	par[x] = con[x].get(i);
	        	continue;
        	}
	        
	        dfs(con[x].get(i));
	        for (j=0; j <= num[x] + num[con[x].get(i)]; j++) d2[0][x][j] = d2[1][x][j] = INF;
	        for (j=0; j <= num[x]; j++)
	        {
	            for (k=0; k <= num[con[x].get(i)]; k++)
	            {
	                if (d2[0][x][j+k]>d[0][x][j]+d[0][con[x].get(i)][k] + edge[x].get(i)) {
	                	d2[0][x][j+k]=d[0][x][j]+d[0][con[x].get(i)][k] + edge[x].get(i);
	                	pos[0][con[x].get(i)][j+k] = new Data(0, k);
	                }
	                if (d2[0][x][j+k]>d[0][x][j]+d[1][con[x].get(i)][k]) {
	                	d2[0][x][j+k]=d[0][x][j]+d[1][con[x].get(i)][k];
	                	pos[0][con[x].get(i)][j+k] = new Data(1, k);
	                }
	                if (d2[1][x][j+k]>d[1][x][j]+d[1][con[x].get(i)][k] + edge[x].get(i)) {
	                	d2[1][x][j+k]=d[1][x][j]+d[1][con[x].get(i)][k] + edge[x].get(i);
	                	pos[1][con[x].get(i)][j+k] = new Data(1, k);
	                }
	                if (d2[1][x][j+k]>d[1][x][j]+d[0][con[x].get(i)][k]) {
	                	d2[1][x][j+k]=d[1][x][j]+d[0][con[x].get(i)][k];
	                	pos[1][con[x].get(i)][j+k] = new Data(0, k);
	                }
	            }
	        }
	        
	        for (j=0; j <= num[x]+num[con[x].get(i)]; j++) {
	        	d[0][x][j] = d2[0][x][j];
	        	d[1][x][j] = d2[1][x][j];
	        }
	        num[x] += num[con[x].get(i)];
	    }
	}

	static void path(int x, int sw, int num)
	{
	    int i;
	    if (sw == 1) dap[++W] = x;
	    
	    for(i = con[x].size()-1; i >= 0; i--) {
	        if (con[x].get(i) == par[x]) continue;
	        path(con[x].get(i), pos[sw][con[x].get(i)][num].sw, pos[sw][con[x].get(i)][num].num);
	        num-=pos[sw][con[x].get(i)][num].num;
	    }
	}
	
	static class Data {
		int sw, num;
		public Data(int sw, int num) {
			this.sw = sw;
			this.num = num;
		}
	}
}