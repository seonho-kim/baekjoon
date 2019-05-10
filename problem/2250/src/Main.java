import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static ArrayList<Integer>[] mark;
	static int level, width;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(st.nextToken());
		mark = new ArrayList[N + 1];
		for (int i = 0; i < N + 1; i++)
			mark[i] = new ArrayList<Integer>();
		
		Tree tree = new Tree();
		for (int i = 0 ; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			tree.add(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		tree.inorder(tree.root, mark);
		
		for (int i = 1; i < mark.length; i++) {		
			if (mark[i].size() == 0) continue;
			
			if (width < (mark[i].get(1) - mark[i].get(0) + 1)) {
				width = (mark[i].get(1) - mark[i].get(0) + 1);
				level = i;
			} else if (width == (mark[i].get(1) - mark[i].get(0) + 1)) {
				if (i < level) {
					width = (mark[i].get(1) - mark[i].get(0) + 1);
					level = i;
				}
			}
		}
		bw.write(level + " " + width + "\n");
		bw.flush();
	}
}
class Node {
	int data;
	int level;
	Node left, right;
	
	Node(int data) {
		this.data = data;
	}
}

class Tree {
	Node root;
	static int number = 1;
	
	public void add(int data, int leftData, int rightData) {
		if (root == null) {
			if (data != -1) { root = new Node(data); root.level = 1; }
			if (leftData != -1) { root.left = new Node(leftData); root.left.level = 2; }
			if (rightData != -1) { root.right = new Node(rightData); root.right.level = 2; }
		}
		else {
			search(root, data, leftData, rightData);
		}
	}
	private void search(Node root, int data, int leftData, int rightData) {
		if (root == null) return;
		else if (root.data == data) {
			if (leftData != -1) { root.left = new Node(leftData); root.left.level = root.level + 1; }
			if (rightData != -1) { root.right = new Node(rightData); root.right.level = root.level + 1; }
		}
		else {
			search(root.left, data, leftData, rightData);
			search(root.right, data, leftData, rightData);
		}
	}
	public void inorder(Node root, ArrayList<Integer>[] mark) {
		if (root.left != null) inorder(root.left, mark);
		
		if (mark[root.level].size() == 0) {
			mark[root.level].add(number);
			mark[root.level].add(number);
			number++;
		}
		else {
			mark[root.level].set(0, Math.min(mark[root.level].get(0), number));
			mark[root.level].set(1, Math.max(mark[root.level].get(1), number));
			number++;
		}
		if (root.right != null) inorder(root.right, mark);
	}
}