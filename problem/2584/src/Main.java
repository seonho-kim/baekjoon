import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

	static int N, K;
	static final int MAX = 1001;
	static final int INF = 987654321;
	static int d[][][] = new int[MAX][MAX][2];
	static int ldp[][] = new int[MAX][2];
	static int cnt[] = new int[MAX];
	static ArrayList<Node> adj[] = new ArrayList[MAX];
	
	static ArrayList<Integer> ans = new ArrayList<Integer>();
	static int idx[][][] = new int[MAX][MAX][2];
	static int tdp[][] = new int[MAX][2];
	
	public static void main(String[] args) throws IOException  { 
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		for (int i = 0 ; i < MAX; i++) {
			for (int j = 0; j < MAX; j++) {
				Arrays.fill(d[i][j], INF);
			}
		}
		for (int i = 0; i < MAX; i++) {
			adj[i] = new ArrayList<Node>();
		}
		
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a, b, c;
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			adj[a].add(new Node(b, c));
			adj[b].add(new Node(a, c));
		}
		dfs(1, 0);
		bw.write(Math.min(d[1][K][0], d[1][K][1]) + "\n");
		
		get_ans(1, 0, d[1][K][0] > d[1][K][1] ? 1 : 0, K);
		Collections.sort(ans);
		
		for(int i=0; i < ans.size(); i++)
			bw.write(ans.get(i) + " ");
		bw.write("\n");
		bw.flush();
	}
	
	static void dfs(int current, int parent) {
		cnt[current] = 1;
		for (int i=0; i < adj[current].size(); i++) {
			int there = adj[current].get(i).from;
			if(there == parent)
				continue;
			dfs(there, current);
			cnt[current]+=cnt[there];
		}
		d[current][1][1]=0;
		d[current][0][0]=0;
		int kk=1;
		for(int q=0; q < adj[current].size(); q++) {
			int there = adj[current].get(q).from;
			
			if(there==parent)
				continue;
			
			int cost = adj[current].get(q).to;
			for (int i = 0; i < MAX; i++)
				Arrays.fill(ldp[i], INF);
			
			for(int i=0; i <= kk; i++)
				for(int j=0; j <= cnt[there]; j++) {
					ldp[i+j][1] = Math.min(ldp[i+j][1], d[current][i][1] + d[there][j][1] + cost);
					ldp[i+j][1] = Math.min(ldp[i+j][1], d[current][i][1] + d[there][j][0]);
					ldp[i+j][0] = Math.min(ldp[i+j][0], d[current][i][0] + d[there][j][1]);
					ldp[i+j][0] = Math.min(ldp[i+j][0], d[current][i][0] + d[there][j][0] + cost);
				}
			kk+=cnt[there];
			for(int i=0; i <= kk; i++) {
				d[current][i][0] = ldp[i][0];
				d[current][i][1] = ldp[i][1];
			}
		}
	}
	static void get_ans(int current, int parent, int pick, int kk) {
		if (pick != 0) {
			ans.add(current);
		}
		for (int i = 0; i < MAX; i++)
			Arrays.fill(tdp[i], INF);
		
		tdp[1][1] = 0;
		tdp[0][0] = 0;
		int pp=1;
		
		for (int i = 0; i < MAX; i++)
			for (int j = 0; j < MAX; j++)
				Arrays.fill(idx[i][j], INF);
		
		for(int q = 0; q < adj[current].size(); q++){
			int there = adj[current].get(q).from;
			
			if(there == parent)
				continue;
			
			int cost = adj[current].get(q).to;
			for (int i = 0; i < MAX; i++)
				Arrays.fill(ldp[i], INF);

			for(int i=0; i <= pp; i++) {
				for(int j=0; j <= cnt[there]; j++) {
					if(ldp[i+j][1] > tdp[i][1] + d[there][j][1] + cost) {
						ldp[i+j][1] = tdp[i][1] + d[there][j][1] + cost;
						idx[q][i+j][1] = j;
					}
					if(ldp[i+j][1] > tdp[i][1] + d[there][j][0]) {
						ldp[i+j][1] = tdp[i][1] + d[there][j][0];
						idx[q][i+j][1] = -j;
					}
					if(ldp[i+j][0] > tdp[i][0] + d[there][j][1]){
						ldp[i+j][0] = tdp[i][0] + d[there][j][1];
						idx[q][i+j][0] = j;
					}
					if(ldp[i+j][0] > tdp[i][0] + d[there][j][0] + cost){
						ldp[i+j][0] = tdp[i][0] + d[there][j][0] + cost;
						idx[q][i+j][0] = -j;
					}
				}
			}
			pp += cnt[there];
			
			for(int i = 0; i <= pp; i++) {
				tdp[i][0] = ldp[i][0];
				tdp[i][1] = ldp[i][1];
			}
		}
		int q = adj[current].size()-1;
		int w = kk;
		int e = pick;
		
		ArrayList<Node> list = new ArrayList<Node>();
		while(q >= 0) {
			int next = idx[q][w][e];
			int there = adj[current].get(q--).from;
			if(there == parent)
				continue;
			
			int npick = next > 0 ? 1 : 0;
			next = Math.abs(next);
			list.add(new Node(there, current));
			list.add(new Node(npick, next));
			w -= next;
		}
		
		for(int i = 0; i < list.size() / 2; i++) {
			int index = 2*i;
			get_ans(list.get(index).from, list.get(index).to, list.get(index+1).from, list.get(index+1).to); 
		}
	}
	
	static class Node {
		int from, to;
		public Node(int from, int to) {
			this.from = from;
			this.to = to;
		}
	}
}