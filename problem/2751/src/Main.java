import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[] array = new int[1000000];
	static int[] tempArray = new int[1000000];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			array[i] = Integer.parseInt(st.nextToken());
		}
		
		StringBuffer sb = new StringBuffer();
		mergeSort(0, N-1);
		for (int i = 0; i < N; i++) {
			sb.append(array[i] + "\n");
		}
		
		bw.write(sb.toString() + "\n");		
		bw.flush();
	}
	
	static void merge(int low, int mid, int high)
	{
        int i = low, j = mid + 1, k = low;

        while (i <= mid && j <= high)
        {
        	if (array[i] < array[j])
        		tempArray[k] = array[i++];
        	else
        		tempArray[k] = array[j++];
	        k++;
        }

        if (i > mid)
        	for (int idx = j; idx <= high; idx++)
        		tempArray[k++] = array[idx];
        else
             for (int idx = i; idx <= mid; idx++)
            	 tempArray[k++] = array[idx];

        for (int idx = low; idx <= high; idx++)
        	array[idx] = tempArray[idx];
	}

	static void mergeSort(int low, int high)
	{
        if (high > low)
        {
             int mid = (low + high) / 2;
             mergeSort(low, mid);
             mergeSort(mid + 1, high);
             merge(low, mid, high);
        }
	}
}