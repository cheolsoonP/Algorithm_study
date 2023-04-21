import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int parent[];
	static boolean visited[];
	static ArrayList<ArrayList<Integer>> tree;
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(in.readLine());
		parent = new int[N+1];
		visited = new boolean[N+1];
		tree = new ArrayList<>();
		for(int i=0;i<=N;i++) {
			tree.add(new ArrayList<>());
		}
		
		for(int i=0;i<N-1;i++) {
			st = new StringTokenizer(in.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			tree.get(A).add(B);
			tree.get(B).add(A);
		}
		
		Queue<Integer> q = new LinkedList<>();
		q.add(1);
		visited[1] = true;
		
		while(!q.isEmpty()) {
			int v = q.remove();
			for(int node : tree.get(v)) {
				if(!visited[node]) {
					visited[node] = true;
					q.add(node);
					parent[node] = v;
				}
			}
		}
		
		
		for(int i=2;i<=N;i++) {
			System.out.println(parent[i]);
		}
	}
	
	
}
