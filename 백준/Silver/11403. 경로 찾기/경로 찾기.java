
import java.util.*;
import java.io.*;

public class Main {
	/*
	 * 가중치 없는 방향 그래프 G 
	 * i j 에 대해 i -> j 로가는 길이가 양수인 
	 * 경로가 있는지 없는지 구해라. 
	 * 인접행렬 주어진다. 
	 * 
	 * */
	static int N, graph[][];
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;		
		N = Integer.parseInt(in.readLine());
		graph = new int[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=0;j<N;j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// i에서 j까지 갈 수 있는가? 
		// i에서 k로 가고, k에서 j로 갈 수 있는가?
		for(int k=0;k<N;k++) {			
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(graph[i][k] == 1 && graph[k][j] == 1) {
						graph[i][j] = 1;
					}
				}
			}			
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				sb.append(graph[i][j]+" ");
			}
			sb.append('\n');
		}
		System.out.println(sb);
		
	}

}
