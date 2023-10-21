import java.util.*;
import java.io.*;

public class Main {
	static class Node {
		char value;
		Node left;
		Node right; 

		public Node(char value, Node left, Node right) {
			this.value = value;
			this.left = left;
			this.right = right;
		}
	}

	static int N;
	static Node head = new Node('A', null, null);
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(in.readLine());
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(in.readLine());
			char root = st.nextToken().charAt(0);
			char left = st.nextToken().charAt(0);
			char right = st.nextToken().charAt(0);
			insertNode(head, root, left, right);
			
		}
		
		preorder(head);
		System.out.println();
		inorder(head);
		System.out.println();
		postorder(head);
		System.out.println();
	}

	private static void insertNode(Node temp, char root, char left, char right) {

		if(temp.value == root) {
			temp.left = (left == '.' ? null : new Node(left, null, null));
			temp.right = (right == '.' ? null : new Node(right, null, null));
		} else {
			if(temp.left != null) insertNode(temp.left, root, left, right);
			if(temp.right != null) insertNode(temp.right, root, left, right);
		}
		
	}
	
	private static void preorder(Node temp) {
		// root, left, right 
		System.out.print(temp.value);
		
		//print root
		if(temp.left != null) {
			preorder(temp.left);
		}
		
		if(temp.right != null) {
			preorder(temp.right);
		}	
	}
	
	private static void inorder(Node temp) {
		// left, root, right 
		if(temp.left != null) {
			inorder(temp.left);
		}
		System.out.print(temp.value);
		if(temp.right != null) {
			inorder(temp.right);
		}
	}
	
	private static void postorder(Node temp) {
		// left, right, root
		if(temp.left != null) {
			postorder(temp.left);
		}
		if(temp.right != null) {
			postorder(temp.right);
		}
		System.out.print(temp.value);

	}

}
