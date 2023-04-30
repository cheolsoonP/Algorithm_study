import java.io.*;
import java.util.*;

// 방법 3: Trie 자료구조
public class Main {
	static class TrieNode {
		boolean isEndWord;
		TrieNode[] children = new TrieNode[10];
		
		public TrieNode() {
			isEndWord = false;
			children = new TrieNode[10];
			for(int i=0;i<=9;i++) {
				children[i] = null;
			}
		}
	}
	
	static TrieNode root;//root 노드 설정
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		for(int tc=1;tc<=T;tc++) {
			N = Integer.parseInt(in.readLine());
			root = new TrieNode();
			String num[] = new String[N];
			
			for(int i=0;i<N;i++) {
				num[i] = in.readLine();
				insert(num[i]);
			}

			for(int i=0;i<N;i++) {
				if(!available(num[i])) {
					sb.append("NO\n");
					break;
				}
				if(i==N-1) {
					sb.append("YES\n");
				}
			}
		}
		
		System.out.println(sb);
		in.close();
	}
	
	private static void insert(String str) {
		TrieNode curr = root;
		int length = str.length();
		int level;
		int index;
		
		for(level=0;level<length;level++) {
			index = str.charAt(level) - '0';
			if(curr.children[index] == null) {
				curr.children[index] = new TrieNode();				
			}
			curr = curr.children[index];
		}
		curr.isEndWord = true;
	}
	
	private static boolean available(String str) {
		TrieNode curr = root;
		int length = str.length();
		int level;
		int index;
		
		for(level=0;level<length;level++) {
			index = str.charAt(level) - '0';
			if(curr.isEndWord)
				return false;
			curr = curr.children[index];
		}
		return true;
	}
}
