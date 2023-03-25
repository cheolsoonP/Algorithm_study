import java.util.*;
import java.io.*;

public class Main {
	static int graph[][], n, k, result;
	static boolean  visited[];
	static Deque<Integer> q;
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(in.readLine());
		k = Integer.parseInt(in.readLine());
		graph = new int[101][101];
		visited = new boolean[101];
		q = new ArrayDeque<>();
		for(int i=0;i<k;i++) {
			st = new StringTokenizer(in.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			graph[from][to] = 1;
			graph[to][from] = 1;	
		}
		q.offer(1);
		visited[1] = true;
		while(!q.isEmpty()) {
			int curr = q.pollFirst();
			result++;
			for(int i=1;i<=100;i++) {
				if(graph[curr][i] == 1 && !visited[i]) {
					q.offer(i);
					visited[i] = true;
				}
			}
		}
		
		System.out.println(result-1);
	}
}
