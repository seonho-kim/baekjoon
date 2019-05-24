import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main_pq {

	static int N;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		Heap h = new Heap();
		for (int i = 1; i <= N; i++) {
			int value = Integer.parseInt(br.readLine());
			
			if (value == 0) bw.write(h.Pop() + "\n");
			else h.Push(value);
		}
		bw.flush();
	}
	
	static class Heap {
		static final int MAX = 100000 + 10;
		int[] heap = new int[MAX];
		int heapSize = 0;
		
		Heap() {
			heapSize = 0;
		}
		
		void Push(int value) {
			if (heapSize >= MAX) return;
			heap[++heapSize] = value;
			
			int current = heapSize;
			while (current > 1 && heap[current] > heap[current >> 1]) {
				int tmp = heap[current >> 1];
				heap[current >> 1] = heap[current];
				heap[current] = tmp;
				current = current >> 1;
			}
		}
		
		int Pop() {
			if (heapSize <= 0) return 0;
			int value = heap[1];
			
			heap[1] = heap[heapSize--];
			
			int current = 1;
			while (current <= heapSize && (current << 1) <= heapSize) {
				int child;
				if ((current << 1) + 1 > heapSize) child = current << 1;
				else child = heap[current << 1] > heap[(current << 1) + 1] ? current << 1 : (current << 1) + 1;
				if (heap[current] > heap[child]) break;
				
				int tmp = heap[current];
				heap[current] = heap[child];
				heap[child] = tmp;
				
				current = child;
			}
			
			return value;
		}
	}
}