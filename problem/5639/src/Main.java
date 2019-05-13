import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int root = Integer.parseInt(br.readLine());
		
		Tree tree = new Tree(root);
		
		String temp;
		while ((temp = br.readLine()) != null) {
			int value = Integer.parseInt(temp);

			tree.insert(value, tree.root);			
		}
		
		tree.postorder(tree.root);
	}
}
class Node {
	int data;
	Node left, right;
	
	Node(int value) {
		data = value;
	}
}
class Tree {
	Node root;
	
	Tree(int value) {
		if (root == null) root = new Node(value);
	}
	
	public void insert(int value, Node n) {
		if (value < n.data) {
			if (n.left == null) n.left = new Node(value);
			else insert(value, n.left);
		}
		else {
			if (n.right == null) n.right = new Node(value);
			else insert(value, n.right);
		}
	}
	
	public void postorder(Node n) {
		if (n.left != null) postorder(n.left);
		if (n.right != null) postorder(n.right);
		System.out.println(n.data);
	}
}